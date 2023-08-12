package com.haneum.petconnect

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mFirebaseAuth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mFirebaseAuth = FirebaseAuth.getInstance()
        val intent = Intent(this,LoginActivity::class.java)


        //회원가입 및 이메일 인증 처리
        binding.btnRegister.setOnClickListener {
            var strEmail: String = binding.etEmail.text.toString()
            var strPwd: String = binding.etPwd.text.toString()
            mFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener {
                if (it.isSuccessful){
                    val firebaseuser: FirebaseUser? = mFirebaseAuth.currentUser
                    firebaseuser?.let{ fb ->
                        fb.sendEmailVerification()
                            .addOnSuccessListener { verify ->
                                startActivity(intent)
                            }

                        val account = UserAccount(fb.uid, strEmail, strPwd)
                        // Firestore DB 유저 정보 저장
                        db.collection("users")
                            .add(account)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                        Toast.makeText(this,"회원가입?", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
