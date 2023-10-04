package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.contracts.CheckHealthContract
import com.haneum.petconnect.models.CheckHealthRepository
import com.haneum.petconnect.presenters.CheckHealthPresenter

class ShowHealthInfoActivity : ComponentActivity(){
    lateinit var docId: String
    lateinit var dogName: String
    lateinit var kind: String
    lateinit var result: String

    override fun onCreate(savedInstanceState: Bundle?) {
        docId = intent.getStringExtra("docId").toString()
        dogName = intent.getStringExtra("dogName").toString()
        kind = intent.getStringExtra("kind").toString()
        result = intent.getStringExtra("result").toString()

        super.onCreate(savedInstanceState)
        setContent {
            ShowHealthInfoScreen(startDestination = kind, result = result)
        }
    }

    fun deleteData(){
        val db = Firebase.firestore
        db.collection(kind).document(docId).delete()
    }


}

@Composable
fun ShowHealthInfoScreen(startDestination: String, result: String){
    ResultNavigation(startDestination = startDestination, result= result)
}

@Composable
fun ResultNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    result: String
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ){
        composable("eye"){

        }
        composable("showCataract"){

        }
        composable("showCherry"){

        }
        composable("skin"){

        }
        composable("hairloss"){

        }
        composable("hotspot"){

        }
        composable("atopy"){

        }
    }
}