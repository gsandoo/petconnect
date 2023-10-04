package com.haneum.petconnect.contracts

import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.data.Disease
import com.haneum.petconnect.data.Eye

interface HealthContract {
    interface View {
        fun makeFailureText(reason: String)

        // 건강기록 리스트 출력
        fun showEyeList(eyeData: List<Eye>)
        fun showDiseaseList(diseaseData: List<Disease>)
    }

    interface Presenter {
        fun getEyeData(dogId: String)
        fun getDiseaseData(dogId: String)
    }
}