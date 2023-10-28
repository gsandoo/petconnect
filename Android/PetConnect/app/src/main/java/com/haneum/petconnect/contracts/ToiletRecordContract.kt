package com.haneum.petconnect.contracts

import com.haneum.petconnect.data.ToiletRecord

interface ToiletRecordContract {
    interface View {
        fun showToiletRecords(records: List<ToiletRecord>)
    }

    interface Presenter {
        fun saveToiletRecord(type: String)
        fun loadToiletRecords()
    }
}
