package com.haneum.petconnect.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.haneum.petconnect.MainActivity
import com.haneum.petconnect.R
import com.haneum.petconnect.WriteUsedTradePostActivity
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.data.UsedTradePost
import com.haneum.petconnect.databinding.FragmentCommunityBinding
import com.haneum.petconnect.databinding.FragmentUsedTradeBinding
import com.haneum.petconnect.ui.Post
import com.haneum.petconnect.ui.TakePictureActivity
import com.haneum.petconnect.ui.UsedTradePostCard
import com.haneum.petconnect.ui.theme.md_theme_dark_onPrimary
import com.haneum.petconnect.ui.theme.md_theme_light_onSecondary
import com.haneum.petconnect.ui.theme.md_theme_light_primary
import com.haneum.petconnect.ui.theme.md_theme_light_secondary

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsedTradeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsedTradeFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                UsedTradeScreen(
                    writePost = {
                        val intent = Intent(mContext, WriteUsedTradePostActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsedTradeScreen(
    writePost: () -> Unit
){
    var showSheet by remember { mutableStateOf(false) }
    var category by remember {
        mutableStateOf("사료")
    }
    var isFabClicked by remember {
        mutableStateOf(false)
    }

    if (showSheet) {
        CategoryModalSheet(
            selectCategory = { item ->
                category = item
                showSheet = false
            }
        ) {
            showSheet = false
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "")},
                navigationIcon = {
                    Text(category, modifier = Modifier.clickable { showSheet = true })
                }
            )
        },
        floatingActionButton = {
            Column() {
                if(isFabClicked){
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = md_theme_light_primary,
                        shape = RectangleShape,
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Outlined.AddCircle, null)
                    }
                    FloatingActionButton(
                        onClick = { writePost() },
                        containerColor = md_theme_light_primary,
                        shape = RectangleShape,
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Outlined.Create, null)
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                FloatingActionButton(
                    onClick = { isFabClicked = !isFabClicked },
                    shape = CircleShape,
                    containerColor = (if(isFabClicked){
                        Color.White
                    }else{
                        md_theme_light_primary
                    }),
                    contentColor = (if(isFabClicked){
                        md_theme_light_primary
                    }else{
                        Color.White
                    })
                ) {
                    Icon(Icons.Outlined.MoreVert,null)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.padding(10.dp)
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column() {
                SearchField()
                InfiniteScrollUsedTrade(
                    posts = listOf(UsedTradePost()),
                    loadMoreItems = { /*TODO*/ },
                    onPostClick = {  },
                    filter = ""
                )
            }
            
            
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryModalSheet(
    selectCategory: (String) -> Unit,
    onDismiss: () -> Unit
){
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CategoryList(selectCategory)
    }
}

@Composable
fun CategoryList(
    selectCategory: (String) -> Unit
){
    val category = listOf("사료","간식","장난감","하우스","의류","급식기/급수기","미용/관리","배변용품")

    LazyColumn{
        items(category){ item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .clickable { selectCategory(item) }
            ) {
                Text(text = item)
            }
        }
    }
}

@Composable
fun SearchField(){
    var contentField by remember {
        mutableStateOf("")
    }
    BasicTextField(
        value = contentField,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
        ,
        onValueChange = {
            contentField = it
        },
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                if (contentField.isEmpty()) {
                    Text(
                        text = "원하시는 키워드를 검색해보세요!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
                innerTextField.invoke()
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(Icons.Outlined.Search, null, tint = md_theme_light_primary)
                }
            }
        }
    )
}

@Composable
fun InfiniteScrollUsedTrade(
    posts: List<UsedTradePost>,
    loadMoreItems: () -> Unit,
    onPostClick: (UsedTradePost) -> Unit,
    filter: String
) {

    val listState = rememberLazyListState()


    LazyColumn(
        state = listState,
        content = {
            items(posts){item ->
                if(item.category == filter || filter == ""){
                    UsedTradePostCard(
                        post = item,
                        onPostClick = {onPostClick(item)}
                    )
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.5f),
                        thickness = 4.dp
                    )
                }
            }
            if(listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= posts.size -1){
                loadMoreItems()
            }
        }
    )
}