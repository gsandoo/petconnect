package com.haneum.petconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme


class CommunityAcitivity : ComponentActivity() {
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(arrs: List<String>) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(arrs[0]) }

    Box(
        modifier = Modifier
            .padding(25.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .menuAnchor()
                    .width(100.dp)
                    .height(35.dp)// 너비를 조절하려면 이 부분을 조절하세요
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                arrs.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item, color = Color.Black )
                        },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarCommunity(modifier: Modifier = Modifier) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = "커뮤니티",
                color = Color(0xff426cb4),
                lineHeight = 1.4.em,
                style = TextStyle(
                    fontSize = 20.sp)
            )
        },
        actions = {
            Box(
                modifier = Modifier
                    .requiredSize(size = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search_black),
                    contentDescription = "search_black_24dp 1",
                    modifier = Modifier
                        .requiredSize(size = 24.dp))
            }
        },
        modifier = modifier)

}

@Composable
fun Frame(modifier: Modifier = Modifier, category:String, content:String, command_num:String, heart_num:String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .background(color = Color.White)
            .padding(all = 16.dp)
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
                    .requiredWidth(width = 328.dp))
        }
    }
}
@Preview
@Composable
private fun TopAppBarPreview() {
    Column {

        TopAppBarCommunity()
        Row(modifier = Modifier){

            Text(
                text = "총 5개의 글",
                color = Color(0xffb1c5cd),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp))
            val array1 = listOf("최신순")
            DropdownMenuBox(arrs = array1)
        }

        Frame(
            modifier = Modifier.fillMaxWidth(), // 여기에 원하는 Modifier를 설정하세요.
            category = "고민있어요",
            content = "text = \"저희집 강아지는 말티즈이고 3살 입니다.\n" +
                    "\\n현재 건강백서 말티즈를 먹이고 있는데 사료 바꿔볼까 합니다!\n" +
                    "더 좋은 사료 먹이고 싶어서요 강..",
            command_num = "10", // 명령 번호를 원하는 값으로 설정하세요.
            heart_num = "42" // 좋아요 수를 원하는 값으로 설정하세요.
        )

        Frame(
            modifier = Modifier.fillMaxWidth(), // 여기에 원하는 Modifier를 설정하세요.
            category = "고민있어요",
            content = "text = \"저희집 강아지는 말티즈이고 3살 입니다.\n" +
                    "\\n현재 건강백서 말티즈를 먹이고 있는데 사료 바꿔볼까 합니다!\n" +
                    "더 좋은 사료 먹이고 싶어서요 강..",
            command_num = "10", // 명령 번호를 원하는 값으로 설정하세요.
            heart_num = "42" // 좋아요 수를 원하는 값으로 설정하세요.
        )
        Frame(
            modifier = Modifier.fillMaxWidth(), // 여기에 원하는 Modifier를 설정하세요.
            category = "고민있어요",
            content = "text = \"저희집 강아지는 말티즈이고 3살 입니다.\n" +
                    "\\n현재 건강백서 말티즈를 먹이고 있는데 사료 바꿔볼까 합니다!\n" +
                    "더 좋은 사료 먹이고 싶어서요 강..",
            command_num = "10", // 명령 번호를 원하는 값으로 설정하세요.
            heart_num = "42" // 좋아요 수를 원하는 값으로 설정하세요.
        )
    }

}