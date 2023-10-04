package com.haneum.petconnect

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import com.google.firebase.functions.FirebaseFunctions
import com.haneum.petconnect.contracts.CheckHealthContract
import com.haneum.petconnect.models.CheckHealthRepository
import com.haneum.petconnect.presenters.CheckHealthPresenter

class CheckSkinActivity : ComponentActivity(),CheckHealthContract.View {
    lateinit var dogId: String
    private lateinit var dogName: String
    private lateinit var imageUri: Uri
    private lateinit var checkSkinPresenter: CheckHealthPresenter
    private lateinit var checkSkinRepository: CheckHealthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dogId = intent.getStringExtra("dogId").toString()
        imageUri = intent.getStringExtra("fileUri")!!.toUri()
        dogName = intent.getStringExtra("dogName").toString()
        checkSkinRepository = CheckHealthRepository()
        checkSkinPresenter = CheckHealthPresenter(this@CheckSkinActivity, checkSkinRepository)
        checkSkinPresenter.getHealthCheckData(dogId,imageUri,"","skin")
        setContent {
            PendingScreen()
        }
    }

    override fun makeFailureText(reason: String) {
        runOnUiThread { Toast.makeText(this,reason,Toast.LENGTH_SHORT).show() }
    }

    override fun refreshList() {

    }

    override fun showHealthData(docId: String, result: String, kind: String) {
        runOnUiThread { Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show() }
        val intent = Intent(this, ShowHealthInfoActivity::class.java)
        intent.putExtra("docId",docId)
        intent.putExtra("dogName",dogName)
        intent.putExtra("kind", kind)
        intent.putExtra("result", result)
        startActivity(intent)
        finish()
    }
}

