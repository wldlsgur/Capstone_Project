package com.example.issueproject.res.Add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.issueproject.databinding.ActivitySchoolAddBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.LoginResult
import com.example.issueproject.dto.Presidentinfo
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.MediaStore

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

private const val TAG = "SchoolAddActivity"
class SchoolAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySchoolAddBinding.inflate(layoutInflater)
    }
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var currentImageUri: Uri

//    private val PICK_FROM_ALBUM = 1
    // key_id 대신에 id주는걸로

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                            binding.imageView?.setImageBitmap(bitmap)
                        } else {
                            val source = ImageDecoder.createSource(this.contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            binding.imageView?.setImageBitmap(bitmap)
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

        binding.buttonSchoolAdd.setOnClickListener {
            Log.d(TAG, "onCreate: ")
            val id = "test2"
            val school = binding.editTextSchoolName.text.toString()
            val room = binding.editTextRoomName.text.toString()
            val number = binding.editTextSchoolNum.text.toString()

            var presidentinfo = Presidentinfo(id, school, room, number)
            SchoolAdd(presidentinfo)
        }
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
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}