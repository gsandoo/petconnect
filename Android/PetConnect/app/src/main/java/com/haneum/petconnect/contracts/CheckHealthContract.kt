package com.haneum.petconnect.contracts

import android.net.Uri

interface CheckHealthContract {
    interface View {
        fun makeFailureText(reason: String)

        // 건강기록 리스트 출력
        fun refreshList()
        fun showHealthData(docId: String, result: String, kind: String)
    }

    interface Presenter {
        fun getHealthCheckData(dogId: String, image: Uri, side: String, kind: String)
        fun saveHealthData(docId: String, kind: String)
    }
}