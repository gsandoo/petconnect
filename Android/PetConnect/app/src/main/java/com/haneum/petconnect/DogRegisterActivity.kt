package com.haneum.petconnect

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.compose.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.haneum.petconnect.contracts.DogRegisterContract
import com.haneum.petconnect.data.DogInfo
import com.haneum.petconnect.fragment.DogProfileCard
import com.haneum.petconnect.models.DogRegisterRepository
import com.haneum.petconnect.presenters.DogRegisterPresenter
import com.haneum.petconnect.service.NoseApi
import com.haneum.petconnect.service.NoseRegisterRes
import com.haneum.petconnect.service.RetrofitSetting
import com.haneum.petconnect.ui.bottomBorder
import com.haneum.petconnect.ui.theme.md_theme_dark_onPrimary
import com.haneum.petconnect.ui.theme.md_theme_light_primary
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.annotation.Nullable

class DogRegisterActivity : ComponentActivity(),DogRegisterContract.View {
    private lateinit var images: Array<File?>
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    private lateinit var repository: DogRegisterRepository
    private lateinit var presenter: DogRegisterPresenter
    private var isDone: Boolean = false
    private lateinit var dogId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        var fileName: String = ""
        repository = DogRegisterRepository()
        presenter = DogRegisterPresenter(this@DogRegisterActivity, repository)

        auth = FirebaseAuth.getInstance()
        images = arrayOfNulls<File>(5)
        user = auth.currentUser!!
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                val navController = rememberNavController()
                var selectedProfile by remember {
                    mutableStateOf<Uri?>(null)
                }
                var selectedNose by remember {
                    mutableStateOf<List<Uri>>(emptyList())
                }
                val singleImagePickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickVisualMedia(),
                    onResult = {uri ->
                        selectedProfile = uri
                    }
                )
                val multipleImagePickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickMultipleVisualMedia(),
                    onResult = {uris ->
                        if(uris.size == 5){
                            selectedNose = uris
                        }else {
                            Toast.makeText(this, "5장을 선택해 주세요", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
                // A surface container using the 'background' color from the theme {
                Navigation(
                    modifier = Modifier,
                    navController = navController,
                    getProfile = {
                        singleImagePickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )) },
                    getNose = {
                        multipleImagePickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    profile = selectedProfile,
                    nose = selectedNose,
                    clickFinish = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    },
                    isImageSelected = (selectedProfile != null),
                    showMessage = { message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()},
                    noseRegister = { data ->
                        if(fileName != "") {
                            presenter.dogRegister(data, fileName)
                        } },
                    imageUpload = {fileName = imageUpload(selectedProfile, selectedNose)}
                )
            }
        }
    }


    private fun imageUpload(profile: Uri?, noses: List<Uri>): String{
        val storage = FirebaseStorage.getInstance()
        //storage.useEmulator("10.0.2.2",9199)
        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(Date())+"_"+user.uid.toString()
        val db = Firebase.firestore
        storage.reference.child("image").child(fileName).child("profile")
            .putFile(profile!!)//어디에 업로드할지 지정
            .addOnSuccessListener {
                    taskSnapshot -> // 업로드 정보를 담는다
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {uri ->
                    db.collection("nose")
                        .add(hashMapOf("profile" to uri))
                }
            }
        for ((index, nose) in noses.withIndex()){
            storage.reference.child("image").child(fileName).child("nose"+(index + 1).toString())
                .putFile(nose)//어디에 업로드할지 지정
                .addOnSuccessListener {
                        taskSnapshot -> // 업로드 정보를 담는다
                    taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {uri ->
                        db.collection("nose")
                            .add(hashMapOf("profile"+(index+1).toString() to uri))
                    }
                }
        }

        return fileName
    }

    override fun makeFailureMessage(reason: String) {
        runOnUiThread{Toast.makeText(this, reason, Toast.LENGTH_LONG).show()}
    }

    override fun nextStep(result: String, dogId: String) {
        runOnUiThread{Toast.makeText(this,result,Toast.LENGTH_LONG).show()}
        val intent = Intent(this, ShowResultActivity::class.java)
        intent.putExtra("dogId", dogId)
        intent.putExtra("kind", "register")
        intent.putExtra("result",result)
        startActivity(intent)
        finish()
    }

}

//각 화면 마다의 구성들
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectImageContent(
    modifier: Modifier,
    getProfile: () -> Unit,
    getNose: () -> Unit,
    profile: Uri?,
    nose: List<Uri>,
    onNameChange: (String) -> Unit
){
    var nameState by remember {
        mutableStateOf("")
    }
    var profileSelected by remember {
        mutableStateOf(false)
    }
    var noseSelected by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier) {
        Text("아이의 이름을 알려주세요")
        Divider(thickness = 1.dp, color = Color.White)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
                    .clickable {
                        getProfile()
                        profileSelected = true
                    }
                    .clip(RoundedCornerShape(12.dp))
            ){
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    if (!profileSelected || profile == null){
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                            contentDescription = "image description",
                            tint =  Color(0xFF426CB4),
                        )
                        Text(
                            text = "프로필 등록",
                            // Body3
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_black)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF426CB4),
                            )
                        )
                    } else {
                        AsyncImage(
                            model = profile,
                            contentDescription = null
                        )
                    }
                }
            }
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
                    .clickable {
                        getNose()
                        noseSelected = true
                    }
                    .clip(RoundedCornerShape(12.dp))
            ){
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    if (!noseSelected || nose.size != 5){
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                            contentDescription = "image description",
                            tint =  Color(0xFF426CB4),
                        )
                        Text(
                            text = "비문 선택",
                            // Body3
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_black)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF426CB4),
                            )
                        )
                    } else {
                        AsyncImage(
                            model = nose[0],
                            contentDescription = null)
                    }
                }
            }
        }
        Text(text = "이름")
        TextField(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)),
            value = nameState,
            onValueChange = { textValue ->
                nameState = textValue
                onNameChange(textValue) },
            placeholder = {Text("이름을 입력해주세요")},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF426CB4))
        )
    }
}

@Composable
fun SelectBreedContent(
    modifier: Modifier,
    clickBreed: (String) -> Unit
){
    val nameList = listOf<String>("골든리트리버", "닥스훈트", "레브라도 리트리버", "몰티즈", "미니어쳐 슈나우저", "미니어쳐 푸들", "미니어쳐 핀셔", "베틀링턴 테리어", "비글", "비숑 프리제", "보스턴 테리어", "사모예드", "세틀랜드 쉽독", "스탠더드 푸들", "시바 이누", "시베리안 허스키", "시츄", "포메라니안")
    Column(modifier = modifier) {
        Text(text = "반려견의 견종은 무엇인가요?")
        Divider(color = Color.White, thickness = 1.dp)
        ButtonList(names = nameList, clickItem = clickBreed)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBasicContent(
    modifier: Modifier,
    dialog: DatePickerDialog,
    dateState: String,
    clickItem: (String) -> Unit
){
    var selectedIndex by remember { mutableIntStateOf(0) }
    val onItemClick = { index: Int -> selectedIndex = index}
    Column(modifier = modifier) {
        Text(text = "반려견의 기본 정보를 알려주세요")

        Text(text = "성별")
        Row(
            modifier = Modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectableButton(
                onClick = onItemClick,
                text = {
                    Text(
                        text = "남아",
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(700)
                        )
                    )},
                index = 1,
                selected = selectedIndex == 1,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .shadow(5.dp),
                shape = RoundedCornerShape(10.dp),
                item = "남아",
                clickItem = clickItem
            )
            Spacer(modifier = Modifier.size(20.dp))
            SelectableButton(
                onClick = onItemClick,
                text = {
                    Text(
                        text = "여아",
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(700)
                        )
                    )},
                index = 2,
                selected = selectedIndex == 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(5.dp),
                shape = RoundedCornerShape(10.dp),
                item = "여아",
                clickItem = clickItem
            )
        }
        Text(text = "생년월일")
        Box(
            modifier = Modifier
                .clickable {
                    dialog.show()
                }
                .fillMaxWidth()
                .bottomBorder(1.dp, Color.LightGray)
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
}
@Composable
fun SelectHealthContent(
    modifier: Modifier,
){
    val nameList = listOf<String>("알레르기", "관절", "백내장", "당뇨", "비만", "허리디스크", "외이염", "중이염", "내이염", "슬개골")
    Column(modifier = modifier) {
        Text(text = "반려견의 건강키워드는 무엇인가요?")
        Divider(color = Color.Blue, thickness = 1.dp)
        ButtonList(names = nameList, clickItem = {})
    }
}

@Preview
@Composable
fun PendingScreen(){
        AppTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = md_theme_light_primary
                )
            }
        }
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    noseRegister: (DogInfo) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "selectImage",
    getProfile: () -> Unit,
    getNose: () -> Unit,
    profile: Uri?,
    nose: List<Uri>,
    clickFinish: () -> Unit,
    showMessage: (String) -> Unit,
    isImageSelected: Boolean,
    imageUpload: () -> Unit,
    ){
    val calendar = Calendar.getInstance()
    var dateState by remember { mutableStateOf("") }
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            dateState = "$year $month $dayOfMonth"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
    )
    val contentModifier = Modifier.padding(20.dp)
    var dogInfo: DogInfo = DogInfo()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        ){
        composable("selectImage"){
            BasicBackground(
                content = { SelectImageContent(
                    modifier = contentModifier,
                    getProfile = getProfile,
                    getNose = getNose,
                    profile = profile,
                    nose = nose,
                    onNameChange = {
                        name -> dogInfo.dog_name = name
                }) },
                skip = { clickFinish()},
                next = {
                    if(isNext(dogInfo, 0) && isImageSelected){
                        imageUpload()
                        navController.navigate("selectBreed")
                    }else {
                        showMessage("정보를 입력하세요")
                    }
                },
                back = { clickFinish() },
                state = 0
            )
        }
        composable("selectBreed"){
            BasicBackground(
                content = { SelectBreedContent(modifier = contentModifier, clickBreed = { breed -> dogInfo.breed = breed }) },
                skip = { clickFinish() },
                next = {
                    if(isNext(dogInfo, 1)){
                        navController.navigate("inputBasic")
                    }else {
                        showMessage("정보를 입력하세요")
                    }
                },
                back = { navController.navigate("selectImage")},
                state = 1
            )
        }
        composable("inputBasic"){
            BasicBackground(
                content = { InputBasicContent(modifier = contentModifier, datePickerDialog, dateState, clickItem = {dog_sex -> dogInfo.dog_sex = dog_sex}) },
                skip = { clickFinish() },
                next = {
                    dogInfo.birth = dateState
                    if(isNext(dogInfo, 2)){
                        navController.navigate("selectHealth")
                    }else {
                        showMessage("정보를 입력하세요")
                    }
                },
                back = { navController.navigate("selectBreed")},
                state = 2
            )
        }
        composable("selectHealth"){
            BasicBackground(
                content = { SelectHealthContent(modifier = contentModifier) },
                skip = { navController.navigate("finish")},
                next = {
                    if(isNext(dogInfo, 3)){
                        navController.navigate("finish")
                        noseRegister(dogInfo)
                    }else {
                        showMessage("정보를 입력하세요")
                    }
                       },
                back = { navController.navigate("inputBasic")},
                state = 3
            )
        }
        composable("finish"){
                PendingScreen()
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
    var isButtonEnable by remember {
        mutableStateOf(true)
    }
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
                            enabled = isButtonEnable
                        ) {
                            Text(text = "다음")
                        }
                    }
                )
            },)
        {
            Surface(modifier = Modifier.padding(it)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StepsProgressBar(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        numberOfSteps = 3,
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
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
    val color = if (isCompete || isCurrent) Color(0xFF426CB4) else Color.LightGray
    Box(modifier = modifier) {
        Divider(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 4.dp),
            color = color,
            thickness = 5.dp,
        )
    }
}

@Composable
fun ButtonList(
    names: List<String>,
    clickItem: (String) -> Unit
){
    var selectedIndex by remember { mutableStateOf(0) }
    val onItemClick = { index: Int -> selectedIndex = index}
    LazyVerticalGrid(
        columns = GridCells.Adaptive(15.dp)
    ){
        itemsIndexed(
            names,
            span = {_, item ->
                GridItemSpan(item.length+3)
            }
        )
        { index, item ->
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
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SelectableButton(
    onClick: (Int) -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    index: Int,
    item: String,
    clickItem: (String) -> Unit,
    shape: Shape,
    selected: Boolean
) {
    AppTheme() {
        val bgColor = if(selected) md_theme_light_primary else  Color.White
        val contentColor = if(selected) Color.White else Color.Black
            OutlinedButton(
                onClick = {
                    onClick.invoke(index)
                    clickItem.invoke(item)
                          },
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = bgColor,
                    contentColor = contentColor
                ),
                shape = shape
            ){
                text()
            }
    }
}

fun isNext(dogInfo: DogInfo, state: Int): Boolean{
    return if (state == 0 && dogInfo.dog_name != "")
        true
    else if (state == 1 && dogInfo.breed != "")
        true
    else if (state == 2 && dogInfo.dog_sex != "" && dogInfo.birth != "")
        true
    else state == 3
}