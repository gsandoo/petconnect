package com.haneum.petconnect.models

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.haneum.petconnect.data.UserAccount

class LoginFirebaseDataSource(context: Context): LoginDataSource {

    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun loginUser(callback: LoginDataSource.LoginUserCallback, email:String, pw:String) {
        mFirebaseAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener{
            if(it.isSuccessful){
                if(mFirebaseAuth.currentUser?.isEmailVerified!! ) {
                    callback.loginSuccess(mFirebaseAuth.currentUser!!)
                }else{
                    callback.loginFailure("cert")
                }
            }else{
                callback.loginFailure("fail")
            }
        }
    }

}