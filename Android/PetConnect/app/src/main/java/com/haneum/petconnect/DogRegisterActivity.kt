package com.haneum.petconnect

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haneum.petconnect.service.NoseRegisterApi
import com.haneum.petconnect.service.NoseRegisterRes
import com.haneum.petconnect.service.RegisterDto
import com.haneum.petconnect.service.RetrofitSetting
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.annotation.Nullable

class DogRegisterActivity : ComponentActivity() {
    private lateinit var images: Array<File?>
    private lateinit var cbActivityResultLauncher: ActivityResultLauncher<Intent>
    private val dataMap: HashMap<String, RequestBody> = HashMap()
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser!!
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme {
                Navigation(
                    modifier = Modifier,
                    navController = navController,
                    getImage = { getImage() },
                    onNameChange = {name -> dataMap["dogName"] = RequestBody.create(MediaType.parse("text/plain"),name)}
                )
            }
        }

        val retrofit = RetrofitSetting.getInstance()
        val service = retrofit.create(NoseRegisterApi::class.java)
        images = arrayOfNulls<File>(5)

        cbActivityResultLauncher = registerForActivityResult(androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                val data = it.data?.clipData
                if (data != null) { // 사진 여러개 선택한 경우
                    val count = data!!.itemCount
                    if (count != 5) {
                        Toast.makeText(this, "사진을 5장 선택해주세요", Toast.LENGTH_LONG).show()
                    } else {
                        for (i in 0 until count) {
                            val imageUri = data!!.getItemAt(i).uri
                            images[i] = File(absolutelyPath(this,imageUri))
                        }
                        noseRegister(images, service)
                    }
                }else {
                    Toast.makeText(this, "사진을 5장 선택해주세요", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun getImage(){
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        cbActivityResultLauncher.launch(intent)
    }

    fun isConfirm(){

    }

    @Nullable
    fun absolutelyPath(context: Context, uri: Uri): String? {
        val contentResolver: ContentResolver = context.contentResolver ?: return null

        // 파일 경로를 만듬
        val filePath: String = (context.applicationInfo.dataDir + File.separator
                + System.currentTimeMillis())
        val file = File(filePath)
        try {
            // 매개변수로 받은 uri 를 통해  이미지에 필요한 데이터를 불러 들인다.
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            // 이미지 데이터를 다시 내보내면서 file 객체에  만들었던 경로를 이용한다.
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (ignore: IOException) {
            return null
        }
        return file.absolutePath
    }

    private fun noseRegister(imageFiles: Array<File?>, service: NoseRegisterApi){
        val db = Firebase.firestore
        val dataMap: HashMap<String, RequestBody> = HashMap()

        val dogProfile = RequestBody.create(MediaType.parse("image/*"), imageFiles[0])
        val dogNose1 = RequestBody.create(MediaType.parse("image/*"), imageFiles[0])
        val dogNose2 = RequestBody.create(MediaType.parse("image/*"), imageFiles[1])
        val dogNose3 = RequestBody.create(MediaType.parse("image/*"), imageFiles[2])
        val dogNose4 = RequestBody.create(MediaType.parse("image/*"), imageFiles[3])
        val dogNose5 = RequestBody.create(MediaType.parse("image/*"), imageFiles[4])

        db.collection("users")
            .whereEqualTo("user_id", user.uid)
            .get()
            .addOnSuccessListener {documents ->
                for( doc in documents){
                    dataMap["registrant"] = RequestBody.create(MediaType.parse("text/plain"),doc.data["name"].toString())
                    dataMap["phoneNum"] = RequestBody.create(MediaType.parse("text/plain"),doc.data["phone"].toString())
                }
            }

        dataMap["email"] = RequestBody.create(MediaType.parse("text/plain"),user.email)
        dataMap["dogBreed"] = RequestBody.create(MediaType.parse("text/plain"),"free")
        dataMap["dogBirthYear"] = RequestBody.create(MediaType.parse("text/plain"),"2020")
        dataMap["dogSex"] = RequestBody.create(MediaType.parse("text/plain"),"male")

        val imageMap: HashMap<String, MultipartBody.Part> = HashMap()
        imageMap["dogProfile"] =
            MultipartBody.Part.createFormData("dogProfile", imageFiles[0]!!.name, dogProfile)
        imageMap["dogNose1"] =
            MultipartBody.Part.createFormData("dogNose1", imageFiles[0]!!.name, dogNose1)
        imageMap["dogNose2"] =
            MultipartBody.Part.createFormData("dogNose2", imageFiles[1]!!.name, dogNose2)
        imageMap["dogNose3"] =
            MultipartBody.Part.createFormData("dogNose3", imageFiles[2]!!.name, dogNose3)
        imageMap["dogNose4"] =
            MultipartBody.Part.createFormData("dogNose4", imageFiles[3]!!.name, dogNose4)
        imageMap["dogNose5"] =
            MultipartBody.Part.createFormData("dogNose5", imageFiles[4]!!.name, dogNose5)


        service.postNoseRegister(
            dataMap,
            imageMap["dogProfile"]!!,
            imageMap["dogNose1"]!!,
            imageMap["dogNose2"]!!,
            imageMap["dogNose3"]!!,
            imageMap["dogNose4"]!!,
            imageMap["dogNose5"]!!
        )?.enqueue(object : Callback<NoseRegisterRes> {
            override fun onResponse(call: Call<NoseRegisterRes>, response: Response<NoseRegisterRes>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성고된 경우
                    var result: NoseRegisterRes? = response.body()
                    Log.d("YMC", "onResponse 성공: " + result?.toString());
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", "onResponse 실패")
                }
            }


            override fun onFailure(call: Call<NoseRegisterRes>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }
        })
    }
}

//각 화면 마다의 구성들
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectImageContent(
    modifier: Modifier,
    getImage: () -> Unit,
    onNameChange: (String) -> Unit
){
    var nameState by remember {
        mutableStateOf("")
    }
    Surface(){
        Text("아이의 이름을 알려주세요")
        Button(onClick = getImage) {
            Text(text = "이미지 등록")
        }
        Text(text = "이름")
        TextField(value = nameState, onValueChange = { textValue -> nameState = textValue})
    }


}
@Composable
fun SelectBreedContent(
    modifier: Modifier,
){
    Text(text = "Breed")
}
@Composable
fun InputBasicContent(
    modifier: Modifier,
){
    Text(text = "input!")
}
@Composable
fun SelectHealthContent(
    modifier: Modifier,
){
    Text(text = "health")
}
@Composable
fun FinishContent(
    modifier: Modifier,
){
    Text(text = "finish")
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "selectImage",
    getImage: () -> Unit,
    onNameChange: (String) -> Unit
    ){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        ){
        composable("selectImage"){
            BasicBackground(
                content = { SelectImageContent(modifier = Modifier, getImage, onNameChange) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("selectBreed") },
                back = { navController.navigate("selectImage") },
                state = 0
            )
        }
        composable("selectBreed"){
            BasicBackground(
                content = { SelectBreedContent(modifier = Modifier) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("inputBasic") },
                back = { navController.navigate("selectImage")},
                state = 1
            )
        }
        composable("inputBasic"){
            BasicBackground(
                content = { SelectHealthContent(modifier = Modifier) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("selectHealth") },
                back = { navController.navigate("selectBreed")},
                state = 2
            )
        }
        composable("selectHealth"){
            BasicBackground(
                content = { InputBasicContent(modifier = Modifier) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("finish") },
                back = { navController.navigate("inputBasic")},
                state = 3
            )
        }
        composable("finish"){
            BasicBackground(
                content = { FinishContent(modifier = Modifier) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("finish") },
                back = {navController.navigate("selectHealth")},
                state = 4
            )
        }
    }
}


// 화면의 상단바와 하단 버튼
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicBackground(
    content: @Composable () -> Unit,
    skip: () -> Unit,
    next: () -> Unit,
    back: () -> Unit,
    state: Int
                    ){
    AppTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("")},
                    navigationIcon = {
                        IconButton(onClick = back) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        TextButton(onClick = skip) {
                            Text(text = "건너뛰기")
                        }
                    }
                )

            }, bottomBar = {
                BottomAppBar(
                    content = {
                        Button(
                            shape = RectangleShape,
                            onClick = next,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(text = "다음")
                        }
                    }
                )
            },)
        {
            Surface(modifier = Modifier.padding(it)) {
                StepsProgressBar(modifier = Modifier.fillMaxWidth(), numberOfSteps = 4, currentStep = state)
                content()
            }
        }
    }
}

//화면 상단 상태바
@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0..numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                isCompete = step < currentStep,
                isCurrent = step == currentStep
            )
        }
    }
}

@Composable
fun Step(modifier: Modifier = Modifier, isCompete: Boolean, isCurrent: Boolean) {
    val color = if (isCompete || isCurrent) Color.Blue else Color.LightGray

    Box(modifier = modifier) {
        Divider(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 2.dp),
            color = color,
            thickness = 2.dp,
        )
    }
}