package com.haneum.petconnect.fragment

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haneum.petconnect.MainActivity
import com.haneum.petconnect.MultiImageAdapter
import com.haneum.petconnect.R
import android.database.Cursor
import com.haneum.petconnect.service.NoseRegisterApi
import com.haneum.petconnect.service.NoseRegisterRes
import com.haneum.petconnect.service.NoseRegisterService
import com.haneum.petconnect.service.RegisterDto
import com.haneum.petconnect.service.RetrofitSetting
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mActivity: MainActivity? = null
    private lateinit var mContext: Context
    private lateinit var cbActivityResultLauncher: ActivityResultLauncher<Intent>
    // 이미지 데이터 리스트
    var list = ArrayList<Uri>()
    lateinit var images: Array<File>
    private lateinit var adapter: MultiImageAdapter

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
        adapter = MultiImageAdapter(list,mContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val get_image_btn = view.findViewById<Button>(R.id.getImage)
        val recycler_view = view.findViewById<RecyclerView>(R.id.recyclerView)

        val retrofit = RetrofitSetting.getInstance()
        val service = retrofit.create(NoseRegisterApi::class.java)

        cbActivityResultLauncher = registerForActivityResult(androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                list.clear()

                if (it.data?.clipData != null) { // 사진 여러개 선택한 경우
                    val count = it.data?.clipData!!.itemCount
                    if (count != 5) {
                        Toast.makeText(mContext, "사진을 5장 선택해주세요", Toast.LENGTH_LONG)
                    } else {
                        for (i in 0 until count) {
                            val imageUri = it.data?.clipData!!.getItemAt(i).uri
                            list.add(imageUri)
                            images[i] = File(absolutelyPath(imageUri,mContext))
                        }
                    }
                }else {
                    Toast.makeText(mContext, "사진을 5장 선택해주세요", Toast.LENGTH_LONG)
                }
                noseRegister(images, service)
                adapter.notifyDataSetChanged()
            }
            else {
                Log.d(TAG,"Bluetooth disabled")
            }
        }

        get_image_btn.setOnClickListener{
            var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            cbActivityResultLauncher.launch(intent)
        }
        val layoutManager = LinearLayoutManager(mContext)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter


        // Inflate the layout for this fragment
        return view
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
    // 절대경로 변환
    fun absolutelyPath(path: Uri?, context : Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    fun noseRegister(imageFiles: Array<File>, service: NoseRegisterApi){
        val dogProfile = RequestBody.create(MediaType.parse("image/*"), imageFiles[0])
        val dogNose1 = RequestBody.create(MediaType.parse("image/*"), imageFiles[0])
        val dogNose2 = RequestBody.create(MediaType.parse("image/*"), imageFiles[1])
        val dogNose3 = RequestBody.create(MediaType.parse("image/*"), imageFiles[2])
        val dogNose4 = RequestBody.create(MediaType.parse("image/*"), imageFiles[3])
        val dogNose5 = RequestBody.create(MediaType.parse("image/*"), imageFiles[4])

        val dataMap: HashMap<String, RequestBody> = HashMap()
        dataMap.put("registrant", RequestBody.create(MediaType.parse("text/plain"),"jihwan"))
        dataMap.put("phoneNum", RequestBody.create(MediaType.parse("text/plain"),"01012341234"))
        dataMap.put("email", RequestBody.create(MediaType.parse("text/plain"),"wlghks@naver.com"))
        dataMap.put("dogName", RequestBody.create(MediaType.parse("text/plain"),"jeon"))
        dataMap.put("dogBreed", RequestBody.create(MediaType.parse("text/plain"),"free"))
        dataMap.put("dogBirthYear", RequestBody.create(MediaType.parse("text/plain"),"2020"))
        dataMap.put("dogSex", RequestBody.create(MediaType.parse("text/plain"),"male"))

        val imageMap: HashMap<String, MultipartBody.Part> = HashMap()
        imageMap.put("dogProfile", MultipartBody.Part.createFormData("dogProfile", imageFiles[0].name, dogProfile))
        imageMap.put("dogNose1", MultipartBody.Part.createFormData("dogNose1", imageFiles[0].name, dogNose1))
        imageMap.put("dogNose2", MultipartBody.Part.createFormData("dogNose2", imageFiles[1].name, dogNose2))
        imageMap.put("dogNose3", MultipartBody.Part.createFormData("dogNose3", imageFiles[2].name, dogNose3))
        imageMap.put("dogNose4", MultipartBody.Part.createFormData("dogNose4", imageFiles[3].name, dogNose4))
        imageMap.put("dogNose5", MultipartBody.Part.createFormData("dogNose5", imageFiles[4].name, dogNose5))


        service.postNoseRegister(dataMap, imageMap)?.enqueue(object : Callback<NoseRegisterRes> {
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








