package com.haneum.petconnect.service

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class NoseCheckRes(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: CheckData
)

data class CheckData(
    val dogBirthYear: String,
    val dogBreed: String,
    val dogName: String,
    val dogProfile: String,
    val dogRegistNum: String,
    val dogSex: String,
    val email: String,
    val isSuccess: Boolean,
    val matchRate: String,
    val phoneNum: String,
    val registrant: String
)