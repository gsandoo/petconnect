package com.haneum.petconnect.data

import com.google.firebase.Timestamp

data class Disease(
    var dog_id: String,
    var create_date: Timestamp,
    var skin_disease: String
)
