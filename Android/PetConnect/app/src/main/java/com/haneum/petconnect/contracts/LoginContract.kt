package com.haneum.petconnect.contracts

import com.google.firebase.auth.FirebaseUser

interface LoginContract {
    interface View {
        // 메인화면으로 전환
        fun goMain(user: FirebaseUser)

        fun makeFailureText(reason: String)

        //회원가입 화면으로 전환
        fun goRegi()
    }

    interface Presenter {
        fun firebaseLogin(email: String, pw: String)
    }
}