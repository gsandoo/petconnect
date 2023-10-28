package com.haneum.petconnect.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.MainActivity
import com.haneum.petconnect.R
import com.haneum.petconnect.ToiletRecord0Activity
import com.haneum.petconnect.models.HealthRepository
import com.haneum.petconnect.presenters.HealthPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    HealthScreen(
                        goCheck = {
                            

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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    goCheck: (String) -> Unit
){
    Scaffold() {
        Surface(
            modifier = Modifier.padding(it)
        ) {

            MyPageview(modifier = Modifier, goCheck = goCheck)
        }
    }
}

@Composable
fun ProfileFrame(modifier: Modifier = Modifier,    goCheck: (String) -> Unit) {
    Box(
        modifier = modifier
            .requiredWidth(width = 359.dp)
            .requiredHeight(height = 168.dp)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 144.dp,
                    y = 24.dp
                )
                .requiredSize(size = 75.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 75.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.White)
                    .border(
                        border = BorderStroke(1.dp, Color(0xff426cb4)),
                        shape = CircleShape
                    ))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 19.921875.dp,
                        y = 18.75.dp
                    )
                    .requiredWidth(width = 36.dp)
                    .requiredHeight(height = 36.dp)
            ) {

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 7.38818359375.dp,
                            y = 14.162109375.dp
                        )
                        .requiredWidth(width = 21.dp)
                        .requiredHeight(height = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 8.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.White))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 4.10498046875.dp,
                                y = 4.00244140625.dp
                            )
                            .requiredSize(size = 3.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff426cb4)))

                    Image(
                        painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = "chevron-right",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .requiredSize(size = 40.dp))
                }
            }
        }

        Text(
            text = "김하나",
            color = Color.Black,
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = (-10.5).dp,
                    y = 115.dp
                ))
        Text(
            text = ">",
            color = Color.Gray,
            lineHeight = 1.4.em,
            style = TextStyle(
                fontSize = 20.sp),
            modifier = Modifier
                .clickable { goCheck("Myprofile") }
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 30.5.dp,
                    y = 118.dp
                ))

    }
}


@Composable
fun petBox(
    name: String, // 반려견 이름을 받을 매개변수
    imageResource: Painter, // 이미지 리소스를 받을 매개변수
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column (modifier = modifier){

            Image(
                painter = imageResource,
                contentDescription = "Pet Image",
                modifier = Modifier
                    .requiredSize(size = 61.dp)
                    .clip(shape = CircleShape)
            )
            Text(
                text = name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 1.29.em,
                style = TextStyle(fontSize = 14.sp),
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )
        }

    }
}

@Composable
fun PetFrame(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 362.dp)
            .requiredHeight(height = 160.dp)
            .background(color = Color.White)
    ) {
        Column(){
            Text(
                text = "나의 반려가족",
                color = Color(0xff2b2b2b),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .offset(
                        x = 18.dp,
                        y = 12.dp
                    ))
            Box(modifier = Modifier
                .offset(
                    x = 25.dp,
                    y = 30.dp
                )){
                Row(){
                    Box(modifier = modifier
                        .requiredWidth(width = 100.dp)
                        .requiredHeight(height = 170.dp)
                        .padding(10.dp)) {
                        val ImageResource = painterResource(id = R.drawable.skin_icon)
                        petBox(name = "사랑이", imageResource = ImageResource)}
                    Box(modifier = modifier
                        .requiredWidth(width = 100.dp)
                        .requiredHeight(height = 170.dp)
                        .padding(10.dp)) {
                        val aImageResource = painterResource(id = R.drawable.skin_icon)
                        petBox(name = "초코", imageResource = aImageResource)}
                    Box(modifier = modifier
                        .requiredWidth(width = 100.dp)
                        .requiredHeight(height = 170.dp)
                        .padding(10.dp)) {
                        val ImageResource = painterResource(id = R.drawable.skin_icon)
                        petBox(name = "사랑이", imageResource = ImageResource)}
                }

            }


        }
    }


}

@Composable
fun myActivity(modifier: Modifier = Modifier,    goCheck: (String) -> Unit) {
    Column(
        modifier = modifier
            .requiredWidth(width = 362.dp)
            .background(color = Color.White)
            .padding(all = 16.dp)
    ) {
        Text(
            text = "나의 활동",
            color = Color(0xff2b2b2b),
            lineHeight = 1.41.em,
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold)
        )
        Box(
            modifier = Modifier
                .requiredWidth(width = 330.dp)
                .requiredHeight(height = 104.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 330.dp)
                    .requiredHeight(height = 24.dp)
            ) {
                Text(
                    text = "내가 쓴 글",
                    color = Color.Black,
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp)
                )
                Text(
                    text = ">",
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .clickable { goCheck("MywritePost") }
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 306.dp,
                            y = 0.dp
                        )
                        .requiredSize(size = 24.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 40.dp
                    )
                    .requiredWidth(width = 330.dp)
                    .requiredHeight(height = 24.dp)
            ) {
                Text(
                    text = "나의 중고거래",
                    color = Color.Black,
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp)
                )
                Text(
                    text = ">",
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .clickable { goCheck("MyusedTrade") }
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 306.dp,
                            y = 0.dp
                        )
                        .requiredSize(size = 24.dp))


            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 80.dp
                    )
                    .requiredWidth(width = 330.dp)
                    .requiredHeight(height = 24.dp)
            ) {
                Text(
                    text = "스크랩북",
                    color = Color.Black,
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp)
                )
                Text(
                    text = ">",
                    lineHeight = 1.4.em,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 306.dp,
                            y = 0.dp
                        )
                        .requiredSize(size = 24.dp))
            }
        }
    }
}
@Composable
fun etc(modifier: Modifier = Modifier,    goCheck: (String) -> Unit) {
    Column(

        modifier = modifier
            .requiredWidth(width = 362.dp)
            .background(color = Color.White)
            .padding(all = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 330.dp)
                .requiredHeight(height = 24.dp)
        ) {
            Text(
                text = "문의하기",
                color = Color.Black,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp)
            )
            Text(
                text = ">",
                lineHeight = 1.4.em,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 306.dp,
                        y = 0.dp
                    )
                    .requiredSize(size = 24.dp))
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = 63.dp)
                .requiredHeight(height = 24.dp)
                .clickable { goCheck("logout") }
        ) {
            Text(
                text = "로그아웃",
                color = Color.Black,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp)
            )
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = 116.dp)
                .requiredHeight(height = 24.dp)
        ) {
            Text(
                text = "앱 버전",
                color = Color.Black,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp)
            )
            Text(
                text = "1.16.0",
                color = Color(0xffb3b3b3),
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 68.dp,
                        y = 0.dp
                    ))
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = 63.dp)
                .requiredHeight(height = 24.dp)
                .clickable { goCheck("delete") }
        ) {
            Text(
                text = "회원탈퇴",
                color = Color.Black,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp)
            )
        }
    }
}


@Composable
fun MyPageview(modifier: Modifier, goCheck: (String) -> Unit) {
    Column {
        ProfileFrame(Modifier,goCheck = goCheck)
        PetFrame()
        myActivity(Modifier,goCheck = goCheck)
        etc(Modifier,goCheck = goCheck)
    }
}
