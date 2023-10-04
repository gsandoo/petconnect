package com.haneum.petconnect.contracts

import com.haneum.petconnect.data.DogInfo

interface DogCheckContract {
    interface View {
        fun makeFailureMessage(reason: String)
        fun nextStep(result: String, dogId: String)
    }
    interface Presenter {
        fun dogCheck(fileName: String)
    }
}