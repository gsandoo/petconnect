package com.haneum.petconnect.models

import com.haneum.petconnect.data.DogInfo


interface DogRegisterDataSource {
    interface DogRegisterCallback{
        fun registerSuccess(result: String, dogId: String)
        fun registerFailure(reason: String)
    }

    fun dogRegister(callback: DogRegisterCallback, dogInfo: DogInfo, fileName: String)
}