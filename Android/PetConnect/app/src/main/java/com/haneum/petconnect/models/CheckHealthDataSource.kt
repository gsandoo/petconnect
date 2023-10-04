package com.haneum.petconnect.models

import android.net.Uri

interface CheckHealthDataSource {
    interface GetHealthCheckDataCallback{
        fun getDataSuccess(docId: String, result: String)
        fun getDataFailure(reason: String)
    }
    interface SaveHealthDataCallback{
        fun getDataSuccess()
        fun getDataFailure(reason: String)
    }

    fun getHealthCheckData(callback: GetHealthCheckDataCallback, dogId: String, image: Uri, side: String, kind: String)
    fun saveHealthData(callback: SaveHealthDataCallback, docId: String, kind: String)
}