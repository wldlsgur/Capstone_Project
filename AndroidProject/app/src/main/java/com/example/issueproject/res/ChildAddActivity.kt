package com.example.issueproject.res

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.issueproject.databinding.ActivityChildAddBinding
import com.example.issueproject.dto.ParentInfo
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "ChildAddActivity"
class ChildAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityChildAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonChildAdd.setOnClickListener {
            val id = "test"
            val school = "달서어린이집"
            val room = "인혁반"
            val childage = binding.editTextChildAge.text.toString()
            val childname = binding.editTextChildName.text.toString()
            val childspec = binding.editTextChildSpec.text.toString()
            val parentnum = binding.editTextParentNum.text.toString()
            val image = "default"

            var parentinfo = ParentInfo(id, school, room, parentnum, childname, childage, image, childspec)
            ChildAdd(parentinfo)
        }
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
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}