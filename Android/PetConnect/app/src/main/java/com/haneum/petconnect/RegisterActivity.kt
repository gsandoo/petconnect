package com.haneum.petconnect

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.contracts.RegisterContract
import com.haneum.petconnect.data.UserAccount
import com.haneum.petconnect.databinding.ActivityRegisterBinding
import com.haneum.petconnect.models.RegisterRepository
import com.haneum.petconnect.presenters.RegisterPresenter

class RegisterActivity : AppCompatActivity(), RegisterContract.View{

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter:RegisterPresenter
    private lateinit var repository: RegisterRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = Intent(this,LoginActivity::class.java)

        repository = RegisterRepository(this)
        presenter = RegisterPresenter(this@RegisterActivity, repository)

        initButtonListener()
    }

    private fun initButtonListener(){
        binding.btnRegister.setOnClickListener {
            presenter.register(binding.etEmail.text.toString(),binding.etPwd.text.toString())
        }
    }

    override fun goLogin() {
        Toast.makeText(this,"이메일 인증을 확인하세요", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    override fun makeFailureText(reason: String) {
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }

}
