package com.haneum.petconnect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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

class ToiletRecordStartActivity {
    @Composable
    fun GeneratedCode(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 360.dp)
                .requiredHeight(height = 800.dp)
                .background(color = Color.White)
        ) {
            Darkmodefalse()
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 16.dp,
                        y = 322.dp)
                    .requiredWidth(width = 328.dp)
                    .requiredHeight(height = 156.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 0.dp,
                            y = 0.dp)
                        .requiredSize(size = 156.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.poop),
                        contentDescription = "poop",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 44.51953125.dp,
                                y = 34.dp)
                            .requiredWidth(width = 68.dp)
                            .requiredHeight(height = 62.dp))
                    Text(
                        text = "대변 기록",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.41.em,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .offset(x = 0.5.dp,
                                y = 33.dp))
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .offset(x = 172.dp,
                            y = 0.dp)
                        .requiredSize(size = 156.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                ) {
                    Text(
                        text = "소변 기록",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.41.em,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .offset(x = 0.5.dp,
                                y = 33.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pee),
                        contentDescription = "pee",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 47.dp,
                                y = 39.000030517578125.dp)
                            .requiredWidth(width = 48.dp)
                            .requiredHeight(height = 38.dp)
                            .rotate(degrees = -49.03f))
                }
            }
            FloatingActionButton(
                onClick = { },
                containerColor = Color.White
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 56.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .offset(x = (-16).dp,
                                y = 16.dp))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 16.dp,
                                y = 16.dp)
                            .requiredSize(size = 24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "baseline_arrow_back_24",
                            tint = Color(0xff191c1b))
                    }
                }
            }
            Text(
                lineHeight = 1.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color(0xff426cb4),
                        fontSize = 24.sp)) {append("대소변을 선택")}
                    withStyle(style = SpanStyle(
                        color = Color(0xff2b2b2b),
                        fontSize = 24.sp)) {append("해주세요")}},
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 17.dp,
                        y = 191.dp))
            Text(
                lineHeight = 1.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color(0xff2b2b2b),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold)) {append("아토")}
                    withStyle(style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = 17.sp)) {append("의 정확한 기록을 위해")}},
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 17.dp,
                        y = 167.dp))
            Tab(
                selected = false,
                onClick = {  },
                text = {
                    Text(
                        text = "확인",
                        color = Color(0xffb3b3b3),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.41.em,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold))
                },
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 27.dp,
                        y = 699.dp)
                    .requiredWidth(width = 307.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(color = Color(0xffe2e2e2)))
        }
    }

    @Composable
    fun Darkmodefalse(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(height = 24.dp))
    }
    @Preview(widthDp = 360, heightDp = 800)
    @Composable
    private fun GeneratedCodePreview() {
        GeneratedCode(Modifier)
    }

}