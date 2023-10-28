package com.haneum.petconnect

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import org.checkerframework.checker.signature.qual.PrimitiveType

class CommunityDetailContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme{
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
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarDetailCommu(modifier: Modifier = Modifier) {
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
                    tint = Color(0xff191c1b)
                )

            }
        },

        modifier = modifier
    )
}
@Composable
fun CommandCode(modifier: Modifier = Modifier, writer:String, content:String, date:String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 328.dp)
            .background(color = Color.White)
            .padding(all = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 317.dp)
                .requiredHeight(height = 26.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 89.dp)
                    .requiredHeight(height = 26.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 26.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 26.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.White)
                            .border(
                                border = BorderStroke(0.5.dp, Color(0xff426cb4)),
                                shape = CircleShape
                            ))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 5.77783203125.dp,
                                y = 5.77783203125.dp
                            )
                            .requiredWidth(width = 15.dp)
                            .requiredHeight(height = 15.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.save_icon),
                            contentDescription = "Union",
                            modifier = Modifier
                                .requiredWidth(width = 15.dp)
                                .requiredHeight(height = 15.dp))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 3.05517578125.dp,
                                    y = 5.85595703125.dp
                                )
                                .requiredWidth(width = 9.dp)
                                .requiredHeight(height = 3.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 3.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color.White))
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 1.697265625.dp,
                                        y = 1.6552734375.dp
                                    )
                                    .requiredSize(size = 1.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xff426cb4)))
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 5.34716796875.dp,
                                        y = 0.dp
                                    )
                                    .requiredSize(size = 3.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color.White))
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 6.95947265625.dp,
                                        y = 1.6552734375.dp
                                    )
                                    .requiredSize(size = 1.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color(0xff426cb4)))
                        }
                    }
                }
                Text(
                    text = "$writer",
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.09.em,
                    style = TextStyle(
                        fontSize = 11.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 35.dp,
                            y = 7.dp
                        ))
            }
            Image(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "User Interface / Menu",
                colorFilter = ColorFilter.tint(Color(0xffb3b3b3)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 300.dp,
                        y = 3.dp
                    )
                    .requiredSize(size = 17.dp))
        }
        Text(
            text = "$content",//댓글입니다.
            color = Color(0xff2b2b2b),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp))
        Text(
            text = "$date",//08/26  16:02
            color = Color(0xffb1c5cd),
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 12.sp))
    }
}


@Composable
fun boardFrame(modifier: Modifier = Modifier, nickname: String, time:String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = modifier
            .background(color = Color.White)
            .padding(
                horizontal = 18.dp,
                vertical = 4.dp
            )
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 324.dp)
                .requiredHeight(height = 45.dp)
        ) {
            Text(
                text = "$time",
                color = Color(0xffb3b3b3),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .offset(
                        x = (-234).dp,
                        y = 12.5.dp
                    ))
            Box(
                modifier = Modifier
                    .requiredSize(size = 45.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 45.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color.White)
                        .border(
                            border = BorderStroke(0.5.dp, Color(0xff426cb4)),
                            shape = CircleShape
                        ))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 10.dp,
                            y = 10.dp
                        )
                        .requiredWidth(width = 26.dp)
                        .requiredHeight(height = 26.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = "Union",
                        modifier = Modifier
                            .requiredWidth(width = 26.dp)
                            .requiredHeight(height = 26.dp))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 5.2880859375.dp,
                                y = 10.135498046875.dp
                            )
                            .requiredWidth(width = 15.dp)
                            .requiredHeight(height = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 0.dp,
                                    y = 0.000244140625.dp
                                )
                                .requiredSize(size = 6.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color.White))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 2.9375.dp,
                                    y = 2.86474609375.dp
                                )
                                .requiredSize(size = 2.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xff426cb4)))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 9.25439453125.dp,
                                    y = (-0.000244140625).dp
                                )
                                .requiredSize(size = 6.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color.White))
                        Box(
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 12.04541015625.dp,
                                    y = 2.86474609375.dp
                                )
                                .requiredSize(size = 2.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xff426cb4)))
                    }
                }
            }
            Text(
                text = "$nickname",
                color = Color(0xff2b2b2b),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 61.dp,
                        y = 3.dp
                    ))
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "User Interface / Menu",
                tint = Color(0xff426cb4),
                modifier = Modifier
                    .size(20.dp)
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 300.dp,
                        y = 11.dp
                    ))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun subFrame(modifier: Modifier = Modifier,context:String, command_num:String, heart_num:String) {
    Column {

        Box(){
            Text(
                text = "$context",
                color = Color.DarkGray,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp),
                modifier = modifier
                    .padding(20.dp)
                    .requiredWidth(width = 311.dp)
                )
        }


    }
    Box(
        modifier = modifier
            .requiredWidth(width = 324.dp)
            .requiredHeight(height = 51.dp)
    ) {
        InputChip(
            label = {
                Text(
                    text = " 저장취소하기",
                    color = Color.Black,
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 12.sp),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            },
            shape = MaterialTheme.shapes.small,
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color.White
            ),
            selected = true,
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 239.dp,
                    y = 8.dp
                ))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 16.dp
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
                    text = "  댓글 $command_num",
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
    }
}


@Composable
fun generatecode(modifier: Modifier){
    Column(){
        TopAppBarDetailCommu()
        Box(modifier=modifier .fillMaxWidth() ,contentAlignment = Alignment.Center){
            Column {
                boardFrame(nickname = "작성자이름", time = "1분 전" )
                subFrame(context="본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다. 본문내용입니다.", command_num = "7", heart_num = "38")

                CommandCode(writer="작성자 이름", content="댓글입니다.", date="08/26  16:02")
                CommandCode(writer="작성자 이름", content="댓글입니다.", date="08/26  16:02")
                CommandCode(writer="작성자 이름", content="댓글입니다.", date="08/26  16:02")
            }
        }
}}
@Preview
@Composable
private fun preview(){
    generatecode(modifier= Modifier)
}
