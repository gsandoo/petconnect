package com.haneum.petconnect.models

import android.net.Uri
import androidx.core.net.toFile
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date

class CheckHealthFirestoreDataSource: CheckHealthDataSource {
    override fun getHealthCheckData(
        callback: CheckHealthDataSource.GetHealthCheckDataCallback,
        dogId: String,
        image: Uri,
        side: String,
        kind: String
    ) {
        val storage = FirebaseStorage.getInstance()
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val functions = Firebase.functions
        functions.useEmulator("10.0.2.2", 5001)
        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(Date())+"_"+user!!.uid.toString()
        val callData = hashMapOf<String, String>(
            "dogId" to dogId,
            "fileName" to fileName,
            "kind" to kind,
            "side" to side
        )
        var responseData: HashMap<String, String>

        storage.reference.child("image").child(kind).child(fileName)
            .putFile(image)//어디에 업로드할지 지정
            .addOnSuccessListener {
                image.toFile().delete()
                GlobalScope.launch {
                    responseData = callFunctions("health",callData, functions).await()
                    if (responseData["message"] == "fail"){
                        callback.getDataFailure(responseData["error"]!!)
                    }else{
                        callback.getDataSuccess(responseData["docId"]!!, responseData["result"]!!)
                    }
                }
            }
    }

    override fun saveHealthData(
        callback: CheckHealthDataSource.SaveHealthDataCallback,
        docId: String,
        kind: String
    ) {
        val db = Firebase.firestore
        db.collection(kind).document(docId).delete()
    }

    private fun callFunctions(name: String, data: HashMap<String, String>, functions: FirebaseFunctions): Task<HashMap<String, String>> {
        return functions.getHttpsCallable(name)
            .call(data)
            .continueWith{ task ->
                val result = task.result?.data as HashMap<String, String>
                result
            }
    }
}