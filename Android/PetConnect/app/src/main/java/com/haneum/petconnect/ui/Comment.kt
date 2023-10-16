package com.haneum.petconnect.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.R
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.data.Comments
import com.haneum.petconnect.data.UserAccount
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Comment(
    comment: Comments,
    delete: () -> Unit,
    fix: () -> Unit,
    report: () -> Unit
) {
    val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
    val date = comment.comment_create.toDate()
    val strDate = dateFormat.format(date)
    Column() {
        CommentInfo(
            comment = comment,
            delete = { delete() },
            fix = { fix() },
            report = { report() }
        )
        Text(text = comment.comment_contents)
        Text(text = strDate, style = TextStyle(color = Color.LightGray))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentInfo(
    comment: Comments,
    delete: () -> Unit,
    fix: () -> Unit,
    report: () -> Unit
){
    val auth = Firebase.auth
    val user = auth.currentUser!!
    var isDropDownExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Row() {
                Icon(painter = painterResource(id = R.drawable.group_2607973), contentDescription = null)
                Text(text = comment.user_nickname)
            }
        },
        actions = {
            IconButton(onClick = { isDropDownExpanded = !isDropDownExpanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More",
                )
            }
            DropdownMenu(expanded = isDropDownExpanded, onDismissRequest = { isDropDownExpanded = false }) {
                if(comment.user_id == user.uid){
                    DropdownMenuItem(
                        text = {
                            Text("삭제하기")
                        },
                        onClick = { delete() },
                    )
                    DropdownMenuItem(
                        text = {
                            Text("수정하기")
                        },
                        onClick = { fix() },
                    )
                }else{
                    DropdownMenuItem(
                        text = {
                            Text("신고하기")
                        },
                        onClick = { report() },
                    )
                }
            }
        }
    )
}