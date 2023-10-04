package com.haneum.petconnect.models

class HealthRepository: HealthDataSource {
    val healthDataSource = HealthFirestoreDataSource()

    override fun getEyeData(callback: HealthDataSource.GetEyeDataCallback, dogId: String) {
        healthDataSource.getEyeData(callback, dogId)
    }

    override fun getDiseaseData(callback: HealthDataSource.GetDiseaseDataCallback, dogId: String) {
        healthDataSource.getDiseaseData(callback, dogId)
    }

}