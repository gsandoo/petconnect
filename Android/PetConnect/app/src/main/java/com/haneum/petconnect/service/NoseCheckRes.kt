package com.haneum.petconnect.service

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class NoseCheckRes(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Data
)

data class Data(
    val dogBirthYear: String,
    val dogBreed: String,
    val dogName: String,
    val DogProfile: String,
    val dogRegistNum: String,
    val dogSex: String,
    val email: String,
    val isSuccess: String,
    val matchRate: String,
    val phoneNum: String,
    val registrant: String
)