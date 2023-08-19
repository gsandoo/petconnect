package com.haneum.petconnect.models

import android.content.Context
import com.haneum.petconnect.data.UserAccount

class RegisterRepository(context: Context): RegisterDataSource {

    private val registerDataSource = RegisterFirebaseDataSource(context)

    override fun registerUser(
        callback: RegisterDataSource.RegisterCallback,
        email: String,
        pw: String
    ) {
        registerDataSource.registerUser(callback,email,pw)
    }

    override fun updateData(
        callback: RegisterDataSource.UpdateDataCallback,
        user: UserAccount
    ) {
        registerDataSource.updateData(callback,user)
    }
}