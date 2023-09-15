package com.haneum.petconnect

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme

class CheckResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckResultView(
                        next = {finish()},
                        back = {
                            val intent = Intent(this, DogCheckActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckResultView(
    modifier: Modifier = Modifier,
    next: () -> Unit,
    back: () -> Unit
    ) {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { back() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )

        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .background(color = Color(0xFF426CB4), shape = RoundedCornerShape(size = 32.dp))
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f),
                onClick = next,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF426CB4),
                    contentColor = Color.White
                )
            ){
                Text(
                    text = "확인",
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center,
                )
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
        ) {

        }
    }
}