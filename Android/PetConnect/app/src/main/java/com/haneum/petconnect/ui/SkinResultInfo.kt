package com.haneum.petconnect.ui

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.haneum.petconnect.R


@Composable
fun itemSkin(modifier: Modifier, itemNumber: Int, itemText: String){
    Row(){
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(2.dp)){
            Box(
                modifier = modifier
                    .requiredSize(size = 20.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff72b9ef)))
            Text(
                text = itemNumber.toString(),
                color = Color.White,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold)
            )
            modifier.padding(2.dp)
        }

        Text(
            text = itemText,
            color = Color.DarkGray,
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp)
        )
    }

}


//증상 설명
@Composable
fun Skin_symptoms(modifier: Modifier=Modifier,
                      diseaseName: String = "백내장" ){
    Column (modifier=Modifier.padding(10.dp, bottom = 30.dp)){
        Text(
            text = "$diseaseName 증상",
            color = Color(0xff2b2b2b),
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
        )
        Column {
            itemSkin(
                modifier = Modifier,
                itemNumber = 1,
                itemText = "눈을 자주 문지름"
            )

            itemSkin(
                modifier = Modifier,
                itemNumber = 2,
                itemText = "다양한 과일 섭취"
            )
        }

    }
}

@Composable
fun contentContextSkin(
    modifier: Modifier=Modifier,
    diseaseCause: String,
    dogDescription: String,
    diseaseName: String = "탈모"
) {
    Column(modifier=Modifier.padding(10.dp, bottom = 30.dp)){
        Text(
            text = "$diseaseName 원인",
            color = Color(0xff2b2b2b),
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold),
        )
        Text(
            text = diseaseCause, // 사용자가 입력한 백내장 원인
            color = Color.DarkGray,
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp),

            )
    }
    Column(modifier=Modifier.padding(10.dp, bottom = 30.dp)) {
        Text(
            text = "$diseaseName 관리방법",
            color = Color(0xff2b2b2b),
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold),
        )

        Text(
            text = dogDescription, // 사용자가 입력한 강아지 설명
            color = Color.DarkGray,
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp),

            )
    }

}

@Composable
fun toplogoSkin( modifier: Modifier = Modifier,
             diseaseName: String,
             diseaseDescription: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 139.dp)
            .background(color = Color(0xff426cb4))
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 233.dp,
                    y = 9.dp
                )
                .requiredWidth(width = 141.dp)
                .requiredHeight(height = 98.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.skin_icon),
                contentDescription = "Vector 490",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 23.251953125.dp,
                        y = 0.7266845703125.dp
                    )
                    .requiredSize(size = 150.dp)
            )

        }
        Text(
            text = diseaseDescription, // 사용자로부터 받은 질병 설명을 사용
            color = Color.White,
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 14.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 17.dp,
                    y = 74.dp
                )
                .requiredWidth(width = 327.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 17.dp,
                    y = 18.dp
                )
                .clip(shape = RoundedCornerShape(21.dp))
                .background(color = Color.White)
                .padding(
                    horizontal = 12.dp,
                    vertical = 4.dp
                )
        ) {
            Row(modifier = Modifier){
                Image(
                    painter = painterResource(id = R.drawable.component_1),
                    contentDescription = "Vector 490",
                    modifier = Modifier
                        .requiredSize(size = 20.dp)
                        .offset(y = 3.dp)
                )
                Text(
                    text = diseaseName,
                    color = Color(0xff2b2b2b),
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold)
                )
            }

        }
    }
}
@Composable
fun logoCodeSkin(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth(),  contentAlignment = Alignment.Center){
        Box(
            modifier = modifier
                .requiredWidth(width = 327.dp)
                .requiredHeight(height = 76.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xff72b9ef))
                .border(
                    border = BorderStroke(2.dp, Color(0xff72b9ef)),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = "다른 반려가족들의 건강관리가 궁금하신가요?",
                color = Color(0xff426cb4),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 16.dp
                    ))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 39.dp
                    )
                    .clip(shape = RoundedCornerShape(21.dp))
                    .background(color = Color(0xff72b9ef))
                    .border(
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(21.dp)
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = "건강관리 TIP보러가기",
                    color = Color.White,
                    lineHeight = 1.29.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold)
                )
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 10.dp)
                        .requiredHeight(height = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "ic_arrow_back",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .requiredWidth(width = 10.dp)
                            .requiredHeight(height = 10.dp)
                            .rotate(degrees = 180f))
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 260.dp,
                        y = 18.dp
                    )
                    .requiredWidth(width = 40.dp)
                    .requiredHeight(height = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_2607973),
                    contentDescription = "Union",
                    modifier = Modifier
                        .requiredWidth(width = 40.dp)
                        .requiredHeight(height = 40.dp))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 8.1357421875.dp,
                            y = 15.59326171875.dp
                        )
                        .requiredWidth(width = 23.dp)
                        .requiredHeight(height = 9.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 9.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.White))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 4.51953125.dp,
                                y = 4.4072265625.dp
                            )
                            .requiredSize(size = 3.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff426cb4)))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 14.2373046875.dp,
                                y = 0.dp
                            )
                            .requiredSize(size = 9.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.White))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 18.53125.dp,
                                y = 4.4072265625.dp
                            )
                            .requiredSize(size = 3.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff426cb4)))
                }
            }
            Image(
                painter = painterResource(id = R.drawable.vect),
                contentDescription = "Vector",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 283.dp,
                        y = 13.dp
                    )
                    .requiredWidth(width = 32.dp)
                    .requiredHeight(height = 50.dp)
            )
        }
    }

}


// 탈모의 경우
@Composable
fun HairlossCode(modifier: Modifier){
        Column(modifier = modifier.fillMaxSize()) {
            toplogoSkin(
                modifier = Modifier.padding(bottom = 10.dp),
                diseaseName = "핫스팟 피부염",
                diseaseDescription = "투명하게 눈의 건강을 유지하고 있는\n유성체가 혼탁해져서 뿌옇게 변색하여\n결국 빛이 통과되지 못하여 시력이 낮아지는 질병"
            )
            Box() {
                Column {
                    Skin_symptoms(modifier = Modifier.padding(bottom = 30.dp))
                    contentContextSkin(
                        modifier = Modifier.padding(bottom = 50.dp),
                        diseaseName = "핫스팟 피부염",
                        diseaseCause = "가장 많은 이유는 음식이나 환경으로 인한 알레르기성이고 이외에도 벌레물림이나 외부 기생충 화상 또는 화학물질 같은 자극으로 인해 발생해요\n특히 여름철,장마같은 습한 환경에 발병률이 높아서 주의해야해요",
                        dogDescription = "강아지 혀 아래에 주사를 매주 맞거나 몇방울 떨어트리는 방식의 면역요법으로 관리를 하기도 하고,\n\n정기적인 목욕과 외출 후 발을 닦는 것이 핫스팟 피부염 예방에 도움이 될거에요\n\n핫스팟피부염이 발생하면 우선 피부 주변의 털을 조심스럽게 면도하여 깨끗하고 통풍이 잘되는 상태를 유지하는 것이 중요해요\n강아지가 환부를 물거나 핥지 않기위해 발톱정리, 넥카타 착용을 권합니다 ! "    )
                }
            }
            logoCodeSkin(modifier = Modifier)

        }
}

// 핫스팟 피부염의 경우
@Composable
fun HotspotCode(modifier: Modifier){
        Column(modifier = modifier.fillMaxSize()) {
            toplogoSkin(
                modifier = Modifier.padding(bottom = 10.dp),
                diseaseName = "핫스팟 피부염",
                diseaseDescription = "투명하게 눈의 건강을 유지하고 있는\n유성체가 혼탁해져서 뿌옇게 변색하여\n결국 빛이 통과되지 못하여 시력이 낮아지는 질병"
            )
            Box() {
                Column {
                    Skin_symptoms(modifier = Modifier.padding(bottom = 30.dp))
                    contentContextSkin(
                        modifier = Modifier.padding(bottom = 50.dp),
                        diseaseName = "핫스팟 피부염",
                        diseaseCause = "가장 많은 이유는 음식이나 환경으로 인한 알레르기성이고 이외에도 벌레물림이나 외부 기생충 화상 또는 화학물질 같은 자극으로 인해 발생해요\n특히 여름철,장마같은 습한 환경에 발병률이 높아서 주의해야해요",
                        dogDescription = "강아지 혀 아래에 주사를 매주 맞거나 몇방울 떨어트리는 방식의 면역요법으로 관리를 하기도 하고,\n\n정기적인 목욕과 외출 후 발을 닦는 것이 핫스팟 피부염 예방에 도움이 될거에요\n\n핫스팟피부염이 발생하면 우선 피부 주변의 털을 조심스럽게 면도하여 깨끗하고 통풍이 잘되는 상태를 유지하는 것이 중요해요\n강아지가 환부를 물거나 핥지 않기위해 발톱정리, 넥카타 착용을 권합니다 ! "    )
                }
            }
            logoCodeSkin(modifier = Modifier)

        }
}

// 아토피의 경우
@Composable
fun AtopiCode(modifier: Modifier){
        Column(modifier = modifier.fillMaxSize()){
            toplogoSkin(
                modifier = Modifier.padding(bottom=10.dp),
                diseaseName = "체리아이",
                diseaseDescription = "투사람에게는 없는 제3안검이라는 조직의\n 안쪽에 있는 샘이 겉으로 튀어나오는 현상"    )
            Box(){
                Column {
                    Skin_symptoms(diseaseName = "체리아이",modifier=Modifier)
                    contentContextSkin(
                        modifier = Modifier,
                        diseaseName = "체리아이",
                        diseaseCause = "제 3안검에 염증이 생겼거나, 유전적으로 제 3안검의 주위 조직이 느슨해지면서 제 3안검이 제자리에 있지 못하고 눈의 안쪽에 튀어나오면서 생겨요",
                        dogDescription ="체리아이는 안약을 처방받는걸로는 낫지 않는걸로 알려져 있어요 제 3안검의 눈물샘 기능이 손상되기 전에 최대한 빨리 수술을 해주는게 좋아요\n\n3안검의 눈물샘이 손상되면 안구건조증이 유발되고 시력에도 악영향을 줄 수 있기 때문이에요\n\n눈물샘이 영구적으로 손상되지 않았다면 수술 후 눈물샘은 정상기능을 회복할거에요 !"
                    )
                }
            }
            logoCode(modifier=Modifier)
        }
}