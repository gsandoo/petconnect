package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme

class SkinResultActivity : ComponentActivity() {
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
fun Greeting7( modifier: Modifier = Modifier) {
    Column {
        TopAppBarDisease4()
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Box(modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "피부 촬영 결과",
                        color = Color(0xff2b2b2b),
                        lineHeight = 1.33.em,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = modifier
                    )
                    Text(
                        text = "약 02개의 피부질환이 예상되네요😢",
                        color = Color(0xff426cb4),
                        lineHeight = 1.41.em,
                        style = TextStyle(
                            fontSize = 17.sp
                        ),
                        modifier = modifier
                    )
                }
            }
            Box(modifier = Modifier.padding(5.dp)) {
                // Pass the desired text here
                GeneratedCode(nameText = "아토")
            }


            Text(
                text = "발견된 피부 질환",
                color = Color(0xff2b2b2b),
                lineHeight = 1.4.em,
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .padding(top = 10.dp, bottom = 10.dp)
            )


            diseasePanel2(diseaseName = "탈모", percentage = "00")
            diseasePanel2(diseaseName = "핫스팟피부염",percentage = "00")

            Box(modifier = modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Row(){
                    RoundButton3(
                        text = "다시하기",
                        modifier = Modifier,
                        onClick = { /* 클릭 핸들러 로직을 여기에 추가*/ }
                    )
                    RoundButton3(
                        text = "저장하기",
                        modifier = Modifier,
                        onClick = { /* 클릭 핸들러 로직을 여기에 추가 */ }
                    )

                }

            }
        }
    }

}

@Composable
fun RoundButton3(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff426cb4)),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 13.dp),
        modifier = modifier
            .padding(10.dp)
            .requiredWidth(width = 147.dp)
            .requiredHeight(height = 50.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, // 가운데 정렬
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(width = 100.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun diseasePanel2(
    modifier: Modifier = Modifier,
    diseaseName: String,
    percentage:String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 162.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.White)
    ) {
        Text(
            text = diseaseName,
            color = Color(0xff2b2b2b),
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 17.dp, y = 16.dp)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 17.dp, y = 61.dp)
        ) {

            Row() {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 74.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffd9d9d9))
                        .padding(end = 30.dp)
                )
                Text(
                    text = "피부에서 약 $percentage %확률로\n $diseaseName 이 의심되어요",
                    color = Color(0xff2b2b2b),

                    lineHeight = 1.29.em,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    modifier = modifier
                        .offset(x = 28.dp, y = 19.dp)
                )
            }


        }

        InputChip(
            label = {
                Text(
                    text = diseaseName,
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.09.em,
                    style = TextStyle(
                        fontSize = 11.sp
                    )
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.component2),
                    contentDescription = "Vector"
                )
            },
            shape = RoundedCornerShape(21.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color.White
            ),
            selected = true,
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 246.dp, y = 14.dp)
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarDisease4(modifier: Modifier = Modifier) {
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
                    tint = Color(0xff191c1b))

            }
        },

        modifier = modifier
    )
}
@Composable
fun GeneratedCode2(
    modifier: Modifier = Modifier,
    nameText: String // Add a parameter to accept the "아토" text
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 327.dp)
            .requiredHeight(height = 107.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xff426cb4))
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 253.dp,
                    y = 16.dp
                )
                .requiredWidth(width = 90.dp)
                .requiredHeight(height = 65.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.skin_icon),
                contentDescription = "Vector 154",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 5.01171875.dp)
                    .requiredWidth(width = 150.dp)
                    .requiredHeight(height = 80.dp)
            )
        }
        Text(
            text = "$nameText 의 피부는 조금의 관리가 필요해요", // Use the parameter here
            color = Color.White,
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .offset(x = 17.dp, y = 16.dp)
        )
        Text(
            text = "펫커넥트는 AI분석기술을 활용하여 질병예측을 돕고있어요\n정확하고 전문적인 소견을 원하시면 병원방문을 추천드려요",
            color = Color.White,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 12.sp
            ),
            modifier = Modifier.offset(x = 17.dp, y = 62.dp)
        )
    }
}

@Preview
@Composable
fun DiseaseCodePreview2(){

    Greeting7()


}