package com.haneum.petconnect.models

import com.haneum.petconnect.data.DogInfo

class DogCheckRepository: DogCheckDataSource {
    private val dogCheckDataSource = DogCheckFirestoreDataSource()

    override fun dogCheck(callback: DogCheckDataSource.DogCheckCallback, fileName: String) {
        dogCheckDataSource.dogCheck(callback, fileName)
    }
}