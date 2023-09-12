package com.haneum.petconnect.service

import okhttp3.MultipartBody

data class RegisterDto(
    val registrant: String,
    val phoneNum: String,
    val email: String,
    val dogName: String,
    val dogBreed: String,
    val dogBirthYear: String,
    val dogSex: String,
    val dogProfile: MultipartBody.Part,
    val dogNose1: MultipartBody.Part,
    val dogNose2: MultipartBody.Part,
    val dogNose3: MultipartBody.Part,
    val dogNose4: MultipartBody.Part,
    val dogNose5: MultipartBody.Part
    )