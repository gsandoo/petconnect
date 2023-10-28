package com.haneum.petconnect

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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.ColorFilter
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

//스크랩 페이지 UI 구현
class MyPageScrapAtivity : ComponentActivity() {
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
fun UsedTradeFrame(
    title: String,
    price: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 240.dp)
            .requiredHeight(height = 88.dp)
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 16.dp, y = 4.dp)
                .requiredWidth(width = 155.dp)
                .requiredHeight(height = 40.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                lineHeight = 1.29.em,
                style = TextStyle(fontSize = 14.sp)
            )
            Text(
                text = price,
                color = Color(0xff426cb4),
                lineHeight = 1.29.em,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 1.dp, y = 22.dp)
            )
        }
        // 나머지 부분은 변함이 없습니다.
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 16.dp, y = 64.dp)
                .requiredWidth(width = 216.dp)
                .requiredHeight(height = 19.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 65.dp)
                    .requiredHeight(height = 19.dp)
            ) {
                Box(
                    modifier = Modifier
                ) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "heart",
                            modifier = Modifier
                        )
                        Text(
                            text = "8",
                            color = Color.Black,
                            lineHeight = 1.33.em,
                            style = TextStyle(fontSize = 12.sp),
                            modifier = Modifier
                                .offset(x = 21.dp, y = 2.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.command),
                        contentDescription = "Outline/Status/Bookmark",
                        modifier = Modifier.offset(x = 24.dp, y = 0.dp)
                    )
                    Text(
                        text = "2",
                        color = Color.Black,
                        lineHeight = 1.33.em,
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.offset(x = 20.dp, y = 0.dp)
                    )
                }
            }
            Text(
                text = location,
                color = Color(0xffb3b3b3),
                textAlign = TextAlign.End,
                lineHeight = 1.09.em,
                style = TextStyle(fontSize = 11.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 152.dp, y = 3.5.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.bookmark),
            contentDescription = "Outline/Status/Bookmark",
            tint = Color(0xff426cb4),
            modifier = Modifier
                .size(23.dp)
                .offset(x = 216.dp, y = 1.dp)
        )
    }
}
@Composable
fun CommunityFrame(modifier: Modifier = Modifier, category:String, content:String, command_num:String, heart_num:String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .background(color = Color.White)
            .padding(all = 3.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = Color.White)
                .border(
                    border = BorderStroke(1.dp, Color(0xff426cb4)),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp
                )
        ) {
            Text(
                text = "$category",
                color = Color(0xff426cb4),
                textAlign = TextAlign.Center,
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp))
        }
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xff2b2b2b),
                    fontSize = 14.sp)
                ) {append("$content")}
                withStyle(style = SpanStyle(
                    color = Color(0xffb3b3b3),
                    fontSize = 12.sp)
                ) {append("...더보기")}},
            modifier = Modifier
                .requiredWidth(width = 324.dp))
        Box(
            modifier = Modifier
                .requiredWidth(width = 324.dp)
                .requiredHeight(height = 51.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 17.dp
                    )
                    .requiredWidth(width = 112.dp)
                    .requiredHeight(height = 19.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 53.dp)
                        .requiredHeight(height = 19.dp)
                ) {
                    Text(
                        text = "  공감 $heart_num",
                        color = Color.Black,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 21.dp,
                                y = 2.dp
                            ))
                    Icon(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "heart",
                        tint = Color(0xff191c1b)
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 60.dp,
                            y = 1.5.dp
                        )
                        .requiredWidth(width = 52.dp)
                        .requiredHeight(height = 16.dp)
                ) {
                    Text(
                        text = "    댓글 $command_num",
                        color = Color.Black,
                        lineHeight = 1.33.em,
                        style = TextStyle(
                            fontSize = 12.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 20.dp,
                                y = 0.dp
                            ))
                    Icon(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.command),
                        contentDescription = "command",
                        tint = Color(0xff191c1b)
                    )
                }
            }
            Text(
                text = "1분전",
                color = Color(0xffb3b3b3),
                textAlign = TextAlign.End,
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .offset(
                        x = (-5).dp,
                        y = 17.5.dp
                    ))
            Divider(
                color = Color(0xffe2e2e2),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 0.999969482421875.dp
                    )
                    .requiredWidth(width = 200.dp))
        }
    }
}


@Composable
fun ToggleButton(
    text: String,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = text == selectedTab

    val buttonColors = if (isSelected) {
        ButtonDefaults.buttonColors(
            containerColor = Color(0xff426cb4),
            contentColor = Color.White
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xffb3b3b3)
        )
    }

    Button(
        onClick = { onTabSelected(text) },
        shape = RoundedCornerShape(32.dp),
        colors = buttonColors,
        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 12.dp),
        modifier = modifier.requiredWidth(148.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.requiredWidth(148.dp)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}