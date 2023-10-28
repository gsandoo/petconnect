package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text

import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.buildAnnotatedString

import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class PlusPooRecordActivity : ComponentActivity() {
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
fun PooRecord(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 857.dp)
            .background(color = Color.White)
    ) {

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 32.dp
                )
                .requiredWidth(width = 360.dp)
                .requiredHeight(height = 56.dp)
                .background(color = Color.White)
        ) {
            Text(
                text = "완료",
                color = Color(0xffb3b3b3),
                textAlign = TextAlign.End,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(
                        x = 312.dp,
                        y = 0.dp
                    )
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "baseline_arrow_back_24",
                tint = Color(0xff2b2b2b),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(
                        x = 16.dp,
                        y = 0.dp
                    )
            )
            Text(
                text = "대변 기록 추가",
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 1.4.em,
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(
                        x = 0.5.dp,
                        y = 0.dp
                    )
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = (-128.5).dp,
                    y = 518.dp
                )
                .requiredSize(size = 71.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .border(
                    border = BorderStroke(1.dp, Color(0xffb1c5cd)),
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 37.dp,
                    y = 532.dp
                )
                .requiredWidth(width = 29.dp)
                .requiredHeight(height = 45.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 4.dp,
                        y = 0.dp
                    )
                    .requiredWidth(width = 22.dp)
                    .requiredHeight(height = 18.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 3.3435401916503906.dp
                        )
                        .requiredWidth(width = 22.dp)
                        .requiredHeight(height = 15.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = Color(0xff426cb4))
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 5.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 12.dp)
                        .requiredHeight(height = 7.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = Color(0xff426cb4))
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 7.4296875.dp,
                            y = 6.686981201171875.dp
                        )
                        .requiredSize(size = 7.dp)
                        .clip(shape = CircleShape)
                        .border(
                            border = BorderStroke(2.dp, Color.White),
                            shape = CircleShape
                        )
                )
            }
            Text(
                lineHeight = 1.sp,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xff426cb4),
                            fontSize = 14.sp
                        )
                    ) { append("0 ") }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xffb3b3b3),
                            fontSize = 14.sp
                        )
                    ) { append("/ 6") }
                },
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 27.dp
                    )
            )
        }
        Text(
            text = "기록 날짜",
            color = Color(0xff2b2b2b),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 18.dp,
                    y = 374.dp
                )
        )
        Text(
            text = "색상 기록",
            color = Color(0xff2b2b2b),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 18.dp,
                    y = 113.dp
                )
        )
        Box(modifier=Modifier
            .offset(x=20.dp,y=130.dp)){
            colorSelect2(modifier=Modifier)
        }

        Text(
            text = "기록 시간",
            color = Color(0xff2b2b2b),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 188.dp,
                    y = 374.dp
                )
        )
        Text(
            text = "사진 등록 및 메모 추가(선택)",
            color = Color(0xff2b2b2b),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 18.dp,
                    y = 484.dp
                )
        )
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color(0xffe2e2e2)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 605.dp
                )
                .clip(shape = RoundedCornerShape(8.dp))
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 328.dp)
                    .requiredHeight(height = 212.dp)
            ) {
                Text(
                    text = "기록이 필요하다면 활용해주세요",
                    color = Color(0xffb3b3b3),
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 16.dp,
                            y = 24.dp
                        )
                        .requiredWidth(width = 293.dp)
                )
                Text(
                    textAlign = TextAlign.End,
                    lineHeight = 1.sp,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xff2b2b2b),
                                fontSize = 12.sp
                            )
                        ) { append("0") }
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xffb3b3b3),
                                fontSize = 12.sp
                            )
                        ) { append(" / 100") }
                    },
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 271.dp,
                            y = 178.dp
                        )
                )
            }
        }
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color(0xffe2e2e2)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 407.dp
                )
                .clip(shape = RoundedCornerShape(24.dp))
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 156.dp)
                    .requiredHeight(height = 44.dp)
            ) {
                Text(
                    text = "날짜선택",
                    color = Color(0xffb3b3b3),
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 20.dp,
                            y = 10.dp
                        )
                )

            }
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 188.dp,
                        y = 407.dp
                    )
                    .clip(shape = RoundedCornerShape(24.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 156.dp)
                        .requiredHeight(height = 44.dp)
                ) {
                    Text(
                        text = "시간선택",
                        color = Color(0xffb3b3b3),
                        lineHeight = 1.41.em,
                        style = TextStyle(
                            fontSize = 17.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 20.dp,
                                y = 10.dp
                            )
                    )

                }
            }

        }
    }
}
//@Composable
//fun colorSelect2(modifier: Modifier){
//    Column(modifier = Modifier) {
//        Row(modifier= Modifier){
//            Box(modifier = Modifier.padding(10.dp)){
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(8.dp))
//                        .background(color = Color.White)
//                        .padding(
//                            horizontal = 4.dp,
//                            vertical = 9.dp
//                        )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .requiredSize(size = 52.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = Color(0xff8b6866))
//                            .border(
//                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
//                                shape = CircleShape
//                            ))
//                    Text(
//                        text = "갈색",
//                        color = Color(0xff2b2b2b),
//                        textAlign = TextAlign.Center,
//                        lineHeight = 1.33.em,
//                        style = TextStyle(
//                            fontSize = 12.sp)
//                    )
//                }
//
//            }
//            Box(modifier = Modifier.padding(10.dp)){
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(8.dp))
//                        .background(color = Color.White)
//                        .padding(
//                            horizontal = 4.dp,
//                            vertical = 9.dp
//                        )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .requiredSize(size = 52.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = Color(0xffd98183))
//                            .border(
//                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
//                                shape = CircleShape
//                            ))
//                    Text(
//                        text = "붉은색",
//                        color = Color(0xff2b2b2b),
//                        textAlign = TextAlign.Center,
//                        lineHeight = 1.33.em,
//                        style = TextStyle(
//                            fontSize = 12.sp)
//                    )
//                }
//
//            }
//            Box(modifier = Modifier.padding(10.dp)){
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(8.dp))
//                        .background(color = Color.White)
//                        .padding(
//                            horizontal = 4.dp,
//                            vertical = 9.dp
//                        )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .requiredSize(size = 52.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = Color(0xfff6cca0))
//                            .border(
//                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
//                                shape = CircleShape
//                            ))
//                    Text(
//                        text = "주황색\n노랑색",
//                        color = Color(0xff2b2b2b),
//                        textAlign = TextAlign.Center,
//                        lineHeight = 1.33.em,
//                        style = TextStyle(
//                            fontSize = 12.sp)
//                    )
//                }
//
//            }
//            Box(modifier = Modifier.padding(10.dp)){
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(8.dp))
//                        .background(color = Color.White)
//                        .padding(
//                            horizontal = 4.dp,
//                            vertical = 9.dp
//                        )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .requiredSize(size = 52.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = Color(0xff87bb83))
//                            .border(
//                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
//                                shape = CircleShape
//                            ))
//                    Text(
//                        text = "초록색",
//                        color = Color(0xff2b2b2b),
//                        textAlign = TextAlign.Center,
//                        lineHeight = 1.33.em,
//                        style = TextStyle(
//                            fontSize = 12.sp)
//                    )
//                }
//
//            }
//
//        }
//        Row(modifier= Modifier){
//            Box(modifier = Modifier.padding(10.dp)){
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(8.dp))
//                        .background(color = Color.White)
//                        .padding(
//                            horizontal = 4.dp,
//                            vertical = 9.dp
//                        )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .requiredSize(size = 52.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = Color(0xffb3b3b3))
//                            .border(
//                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
//                                shape = CircleShape
//                            ))
//                    Text(
//                        text = "회색",
//                        color = Color(0xff2b2b2b),
//                        textAlign = TextAlign.Center,
//                        lineHeight = 1.33.em,
//                        style = TextStyle(
//                            fontSize = 12.sp)
//                    )
//                }
//
//            }
//            Box(modifier = Modifier.padding(10.dp)){
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(8.dp))
//                        .background(color = Color.White)
//                        .padding(
//                            horizontal = 4.dp,
//                            vertical = 9.dp
//                        )
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .requiredSize(size = 52.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = Color.DarkGray)
//                            .border(
//                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
//                                shape = CircleShape
//                            ))
//                    Text(
//                        text = "검은색",
//                        color = Color(0xff2b2b2b),
//                        textAlign = TextAlign.Center,
//                        lineHeight = 1.33.em,
//                        style = TextStyle(
//                            fontSize = 12.sp)
//                    )
//                }
//
//            }
//
//        }
//    }
//
//}

@Preview
@Composable
private fun GeneratedCodessPreview() {
    PooRecord()
}
