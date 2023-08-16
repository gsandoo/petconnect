package com.haneum.petconnect.models

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.data.UserAccount

class RegisterFirebaseDataSource(context: Context): RegisterDataSource {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    override fun registerUser(
        callback: RegisterDataSource.RegisterCallback,
        email: String,
        pw: String
    ) {
        auth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener { task ->
            if(task.isSuccessful){
                auth.currentUser!!.sendEmailVerification().addOnCompleteListener { task2 ->
                    if(task2.isSuccessful){
                        callback.registerSuccess(auth.currentUser!!)
                    }else{
                        callback.registerFailure()
                    }
                }
            }else{
                callback.registerFailure()
            }
        }
    }

    override fun updateData(
        callback: RegisterDataSource.UpdateDataCallback,
        user: UserAccount
    ) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                callback.updateSuccess()
            }
            .addOnFailureListener {
                callback.updateFailure()
            }
    }

}