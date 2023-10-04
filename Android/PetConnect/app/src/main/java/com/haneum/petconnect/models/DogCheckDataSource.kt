package com.haneum.petconnect.models

interface DogCheckDataSource {
    interface DogCheckCallback{
        fun checkSuccess(result: String, dogId: String)
        fun checkFailure(reason: String)
    }

    fun dogCheck(callback: DogCheckCallback, fileName: String)
}