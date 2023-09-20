package com.haneum.petconnect.data

data class UserAccount(
    val idToken: String,  // 고유정보
    val emailId: String,   // 이메일 아이디
    val passwd: String     // 비밀번호
){
}