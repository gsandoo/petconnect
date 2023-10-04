package com.haneum.petconnect.presenters

import android.net.Uri
import com.haneum.petconnect.contracts.CheckHealthContract
import com.haneum.petconnect.models.CheckHealthDataSource
import com.haneum.petconnect.models.CheckHealthRepository

class CheckHealthPresenter(
    val view: CheckHealthContract.View,
    private val repository: CheckHealthRepository
): CheckHealthContract.Presenter{
    override fun getHealthCheckData(dogId: String, image: Uri, side: String, kind: String) {
        repository.getHealthCheckData(object: CheckHealthDataSource.GetHealthCheckDataCallback{
            override fun getDataSuccess(docId: String, result: String) {
                view.showHealthData(docId,result, kind)
            }

            override fun getDataFailure(reason: String) {
                view.makeFailureText(reason)
            }
        },dogId, image, side, kind)
    }

    override fun saveHealthData(docId: String, kind: String) {
        repository.saveHealthData(object: CheckHealthDataSource.SaveHealthDataCallback{
            override fun getDataSuccess() {
                view.refreshList()
            }

            override fun getDataFailure(reason: String) {
                view.makeFailureText(reason)
            }

        },docId, kind)
    }
}