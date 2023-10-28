package com.haneum.petconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

class CommunityWriteActivity : ComponentActivity() {
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
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarWrite(modifier: Modifier = Modifier) {
    androidx.compose.material3.TopAppBar(
        title = {
            Row(){
                Box(modifier=Modifier
                    .offset(x=140.dp)){
                    Text(
                    text = "글쓰기",
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    lineHeight = 3.41.em,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold // 텍스트를 Bold로 설정
                    )
                )}

                Box(modifier=Modifier
                    .offset(x=260.dp)){
                    Text(
                        text = "완료",
                        color = Color(0xff426cb4),
                        textAlign = TextAlign.Right,
                        lineHeight = 1.00.em,
                        style = TextStyle(
                            fontSize = 17.sp
                        )
                    )
                }

            }

        },
        navigationIcon = {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "ic_arrow_back",
                tint = Color(0xff191c1b)
            )
        },
        modifier = modifier
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownboardBox(board: List<String>) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(board[0]) }

    Box(
        modifier = Modifier
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
                    .width(350.dp)
                    .height(35.dp)// 너비를 조절하려면 이 부분을 조절하세요
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                board.forEach { item ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteFrame(modifier: Modifier = Modifier) {
    var textValue by remember { mutableStateOf("") } // 사용자의 입력값을 저장하는 변수

    OutlinedTextField(
        value = textValue, // 현재 입력값 표시
        onValueChange = {
            if (it.length <= 300) {
                textValue = it // 입력값 업데이트
            }
        },
        label = {
            Text(
                text = "게시글 내용을 작성해주세요.",
                color = Color(0xffb3b3b3),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp))
        },
        supportingText = {
            Text(
                textAlign = TextAlign.End,
                lineHeight = 1.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color(0xff2b2b2b),
                        fontSize = 12.sp)) { append("${textValue.length} / 300") } // 입력된 문자 수 표시
                }
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White),
        modifier = modifier
            .requiredWidth(width = 325.dp)
            .requiredHeight(height = 410.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    )
}



@Composable
fun Photobutton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 328.dp)
            .requiredHeight(height = 50.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, Color(0xff426cb4)),
                shape = RoundedCornerShape(8.dp)
            )
    ,contentAlignment = Alignment.Center){
            Row(){

                Box(){
                    Image(
                        painter = painterResource(id = R.drawable.photo),
                        contentDescription = "camera",
                        modifier = Modifier
                            .offset(x = 5.01171875.dp)
                            .requiredWidth(width = 30.dp)
                            .requiredHeight(height = 30.dp)
                            .padding(end = 10.dp)
                    )
                }

                Text(
                    text = "사진 첨부하기",
                    color = Color(0xff426cb4),
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp))
            }
    }
}
@Composable
fun generated(modifier: Modifier){
    Column {
        TopAppBarWrite()
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        ){

            Box(contentAlignment = Alignment.Center){
                val array1 = listOf("정보공유해요","고민있어요","자랑해요","기타")
                Column {
                    DropdownboardBox(board=array1)
                    WriteFrame()
                    Photobutton()
                }


            }

        }
    }


}

@Preview
@Composable
private fun TopAppBarPreview() {
    generated(modifier = Modifier)
}