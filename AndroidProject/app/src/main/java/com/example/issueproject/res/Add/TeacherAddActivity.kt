package com.example.issueproject.res.Add

import android.R
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.issueproject.databinding.ActivityTeacherAddBinding
import com.example.issueproject.dto.*
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

private const val TAG = "TeacherAddActivity"
class TeacherAddActivity : AppCompatActivity() {
    private lateinit var getResult: ActivityResultLauncher<Intent>
    var school : String = ""
    var room : String = ""
    var key_id : String = ""
    val itemList = mutableListOf<String>()
    val roomList = mutableListOf<String>()
    private lateinit var currentImageUri: Uri

    private val binding by lazy{
    ActivityTeacherAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        GetSchool()

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
                            binding.TeacherAddImageView?.setImageBitmap(bitmap)
                        } else {
                            val source = ImageDecoder.createSource(this.contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            binding.TeacherAddImageView?.setImageBitmap(bitmap)
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

        binding.TeacherAddImageView?.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            getResult.launch(intent)
        }

        binding.TeacherAddButtonAdd.setOnClickListener {
            val id = "test1"
            val schoolname = school
            val roomname = room
            val teachernum = binding.TeacherAddEditTextNumber.text.toString()

            var teacherinfo = TeacherInfo(id, schoolname, roomname, teachernum)
            Log.d(TAG, "onCreate: $teacherinfo")
            TeacherAdd(teacherinfo)

//            if(currentImageUri != null){
//
//            }
        }

        binding.TeacherAddSpinnerSchool.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                school = itemList[position]
                GetRoom(school)
            }

        }
        binding.TeacherAddSpinnerRoom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                room = roomList[position]
            }
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

    fun TeacherAdd(info: TeacherInfo){
        ResponseService().CreateTeacherinfo(info, object: RetrofitCallback<SignUpResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
                if(responseData.msg == "success"){
                    Toast.makeText(this@TeacherAddActivity, "성공", Toast.LENGTH_SHORT).show()
                    GetTeacherInfo("이승현12")
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }

    fun GetTeacherInfo(id: String){
        ResponseService().GetTeacherInfo(id, object : RetrofitCallback<MutableList<TeacherinfoResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<TeacherinfoResult>) {
                Log.d(TAG, "onSuccess: ..")
                key_id = responseData[0].key_id.toString()
                savaimage(currentImageUri)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: ..")
            }
        })
    }

    fun GetSchool(){
        ResponseService().GetSchool(object : RetrofitCallback<MutableList<GetSchool>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetSchool>) {
                itemList.clear()
                itemList.add("어린이집")
                for(item in responseData) {
                    itemList.add(item.school)
                }

                val adapter = ArrayAdapter(this@TeacherAddActivity, R.layout.simple_spinner_dropdown_item, itemList)
                binding.TeacherAddSpinnerSchool.adapter = adapter

            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

    fun GetRoom(school: String){
        ResponseService().GetRoom(school, object: RetrofitCallback<MutableList<GetRoom>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetRoom>) {
                roomList.clear()
                roomList.add("반")
                for(item in responseData) {
                    roomList.add(item.room)
                }

                val adapter = ArrayAdapter(this@TeacherAddActivity, R.layout.simple_spinner_dropdown_item, roomList)
                binding.TeacherAddSpinnerRoom.adapter = adapter
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}