package com.haneum.petconnect.data

import android.net.Uri
import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//data class Board(
//    @SerializedName("user_id") var user_id: String = "",
//    @SerializedName("contents") var contents: String = "",
//    @SerializedName("created_date") var created_date: Timestamp = Timestamp.now(),
//    @SerializedName("category") var category: String = "",
//    @SerializedName("comments") var comments: Int = 0,
//    @SerializedName("like") var like: Int = 0,
//    @SerializedName("picture") var picture: Int = 0,
//    @SerializedName("pictureName") var pictureName: List<String> = listOf()
//): Serializable

@Parcelize
data class Board(
    var user_id: String = "",
    var contents: String = "",
    var created_date: Timestamp = Timestamp.now(),
    var category: String = "",
    var comments: Int = 0,
    var like: Int = 0,
    var picture: Int = 0,
    var pictureName: List<String> = listOf()
): Parcelable
