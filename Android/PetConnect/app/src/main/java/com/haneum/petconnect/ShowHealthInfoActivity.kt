package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.ui.CataractCode
import com.haneum.petconnect.ui.CherryeyeCode
import com.haneum.petconnect.ui.EyeInfoScreen
import com.haneum.petconnect.ui.HairlossCode
import com.haneum.petconnect.ui.HotspotCode
import com.haneum.petconnect.ui.SavedScreen
import com.haneum.petconnect.ui.SkinInfoScreen

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
            ShowHealthInfoScreen(startDestination = kind, result = result, dogName = dogName, delete = { deleteData() }, kind = kind)
        }
    }

    private fun deleteData(){
        val db = Firebase.firestore
        db.collection(kind).document(docId).delete()
    }


}

@Composable
fun ShowHealthInfoScreen(startDestination: String, result: String, dogName: String, delete: () -> Unit, kind: String){
    ResultNavigation(startDestination = startDestination, result= result, dogName = dogName, delete = delete, kind = kind)
}

@Composable
@Preview
fun ShowHealthInfoPreview(){
    ResultNavigation(startDestination = "eye", result= "체리아이", dogName = "아토", delete = {}, kind = "eye")
}


@Composable
fun ResultNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    result: String,
    dogName: String,
    delete: () -> Unit,
    kind: String
){
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination,
    ){
        composable("eye"){
            EyeInfoScreen(
                clickPanel = { navController.navigate(result) },
                diseaseName = result,
                dogName = dogName,
                retry = { delete() },
                save = { navController.navigate("save") })
        }
        composable("cataract"){
            InfoBasic(back = {navController.navigate("eye")}) {
                CataractCode(modifier = Modifier.fillMaxSize())
            }
        }
        composable("cherry"){
            InfoBasic(back = { navController.navigate("eye")}) {
                CherryeyeCode(modifier = Modifier.fillMaxSize())
            }
        }
        composable("skin"){
            SkinInfoScreen(
                clickPanel = { navController.navigate(result)},
                diseaseName = result,
                dogName = dogName,
                retry = { delete() },
                save = { navController.navigate("save") }
            )
        }
        composable("hairloss"){
            InfoBasic(back = { navController.navigate("skin")}) {
                HairlossCode(modifier = Modifier)
            }
        }
        composable("hotspot"){
            InfoBasic(back = { navController.navigate("skin") }) {
                HotspotCode(modifier = Modifier)
            }
        }
        composable("atopy"){
            InfoBasic(back = { navController.navigate("skin") }) {
                HotspotCode(modifier = Modifier)
            }
        }
        composable("save"){
            var name = if(kind == "eye"){
                "눈"
            }else{
                "피부"
            }
            SavedScreen(
                name = name
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoBasic(
    back: () -> Unit,
    content: @Composable () -> Unit
){
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ){
        Surface(modifier = Modifier.padding(it)) {
            content()
        }
    }
}