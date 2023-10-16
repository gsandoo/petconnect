package com.haneum.petconnect.data

import com.google.firebase.Timestamp

data class Comments(
    var board_id: String = "",
    var user_id: String = "",
    var comment_contents: String = "",
    var user_nickname: String = "",
    var profile_path: String = "",
    var comment_create: Timestamp = Timestamp.now(),
    var comment_id: String = ""
)
