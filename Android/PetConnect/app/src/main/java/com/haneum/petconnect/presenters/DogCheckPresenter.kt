package com.haneum.petconnect.presenters

import com.haneum.petconnect.contracts.DogCheckContract
import com.haneum.petconnect.models.DogCheckDataSource
import com.haneum.petconnect.models.DogCheckRepository
import com.haneum.petconnect.models.DogRegisterDataSource

class DogCheckPresenter(
    val view: DogCheckContract.View,
    private val repository: DogCheckRepository
):DogCheckContract.Presenter {
    override fun dogCheck(fileName: String) {
        repository.dogCheck(object : DogCheckDataSource.DogCheckCallback{
            override fun checkSuccess(result: String, dogId: String) {
                view.nextStep(result, dogId)
            }

            override fun checkFailure(reason: String) {
                view.makeFailureMessage(reason)
            }

        },fileName)
    }

}