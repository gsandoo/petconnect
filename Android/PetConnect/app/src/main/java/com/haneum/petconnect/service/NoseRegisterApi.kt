package com.haneum.petconnect.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

interface NoseRegisterApi {
    @Multipart
    @POST("/register")
    fun postNoseRegister(
        @PartMap data: HashMap<String, RequestBody>,
        @PartMap images: HashMap<String, MultipartBody.Part>,
    ): Call<NoseRegisterRes>
}