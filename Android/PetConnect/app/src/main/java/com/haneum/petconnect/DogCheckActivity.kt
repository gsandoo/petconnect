package com.haneum.petconnect

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.icu.text.IDNA.Info
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import com.example.compose.AppTheme
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.haneum.petconnect.service.NoseApi
import com.haneum.petconnect.service.NoseCheckRes
import com.haneum.petconnect.service.NoseRegisterRes
import com.haneum.petconnect.service.RetrofitSetting
import com.haneum.petconnect.ui.bottomBorder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import javax.annotation.Nullable

class DogCheckActivity : ComponentActivity() {
    private lateinit var uri: Uri
    private lateinit var intent: Intent
    private lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        val retrofit = RetrofitSetting.getInstance()
        context = this
        intent = Intent(this, TakePictureActivity::class.java)
        intent.putExtra("kind","lookup")
        intent.putExtra("dogId","")
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                DogCheck(
                    back = { finish() },
                    getImage = {
                        startActivity(intent)
                        finish() },
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogCheck(
    modifier: Modifier = Modifier,
    back: () -> Unit,
    getImage: () -> Unit
) {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { back() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )

        },
        bottomBar = {
            contentColorFor(backgroundColor = Color.White)
            BottomAppBar(
                modifier = Modifier
                    .background(Color.White),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GetImageButton(
                            getImage = { getImage() },
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                },

                )
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {
            Box() {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    Row() {
                        Text(text = "유기견 신고 및 조회",
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 32.sp,
                                fontWeight = FontWeight(900)
                            )
                        )
                        Text(text = "를 하시려면",
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 32.sp,
                                fontWeight = FontWeight(900),
                                color = Color(0xFF426CB4)
                            ))
                    }
                    Text(text = "다음과 같은 조건이 필요해요",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight(900)
                        ))
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    InfoText(
                        text = "강아지와 적정거리를 유지할 수 있어야 한다.",
                        num = "1")
                    InfoText(
                        text = "강아지의 비문을 약 1~2분 가량 촬영이 가능해야 한다.",
                        num = "2")
                    InfoText(
                        text = "펫커넥트에 유기견 비문이 등록이 되어 있어야 한다.",
                        num = "3")
                }
            }
        }
    }
}

@Composable
fun GetImageButton(
    modifier: Modifier = Modifier,
    getImage: () -> Unit,
){
    Button(
        modifier = Modifier
            .background(color = Color(0xFF426CB4), shape = RoundedCornerShape(size = 32.dp))
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.8f),
        onClick = getImage,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF426CB4),
            contentColor = Color.White
        )
    ){
        Text(
            text = "촬영하기",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun InfoText(
    modifier: Modifier = Modifier,
    text: String,
    num: String
){
    Row(
        modifier = Modifier
            .padding(start = 30.dp, bottom = 50.dp, end = 20.dp)
    ){
        Text(
            modifier = Modifier
                .padding(10.dp)
                .drawBehind {
                    drawCircle(
                        color = Color(0xFF7FB8E3),
                        radius = this.size.maxDimension / 1.5f
                    )
                }
                .align(Alignment.CenterVertically),
            text = num,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(700),
                color = Color.White,
            )
        )
        Spacer( modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .padding(
                    start = 10.dp
                ),
            text = text,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF4B4B4B),
            )
        )
    }
}