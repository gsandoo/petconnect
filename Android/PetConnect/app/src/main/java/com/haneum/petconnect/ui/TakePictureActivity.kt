package com.haneum.petconnect.ui




import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.haneum.petconnect.CheckEyeActivity
import com.haneum.petconnect.CheckResultActivity
import com.haneum.petconnect.CheckSkinActivity
import com.haneum.petconnect.R
import com.haneum.petconnect.databinding.ActivityTakePictureBinding
import java.io.File
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TakePictureActivity : AppCompatActivity() {
    private var imageCapture: ImageCapture? = null
    private lateinit var binding: ActivityTakePictureBinding
    lateinit var dogName: String

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTakePictureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat
                .requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        binding.btnMainCapture.setOnClickListener { takePhoto() }
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture
            .OutputFileOptions
            .Builder(photoFile)
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val message = "Photo capture succeeded: $savedUri"
                    Log.d(TAG, message)
                    intentActivity(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                    Toast.makeText(applicationContext, "촬영 실패, 다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Future니까....
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                // setSurfaceProvider는 어떤 함수인지 찾아봐야겠다
                .also { it.setSurfaceProvider(binding.previewMain.surfaceProvider) }

            imageCapture = ImageCapture.Builder().build()

            // 일단 후면 카메라 선택해
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            runCatching {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            }.onFailure { Log.e(TAG, "Use case binding failed", it) }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun getOutputDirectory(): File {
        val mediaDirectory = externalMediaDirs.firstOrNull()?.let {
            // 파일을 왜 이렇게 정의할까?
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDirectory != null && mediaDirectory.exists()) mediaDirectory else filesDir
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        // checkSelfPermission에 대해서 정리해야겠다.
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun intentActivity(uri: Uri){
        val kind = intent.getStringExtra("kind").toString()
        val dogId = intent.getStringExtra("dogId").toString()
        val dogName = intent.getStringExtra("dogName").toString()

        var intent: Intent = Intent()
        when(kind){
            "eye" -> intent = Intent(applicationContext, CheckEyeActivity::class.java)
            "skin" -> intent = Intent(applicationContext, CheckSkinActivity::class.java)
            "feed" -> intent = Intent(applicationContext, CheckEyeActivity::class.java)
            "lookup" -> intent = Intent(applicationContext, CheckResultActivity::class.java)
        }

        Toast.makeText(applicationContext, kind, Toast.LENGTH_SHORT).show()
        intent.putExtra("fileUri", uri.toString())
        intent.putExtra("dogId",dogId)
        intent.putExtra("dogName",dogName)
        startActivity(intent)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Executor.shutDown() 함수는 어떤 역할이지?
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}