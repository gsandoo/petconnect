package com.haneum.petconnect.data

import com.google.firebase.Timestamp

data class Eye(
    var dog_id: String,
    var create_date: Timestamp,
    var eye_disease: String
)
