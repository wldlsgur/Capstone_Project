package com.example.issueproject.res

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

private const val TAG = "SchoolAddActivity"
class SchoolAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySchoolAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSchoolAdd.setOnClickListener {
            Log.d(TAG, "onCreate: ")
            val id = "test"
            val school = binding.editTextSchoolName.text.toString()
            val room = binding.editTextRoomName.text.toString()
            val num = binding.editTextSchoolNum.text.toString()
            val image = "defult"

            var presidentinfo = Presidentinfo(id, school, room, num, image)
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