package com.haneum.petconnect.models

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.data.Disease
import com.haneum.petconnect.data.Eye

class HealthFirestoreDataSource: HealthDataSource{
    private var auth = FirebaseAuth.getInstance()
    private var db = Firebase.firestore
    override fun getEyeData(callback: HealthDataSource.GetEyeDataCallback, dogId: String) {
        var eyeData = mutableListOf<Eye>()
        db.collection("eye")
            .whereEqualTo("dog_id", dogId)
            .get()
            .addOnSuccessListener {documents ->
                for((index, doc) in documents.withIndex()){
                    eyeData[index] = Eye(
                        dog_id = doc.data["dog_id"].toString(),
                        eye_disease = doc.data["eye_disease"].toString(),
                        create_date = doc.data["create_date"] as Timestamp
                    )
                }
                callback.getDataSuccess(eyeData = eyeData)
            }
            .addOnFailureListener {
                callback.getDataFailure(it.toString())
            }
    }

    override fun getDiseaseData(callback: HealthDataSource.GetDiseaseDataCallback, dogId: String) {
        var diseaseData = mutableListOf<Disease>()
        db.collection("disease")
            .whereEqualTo("dog_id", dogId)
            .get()
            .addOnSuccessListener {documents ->
                for((index, doc) in documents.withIndex()){
                    diseaseData[index] = Disease(
                        dog_id = doc.data["dog_id"].toString(),
                        skin_disease = doc.data["skin_disease"].toString(),
                        create_date = doc.data["create_date"] as Timestamp
                    )
                }
                callback.getDataSuccess(diseaseData = diseaseData)
            }
            .addOnFailureListener {
                callback.getDataFailure(it.toString())
            }
    }


}