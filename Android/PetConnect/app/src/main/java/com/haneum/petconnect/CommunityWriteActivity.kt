package com.haneum.petconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme

class CommunityWriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun WriteTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "완료",
                color = Color(0xff426cb4),
                textAlign = TextAlign.End,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp))
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "ic_arrow_back",
                tint = Color(0xff191c1b))
        },
        modifier = modifier)
}