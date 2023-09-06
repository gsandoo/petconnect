package com.haneum.petconnect.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import retrofit2.Retrofit

class NoseRegisterService: Service(){

    class myBinder: Binder(){

    }
    val binder = myBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
}