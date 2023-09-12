package com.haneum.petconnect.data

import com.google.firebase.Timestamp

data class Board(
    var board_id: Int,
    var user_id: String,
    var title: String,
    var contents: String,
    var created_date: Timestamp ,
    var category: String
)
