package com.example.issueproject.res.Add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.issueproject.databinding.ActivitySchoolAddBinding
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.MediaStore

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.issueproject.dto.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

private const val TAG = "SchoolAddActivity"
class SchoolAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySchoolAddBinding.inflate(layoutInflater)
    }
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var currentImageUri: Uri
    var key_id : String = ""
    var id : String = ""
//    private val PICK_FROM_ALBUM = 1
    // key_id 대신에 id주는걸로

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK && it.data !=null) {
                currentImageUri = it.data?.data!!
                try {
                    currentImageUri?.let {
                        if(Build.VERSION.SDK_INT < 28) {
                            val bitmap = MediaStore.Images.Media.getBitmap(
                                this.contentResolver,
                                currentImageUri
                            )
                            Log.d(TAG, "currentImageUri: $currentImageUri")
                            Log.d(TAG, "bitmap: $bitmap")
                            binding.imageViewSchool?.setImageBitmap(bitmap)
                        } else {
                            val source = ImageDecoder.createSource(this.contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            binding.imageViewSchool?.setImageBitmap(bitmap)
                        }
                    }
                }catch(e:Exception) {
                    e.printStackTrace()
                }
            } else if(it.resultCode == RESULT_CANCELED){
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }else{
                Log.d("ActivityResult","something wrong")
            }
        }

        binding.imageViewSchool.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            getResult.launch(intent)
        }

        binding.buttonSchoolAdd.setOnClickListener {
            Log.d(TAG, "onCreate: ")
            val id = id!!
            val school = binding.editTextSchoolName.text.toString()
            val room = binding.editTextRoomName.text.toString()
            val number = binding.editTextSchoolNum.text.toString()

            var presidentinfo = Presidentinfo(id, school, room, number)
            SchoolAdd(presidentinfo)
        }
    }

    fun savaimage(uri: Uri){
        val file = File(uri!!.path)
        var fileExtension = contentResolver.getType(uri)
        var inputStream : InputStream? = null
        try{
            inputStream = this.contentResolver.openInputStream(uri!!)
        }catch (e : IOException){
            e.printStackTrace()
        }

        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream)
        val requestBody = RequestBody.create(MediaType.parse("image/*"),byteArrayOutputStream.toByteArray())
        val uploadFile = MultipartBody.Part.createFormData("image","${file.name}.${fileExtension?.substring(6)}",requestBody)

        Log.d(TAG, "savaimage: $key_id")
        uploadimage("parent", key_id, uploadFile)
    }

    fun uploadimage(target: String, key: String, file: MultipartBody.Part){
        Log.d(TAG, "uploadimage: ....")
        ResponseService().uploadimage(target, key, file, object : RetrofitCallback<LoginResult>{

            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: LoginResult) {
                Log.d(TAG, "onSuccess: $responseData")
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }

    fun GetPresidentInfo(id: String){
        ResponseService().GetPresidentInfo(id, object : RetrofitCallback<MutableList<PresidentinfoResult>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<PresidentinfoResult>) {
                Log.d(TAG, "onSuccess: ..")
                key_id = responseData[0].key_id.toString()
                savaimage(currentImageUri)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

    fun SchoolAdd(info: Presidentinfo){
        ResponseService().CreatePresidentinfo(info, object: RetrofitCallback<SignUpResult>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }
            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
                if(responseData.msg == "success"){
                    Toast.makeText(this@SchoolAddActivity, "성공", Toast.LENGTH_SHORT).show()
                    GetPresidentInfo(id!!)
                }
            }
            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}