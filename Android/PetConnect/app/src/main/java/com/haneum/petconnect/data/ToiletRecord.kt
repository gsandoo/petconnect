package com.haneum.petconnect.data

data class ToiletRecord(
    var record_id: Int,
    var user_id: String,
    val type: String,
    val timestamp: Long,
    val color:String,
    val recode_memo:String
)
