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
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import androidx.compose.material3.Text as Text

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

class HealthActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeneratedCodePreview()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar7(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 56.dp)
            .background(color = Color.White)
    ) {
        Text(
            text = "건강",
            color = Color(0xff426cb4),
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 16.dp
                ))
    }
}


@Composable
fun riceiconCode(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 327.dp)
            .requiredHeight(height = 123.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xff426cb4))
    ) {
        Text(
            text = "사료  측정",
            color = Color.White,
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 17.dp,
                    y = 16.dp
                ))
        Text(
            text = "사료 무게와 배급시간 측정",
            color = Color.White,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 17.dp,
                    y = 44.dp
                ))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 212.dp,
                    y = 1.00018310546875.dp
                )
                .requiredWidth(width = 99.dp)
                .requiredHeight(height = 109.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 50.99981689453125.dp
                    )
                    .requiredWidth(width = 99.dp)
                    .requiredHeight(height = 58.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rice_icon),
                    contentDescription = "Rectangle 6667355",
                    modifier = Modifier
                        .requiredWidth(width = 159.dp)
                        .requiredHeight(height = 178.dp))

    }
}}}

@Composable
fun eyeCode(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 156.dp)
            .requiredHeight(height = 123.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xff72b9ef))
    ) {
        Text(
            text = "눈 촬영",
            color = Color.White,
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 16.dp
                )
        )
        Text(
            text = "안구질환 관리",
            color = Color.White,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 12.sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 40.dp
                )
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 72.353515625.dp,
                    y = 72.dp
                )
                .requiredWidth(width = 87.dp)
                .requiredHeight(height = 59.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.eye_icon),
                contentDescription = "Vector 154",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 5.48046875.dp,

                    )
                    .requiredWidth(width = 120.dp)
                    .requiredHeight(height = 80.dp)
            )
        }

            }
        }
@Composable
fun skinCode(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 156.dp)
            .requiredHeight(height = 123.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xffb1c5cd))
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 88.544921875.dp,
                    y = 49.279541015625.dp
                )
                .requiredWidth(width = 49.dp)
                .requiredHeight(height = 43.dp)
                .rotate(degrees = 34.86f)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 39.dp)
                    .requiredHeight(height = 23.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.skin_icon),
                    contentDescription = "Vector 489",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 21.388671875.dp,
                            y = 23.7291259765625.dp
                        )
                        .requiredWidth(width = 80.dp)
                        .requiredHeight(height = 88.dp)
                        .rotate(degrees = 310f))
            }
        }
        Text(
            text = "피부 촬영",
            color = Color.White,
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 16.dp
                ))
        Text(
            text = "피부질환 관리",
            color = Color.White,
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
                    y = 40.dp
                ))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox(mypets: List<String>) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(mypets[0]) }

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
                mypets.forEach { item ->
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
fun health_record(modifier: Modifier) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 가운데 정렬할 요소를 이곳에 배치
        Column {
            TopAppBar7(
                modifier = Modifier
                    .padding(10.dp)
                    .offset(
                        x = 0.dp,
                        y = 0.dp
                    ),
                )
            Box(modifier=Modifier
                .offset(x=0.dp, y=15.dp)
                ){
                Row{
                    val mypets = listOf("아토", "별", "Espresso")
                    Demo_ExposedDropdownMenuBox(mypets = mypets)

                    Text(
                        text = "의 건강기록",
                        color = Color(0xff2b2b2b),
                        lineHeight = 1.4.em,
                        style = TextStyle(
                            fontSize = 20.sp),
                        modifier = modifier
                            .offset(y=30.dp))
                }
            }

            Box(modifier=Modifier
                .offset(x=15.dp, y=0.dp)
                .padding(20.dp)){
                Column ( verticalArrangement = Arrangement.Center,) {
                    riceiconCode(
                    )

                    Row (modifier=Modifier
                        .padding(6.dp)
                        ){
                        skinCode()
                        eyeCode(modifier=Modifier
                            .offset(x=7.dp, y=0.dp))
                    }
                }

            }


            var selectedTabIndex by remember { mutableStateOf(0) }

            CustomTabRow(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { newIndex ->
                    selectedTabIndex = newIndex
                }
            )


        }
    }

}



@Composable
fun EyeContentLayout(
    eyeConditions: String,
    eyeDate: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        modifier = modifier
            .requiredWidth(width = 327.dp)
            .background(color = Color.White)
            .padding(all = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 295.dp)
                .requiredHeight(height = 32.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 114.dp)
                    .requiredHeight(height = 32.dp)
            ) {
                Text(
                    text = "눈 촬영 결과",
                    color = Color(0xff426cb4),
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "총 02가지 안구질환",
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.14.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 16.dp
                        )
                )
            }
            Text(
                text = eyeDate,
                color = Color(0xffb3b3b3),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 248.dp,
                        y = 8.dp
                    )
            )
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = 295.dp)
                .requiredHeight(height = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 271.dp,
                        y = 0.dp
                    )
                    .requiredSize(size = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 24.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color.White)
                        .border(
                            border = BorderStroke(1.dp, Color(0xffb1c5cd)),
                            shape = CircleShape
                        ))

            }
            Text(
                text = eyeConditions,
                color = Color(0xff2b2b2b),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp
                )
            )
        }
    }
}

@Composable
fun EatContentLayout(
    foodName: String,
    foodAmount: String,
    foodDate: String,
    foodTime: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        modifier = Modifier
            .requiredWidth(width = 327.dp)
            .background(color = Color.White)
            .padding(all = 16.dp)
    ) {
        Text(
            text = foodName,
            color = Color(0xff2b2b2b),
            lineHeight = 1.14.em,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Box(
            modifier = Modifier
                .requiredWidth(width = 295.dp)
                .requiredHeight(height = 44.dp)
        ) {
            Text(
                text = "사료 양",
                color = Color(0xffb3b3b3),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
            Text(
                text = foodAmount,
                color = Color(0xff2b2b2b),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 20.dp
                    )
            )
            Text(
                text = foodDate,
                color = Color(0xff2b2b2b),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 100.dp,
                        y = 20.dp
                    )
            )
            Text(
                text = foodTime,
                color = Color(0xff2b2b2b),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 222.dp,
                        y = 20.dp
                    )
            )
            Text(
                text = "사료 급여일",
                color = Color(0xffb3b3b3),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 100.dp,
                        y = 0.dp
                    )
            )
            Text(
                text = "사료 급여 시간",
                color = Color(0xffb3b3b3),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 222.dp,
                        y = 0.dp
                    )
            )
        }
    }
}
@Composable
fun CustomTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xff426cb4),
                )
            }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = {
                    onTabSelected(0)
                },
                text = {
                    Text(text = "사료 기록", color = if (selectedTabIndex == 0) Color(0xff426cb4) else Color.Gray)
                }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = {
                    onTabSelected(1)
                },
                text = {
                    Text(text = "눈 기록", color = if (selectedTabIndex == 1) Color(0xff426cb4) else Color.Gray)
                }
            )
            Tab(
                selected = selectedTabIndex == 2,
                onClick = {
                    onTabSelected(2)
                },
                text = {
                    Text(text = "피부 기록", color = if (selectedTabIndex == 2) Color(0xff426cb4) else Color.Gray)
                }
            )
        }

        when (selectedTabIndex) {
            0 -> {
                // Display content for "사료 기록" tab
                EatContentLayout(
                    foodName = "사료이름",
                    foodAmount = "200g",
                    foodDate = "23.08.27",
                    foodTime = "13:50"
                )
            }
            1 -> {
                // Display content for "눈 기록" tab

                EyeContentLayout(
                eyeConditions = "백내장.체리아이",
                eyeDate = "23.08.27"
                )

            }
            2 -> {
                SkinContentsLayout(
                    skinDate = "23.08.27",
                    skinConditions = "탈모,핫스팟피부염,아토피")
            }
        }
    }
}

@Composable
fun SkinContentsLayout(
    skinDate: String,
    skinConditions: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        modifier = Modifier
            .requiredWidth(width = 327.dp)
            .background(color = Color.White)
            .padding(all = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 295.dp)
                .requiredHeight(height = 32.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 114.dp)
                    .requiredHeight(height = 32.dp)
            ) {
                Text(
                    text = "피부 촬영 결과",
                    color = Color(0xff426cb4),
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "총 03가지 피부질환",
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.14.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 16.dp
                        )
                )
            }
            Text(
                text = skinDate,
                color = Color(0xffb3b3b3),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 248.dp,
                        y = 8.dp
                    )
            )
        }
        Text(
            text = skinConditions,
            color = Color(0xff2b2b2b),
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp
            )
        )
    }
}

@Preview
@Composable
private fun GeneratedCodePreview() {
    health_record(Modifier)
}