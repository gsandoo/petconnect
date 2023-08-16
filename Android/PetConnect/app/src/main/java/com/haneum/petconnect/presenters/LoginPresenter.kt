package com.haneum.petconnect.presenters

import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.contracts.LoginContract
import com.haneum.petconnect.models.LoginDataSource
import com.haneum.petconnect.models.LoginRepository


class LoginPresenter(
    val view: LoginContract.View,
    private val repository: LoginRepository
    ): LoginContract.Presenter {

    override fun firebaseLogin(email: String, pw: String) {
        repository.loginUser(object: LoginDataSource.LoginUserCallback {
            override fun loginSuccess(user: FirebaseUser) {
                view.goMain(user)
            }

            override fun loginFailure(reason: String) {
                view.makeFailureText(reason)
            }
        },email,pw)
    }
    }