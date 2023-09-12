package com.haneum.petconnect.service

data class NoseRegisterRes(
    val message: String,
){
    data class Data(
        val dogName: String
    )
}
