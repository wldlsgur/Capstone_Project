package com.example.issueproject.res

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.issueproject.databinding.ActivityMainParentBinding
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MainParentActivity"
class MainParentActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainParentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        Log.d(TAG, "id: $id")
        if (id != null) {
            GetParentInfo(id)
        }
        val name = intent.getStringExtra("name")
        binding.textViewName.text = name
    }

    fun GetParentInfo(id: String){
        ResponseService().GetParentInfo(id, object : RetrofitCallback<MutableList<ParentInfoResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
                binding.textViewSchool.text = "어린이집을 등록해주세요"
            }

            override fun onSuccess(code: Int, responseData: MutableList<ParentInfoResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                binding.textViewSchool.text = responseData[0].school
                binding.textViewRoom.text = responseData[0].room
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
                binding.textViewSchool.text = "어린이집을 등록해주세요"
            }
        })
    }
}