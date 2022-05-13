package com.example.issueproject.res

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityMainParentBinding
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.ResponseBody

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
//        val name = intent.getStringExtra("name")
//        binding.textViewName.text = name
    }

    fun GetParentInfo(id: String){
        ResponseService().GetParentInfo(id, object : RetrofitCallback<MutableList<ParentInfoResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
                binding.textViewSchool.text = "등록해주세요"
            }

            override fun onSuccess(code: Int, responseData: MutableList<ParentInfoResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                binding.textViewSchool.text = responseData[0].school
                binding.textViewRoom.text = responseData[0].room
                binding.textViewName.text = responseData[0].child_name

                val childimage: ImageView = binding.imageViewChild

                Glide.with(childimage.context)
                    .load("${RetrofitBuilder.servers}/image/parent/${responseData[0].image_url}")
                    .into(childimage)

            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
                binding.textViewSchool.text = "등록해주세요"
            }
        })
    }
}