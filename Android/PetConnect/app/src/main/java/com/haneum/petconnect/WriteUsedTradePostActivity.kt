package com.haneum.petconnect

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.data.UsedTradePost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Date

class WriteUsedTradePostActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        super.onCreate(savedInstanceState)
        setContent {
            WriteUsedTradePostScreen(
                back = { finish() }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WriteUsedTradePostScreen(
        back: () -> Unit
    ){
        var selectedImage: List<Uri> = listOf()
        var imageSelected by remember {
            mutableStateOf(false)
        }
        var isGiven by remember {
            mutableStateOf(false)
        }
        var isLoading by remember {
            mutableStateOf(false)
        }
        var post = UsedTradePost()
        val multipleImagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = {uris ->
                if(uris.size in 1..5){
                    selectedImage = uris
                    imageSelected = true
                }else {
                    // Toast.makeText(this, "사진을 다시 선택해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        )

        if(isLoading){
            PendingScreen()
        }else{
            Scaffold(
                topBar = {
                    androidx.compose.material3.TopAppBar(
                        title = { Text(text = "글쓰기") },
                        navigationIcon = {
                            IconButton(onClick = { back() }) {
                                Icon(Icons.Default.ArrowBack, null)
                            }
                        },
                        actions = {
                            Text(text = "작성",
                                modifier = Modifier.clickable {
                                    isLoading = true
                                    CoroutineScope(Dispatchers.Main).launch{
                                        withContext(Dispatchers.Main){
                                            writeUsedTradePost(post, selectedImage)
                                        }
                                        back()
                                    }
                                })
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar() {

                    }
                }
            ) {
                Surface(
                    modifier = Modifier.padding(it)
                ) {
                    Column() {
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 4.dp,
                                    spotColor = Color(0x1F000000),
                                    ambientColor = Color(0x1F000000)
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFB1C5CD),
                                    shape = RoundedCornerShape(size = 12.dp)
                                )
                                .padding(1.dp)
                                .width(153.dp)
                                .height(153.dp)
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(size = 12.dp)
                                )
                                .clickable {
                                    multipleImagePickerLauncher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                }
                                .clip(RoundedCornerShape(12.dp))
                        ){
                            Column(
                                modifier = Modifier.align(Alignment.Center)
                            ) {
                                if (!imageSelected){
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                                        contentDescription = "image description",
                                        tint =  Color(0xFF426CB4),
                                    )
                                    Text(
                                        text = "프로필 등록",
                                        // Body3
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            lineHeight = 16.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_black)),
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF426CB4),
                                        )
                                    )
                                } else {
                                    AsyncImage(
                                        model = selectedImage[0],
                                        contentDescription = null
                                    )
                                }
                            }
                        }

                        Text(text = "제목")
                        Row {
                            Text(text = "판매 가격 설정")
                            Checkbox(checked = isGiven, onCheckedChange = {isGiven = !isGiven})
                            Text(text = "나눔해요")
                        }
                        Text(text = "카테고리 설정")
                        Text(text = "판매 용품 설명")
                    }
                }
            }
        }
    }


    private suspend fun writeUsedTradePost(post: UsedTradePost, photos: List<Uri>){
        val db = Firebase.firestore
        val nowTime = Timestamp(Date())
        val fileName = getBoardId(nowTime, user.uid)
        var downloadUrl = listOf<String>()

        if(post.picture != 0){
            coroutineScope {
                withContext(Dispatchers.Default){
                    downloadUrl = uploadImage(uris = photos, fileName)
                }
            }
        }
        post.pictureName = downloadUrl
        if(post.contents != "" || post.category != ""){
            post.user_id = user.uid
            post.created_date = nowTime
            db.collection("used_trade").document(fileName).set(post).addOnCompleteListener{ task2 ->
                if(task2.isSuccessful){
                    Toast.makeText(this,"작성 완료",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"작성 실패 by set", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun uploadImage(uris: List<Uri>, fileName: String): List<String>{
        val storage = Firebase.storage
        var downloadUrl: MutableList<String> = mutableListOf()

        for((index, uri) in uris.withIndex()){
            downloadUrl.add(index, storage.reference.child("image/used_trade").child(fileName).child(index.toString())
                .putFile(uri).await()
                .storage.downloadUrl.await().toString())
        }
        return downloadUrl
    }
}
