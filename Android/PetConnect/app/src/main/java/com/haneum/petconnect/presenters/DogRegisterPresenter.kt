package com.haneum.petconnect.presenters

import com.haneum.petconnect.contracts.DogRegisterContract
import com.haneum.petconnect.data.DogInfo
import com.haneum.petconnect.models.DogRegisterDataSource
import com.haneum.petconnect.models.DogRegisterRepository

class DogRegisterPresenter(
    val view: DogRegisterContract.View,
    private val repository: DogRegisterRepository
):DogRegisterContract.Presenter {
    override fun dogRegister(dogInfo: DogInfo, fileName: String) {
        repository.dogRegister(object: DogRegisterDataSource.DogRegisterCallback{
            override fun registerSuccess(result: String, dogId: String) {
                view.nextStep(result,dogId)
            }

            override fun registerFailure(reason: String) {
                view.makeFailureMessage(reason)
            }

        }, dogInfo,fileName)
    }
}