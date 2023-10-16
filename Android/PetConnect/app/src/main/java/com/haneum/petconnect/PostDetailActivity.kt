package com.haneum.petconnect

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.data.Comments
import com.haneum.petconnect.data.UserAccount
import com.haneum.petconnect.ui.Comment
import com.haneum.petconnect.ui.intervalBetweenDateText
import com.haneum.petconnect.ui.theme.md_theme_light_primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID


class PostDetailActivity : ComponentActivity() {
    private lateinit var post: Board

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        post = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("post", Board::class.java)!!
        } else {
            intent.getParcelableExtra<Board>("post")!!
        }
        setContent {
            PostDetailScreen(back = { finish() }, post = post, like = {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    back: () -> Unit,
    like: (Boolean) -> Unit,
    post: Board
){

    val comments = mutableStateListOf<Comments>()
    var isLiked by remember {
        mutableStateOf(false)
    }
    var isComment by remember {
        mutableStateOf(post.comments)
    }
    val db = Firebase.firestore
    val auth = Firebase.auth
    val user = auth.currentUser!!
    if(isComment != 0){
        CoroutineScope(Dispatchers.IO).launch {
            val result = db.collection("comment").whereEqualTo("board_id", getBoardId(post.created_date,post.user_id))
                .get().await().toObjects(Comments::class.java)
            for(data in result){
                comments.add(data)
            }
        }
    }

    AppTheme() {
        Scaffold(
            topBar = {
                androidx.compose.material3.TopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = { back() }) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    }
                )
            },
            bottomBar = {
                MessageBox(sendComment = {content ->
                    val submit = Comments(
                        board_id = getBoardId(post.created_date,post.user_id),
                        user_id = user.uid,
                        comment_contents = content,
                        user_nickname = "blisian",
                        profile_path = "",
                        comment_create = Timestamp.now(),
                        comment_id = UUID.randomUUID().toString()
                    )
                    db.collection("comment").add(submit)
                    db.collection("board").document(getBoardId(post.created_date, post.user_id))
                        .update("comments", FieldValue.increment(1))
                    comments.add(submit)
                    isComment += 1
                })
            }
        ) {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    PostInfo(
                        post,
                        delete = {},
                        fix = {},
                        report = {}
                    )
                    PostContent(post = post, save = {})
                    Divider(thickness = 1.dp, color = Color.LightGray)
                    if(isComment != 0){
                        CommentScreen(comments = comments)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostInfo(
    post: Board,
    delete: () -> Unit,
    fix: () -> Unit,
    report: () -> Unit
){
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val storage = Firebase.storage
    val db = Firebase.firestore
    var userInfo by remember {
        mutableStateOf(UserAccount())
    }
    val date = post.created_date.toDate()
    val strDate = dateFormat.format(date)
    var isDropDownExpanded by remember {
        mutableStateOf(false)
    }
    CoroutineScope(Dispatchers.IO).launch {
        val result = db.collection("user").whereEqualTo("user_id",post.user_id)
            .get().await()
        for(data in result){
            userInfo = data.toObject(UserAccount::class.java)
            Log.d("debug", userInfo.nickname)
        }
    }
    androidx.compose.material3.TopAppBar(
        title = {
            Row() {
                Icon(painter = painterResource(id = R.drawable.group_2607973), contentDescription = null)
                Column {
                    Text(text = userInfo.nickname)
                    Text(text = intervalBetweenDateText(strDate))
                }
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
                if(userInfo.user_id == post.user_id){
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

@Composable
fun PostContent(
    post: Board,
    save: (Boolean) -> Unit
){
    var isSaved by remember {
        mutableStateOf(false)
    }
    Column() {
        Text(text = post.contents,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Outlined.Favorite, contentDescription = null)
                Text("공감"+post.like.toString())
                Icon(Icons.Outlined.Email, contentDescription = null)
                Text("댓글"+post.comments.toString())
            }
            IconButton(
                onClick = { save(isSaved) },
                modifier = Modifier.padding(5.dp)
            ) {
                if(isSaved){
                    Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
                }else{

                }
            }
        }
    }
}


@Composable
internal fun MessageBox(
    sendComment: (String) -> Unit
) {
    var msg by rememberSaveable { mutableStateOf("") }
    var isLiked by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if(!isLiked){
            IconButton(onClick = { isLiked = true}) {
                Icon(Icons.Outlined.Favorite, contentDescription = null,)
            }
        }else{
            IconButton(onClick = { isLiked = false}) {
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = md_theme_light_primary)
            }
        }
        BasicTextField(
            value = msg,
            onValueChange = { msg = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = androidx.compose.ui.text.input.ImeAction.Default
            ),
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(8.dp)
                ) {
                    innerTextField()
                }
            }
        )
        IconButton(
            onClick = {
                sendComment(msg)
                msg = ""
            },
            enabled = msg.isNotBlank()
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
        }
    }
}

@Composable
fun CommentScreen(
    comments: List<Comments>
){
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        content = {
            items(comments){comment ->
                Comment(comment = comment, delete = {}, fix = {}, report = {})
            }
        }
    )
}

@SuppressLint("SimpleDateFormat")
fun getBoardId(createTime: Timestamp, userId: String): String{
    return SimpleDateFormat("yyyyMMddHHmmss").format(createTime.toDate()).toString()+"_"+userId
}