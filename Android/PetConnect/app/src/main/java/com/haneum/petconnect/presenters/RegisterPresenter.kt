package com.haneum.petconnect.presenters

import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.contracts.RegisterContract
import com.haneum.petconnect.data.UserAccount
import com.haneum.petconnect.models.RegisterDataSource
import com.haneum.petconnect.models.RegisterRepository

class RegisterPresenter(
    val view: RegisterContract.View,
    private val repository: RegisterRepository
    ):RegisterContract.Presenter{
    override fun register(email: String, pw: String) {
        repository.registerUser(object : RegisterDataSource.RegisterCallback{
            override fun registerSuccess(user: FirebaseUser) {
                updateData(UserAccount(user.uid,email,pw))
            }
            override fun registerFailure() {
                view.makeFailureText("")
            }

        },email,pw)
    }

    override fun updateData(user: UserAccount){
        repository.updateData(object : RegisterDataSource.UpdateDataCallback{
            override fun updateSuccess() {
                view.goLogin()
            }

            override fun updateFailure() {
                view.makeFailureText("")
            }

        }, user)
    }
}