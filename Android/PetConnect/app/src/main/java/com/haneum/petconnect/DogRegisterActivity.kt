package com.haneum.petconnect

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.load.model.UnitModelLoader
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.haneum.petconnect.data.DogInfo
import com.haneum.petconnect.service.NoseRegisterApi
import com.haneum.petconnect.service.NoseRegisterRes
import com.haneum.petconnect.service.RegisterDto
import com.haneum.petconnect.service.RetrofitSetting
import com.haneum.petconnect.ui.bottomBorder
import com.haneum.petconnect.ui.theme.md_theme_light_primary
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.annotation.Nullable

class DogRegisterActivity : ComponentActivity() {
    private lateinit var images: Array<File?>
    private lateinit var cbActivityResultLauncher: ActivityResultLauncher<Intent>
    private val dataMap: HashMap<String, RequestBody> = HashMap()
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme {
                Navigation(
                    modifier = Modifier,
                    navController = navController,
                    getImage = { getImage() },
                    onNameChange = {name -> dataMap["dogName"] =
                        name.toRequestBody("text/plain".toMediaTypeOrNull())
                    },
                    clickBreed = {},
                    clickFinish = { finish() }
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
                        //noseRegister(images, service)
                        testUpload(images)
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

    private fun testUpload(imageFiles: Array<File?>){
        val db = Firebase.firestore
        var fireStoreData: DogInfo
        val storage = FirebaseStorage.getInstance()

        var fileName =
            SimpleDateFormat("yyyyMMddHHmmss").format(Date())+"_"+user.uid.toString()
        storage.reference.child("image").child(fileName)
            .putFile(imageFiles[0]!!.toUri())//어디에 업로드할지 지정
            .addOnSuccessListener {
                    taskSnapshot -> // 업로드 정보를 담는다
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    fireStoreData = DogInfo(
                        dog_id = UUID.randomUUID().toString(),
                        dog_name = dataMap["dogName"].toString(),
                        birth = "1월",
                        profile_path = it.toString(),
                        height = "100cm",
                        weight = "10kg",
                        description = "쏼라",
                        user_id = user.uid,
                        breed = "포메",
                        dog_sex = "남아"
                    )
                    db.collection("nose")
                        .add(fireStoreData)
                }
            }
    }

    private fun noseRegister(imageFiles: Array<File?>, service: NoseRegisterApi){
        val db = Firebase.firestore
        var fireStoreData: DogInfo
        val storage = FirebaseStorage.getInstance()


        val dogProfile = imageFiles[0]!!.asRequestBody("image/*".toMediaTypeOrNull())
        val dogNose1 = imageFiles[0]!!.asRequestBody("image/*".toMediaTypeOrNull())
        val dogNose2 = imageFiles[1]!!.asRequestBody("image/*".toMediaTypeOrNull())
        val dogNose3 = imageFiles[2]!!.asRequestBody("image/*".toMediaTypeOrNull())
        val dogNose4 = imageFiles[3]!!.asRequestBody("image/*".toMediaTypeOrNull())
        val dogNose5 = imageFiles[4]!!.asRequestBody("image/*".toMediaTypeOrNull())

        var fileName =
            SimpleDateFormat("yyyyMMddHHmmss").format(Date())+"_"+user.uid.toString()
        storage.reference.child("image").child(fileName)
            .putFile(imageFiles[0]!!.toUri())//어디에 업로드할지 지정
            .addOnSuccessListener {
                    taskSnapshot -> // 업로드 정보를 담는다
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    fireStoreData = DogInfo(
                        dog_id = UUID.randomUUID().toString(),
                        dog_name = dataMap["dogName"].toString(),
                        birth = "1월",
                        profile_path = it.toString(),
                        height = "50cm",
                        weight = "10kg",
                        description = "쏼라",
                        user_id = user.uid,
                        breed = "포메",
                        dog_sex = "남아"
                    )
                    db.collection("nose")
                        .add(fireStoreData)
                }
            }

        db.collection("users")
            .whereEqualTo("user_id", user.uid)
            .get()
            .addOnSuccessListener {documents ->
                for( doc in documents){
                    dataMap["registrant"] =
                        doc.data["name"].toString().toRequestBody("text/plain".toMediaTypeOrNull())
                    dataMap["phoneNum"] =
                        doc.data["phone"].toString().toRequestBody("text/plain".toMediaTypeOrNull())
                }
            }

        dataMap["email"] = user.email!!.toRequestBody("text/plain".toMediaTypeOrNull())
        dataMap["dogBreed"] = "free".toRequestBody("text/plain".toMediaTypeOrNull())
        dataMap["dogBirthYear"] = "2020".toRequestBody("text/plain".toMediaTypeOrNull())
        dataMap["dogSex"] = "male".toRequestBody("text/plain".toMediaTypeOrNull())

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
        Column() {
            Text("아이의 이름을 알려주세요")
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x1F000000),
                        ambientColor = Color(0x1F000000)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFFB1C5CD),
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(1.dp)
                    .width(153.dp)
                    .height(153.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
                    .clickable { getImage() },
            ){
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                        contentDescription = "image description",
                        tint =  Color(0xFF426CB4),
                    )
                    Text(
                        text = "0 / 6",
                        // Body3
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_black)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF426CB4),
                        )
                    )
                }
            }
            Text(text = "이름")
            OutlinedTextField(
                modifier = Modifier
                    .bottomBorder(1.dp, Color.LightGray),
                value = nameState,
                onValueChange = { textValue -> nameState = textValue},
                placeholder = {Text("이름을 입력해주세요")},
                colors = TextFieldDefaults.textFieldColors(

                ),
                singleLine = true
            )
        }
    }
}

@Composable
fun SelectBreedContent(
    modifier: Modifier,
    clickBreed: (String) -> Unit
){
    val nameList = listOf<String>("리트리버", "포메")
    Text(text = "반려견의 견종은 무엇인가요?")
    Divider(color = Color.Blue, thickness = 1.dp)
    ButtonList(names = nameList, clickItem = clickBreed)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBasicContent(
    modifier: Modifier,
    dialog: DatePickerDialog,
    dateState: String
){
    Text(text = "반려견의 기본 정보를 알려주세요")
    Text(text = "성별")
    Text(text = "생년월일")
    Box(
        modifier = Modifier.clickable {
            dialog.show()
        }.fillMaxWidth()
    ){
        Text(
            text = dateState,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Icon(
            modifier = Modifier.align(Alignment.TopEnd),
            painter = painterResource(id = com.firebase.ui.auth.R.drawable.material_ic_calendar_black_24dp),
            contentDescription = "image description",
            tint =  Color(0xFF426CB4),
        )
    }

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
    onNameChange: (String) -> Unit,
    clickBreed: (String) -> Unit,
    clickFinish: () -> Unit
    ){
    val calendar = Calendar.getInstance()
    var dateState by remember { mutableStateOf("") }
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { view, year, month, dayOfMonth ->
            dateState = "$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
    )

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
                back = { clickFinish() },
                state = 0
            )
        }
        composable("selectBreed"){
            BasicBackground(
                content = { SelectBreedContent(modifier = Modifier, clickBreed = clickBreed) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("inputBasic") },
                back = { navController.navigate("selectImage")},
                state = 1
            )
        }
        composable("inputBasic"){
            BasicBackground(
                content = { InputBasicContent(modifier = Modifier, datePickerDialog, dateState) },
                skip = { navController.navigate("finish")},
                next = { navController.navigate("selectHealth") },
                back = { navController.navigate("selectBreed")},
                state = 2
            )
        }
        composable("selectHealth"){
            BasicBackground(
                content = { SelectHealthContent(modifier = Modifier) },
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
                next = { clickFinish() },
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
                Column() {
                    StepsProgressBar(
                        modifier = Modifier.fillMaxWidth(),
                        numberOfSteps = 4,
                        currentStep = state
                    )
                    content()
                }
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

@Composable
fun ButtonList(
    names: List<String>,
    clickItem: (String) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(names) { item ->
            InteractionButton(onClick = { clickItem(item) }, text = { Text(item) })
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun InteractionButton(
    onClick: () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    AppTheme() {
        val isPressed by interactionSource.collectIsPressedAsState()
        val bgColor = if(isPressed) Color.White else md_theme_light_primary
        val contentColor = if(isPressed) Color.Black else Color.White
            Button(onClick = onClick,
                modifier = modifier,
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(
                    containerColor = bgColor,
                    contentColor = contentColor
                )
            ){
                text()
            }
    }
}

