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
import com.example.issueproject.databinding.ActivityChildAddBinding
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.issueproject.dto.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Path
import java.io.*


private const val TAG = "ChildAddActivity"
class ChildAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityChildAddBinding.inflate(layoutInflater)
    }
    private lateinit var getResult: ActivityResultLauncher<Intent>
    var school : String = ""
    var room : String = ""
    var key_id : String = ""
    val itemList = mutableListOf<String>()
    val roomList = mutableListOf<String>()
    private lateinit var currentImageUri: Uri

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
                            binding.imageView2?.setImageBitmap(bitmap)
                        } else {
                            val source = ImageDecoder.createSource(this.contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            binding.imageView2?.setImageBitmap(bitmap)
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

        binding.imageView2.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            getResult.launch(intent)
        }

        binding.spinnerSchool.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                school = itemList[position]
                GetRoom(school)
            }

        }
        binding.spinnerRoom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                room = roomList[position]
            }
        }

        binding.buttonChildAdd.setOnClickListener {
            val id = "test1"
            val schoolname = school
            val roomname = room
            val childage = binding.editTextChildAge.text.toString()
            val childname = binding.editTextChildName.text.toString()
            val childspec = binding.editTextChildSpec.text.toString()
            val parentnum = binding.editTextParentNum.text.toString()

            var parentinfo = ParentInfo(id, schoolname, roomname, parentnum, childname, childage, childspec)
            Log.d(TAG, "onCreate: $parentinfo")
            ChildAdd(parentinfo)

//            if(currentImageUri != null){
//
//            }
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
//        var target = "parent"
//        var key = "이승현12"
//        var value1 = "1"
//        var value2 = "1"
//        var data = "${target}-${key}-${value1}-${value2}"
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
    fun ChildAdd(info: ParentInfo){
        ResponseService().CreateParentinfo(info, object: RetrofitCallback<SignUpResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
                if(responseData.msg == "success"){
                    Toast.makeText(this@ChildAddActivity, "성공", Toast.LENGTH_SHORT).show()
                    GetParentInfo("이승현12")
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }

    fun GetParentInfo(id: String){
        ResponseService().GetParentInfo(id, object : RetrofitCallback<MutableList<ParentInfoResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<ParentInfoResult>) {
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
        ResponseService().GetSchool(object : RetrofitCallback<MutableList<GetSchool>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetSchool>) {
                itemList.clear()
                itemList.add("어린이집")
                for(item in responseData) {
                    itemList.add(item.school)
                }

                val adapter = ArrayAdapter(this@ChildAddActivity, R.layout.simple_spinner_dropdown_item, itemList)
                  binding.spinnerSchool.adapter = adapter

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

                val adapter = ArrayAdapter(this@ChildAddActivity, R.layout.simple_spinner_dropdown_item, roomList)
                binding.spinnerRoom.adapter = adapter
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

}