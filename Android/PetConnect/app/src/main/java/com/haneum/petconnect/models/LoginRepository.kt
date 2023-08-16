package com.haneum.petconnect.models

import android.content.Context

class LoginRepository(context: Context): LoginDataSource {

    private val userDataSource = LoginFirebaseDataSource(context)

    override fun loginUser(callback: LoginDataSource.LoginUserCallback, email: String, pw: String) {
        userDataSource.loginUser(callback, email, pw)
    }

}