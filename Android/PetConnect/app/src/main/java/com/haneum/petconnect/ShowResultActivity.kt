package com.haneum.petconnect

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.data.DogInfo
import com.haneum.petconnect.fragment.DogProfileCard
import com.haneum.petconnect.ui.theme.md_theme_dark_onPrimary

class ShowResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dogId = intent.getStringExtra("dogId")
        val kind = intent.getStringExtra("kind")
        val result = intent.getStringExtra("result")
        var dogInfo: DogInfo = DogInfo()
        val db = Firebase.firestore
        val intent = Intent(this, MainActivity::class.java)

        if (result == "성공"){
            db.collection("nose")
                .whereEqualTo("dog_id", dogId)
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
                        AppTheme {
                            ShowResultScreen(dogInfo = dogInfo, result = result!!, home = {startActivity(intent)}, kind = kind!!)
                        }
                    }
                }
        }else if(result == "실패"){
            setContent {
                AppTheme {
                    ShowResultScreen(dogInfo = dogInfo, result = result!!, home = {startActivity(intent)}, kind = kind!!)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowResultScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    dogInfo: DogInfo,
    home: () -> Unit,
    result: String,
    kind: String
){
    var text1 = ""
    var text2 = ""
    if(kind == "register"){
        if(result == "성공"){
            text1 = "축하드려요"
            text2 = "등록이 완료되었어요"
        }else if(result == "중복"){
            text1 = "중복된 비문 입니다."
            text2 = "다른 비문으로 등록해주세요"
        }else if (result == "실패"){
            text1 = "등록에 실패했습니다."
            text2 = "다시 등록해주세요"
        }
    }else if  (kind == "lookup"){
        if(result == "성공"){
            text1 = "조회 성공"
            text2 = "조회된 반려동물입니다."
        }else if (result == "실패"){
            text1 = "조회 실패"
            text2 = "다시 촬영해주세요"
        }
    }
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {Text("")}
            )
        }, bottomBar = {
            BottomAppBar(
                content = {
                    Button(
                        shape = RectangleShape,
                        onClick = home,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "홈으로 돌아가기")
                    }
                }
            )
        }, modifier = modifier
        )
    {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text1,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = md_theme_dark_onPrimary,
                        fontWeight = FontWeight(800)
                    )
                )
                Text(
                    text = text2,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(800)
                    )
                )
                Spacer(modifier = Modifier.size(30.dp))
                if(result == "성공"){
                    DogProfileCard(
                        modifier = Modifier
                            .fillMaxHeight(0.4f)
                            .fillMaxWidth(0.6f),
                        listClick = {},
                        dogInfo = dogInfo,
                        imageSize = 150.dp)
                }
            }
        }
    }
}