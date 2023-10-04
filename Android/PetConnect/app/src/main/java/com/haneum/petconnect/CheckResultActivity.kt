package com.haneum.petconnect

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.haneum.petconnect.contracts.DogCheckContract
import com.haneum.petconnect.data.DogInfo
import com.haneum.petconnect.fragment.DogProfileCard
import com.haneum.petconnect.models.DogCheckRepository
import com.haneum.petconnect.presenters.DogCheckPresenter
import com.haneum.petconnect.ui.theme.md_theme_dark_onPrimary
import java.text.SimpleDateFormat
import java.util.Date

class CheckResultActivity : ComponentActivity(), DogCheckContract.View {
    private lateinit var user: FirebaseUser
    private lateinit var repository: DogCheckRepository
    private lateinit var presenter: DogCheckPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        val auth = Firebase.auth
        val nose = intent.getStringExtra("fileUri")?.toUri()
        user = auth.currentUser!!
        repository = DogCheckRepository()
        presenter = DogCheckPresenter(this@CheckResultActivity, repository)
        setContent {
            PendingScreen()
        }
        Toast.makeText(this, nose.toString(), Toast.LENGTH_SHORT).show()

        imageUpload(nose!!, presenter)

        super.onCreate(savedInstanceState)
    }

    private fun imageUpload(nose: Uri, presenter: DogCheckPresenter){
        val storage = FirebaseStorage.getInstance()

        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(Date())+"_"+ user.uid

        storage.reference.child("image").child(fileName).child("check")
            .putFile(nose)
            .addOnSuccessListener{
                presenter.dogCheck(fileName)
            }

    }

    override fun makeFailureMessage(reason: String) {
        runOnUiThread { Toast.makeText(this, "실패", Toast.LENGTH_LONG).show() }
        nextStep(result = "실패", dogId = "")
    }

    override fun nextStep(result: String, dogId: String) {
        val intent = Intent(this, ShowResultActivity::class.java)
        intent.putExtra("kind", "lookup")
        intent.putExtra("result", result)
        intent.putExtra("dogId", dogId)
        startActivity(intent)
    }
}


