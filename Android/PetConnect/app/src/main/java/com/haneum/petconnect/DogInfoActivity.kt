package com.haneum.petconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.data.DogInfo

class DogInfoActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val db = Firebase.firestore
        var dogInfo: DogInfo = DogInfo()
        val dogNum = intent.getStringExtra("dog_id")

        db.collection("nose")
            .whereEqualTo("dog_id", dogNum)
            .get()
            .addOnSuccessListener { documents ->
                for( doc in documents){
                    dogInfo = DogInfo(
                        dog_name = doc.data["dog_name"].toString(),
                        birth = doc.data["birth"].toString(),
                        profile_path = doc.data["profile_path"].toString(),
                        height = doc.data["height"].toString(),
                        weight = doc.data["weight"].toString(),
                        description = doc.data["description"].toString(),
                        breed = doc.data["breed"].toString(),
                        dog_sex = doc.data["dog_sex"].toString()
                    )
                }
                setContent {
                    AppTheme() {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Main(
                                dogInfo = dogInfo,
                                back = {finish()}
                            )
                        }
                    }
                }
            }
    }
}


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Main(
    modifier: Modifier = Modifier,
    dogInfo: DogInfo,
    back: () -> Unit
){
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
            ) {
                GlideImage(
                    model = dogInfo.profile_path,
                    contentDescription = "",
                    modifier = modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(text = "${dogInfo.dog_name}")
                Text(text = "${dogInfo.breed} - ${dogInfo.dog_sex}")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.33f)
                    ) {
                        Text(text = "나이")
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = "키")
                    }
                    Column(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(text = "몸무게")
                    }
                }
                Text(
                    text = "우리 ${dogInfo.dog_name}는?",
                )
            }
        }
    }
}