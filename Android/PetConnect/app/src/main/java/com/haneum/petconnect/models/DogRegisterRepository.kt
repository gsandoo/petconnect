package com.haneum.petconnect.models

import com.haneum.petconnect.data.DogInfo

class DogRegisterRepository: DogRegisterDataSource {
    private val dogRegisterDataSource = DogRegisterFirestoreDataSource()

    override fun dogRegister(
        callback: DogRegisterDataSource.DogRegisterCallback,
        dogInfo: DogInfo,
        fileName: String
    ) {
        dogRegisterDataSource.dogRegister(callback,dogInfo, fileName)
    }
}