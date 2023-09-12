package com.haneum.petconnect.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.ui.theme.shapes

class WritePostFragment() : Fragment() {

    private lateinit var mContext: Context
    private lateinit var post: Board
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as MainActivity?
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser!!
        //뒤로가기 버튼 누를시 커뮤니티 프라그먼트로 변경
        val callback = requireActivity().onBackPressedDispatcher.addCallback(
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
                WritePostScreen()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post = Board(1,"1","","", Timestamp(java.util.Date()),"")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun WritePostScreen(){
        AppTheme() {
            Surface(tonalElevation = 5.dp) {
                Column(
                    modifier = Modifier.
                            padding(10.dp)
                ) {
                    Row() {
                        Text(text = "글쓰기")
                        TextButton(
                            onClick = {writePost()},
                            modifier = Modifier.wrapContentSize()
                            ) {
                            Text(text = "완료")
                        }
                    }

                    val itemList = listOf("고민거리", "중고거래", "건강정보", "기타")
                    var selectedItem by remember { mutableStateOf(itemList[0]) } // Default       value
                    var contentField by remember { mutableStateOf("") }
                    var titleField by remember { mutableStateOf("") }

                    MySpinner(
                        items = itemList,
                        selectedItem = selectedItem
                    ) {
                        selectedItem = it
                        updateCategory(it)
                    }
                    Spacer(modifier = Modifier
                        .height(5.dp)
                        .width(5.dp))
                    TextField(
                        value = titleField,
                        onValueChange = {
                            titleField = it
                            updateTitle(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = { Text(text = "제목")},
                        shape = shapes.medium
                    )
                    Spacer(modifier = Modifier
                        .height(5.dp)
                        .width(5.dp))
                    TextField(
                        value = contentField,
                        onValueChange = {
                            contentField = it
                            updateContent(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .border(
                                BorderStroke(1.dp, Color.Black)
                            ),
                        placeholder = { Text(text = "내용을 입력하세요")},
                        shape = shapes.medium
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun defaultPreview(){
        WritePostScreen()
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
            selectedItemFactory = { modifier, item ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                        .padding(8.dp)
                        .wrapContentSize()
                ) {
                    Text(item, modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
                        contentDescription = "drop down arrow"
                    )
                }
            },
            dropdownItemFactory = { item, _ ->
                Text(text = item)
            }
        )
    }

    private fun writePost(){
        val db = Firebase.firestore
        db.collection("board").count().get(AggregateSource.SERVER).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                post.board_id = task.result.count.toInt()
                post.user_id = user.uid
                db.collection("board").document(post.board_id.toString()).set(post).addOnCompleteListener{ task2 ->
                    if(task2.isSuccessful){
                        Toast.makeText(mContext,"작성 완료",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(mContext,"작성 실패 by set", Toast.LENGTH_SHORT).show()
                    }
                    goBack(mActivity!!)
                }
            }else{
                Toast.makeText(mContext,"작성 실패 by 카운트", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun updateContent(content: String){
        post.contents = content
    }
    private fun updateTitle(title: String){
        post.title = title
    }
    private fun updateCategory(category: String){
        post.category = category
    }
    private fun goBack(mActivity: MainActivity){
        mActivity.changeFragment(CommunityFragment.newInstance("",""))
        mActivity.setVisibility(View.VISIBLE)
    }
}