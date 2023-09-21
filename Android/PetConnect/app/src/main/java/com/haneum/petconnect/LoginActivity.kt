package com.haneum.petconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.haneum.petconnect.contracts.LoginContract
import com.haneum.petconnect.models.LoginRepository
import com.haneum.petconnect.presenters.LoginPresenter


class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter
    private lateinit var repository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        repository = LoginRepository(this)
        presenter = LoginPresenter(this@LoginActivity, repository)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    GeneratedCode(
                        modifier = Modifier,
                        onLoginClick = { email, pass ->
                            if(email == "" || pass == ""){
                                Toast.makeText(this, "아이디 혹은 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
                            }else{
                                presenter.firebaseLogin(email,pass)
                            }
                        },
                        onRegisterClick = { goRegi() }
                    )

                }
            }
        }

        //initButtonListener()
    }

    override fun goMain(user: FirebaseUser) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goRegi() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun makeFailureText(reason: String) {
        Toast.makeText(this,reason,Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratedCode(modifier: Modifier = Modifier, onLoginClick: (String, String) -> Unit, onRegisterClick: () -> Unit) {
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(width = 400.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color.White)
    ) {
        TopAppBar(
            modifier = Modifier
                .offset(x = 0.dp,
                    y = 0.dp))

        Box(
            modifier = Modifier
                .requiredWidth(width = 200.dp)
                .requiredHeight(height = 150.dp)
        ) {
            Text(
                text = "로그인",
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
                    x = 16.dp,
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
            value = passwordState,
            onValueChange = { textValue -> passwordState = textValue },
            enabled = true,
            placeholder = { Text("비밀번호를 입력하시오") },
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xffb3b3b3)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 16.dp,
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
                    x = 16.dp,
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
                    x = 16.dp,
                    y = 271.dp
                )
        )
        Row(
            modifier = Modifier.align(Alignment.BottomStart)
                .fillMaxWidth()
        ){
            SquareButton(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                text = "로그인",
                onClick = {onLoginClick(emailState,passwordState)}
            )
            SquareButton(
                modifier = Modifier.weight(1F)
                    .fillMaxWidth(),
                text = "회원가입",
                onClick = { onRegisterClick()}
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 32.dp)
                    .requiredHeight(height = 24.dp)
            )
        },
        modifier = modifier)
}
@Composable
fun SquareButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff426cb4)),
        contentPadding = PaddingValues(horizontal = 50.dp, vertical = 15.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text(
                text = text,
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

