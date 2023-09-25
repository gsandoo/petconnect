package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.google.android.play.integrity.internal.x

class feedMeasurement2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Frame260800408Preview()
                }
            }
        }
    }
}
@Composable
fun measurementTitle(modifier: Modifier = Modifier) {
    Text(
        lineHeight = 1.sp,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color(0xff2b2b2b),
                fontSize = 24.sp)
            ) {append("측정할 ")}
            withStyle(style = SpanStyle(
                color = Color(0xff426cb4),
                fontSize = 24.sp)) {append("사료를 입력")}
            withStyle(style = SpanStyle(
                color = Color(0xff2b2b2b),
                fontSize = 24.sp)) {append("해주세요")}},
        modifier = modifier)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun set_layout2(){
    var feedName by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()){
        feed_TopAppBar()
        measurementTitle(modifier = Modifier.padding(50.dp))
        Box(modifier = Modifier
            .padding(10.dp))
        TextField(
            value = feedName,//사료이름
            onValueChange = { textValue -> feedName = textValue },
            enabled = true,
            placeholder = { Text("사료의 이름을 입력하세요") },
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xffb3b3b3)),
            modifier = Modifier

                .offset(
                    x = 30.dp,
                    y = 70.dp
                )
                .requiredWidth(width = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true
        )
        RoundButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // 가운데 정렬
                .padding(30.dp)
                .offset(y=500.dp),
            onClick = { /* 버튼 클릭 시 실행할 작업 */ }
        )
        }


    }
@Preview
@Composable
private fun Frame260800408Preview() {
    set_layout2()
}