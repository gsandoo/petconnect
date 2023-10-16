package com.haneum.petconnect.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.haneum.petconnect.data.UsedTradePost
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun UsedTradePostCard(
    post: UsedTradePost,
    onPostClick: () -> Unit
){
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = post.created_date.toDate()
    val strDate = dateFormat.format(date)

    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if(post.pictureName.isNotEmpty()){
                AsyncImage(model = post.pictureName[0], contentDescription = null,contentScale = ContentScale.Crop)
            }
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = post.title)
                Text(text = post.category)
                Box(){
                    Row(
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(Icons.Outlined.Favorite, contentDescription = null)
                        Text(text = post.like.toString())
                        Icon(Icons.Outlined.AddCircle, contentDescription = null)
                        Text(text = post.chat.toString())
                    }
                    Text(text = post.location, color = Color.LightGray, modifier = Modifier.align(
                        Alignment.CenterEnd
                    ))
                }

            }
        }
    }
}

