package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme

class EyeSaveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Greeting2( modifier: Modifier = Modifier, name:String) {
    Column(modifier = Modifier
        .padding(20.dp)){
        TopAppBarDisease3()
        Text(
            lineHeight = 1.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.DarkGray,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold)) {append("$name 의 안구측정")}
                withStyle(style = SpanStyle(
                    color = Color.DarkGray,
                    fontSize = 17.sp)) {append("을 정확하게 기록하기 위해")}},
            modifier = modifier.padding(top=50.dp, bottom = 10.dp))
        Text(
            lineHeight = 1.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xff2b2b2b),
                    fontSize = 24.sp)) {append("촬영할 ")}
                withStyle(style = SpanStyle(
                    color = Color(0xff426cb4),
                    fontSize = 24.sp)) {append("눈을 선택")}
                withStyle(style = SpanStyle(
                    color = Color(0xff2b2b2b),
                    fontSize = 24.sp)) {append("해주세요")}},
            modifier = modifier.padding(bottom = 30.dp))

        Row(modifier = Modifier .padding(20.dp)){
            ButtonCode(direction = "왼쪽", modifier = Modifier .padding(end=10.dp))
            ButtonCode(direction = "오른쪽")


        }

        Box(modifier = modifier){
            Image(
                painter = painterResource(id = R.drawable.eye_icon2),
                contentDescription = "Vector 490",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 223.251953125.dp,
                        y = 0.7266845703125.dp
                    )
                    .requiredSize(size = 150.dp)
            )
        }
        RoundButton3()

    }}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarDisease3(modifier: Modifier = Modifier) {
    androidx.compose.material3.TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .requiredSize(size = 24.dp)
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "ic_arrow_back",
                    tint = Color(0xff191c1b))

            }
        },

        modifier = modifier
    )
}
@Composable
fun RoundButton3(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff426cb4)),
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 50.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Text(
                text = "확인",
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 137.dp,
                        y = 13.dp))
        }
    }
}

@Composable
fun ButtonCode(modifier: Modifier = Modifier, direction:String) {
    var isClicked by remember { mutableStateOf(false) }

    val buttonColors = if (isClicked) {
        ButtonDefaults.buttonColors(containerColor = Color(0xff426cb4))
    } else {
        ButtonDefaults.buttonColors(containerColor = Color.White)
    }

    Button(
        onClick = { isClicked = !isClicked },
        shape = RoundedCornerShape(8.dp),
        colors = buttonColors,
        modifier = modifier.requiredSize(size = 156.dp)
    ) {
        Box(
            modifier = Modifier.requiredSize(size = 156.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = if (isClicked) Color.White else Color(0xff2b2b2b),
                        fontSize = 20.sp
                    )
                    ) {
                        append("$direction \n")
                    }
                    withStyle(style = SpanStyle(
                        color = if (isClicked) Color.White else Color(0xff2b2b2b),
                        fontSize = 17.sp
                    )) {
                        append("촬영할거에요")
                    }
                },
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = if (isClicked) 31.dp else 30.dp, y = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting2(modifier = Modifier, name = "아토")
    }
}