package com.haneum.petconnect.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.haneum.petconnect.MainActivity
import com.haneum.petconnect.R
import com.haneum.petconnect.ui.Spinner
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.haneum.petconnect.PendingScreen
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.getBoardId
import com.haneum.petconnect.ui.theme.md_theme_light_primary
import com.haneum.petconnect.ui.theme.shapes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date


class WritePostFragment() : Fragment() {

    private lateinit var mContext: Context
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as MainActivity?
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser!!


        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    goBack(mActivity!!)
                }
            }
            )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                WritePostScreen(
                    back = {
                        goBack(mActivity = mActivity!!)
                    }
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun WritePostScreen(
        back: () -> Unit,
    ){
        var post = Board(
            user_id = user.uid,
            category = "",
            comments = 0,
            like = 0,
            contents = "",
            created_date = Timestamp(Date()),
            picture = 0,
            pictureName = listOf()
        )
        var contentState by remember {
            mutableStateOf(false)
        }
        var categoryState by remember {
            mutableStateOf(false)
        }
        var selectedPhotos by remember {
            mutableStateOf<List<Uri>>(emptyList())
        }
        var isLoading by remember { mutableStateOf(false) }
        val multipleImagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = {uris ->
                post.picture = uris.size
                if(uris.size < 4){
                    selectedPhotos = uris
                }else {
                    Toast.makeText(mContext, "사진은 최대 3장까지만 업로드 할 수 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
        AppTheme {
            if(isLoading){
                PendingScreen()
            }else{
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "글쓰기") },
                            navigationIcon = {
                                IconButton(onClick = back) {
                                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            },
                            actions = {
                                if(contentState && categoryState){
                                    TextButton(onClick = {
                                        isLoading = true
                                        CoroutineScope(Dispatchers.Main).launch{
                                            withContext(Dispatchers.Main){
                                                writePost(post, selectedPhotos)
                                            }
                                            goBack(mActivity!!)
                                        }
                                    }) {
                                        Text(text = "완료", color = md_theme_light_primary)
                                    }
                                }else{
                                    TextButton(onClick = { Toast.makeText(mContext, "내용을 입력해주세요",Toast.LENGTH_SHORT).show() }) {
                                        Text(text = "완료", color = Color.LightGray)
                                    }
                                }
                            }
                        )
                    },
                    containerColor = Color.White,
                ) { innerPadding ->
                    Surface(
                        tonalElevation = 5.dp,
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color = Color.White),
                        color = Color.White
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(color = Color.White)
                        ) {
                            val itemList = listOf("고민거리", "중고거래", "건강정보", "기타")
                            var selectedItem by remember { mutableStateOf(itemList[0]) } // Default       value
                            var contentField by remember { mutableStateOf("") }

                            MySpinner(
                                items = itemList,
                                selectedItem = selectedItem
                            ) {
                                selectedItem = it
                                post.category = it
                                categoryState = true
                            }
                            Spacer(modifier = Modifier
                                .height(5.dp)
                                .width(5.dp))
                            Spacer(modifier = Modifier
                                .height(5.dp)
                                .width(5.dp))
                            BasicTextField(
                                value = contentField,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                                    .padding(bottom = 10.dp),
                                onValueChange = {
                                    contentField = it
                                    post.contents = it
                                    contentState = it.isNotEmpty()
                                },
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                ),
                                decorationBox = { innerTextField ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(
                                                width = 1.dp,
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .padding(5.dp)

                                    ) {
                                        if (contentField.isEmpty()) {
                                            Text(
                                                text = "내용을 입력해주세요",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.LightGray
                                            )
                                        }
                                        innerTextField.invoke()
                                        Text(text = contentField.length.toString()+"/300", modifier = Modifier.align(
                                            Alignment.BottomEnd
                                        ))
                                    }
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = md_theme_light_primary,
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                    .padding(10.dp)
                                    .clickable {
                                        multipleImagePickerLauncher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                                    .fillMaxWidth()
                                ,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(painter = painterResource(id = R.drawable.baseline_photo_camera_24), contentDescription = "camera")
                                Text(
                                    text = "사진 첨부하기",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = md_theme_light_primary
                                )
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                            if(selectedPhotos.isNotEmpty()){
                                LazyRow(
                                    modifier = Modifier.height(100.dp)
                                ){
                                    items(selectedPhotos){item ->
                                        AsyncImage(
                                            model = item,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(100.dp)
                                        )
                                        Spacer(modifier = Modifier.size(5.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun MySpinner(
        items: List<String>,
        selectedItem: String,
        onItemSelected: (String) -> Unit
    ) {
        Spinner(
            modifier = Modifier.wrapContentSize(),
            dropDownModifier = Modifier.wrapContentSize(),
            items = items,
            selectedItem = selectedItem,
            onItemSelected = onItemSelected,
            selectedItemFactory = { modifier, item, isSelected ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                        .padding(12.dp)
                        .wrapContentSize()
                ) {
                    if(isSelected){
                        Text(text = item, color = md_theme_light_primary,  modifier = Modifier.weight(1f))
                    }else{
                        Text(text = "게시판을 선택해주세요", color = Color.LightGray, modifier = Modifier.weight(1f))
                    }
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "drop down arrow",
                        tint = md_theme_light_primary
                    )
                }
            },
            dropdownItemFactory = { item, _ ->
                Text(text = item)
            }
        )
    }

    private suspend fun writePost(post: Board, photos: List<Uri>){
        val db = Firebase.firestore
        val nowTime = Timestamp(Date())
        val fileName = getBoardId(nowTime, user.uid.toString())
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
            db.collection("board").document(fileName).set(post).addOnCompleteListener{ task2 ->
                if(task2.isSuccessful){
                    Toast.makeText(mContext,"작성 완료",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(mContext,"작성 실패 by set", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private suspend fun uploadImage(uris: List<Uri>, fileName: String): List<String>{
        val storage = Firebase.storage
        val db = Firebase.firestore
        var downloadUrl: MutableList<String> = mutableListOf()

        for((index, uri) in uris.withIndex()){
            downloadUrl.add(index, storage.reference.child("image/post").child(fileName).child(index.toString())
                .putFile(uri).await()
                .storage.downloadUrl.await().toString())
        }
        return downloadUrl
    }
    private fun goBack(mActivity: MainActivity){
        mActivity.changeFragment(CommunityFragment.newInstance("",""))
        mActivity.setVisibility(View.VISIBLE)
    }
}