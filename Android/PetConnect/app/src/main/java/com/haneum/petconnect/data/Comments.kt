package com.haneum.petconnect.data

import com.google.firebase.Timestamp

data class Comments(
    var board_id: Int,
    var user_id: String,
    var comment_contents: String,
    var comment_create: Timestamp
)
