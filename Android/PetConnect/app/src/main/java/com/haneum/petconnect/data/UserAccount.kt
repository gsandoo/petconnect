package com.haneum.petconnect.data

import com.google.firebase.Timestamp

data class UserAccount(
    val user_id: String = "",  // 고유정보
    var nickname: String = "",
    var profile_path: String = "",
    var create_date: Timestamp = Timestamp.now(),
    var name: String = "",
    var phone: String = "",
){
}