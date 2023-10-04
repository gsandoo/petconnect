package com.haneum.petconnect


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.contracts.CheckHealthContract
import com.haneum.petconnect.models.CheckHealthRepository
import com.haneum.petconnect.presenters.CheckHealthPresenter
import java.text.SimpleDateFormat

class CheckEyeActivity : ComponentActivity(), CheckHealthContract.View {
    lateinit var dogId: String
    lateinit var dogName: String
    lateinit var imageUri: Uri
    lateinit var checkEyePresenter: CheckHealthPresenter
    lateinit var checkEyeRepository: CheckHealthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dogId = intent.getStringExtra("dogId").toString()
        imageUri = intent.getStringExtra("fileUri")!!.toUri()
        dogName = intent.getStringExtra("dogName").toString()

        checkEyeRepository = CheckHealthRepository()
        checkEyePresenter = CheckHealthPresenter(this@CheckEyeActivity, checkEyeRepository)

        setContent {
            var screenState by remember {
                mutableStateOf(true)
            }
            if(screenState){
                SelectEyeScreen(
                    back = {finish()},
                    next = {
                        screenState = false
                        checkEyePresenter.getHealthCheckData(dogId, imageUri,it, "eye")
                    },
                    toast = { Toast.makeText(this, "정보를 입력해주세요",Toast.LENGTH_SHORT).show()}
                )
            }else{
                PendingScreen()
            }
        }
    }

    override fun makeFailureText(reason: String) {
        runOnUiThread{Toast.makeText(applicationContext, reason,Toast.LENGTH_SHORT).show()}
    }

    override fun refreshList() {
        TODO("Not yet implemented")
    }

    override fun showHealthData(docId: String,result: String, kind: String) {
        runOnUiThread { Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show() }
        val intent = Intent(this, ShowHealthInfoActivity::class.java)
        intent.putExtra("docId",docId)
        intent.putExtra("dogName",dogName)
        intent.putExtra("kind", kind)
        intent.putExtra("result", result)
        startActivity(intent)
        finish()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectEyeScreen(
    back: () -> Unit,
    next: (String) -> Unit,
    modifier: Modifier = Modifier,
    toast: () -> Unit
){
    var selectedIndex by remember { mutableIntStateOf(0) }
    val onItemClick = { index: Int -> selectedIndex = index}
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )

        }, bottomBar = {
            BottomAppBar(
                content = {
                    Button(
                        shape = CircleShape,
                        onClick = {
                                  if(selectedIndex == 1){
                                      next("왼쪽")
                                  }else if(selectedIndex == 2){
                                      next("오른쪽")
                                  }else{
                                      toast()
                                  }
                        },
                        modifier = Modifier.fillMaxSize(),
                        enabled = selectedIndex != 0
                    ) {
                        Text(text = "확인")
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectableButton(
                    onClick = onItemClick,
                    text = {
                        Column() {
                            Text(
                                text = "왼쪽",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight(700)
                                )
                            )
                            Text(
                                text = "촬영할거에요",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight(400)
                                )
                            )
                        }
                        },
                    index = 1,
                    selected = selectedIndex == 1,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shadow(1.dp),
                    shape = RoundedCornerShape(10.dp),
                    item = "왼쪽",
                    clickItem = {}
                )
                Spacer(modifier = Modifier.size(20.dp))
                SelectableButton(
                    onClick = onItemClick,
                    text = {
                        Column() {
                            Text(
                                text = "오른쪽",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight(700)
                                )
                            )
                            Text(
                                text = "촬영할거에요",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight(400)
                                )
                            )
                        } },
                    index = 2,
                    selected = selectedIndex == 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    item = "오른쪽",
                    clickItem = {}
                )
            }
        }
    }
}