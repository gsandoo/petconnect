package com.haneum.petconnect.models

import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.data.UserAccount

interface LoginDataSource {

    interface LoginUserCallback{
        fun loginSuccess(user: FirebaseUser)
        fun loginFailure(reason: String)
    }

    fun loginUser(callback: LoginUserCallback, email: String, pw: String)
}