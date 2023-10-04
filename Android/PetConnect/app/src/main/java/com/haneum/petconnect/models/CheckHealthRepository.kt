package com.haneum.petconnect.models

import android.net.Uri

class CheckHealthRepository: CheckHealthDataSource {
    private val checkEyeDataSource = CheckHealthFirestoreDataSource()

    override fun getHealthCheckData(
        callback: CheckHealthDataSource.GetHealthCheckDataCallback,
        dogId: String,
        image: Uri,
        side: String,
        kind: String
    ) {
        checkEyeDataSource.getHealthCheckData(callback, dogId, image, side, kind)
    }

    override fun saveHealthData(
        callback: CheckHealthDataSource.SaveHealthDataCallback,
        docId: String,
        kind: String
    ) {
        checkEyeDataSource.saveHealthData(callback, docId, kind)
    }
}