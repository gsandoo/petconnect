package com.haneum.petconnect.models

import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.data.Disease
import com.haneum.petconnect.data.Eye

interface HealthDataSource {
    interface GetEyeDataCallback{
        fun getDataSuccess(eyeData: List<Eye>)
        fun getDataFailure(reason: String)
    }
    interface GetDiseaseDataCallback{
        fun getDataSuccess(diseaseData: List<Disease>)
        fun getDataFailure(reason: String)
    }

    fun getEyeData(callback: GetEyeDataCallback, dogId: String)
    fun getDiseaseData(callback: GetDiseaseDataCallback, dogId: String)
}