package com.haneum.petconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.haneum.petconnect.contracts.RegisterContract
import com.haneum.petconnect.databinding.ActivityRegisterBinding
import com.haneum.petconnect.models.RegisterRepository
import com.haneum.petconnect.presenters.RegisterPresenter
import kotlin.math.log

class RegisterActivity : AppCompatActivity(), RegisterContract.View{

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter:RegisterPresenter
    private lateinit var repository: RegisterRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this,LoginActivity::class.java)

        repository = RegisterRepository(this)
        presenter = RegisterPresenter(this@RegisterActivity, repository)

        initButtonListener()
    }

    private fun initButtonListener(){
        var strEmail: String?
        var strPw: String?
        binding.btnRegister.setOnClickListener {
            strEmail = binding.etEmail.text?.toString()
            strPw = binding.etPwd.text?.toString()
            if (strEmail == "" || strPw == ""){
                Toast.makeText(this,"실패~",Toast.LENGTH_SHORT).show()
            }else{
                presenter.register(strEmail!!,strPw!!)
            }
        }
    }

    override fun goLogin() {
        Toast.makeText(this,"이메일 인증을 확인하세요", Toast.LENGTH_SHORT).show()
        //startActivity(intent)
        finish()
    }

    override fun makeFailureText(reason: String) {
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }

}
