package com.haneum.petconnect

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.haneum.petconnect.contracts.ToiletRecordContract
import com.haneum.petconnect.data.ToiletRecord
import com.haneum.petconnect.presenters.ToiletRecordPresenter


class ToiletRecord0Activity : ComponentActivity(), ToiletRecordContract.View {
    private val presenter: ToiletRecordContract.Presenter = ToiletRecordPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "start" // 시작 목적지를 "start"로 설정
            ) {
                composable("start") {
                    GeneratedCode_start(navController)
                }
                composable("composePoop") {
                    ComposablePoopContent()
                }
                composable("composePee") {
                    AddPeeRecordActivity()
                }
            }
        }
        }

    override fun showToiletRecords(records: List<ToiletRecord>) {
        // 데이터를 화면에 표시
    }
}
@Composable
fun GeneratedCode_start(navController: NavHostController, modifier: Modifier = Modifier) {
    var isPoopSelected by remember { mutableStateOf(false) }
    var isPeeSelected by remember { mutableStateOf(false) }
    var isTabEnabled by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color.White)
    ) {

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
                    .clickable {
                        isPoopSelected = true
                        isPeeSelected = false
                        isTabEnabled = true
                    }
                    .background(color = if (isPoopSelected) Color(0xFF426CB4) else if (isPeeSelected) Color.White else Color.White)
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
                    .clickable {
                        isPeeSelected = true
                        isPoopSelected = false
                        isTabEnabled = true
                    }
                    .background(color = if (isPeeSelected) Color(0xFF426CB4) else if (isPoopSelected) Color.White else Color.White)
                    .align(alignment = Alignment.Center)
                    .offset(x = 0.5.dp,
                        y = 33.dp)
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
                        .offset(x = 50.dp,
                            y = 39.000030517578125.dp)
                        .requiredWidth(width = 48.dp)
                        .requiredHeight(height = 38.dp)
                        .rotate(degrees = 0.03f))
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
                    fontSize = 24.sp)
                ) {append("대소변을 선택")}
                withStyle(style = SpanStyle(
                    color = Color(0xff2b2b2b),
                    fontSize = 24.sp)
                ) {append("해주세요")}},
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
                    fontWeight = FontWeight.Bold)
                ) {append("아토")}
                withStyle(style = SpanStyle(
                    color = Color.DarkGray,
                    fontSize = 17.sp)
                ) {append("의 정확한 기록을 위해")}},
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 17.dp,
                    y = 167.dp))
        Tab(
            selected = false,
            onClick = {
                if (isPoopSelected) {
                    navController.navigate("composePoop")
                } else if (isPeeSelected) {
                    navController.navigate("composePee")
                }
            },
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
                .background(if (isTabEnabled) Color(0xff426cb4) else Color(0xffe2e2e2))
        )
    }
}

@Preview
@Composable
private fun XPreview() {
    val navController = rememberNavController()
    GeneratedCode_start(navController)
}
@Composable
fun colorSelect(modifier: Modifier){
    Column(modifier = Modifier) {
        Row(modifier= Modifier){
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff8b6866))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "갈색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffd98183))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "붉은색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xfff6cca0))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "주황색\n노랑색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff87bb83))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "초록색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }

        }
        Row(modifier= Modifier){
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffb3b3b3))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "회색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.DarkGray)
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "검은색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }

        }
    }

}

@Composable
fun ComposablePoopContent(modifier: Modifier = Modifier) {
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
            colorSelect(modifier=Modifier)
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
@Composable
fun AddPeeRecordActivity(modifier: Modifier = Modifier) {
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
                text = "소변 기록 추가",
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
                    .clickable {

                    }
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
@Composable
fun colorSelect2(modifier: Modifier){
    Column(modifier = Modifier) {
        Row(modifier= Modifier){
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff8b6866))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "갈색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffd98183))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "붉은색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xfff6cca0))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "주황색",
                        color = Color(0xff2b2b2b),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff87bb83))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "투명한\n" +
                                "노랑색",
                        color = Color(0xFFFFFAEC),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }

            }

        }
        Row(modifier= Modifier){
            Box(modifier = Modifier.padding(10.dp)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 9.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 52.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xffb3b3b3))
                            .border(
                                border = BorderStroke(1.dp, Color(0xffe2e2e2)),
                                shape = CircleShape
                            ))
                    Text(
                        text = "투명한\n무색",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp)
                    )
                }



            }

        }
    }

}
