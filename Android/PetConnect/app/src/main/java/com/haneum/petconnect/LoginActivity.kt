package com.haneum.petconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.contracts.LoginContract
import com.haneum.petconnect.databinding.ActivityLoginBinding
import com.haneum.petconnect.models.LoginRepository
import com.haneum.petconnect.presenters.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    private lateinit var repository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = LoginRepository(this)
        presenter = LoginPresenter(this@LoginActivity,repository)

        initButtonListener()
    }
    private fun initButtonListener(){
        binding.btnLogin.setOnClickListener {
            presenter.firebaseLogin(binding.etEmail.text.toString(),binding.etPwd.text.toString())
        }
        binding.btnRegister.setOnClickListener {
            goRegi()
        }
    }

    override fun goMain(user: FirebaseUser) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goRegi() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun makeFailureText(reason: String) {
        Toast.makeText(this,reason,Toast.LENGTH_SHORT).show()
    }
}