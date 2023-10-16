package com.haneum.petconnect.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.haneum.petconnect.data.Board
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun Post(
    modifier: Modifier = Modifier,
    post: Board,
    onPostClick: () -> Unit
){
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = post.created_date.toDate()
    val strDate = dateFormat.format(date)
    Column(
        modifier = modifier
    ) {
        var isSelected by remember {
            mutableStateOf(true)
        }
        Text(text = post.category)
        if(post.picture != 0){
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.height(100.dp)
            ){
               items(post.pictureName){item ->
                   AsyncImage(
                       model = item,
                       contentDescription = null,
                       contentScale = ContentScale.Crop)
               }
            }
        }
        Text(text = post.contents,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable {
                onPostClick()
            }
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = post.like.toString()
                )
                Text(
                    text = post.comments.toString()
                )
            }
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = intervalBetweenDateText(strDate)
            )
        }
    }
}

/** 현재시간 구하기 ["yyyy-MM-dd HH:mm:ss"] (*HH: 24시간)*/
fun getTime(): String {
    var now = System.currentTimeMillis()
    var date = Date(now)

    var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    return dateFormat.format(date)
}

/** 두 날짜 사이의 간격 계산해서 텍스트로 반환 */
fun intervalBetweenDateText(beforeDate: String): String {
    val nowFormat = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getTime())
    val beforeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(beforeDate)

    val diffMilliseconds = nowFormat.time - beforeFormat.time
    val diffSeconds = diffMilliseconds / 1000
    val diffMinutes = diffMilliseconds / (60 * 1000)
    val diffHours = diffMilliseconds / (60 * 60 * 1000)
    val diffDays = diffMilliseconds / (24 * 60 * 60 * 1000)

    val nowCalendar = Calendar.getInstance().apply { time = nowFormat }
    val beforeCalendar = Calendar.getInstance().apply { time = beforeFormat }

    val diffYears = nowCalendar.get(Calendar.YEAR) - beforeCalendar.get(Calendar.YEAR)
    var diffMonths = diffYears * 12 + nowCalendar.get(Calendar.MONTH) - beforeCalendar.get(Calendar.MONTH)
    if (nowCalendar.get(Calendar.DAY_OF_MONTH) < beforeCalendar.get(Calendar.DAY_OF_MONTH)) {
        // 현재 날짜의 일(day) 값이 이전 날짜의 일(day) 값보다 작은 경우에만 월 차이를 1 감소시킴
        // Ex) 5.31일과 6.2일은 2일차이지만 month가 1로 계속 나오는 이슈해결을 위해서
        diffMonths--
    }

    if (diffYears > 0) {
        return "${diffYears}년 전"
    }
    if (diffMonths > 0) {
        return "${diffMonths}개월 전"
    }
    if (diffDays > 0) {
        return "${diffDays}일 전"
    }
    if (diffHours > 0) {
        return "${diffHours}시간 전"
    }
    if (diffMinutes > 0) {
        return "${diffMinutes}분 전"
    }
    if(diffSeconds > -1){
        return "방금"
    }
    return ""
}