package com.haneum.petconnect.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.compose.AppTheme
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.MainActivity
import com.haneum.petconnect.PostDetailActivity
import com.haneum.petconnect.SelectableButton
import com.haneum.petconnect.data.Board
import com.haneum.petconnect.databinding.FragmentCommunityBinding
import com.haneum.petconnect.ui.InfiniteScrollList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import java.util.Date

class CommunityFragment() : Fragment() {

    private var _binding : FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var writePostFragment: WritePostFragment
    private lateinit var auth : FirebaseAuth
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommunityBinding.inflate(inflater,container,false)

        val view = binding.root
        val db = Firebase.firestore
        val query = db.collection("board")
            .orderBy("created_date",Query.Direction.DESCENDING)
            .limit(5)
        var result = mutableListOf<Board>()
        CoroutineScope(Dispatchers.Main).launch {
            val tasks = query.get().await()
            for(task in tasks){
                result.add(task.toObject(Board::class.java))
            }
            binding.composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    var prevTime by remember{
                        mutableStateOf(result.last().created_date)
                    }
                    var filter by remember {
                        mutableStateOf("")
                    }
                    AppTheme() {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text(text = "커뮤니티")},
                                    actions = {
                                        IconButton(onClick = {  }) {
                                            Icon(Icons.Default.Search,null)
                                        }
                                    }
                                )
                            }
                        ) {
                            Surface(modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CategoryFilter(
                                        clickItem = {category -> filter = category},
                                        modifier = Modifier.padding(3.dp)
                                    )
                                    InfiniteScrollList(
                                        boards = result,
                                        loadMoreItems = {
                                            CoroutineScope(Dispatchers.Main).launch {
                                                val data = loadMoreItems(prevTime = prevTime)
                                                if(data.isNotEmpty()){
                                                    prevTime = data.last().created_date
                                                    result.addAll(result.lastIndex, data)
                                                }
                                            }
                                        },
                                        filter = filter,
                                        onPostClick = {post ->
                                            val intent = Intent(mContext, PostDetailActivity::class.java)
                                            intent.putExtra("post",post)
                                            startActivity(intent)
                                        }
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        writePostFragment = WritePostFragment()
        auth = FirebaseAuth.getInstance()
        binding.fbtWrite.setOnClickListener{
            val activity = activity as MainActivity?
            activity?.setVisibility(View.GONE)
            activity?.changeFragment(writePostFragment)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommunityFragment().apply {
                arguments = Bundle().apply {
                    putString("", param1)
                    putString("", param2)
                }
            }
    }

    private suspend fun loadMoreItems(prevTime: Timestamp): MutableList<Board> {
        val db = Firebase.firestore
        val query = db.collection("board")
            .orderBy("created_date", Query.Direction.DESCENDING)
            .limit(5)
            .startAfter(prevTime)
        return query.get().await().toObjects(Board::class.java).toMutableList()
    }

    @Composable
    fun CategoryFilter(
        modifier: Modifier = Modifier,
        clickItem: (String) -> Unit
    ){
        var selectedIndex by remember { mutableStateOf(0) }
        val onItemClick = { index: Int -> selectedIndex = index}
        LazyRow(
            modifier = modifier
        ){
            itemsIndexed(listOf("정보공유해요", "고민있어요", "자랑해요", "기타")){index, item ->
                SelectableButton(
                    index = index,
                    selected = selectedIndex == index,
                    onClick = onItemClick,
                    text = {Text(item)},
                    modifier = Modifier.wrapContentSize(),
                    shape = CircleShape,
                    item = item,
                    clickItem = clickItem
                )
                Spacer(modifier = Modifier.size(5.dp))
            }
        }
    }
}
