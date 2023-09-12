package com.haneum.petconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.haneum.petconnect.contracts.RegisterContract
import com.haneum.petconnect.databinding.ActivityRegisterBinding
import com.haneum.petconnect.models.RegisterRepository
import com.haneum.petconnect.presenters.RegisterPresenter


class RegisterActivity : AppCompatActivity(), RegisterContract.View{

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter:RegisterPresenter
    private lateinit var repository: RegisterRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = RegisterRepository(this)
        presenter= RegisterPresenter(this@RegisterActivity, repository)

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeneratedCode1(
                        onRegisterClick = { email, pass, name, phone ->
                        presenter.register(email, pass, name, phone) },
                        cancelClick = { finish() }
                    )
                }
            }
        }
    }

//    private fun initButtonListener(){
//        var strEmail: String?
//        var strPw: String?
//        var strName: String?
//        var strPhone: String?
//        binding.btnRegister.setOnClickListener {
//            strEmail = binding.etEmail.text?.toString()
//            strPw = binding.etPwd.text?.toString()
//            strName = binding.etName.text?.toString()
//            strPhone = binding.etPhone.text?.toString()
//            if (strEmail == "" || strPw == ""){
//                Toast.makeText(this,"실패~",Toast.LENGTH_SHORT).show()
//            }else{
//                presenter.register(strEmail!!,strPw!!, strName!!, strPhone!!)
//            }
//        }
//    }

    override fun goLogin() {
        Toast.makeText(this,"이메일 인증을 확인하세요", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun makeFailureText(reason: String) {
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }

}



// 화면 전체

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratedCode1(
    modifier: Modifier = Modifier,
    onRegisterClick: (String, String, String, String) -> Unit,
    cancelClick: () -> Unit
    ) {
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var nameState by remember { mutableStateOf("") }
    var phoneNumberState by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .requiredWidth(width = 400.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color.White)
    ) {
        TopAppBar1(
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp),
            cancelClick = cancelClick
        )
        Box(
            modifier = Modifier
                .requiredWidth(width = 200.dp)
                .requiredHeight(height = 150.dp)
        ) {
            Text(
                text = "회원가입",
                color = Color(0xff426cb4),
                textAlign = TextAlign.Center,
                lineHeight = 2.5.em,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .offset(x = 20.dp,
                        y = 100.dp)
            )
        }

        TextField(
            value = emailState,
            onValueChange = { textValue -> emailState = textValue },
            enabled = true,
            placeholder = { Text("이메일을 입력하시오") },
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xffb3b3b3)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 310.dp
                )
                .requiredWidth(width = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true
        )
        TextField(
            value = phoneNumberState,
            onValueChange = { textValue -> phoneNumberState = textValue },
            enabled = true,
            placeholder = { Text("휴대폰번호를 입력하시오") },
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xffb3b3b3)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 530.dp
                )
                .requiredWidth(width = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true
        )
        TextField(
            value = nameState,
            onValueChange = { textValue -> nameState = textValue },
            enabled = true,
            placeholder = { Text("이름을 입력하시오") },
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xffb3b3b3)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 210.dp
                )
                .requiredWidth(width = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true
        )
        TextField(
            value = passwordState,
            onValueChange = { textValue -> passwordState = textValue },
            enabled = true,
            placeholder = { Text("비밀번호를 입력하시오") },
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xffb3b3b3)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 420.dp
                )
                .requiredWidth(width = 343.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Text(
            text = "비밀번호",
            color = Color(0xff4b4b4b),
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 18.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 384.dp
                )
        )

        Text(
            text = "이메일",
            color = Color(0xff4b4b4b),
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 18.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 271.dp
                )
        )
        Text(
            text = "이름",
            color = Color(0xff4b4b4b),
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 18.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 180.dp
                )
        )

        Text(
            text = "휴대폰 번호",
            color = Color(0xff4b4b4b),
            lineHeight = 1.33.em,
            style = TextStyle(
                fontSize = 18.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 30.dp,
                    y = 490.dp
                )
        )
        SquareButton1(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 702.dp
                ),
            onRegisterClick = { onRegisterClick(emailState, passwordState, nameState, phoneNumberState) }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar1(
    modifier: Modifier = Modifier,
    cancelClick: () -> Unit
) {
    androidx.compose.material3.TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 24.dp)
                    .padding(end = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    modifier = Modifier
                        .clickable { cancelClick() },
                    text = "취소",
                    color = Color(0xffb3b3b3),
                    textAlign = TextAlign.Right,
                    lineHeight = 1.41.em,
                    style = TextStyle(
                        fontSize = 17.sp
                    ),
                )
            }
        },
        modifier = modifier
    )
}
@Composable
fun SquareButton1(modifier: Modifier = Modifier, onRegisterClick: () -> Unit) {
    Button(
        onClick = { onRegisterClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff426cb4)),
        contentPadding = PaddingValues(horizontal = 154.dp, vertical = 15.dp),
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 55.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(width = 500.dp)
                .requiredHeight(height = 70.dp)
        ) {
            Text(
                text = "가입완료",
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 1.41.em,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun GeneratedCodePreview1() {
    //GeneratedCode1(onRegisterClick = {"", "", "", "" -> })
}

