package com.haneum.petconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val db = Firebase.firestore
    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        mFirebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val strEmail = binding.etEmail.text.toString()
            val strPwd = binding.etPwd.text.toString()

            mFirebaseAuth.signInWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener{
                if(it.isSuccessful){
                    if(mFirebaseAuth.currentUser?.isEmailVerified!! ) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "이메일 인증 미완료", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}