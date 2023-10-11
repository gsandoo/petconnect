package com.haneum.petconnect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class ToiletRecordActivity {
    @Composable
    fun X(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .requiredWidth(width = 360.dp)
                .requiredHeight(height = 800.dp)
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 88.dp)
                    .requiredWidth(width = 360.dp)
                    .requiredHeight(height = 669.dp)
                    .background(color = Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(37.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 16.dp,
                            y = 249.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(horizontal = 19.dp,
                            vertical = 43.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 74.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 0.dp)
                                .requiredSize(size = 74.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color.White)
                                .border(border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                    shape = CircleShape))
                        Image(
                            painter = painterResource(id = R.drawable.redpoop),
                            contentDescription = "redpoop",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 14.dp,
                                    y = 16.dp)
                                .requiredWidth(width = 45.dp)
                                .requiredHeight(height = 41.dp))
                    }
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 170.dp)
                            .requiredHeight(height = 124.dp)
                    ) {
                        Text(
                            text = "바이러스,세균,기생충 감염,스트레스,직장 내 상처 등 원인이 다양해요. 빠른 시일 내에 병원을 방문해 정확한 원인을 파악해보세요",
                            color = Color(0xff2b2b2b),
                            lineHeight = 1.29.em,
                            style = TextStyle(
                                fontSize = 14.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 17.dp)
                                .requiredWidth(width = 170.dp))
                        Text(
                            text = "붉은색 대변",
                            color = Color(0xff2b2b2b),
                            lineHeight = 1.29.em,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = (-53).dp)
                                .requiredWidth(width = 170.dp))
                    }
                }
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 16.dp,
                            y = 189.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 156.dp)
                            .requiredHeight(height = 44.dp)
                    ) {
                        Text(
                            text = "23.08.27",
                            color = Color.DarkGray,
                            lineHeight = 1.41.em,
                            style = TextStyle(
                                fontSize = 17.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 20.dp,
                                    y = 10.dp))
                    }
                }
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 188.dp,
                            y = 189.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 156.dp)
                            .requiredHeight(height = 44.dp)
                    ) {
                        Text(
                            text = "15:00",
                            color = Color.DarkGray,
                            lineHeight = 1.41.em,
                            style = TextStyle(
                                fontSize = 17.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 20.dp,
                                    y = 10.dp))
                    }
                }
                Text(
                    text = "기록 날짜",
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.29.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 18.dp,
                            y = 155.dp))
                Text(
                    text = "기록 시간",
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.29.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 188.dp,
                            y = 155.dp))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 16.dp,
                            y = 24.dp)
                        .requiredWidth(width = 327.dp)
                        .requiredHeight(height = 107.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xff426cb4))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.poop),
                        contentDescription = "poop",
                        alpha = 0.9f,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 245.dp,
                                y = 26.dp)
                            .requiredWidth(width = 79.dp)
                            .requiredHeight(height = 72.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pee),
                        contentDescription = "pee",
                        alpha = 0.9f,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 294.dp,
                                y = 48.dp)
                            .requiredWidth(width = 38.dp)
                            .requiredHeight(height = 30.dp)
                            .rotate(degrees = -49.03f))
                    Text(
                        text = "펫커넥트는 기록환경을 제공하여 질병예방을 돕고있어요\n정확하고 전문적인 소견을 원하시면 병원방문을 추천드려요",
                        color = Color.White,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopCenter)
                            .offset(x = 0.5.dp,
                                y = 62.dp)
                            .requiredWidth(width = 294.dp))
                    Text(
                        text = "대소변 기록은 질병악화를 막을 수 있어요",
                        color = Color.White,
                        lineHeight = 1.41.em,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 17.dp,
                                y = 16.dp))
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 32.dp)
                    .requiredWidth(width = 360.dp)
                    .requiredHeight(height = 56.dp)
                    .background(color = Color.White)
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
                Text(
                    text = "대소변기록",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = 0.dp,
                            y = 0.dp))
            }
            Tab(
                selected = false,
                onClick = {  },
                text = {
                    Text(
                        text = "확인",
                        color = Color.White,
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
                    .background(color = Color(0xff426cb4)))
        }
    }


    @Preview(widthDp = 360, heightDp = 800)
    @Composable
    private fun XPreview() {
        X(Modifier)
    }
}