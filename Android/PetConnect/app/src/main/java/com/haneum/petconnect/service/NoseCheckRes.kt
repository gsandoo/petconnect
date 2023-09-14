package com.haneum.petconnect.service

data class NoseCheckRes(
    val message: String
){
    data class Data(
        val dogBirthYear: String,
    )
}
