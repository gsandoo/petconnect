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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
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
import com.google.android.play.integrity.internal.x

class feedMeasurementActivity : ComponentActivity() {
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
fun ButtonFrame(
    buttonText: String,
    modifier: Modifier = Modifier
) {
    var isClicked by remember { mutableStateOf(false) }

    val backgroundColor = if (isClicked) Color(0xff426cb4) else Color.White
    val textColor = if (isClicked) Color.White else Color(0xff1e1e1e)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 328.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .padding(
                horizontal = 32.dp,
                vertical = 16.dp
            )
            .clickable {
                isClicked = !isClicked
            }
    ) {
        Text(
            text = buttonText,
            color = textColor,
            textAlign = TextAlign.Center,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 18.sp
            ),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}

@Composable
fun plus_feed(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .requiredWidth(width = 328.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                horizontal = 32.dp,
                vertical = 16.dp
            )
    ) {
        Text(
            text = "다른 사료 추가하기",
            color = Color(0xffb1c5cd),
            textAlign = TextAlign.Center,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 18.sp),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically))
    }
}
@Composable
fun feedTitle(modifier: Modifier = Modifier) {
    Text(
        lineHeight = 1.sp,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color(0xff2b2b2b),
                fontSize = 24.sp)
            ) {append("측정할 ")}
            withStyle(style = SpanStyle(
                color = Color(0xff426cb4),
                fontSize = 24.sp)) {append("사료를 선택")}
            withStyle(style = SpanStyle(
                color = Color(0xff2b2b2b),
                fontSize = 24.sp)) {append("해주세요")}},
        modifier = modifier)
}
@Composable
fun RoundButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth() // 너비를 화면 너비와 같아지도록 설정
            .requiredHeight(height = 50.dp)
            .clip(shape = RoundedCornerShape(32.dp))
            .background(if (isEnabled) Color(0xFFE2E2E2) else Color(0xFF0000FF)),
        contentPadding = PaddingValues(16.dp),
        enabled = isEnabled
    ) {
        Text(
            text = "확인",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun feed_TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 56.dp)
            .background(color = Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(
                    x = (-16).dp,
                    y = 16.dp
                ))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 16.dp
                )
                .requiredSize(size = 24.dp)
        ) {
        }
        Text(
            text = "사료측정",
            color = Color.Black,
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(
                    x = 0.dp,
                    y = 0.dp
                ))
    }
}


@Composable
fun set_layout(){
    Column(modifier = Modifier
        .fillMaxSize()){
        feed_TopAppBar()
        feedTitle(modifier = Modifier.padding(30.dp, top=60.dp),)
        Box(modifier = Modifier
            .padding(10.dp))

        Box(modifier = Modifier

            .padding(50.dp)){
            Column{

                ButtonFrame(
                    buttonText = "시그니처 오가닉 스킨 컨트롤" // 원하는 텍스트를 여기에 지정
                )
                ButtonFrame(
                    buttonText = "로반 잘먹잘싸" // 원하는 텍스트를 여기에 지정
                )
                plus_feed()

            }

        }

        RoundButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // 가운데 정렬
                .padding(30.dp)
                .offset(y=300.dp),
            onClick = { /* 버튼 클릭 시 실행할 작업 */ }
        )
    }

}
@Preview
@Composable
private fun Frame260800408Preview() {
    set_layout()
}