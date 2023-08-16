package com.haneum.petconnect.models

import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.data.UserAccount

interface RegisterDataSource {

    interface RegisterCallback{
        fun registerSuccess(user: FirebaseUser)
        fun registerFailure()
    }

    interface UpdateDataCallback{
        fun updateSuccess()
        fun updateFailure()
    }

    fun registerUser(callback: RegisterCallback, email:String, pw: String)
    fun updateData(callback: UpdateDataCallback, user: UserAccount)
}