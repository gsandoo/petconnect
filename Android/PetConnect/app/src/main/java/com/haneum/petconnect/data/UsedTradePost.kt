package com.haneum.petconnect.data

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsedTradePost(
    var user_id: String = "",
    var contents: String = "",
    var title: String = "",
    var created_date: Timestamp = Timestamp.now(),
    var category: String = "",
    var chat: Int = 0,
    var location: String = "",
    var price: String = "",
    var like: Int = 0,
    var picture: Int = 0,
    var pictureName: List<String> = listOf()
): Parcelable
