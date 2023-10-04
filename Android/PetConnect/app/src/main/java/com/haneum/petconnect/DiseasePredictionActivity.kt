package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
@Composable
fun GeneratedCode33(
    modifier: Modifier.Companion =Modifier,
    condition1Text: String,
    condition2Text: String
) {
    Column( modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "안구질환 촬영 결과",
            color = Color(0xff426cb4),
            textAlign = TextAlign.Center,
            lineHeight = 1.33.em,
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
                .requiredWidth(width = 300.dp)
                .requiredHeight(height = 100.dp)
                .padding(20.dp))

        Box(modifier = Modifier) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = condition1Text,
                    color = Color(0xff4b4b4b),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.33.em,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = modifier.padding(30.dp)
                )
                Text(
                    text = condition2Text,
                    color = Color(0xff4b4b4b),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.33.em,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = modifier.padding(30.dp)
                )
                Text(
                    text = "총 02가지 안구질환이 의심되니\n병원에 내원해보시는 걸 추천드립니다.",
                    color = Color(0xff4b4b4b),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp
                    ),
                    modifier = modifier.padding(30.dp)
                )
            }
        }

        // 버튼을 화면 아래쪽에 배치
        RoundButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                 // 위쪽 여백 추가
        )
    }
}

@Composable
fun RoundButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            // 저장 로직 추가
        },
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff426cb4)),
        contentPadding = PaddingValues(horizontal = 104.dp, vertical = 13.dp),
        modifier = modifier
            .requiredWidth(width = 307.dp)
            .requiredHeight(height = 50.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Text(
                text = "저장하기",
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold))
        }
    }
}

@Preview
@Composable
private fun GeneratedCodePreview33() {
    GeneratedCode33(
        modifier = Modifier,
        condition1Text = "백내장",
        condition2Text = "체리아이"
    )
}
