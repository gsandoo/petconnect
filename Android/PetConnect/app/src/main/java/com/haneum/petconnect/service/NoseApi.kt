package com.haneum.petconnect.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface NoseApi {
    @Multipart
    @POST("/register")
    fun postNoseRegister(
        @PartMap data: HashMap<String, RequestBody>,
        @Part dogProfile: MultipartBody.Part,
        @Part dogNose1: MultipartBody.Part,
        @Part dogNose2: MultipartBody.Part,
        @Part dogNose3: MultipartBody.Part,
        @Part dogNose4: MultipartBody.Part,
        @Part dogNose5: MultipartBody.Part,
    ): Call<NoseRegisterRes>

    @Multipart
    @POST("/lookup")
    fun getNoseCheck(
        @Part dogNose: MultipartBody.Part
    ): Call<NoseCheckRes>
}