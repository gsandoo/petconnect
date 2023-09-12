package com.haneum.petconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.fragment.CommunityFragment
import com.haneum.petconnect.fragment.HealthFragment
import com.haneum.petconnect.fragment.HomeFragment
import com.haneum.petconnect.fragment.HospitalFragment
import com.haneum.petconnect.fragment.ProfileFragment
import com.haneum.petconnect.fragment.WritePostFragment


class MainActivity : AppCompatActivity() {
    lateinit var homeFragment: HomeFragment
    lateinit var hospitalFragment: HospitalFragment
    lateinit var communityFragment: CommunityFragment
    lateinit var healthFragment: HealthFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var auth:FirebaseAuth
    lateinit var myIntent:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment = HomeFragment()
        hospitalFragment = HospitalFragment()
        communityFragment = CommunityFragment()
        healthFragment = HealthFragment()
        profileFragment = ProfileFragment()
        myIntent = intent


//        var userInfo = myIntent.getStringArrayExtra("userInfo")
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(userInfo?.get(0)!!, userInfo[1]!!).addOnCompleteListener{
//            if(it.isSuccessful){
//
//            }else{
//
//            }
//        }

        //기본 프라그먼트를 홈으로 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.containers, homeFragment)
            .commit()

        val navigationBarView: NavigationBarView = findViewById(R.id.bottom_navigationview)
        //네비게이션 변경
        navigationBarView.setOnItemSelectedListener {item: MenuItem ->
            when(item.itemId){
                R.id.home -> changeFragment(homeFragment)
                R.id.hospital -> changeFragment(hospitalFragment)
                R.id.community -> changeFragment(communityFragment)
                R.id.health -> changeFragment(healthFragment)
                R.id.profile -> changeFragment(profileFragment)
                else -> false
            }
        }
        //네비게이션 바 숨기기

    }

    fun setVisibility(type: Int){
        findViewById<NavigationBarView>(R.id.bottom_navigationview).visibility = type
    }
    fun changeFragment(frag: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.containers,frag).commit()
        return true
    }
}

