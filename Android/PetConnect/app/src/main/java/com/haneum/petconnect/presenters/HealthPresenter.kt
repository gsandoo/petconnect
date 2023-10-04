package com.haneum.petconnect.presenters

import com.haneum.petconnect.contracts.HealthContract
import com.haneum.petconnect.data.Disease
import com.haneum.petconnect.data.Eye
import com.haneum.petconnect.models.HealthDataSource
import com.haneum.petconnect.models.HealthRepository

class HealthPresenter(
    val view: HealthContract.View,
    private val repository: HealthRepository
): HealthContract.Presenter{
    override fun getEyeData(dogId: String){
        repository.getEyeData(object: HealthDataSource.GetEyeDataCallback{
            override fun getDataSuccess(eyeData: List<Eye>) {
                view.showEyeList(eyeData)
            }

            override fun getDataFailure(reason: String) {
                view.makeFailureText(reason = reason)
            }
        }, dogId = dogId)
    }

    override fun getDiseaseData(dogId: String){
        repository.getDiseaseData(object: HealthDataSource.GetDiseaseDataCallback{
            override fun getDataSuccess(diseaseData: List<Disease>) {
                view.showDiseaseList(diseaseData = diseaseData)
            }

            override fun getDataFailure(reason: String) {
                view.makeFailureText(reason)
            }
        }, dogId = dogId)

    }

}