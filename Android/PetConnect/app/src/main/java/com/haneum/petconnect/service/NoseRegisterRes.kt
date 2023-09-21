package com.haneum.petconnect.service

import com.google.gson.annotations.SerializedName

data class NoseRegisterRes(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: RegisterData
)
data class RegisterData(
    val dogBirthYear: String,
    val dogBreed: String,
    val dogName: String,
    val dogProfile: String,
    val dogRegistNum: String,
    val dogSex: String,
    val isSuccess: Boolean
)