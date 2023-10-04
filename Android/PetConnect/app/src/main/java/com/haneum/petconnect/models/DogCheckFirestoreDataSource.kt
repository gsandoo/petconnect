package com.haneum.petconnect.models

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DogCheckFirestoreDataSource:DogCheckDataSource {
    override fun dogCheck(callback: DogCheckDataSource.DogCheckCallback, fileName: String) {
        val functions = Firebase.functions
        //test
        functions.useEmulator("10.0.2.2", 5001)

        val callData = hashMapOf<String, String>(
            "fileName" to fileName,
        )
        var responseData: HashMap<String, String>

        GlobalScope.launch {
            responseData = callFunctions("noseCheck",callData, functions).await()
            if (responseData["message"] == "조회된 강아지가 없습니다"){
                callback.checkFailure("실패")
            }else{
                callback.checkSuccess("성공", responseData["foundDog"]!!)
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