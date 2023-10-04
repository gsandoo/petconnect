package com.haneum.petconnect.models

import android.util.Log
import androidx.core.net.toFile
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.haneum.petconnect.data.DogInfo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date

class DogRegisterFirestoreDataSource: DogRegisterDataSource {
    @OptIn(DelicateCoroutinesApi::class)
    override fun dogRegister(
        callback: DogRegisterDataSource.DogRegisterCallback,
        dogInfo: DogInfo,
        fileName: String
    ) {
        val functions = Firebase.functions
        val db = Firebase.firestore
        //test
        functions.useEmulator("10.0.2.2", 5001)

        val callData = hashMapOf<String, String>(
            "fileName" to fileName,
        )
        val data: HashMap<String, String>

        GlobalScope.launch {
            if (callFunctions("noseRegister",callData, functions).await()["message"]!! == "fail"){
                callback.registerSuccess("실패", fileName)
            }else{
                callback.registerSuccess("성공", fileName)
            }
        }

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