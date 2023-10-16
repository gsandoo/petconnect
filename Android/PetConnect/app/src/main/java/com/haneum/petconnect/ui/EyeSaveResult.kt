@file:JvmName("EyeResultActivityKt")

package com.haneum.petconnect.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haneum.petconnect.R

@Composable
fun SavedScreen(modifier: Modifier = Modifier, name: String) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 145.dp,
                    y = 478.dp
                )
                .requiredWidth(width = 90.dp)
                .requiredHeight(height = 13.dp)
                .background(color = Color(0xff72b9ef).copy(alpha = 0.2f)))
        Text(
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xff426cb4),
                    fontSize = 24.sp)
                ) {append("저장했어요.\n\n")}
                withStyle(style = SpanStyle(
                    color = Color.DarkGray,
                    fontSize = 18.sp)) {append("기록은 "+name+"촬영 기록에서\n언제든 확인할 수 있어요")}},
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 3.dp,
                    y = 410.dp
                )
                .fillMaxHeight()
                .requiredWidth(width = 328.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 269.dp,
                    y = 290.dp
                )
                .requiredSize(size = 12.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xfff3e58d)))
        Box(
            modifier = Modifier.run {
                align(alignment = Alignment.TopStart)
                    .offset(
                        x = 40.dp,
                        y = 298.dp
                    )
                    .requiredSize(size = 9.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffc76d69))
            })
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 292.dp,
                    y = 349.dp
                )
                .requiredSize(size = 9.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xff72b9ef)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 72.dp,
                    y = 328.dp
                )
                .requiredSize(size = 5.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xff72b9ef)))
        Image(
            painter = painterResource(id = R.drawable.save_icon),
            contentDescription = "Group 13",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 70.dp,
                    y = 277.dp
                )
                .requiredSize(size = 10.dp))
        Image(
            painter = painterResource(id = R.drawable.save_icon),
            contentDescription = "Group 14",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 313.dp,
                    y = 302.dp
                )
                .requiredSize(size = 10.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 149.dp,
                    y = 331.dp
                )
                .requiredWidth(width = 59.dp)
                .requiredHeight(height = 59.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.save_icon),
                contentDescription = "Union",
                modifier = Modifier
                    .requiredWidth(width = 59.dp)
                    .requiredHeight(height = 59.dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 12.dp,
                        y = 23.dp
                    )
                    .requiredWidth(width = 34.dp)
                    .requiredHeight(height = 13.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 13.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color.White))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 6.666015625.dp,
                            y = 6.500732421875.dp
                        )
                        .requiredSize(size = 5.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff426cb4)))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 21.dp,
                            y = 0.dp
                        )
                        .requiredSize(size = 13.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color.White))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 27.333984375.dp,
                            y = 6.500732421875.dp
                        )
                        .requiredSize(size = 5.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff426cb4)))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 177.dp,
                    y = 348.dp
                )
                .requiredWidth(width = 34.dp)
                .requiredHeight(height = 35.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 25.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.White.copy(alpha = 0.3f))
                    .border(
                        border = BorderStroke(1.dp, Color(0xff72b9ef)),
                        shape = CircleShape
                    ))
            Image(
                painter = painterResource(id = R.drawable.save_icon),
                contentDescription = "Vector 490",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 20.9853515625.dp,
                        y = 20.9853515625.dp
                    )
                    .requiredSize(size = 6.dp)
                    .border(border = BorderStroke(2.dp, Color(0xff72b9ef))))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 25.2802734375.dp,
                        y = 25.41650390625.dp
                    )
                    .requiredWidth(width = 8.dp)
                    .requiredHeight(height = 5.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .rotate(degrees = -47.73f)
                    .background(color = Color(0xff72b9ef))
                    .border(
                        border = BorderStroke(1.dp, Color(0xff72b9ef)),
                        shape = RoundedCornerShape(5.dp)
                    ))
        }
    }
}
