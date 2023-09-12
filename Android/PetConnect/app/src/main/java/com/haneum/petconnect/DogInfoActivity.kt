package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        val dogInfo = mutableListOf<DogInfo>()
        var cnt = 0

        db.collection("nose")
            .whereEqualTo("user_id", user.uid)
            .get()
            .addOnSuccessListener { documents ->
                for( doc in documents){
                    dogInfo[cnt] = DogInfo(
                        dog_name = doc.data["dog_name"].toString(),
                        birth = doc.data["birth"].toString(),
                        profile_path = doc.data["profile_path"].toString(),
                        height = doc.data["height"].toString(),
                        weight = doc.data["weight"].toString(),
                        description = doc.data["description"].toString(),
                        breed = doc.data["breed"].toString(),
                        dog_sex = doc.data["dog_sex"].toString()
                    )
                    cnt++
                }
            }

        setContent {
            AppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main(dogInfo = dogInfo)
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Main(
    modifier: Modifier = Modifier,
    dogInfo: MutableList<DogInfo>
){
    GlideImage(
        model = dogInfo[0].profile_path,
        contentDescription = "",
        modifier = modifier.fillMaxSize()
    )
}