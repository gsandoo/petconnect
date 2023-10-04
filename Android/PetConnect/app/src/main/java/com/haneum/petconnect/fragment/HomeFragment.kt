package com.haneum.petconnect.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import com.haneum.petconnect.MainActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.withConsumedWindowInsets
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.DogCheckActivity
import com.haneum.petconnect.DogInfoActivity
import com.haneum.petconnect.DogRegisterActivity
import com.haneum.petconnect.Main
import com.haneum.petconnect.R
import com.haneum.petconnect.data.DogInfo


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mActivity: MainActivity? = null
    private lateinit var mContext: Context
    private lateinit var dogInfo: MutableList<DogInfo>
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        dogInfo = mutableListOf(DogInfo())
        var cnt: Int = 0
        val db = Firebase.firestore
//        db.collection("nose")
//            .whereEqualTo("user_id", user.uid.toString())
//            .get()
//            .addOnSuccessListener { documents ->
//                for (doc in documents) {
//                    dogInfo[cnt] = DogInfo(
//                        dog_name = doc.data["dog_name"].toString(),
//                        birth = doc.data["birth"].toString(),
//                        profile_path = doc.data["profile_path"].toString(),
//                        height = doc.data["height"].toString(),
//                        weight = doc.data["weight"].toString(),
//                        description = doc.data["description"].toString(),
//                        breed = doc.data["breed"].toString(),
//                        dog_sex = doc.data["dog_sex"].toString()
//                    )
//                    cnt++
//                }
//            }
        dogInfo[0] = DogInfo(
            dog_name = "포메",
            birth = "2020",
            profile_path = "https://firebasestorage.googleapis.com/v0/b/pet-connect-cdc02.appspot.com/o/dog.jpeg?alt=media&token=20aa134f-40cd-4902-8761-9d2143ce2925",
            height = "100cm",
            weight = "50kg",
            description = "슐라슐라",
            breed = "뽀메",
            dog_sex = "남아",
            dog_id = "11"
        )

        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme() {
                    // In Compose world
                    MainView(
                        dogInfoClick = { dogInfo ->
                            var intent = Intent(mContext, DogInfoActivity::class.java)
                            intent.putExtra("dog_id", dogInfo.dog_id)
                            startActivity(intent)
                        },
                        dogRegiClick = {
                            val intent = Intent(mContext, DogRegisterActivity::class.java)
                            startActivity(intent)
                        },
                        dogInfo = dogInfo,
                        noseCheck = {
                            val intent = Intent(mContext, DogCheckActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

@Preview
@Composable
fun mainPreview(){
    MainView(dogRegiClick = { /*TODO*/ }, dogInfoClick = { /*TODO*/ }, noseCheck = {}, dogInfo = listOf(
        DogInfo(
        dog_name = "포메",
        birth = "2020",
        profile_path = "https://firebasestorage.googleapis.com/v0/b/pet-connect-cdc02.appspot.com/o/dog.jpeg?alt=media&token=20aa134f-40cd-4902-8761-9d2143ce2925",
        height = "100cm",
        weight = "50kg",
        description = "슐라슐라",
        breed = "뽀메",
        dog_sex = "남아"
    )))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    modifier:Modifier = Modifier,
    dogRegiClick: () -> Unit,
    dogInfoClick: (DogInfo) -> Unit,
    noseCheck: () -> Unit,
    dogInfo: List<DogInfo>
){
    AppTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "펫 커넥트",

                            // Body2_Bold
                            style = TextStyle(
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_black)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                            Column(){
                                Text(
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    text = "나의 반려가족",
                                    // Subhead
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        lineHeight = 28.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_black)),
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF2B2B2B),
                                    )
                                )
                                Text(
                                    modifier = Modifier
                                        .wrapContentSize(   ),
                                    text = "3마리까지 비문을 등록할 수 있어요 !",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_black)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF426CB4),
                                    )
                                )
                        }
                        DogRegisterButton(dogRegiClick = {dogRegiClick()})
                    }
                    Row() {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 150.dp),
                        contentPadding = PaddingValues(vertical = 20.dp),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        items(dogInfo.size,) { index ->
                            DogProfileCard(
                                modifier = Modifier.height(150.dp),
                                listClick = { dogInfoClick(dogInfo[index]) },
                                dogInfo = dogInfo[index])
                        }
                    }
                    }
                    Text(
                        text = "유기견 신고",
                        // Subhead
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_black)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF2B2B2B),
                        )
                    )
                    IconCard(
                        btnClick = { noseCheck() },
                        icon = { Icon(
                            painter = painterResource(id = R.drawable.location_check),
                            contentDescription = "nav",
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        ) },
                        text = { Text(
                                text = "유기견을 발견했어요",
                                // Body2
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_black)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                        ) }
                    )
                    IconCard(
                        btnClick = {},
                        icon = { Icon(
                            painter = painterResource(id = R.drawable.location_question),
                            contentDescription = "nav",
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        ) },
                        text = { Text(
                            text = "나의 가족을 잃어버렸어요",
                            // Body2
                            style = TextStyle(
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_black)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        ) }
                    )
                }
            }
        }

    }
}

@Composable
fun DogRegisterButton(
    modifier: Modifier = Modifier,
    dogRegiClick: () -> Unit
){
    Row(
        modifier = modifier
            .clickable { dogRegiClick() }
            .wrapContentSize()
            .background(color = Color(0xFF426CB4), shape = RoundedCornerShape(size = 21.dp))
            .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "image description",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            tint = Color.White
        )
        Text(
            text = "등록하기",

            // Caption1_Bold
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto_black)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DogProfileCard(
    modifier: Modifier = Modifier,
    listClick: () -> Unit,
    dogInfo: DogInfo
){
    ElevatedCard(
        modifier = modifier
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
        , colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape
    ) {
        Column(
            modifier = modifier
                .clickable { listClick() }
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dogInfo.profile_path)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_image),
                contentDescription = "dog image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(80.dp)
                    .height(80.dp)
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Text(
                text = "${dogInfo.dog_name}(${2023 - dogInfo.birth.toInt()}세)",
                style = TextStyle(
                    fontWeight = FontWeight(800),
                    fontSize = 18.sp
                )
            )
            Text(
                text = "${dogInfo.breed} - ${dogInfo.dog_sex}",
                style = TextStyle(
                    fontWeight = FontWeight(300),
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Composable
fun IconCard(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    btnClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit
){
    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .clickable { btnClick() }
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            icon()
            text()
        }
    }
}

