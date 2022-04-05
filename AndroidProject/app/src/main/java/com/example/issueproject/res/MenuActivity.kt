package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.issueproject.databinding.ActivityMenuBinding
import com.example.issueproject.dto.UserInfo
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MenuActivity"
class MenuActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        Log.d(TAG, "id: $id")
        if (id != null) {
            GetUserInfo(id)
        }

        binding.buttonDayNotice.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }

        binding.buttonNotic.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }

        binding.buttonMenuSchoolAdd.setOnClickListener {
            var intent = Intent(this, SchoolAddActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMenuChildAdd.setOnClickListener {
            var intent = Intent(this, ChildAddActivity::class.java)
            startActivity(intent)
        }
        binding.buttonRoomManager.setOnClickListener {
            var intent = Intent(this, RoomChildListActivity::class.java)
            startActivity(intent)
        }

    }
    fun GetUserInfo(id: String){
        ResponseService().GetUserInfo(id, object : RetrofitCallback<UserInfo> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: UserInfo) {
                Log.d(TAG, "onSuccess: $responseData")
                val job = responseData.job

                if(job == "원장님"){
                    binding.textViewSchool.text = "햇살어린이집"
                    binding.textViewName.text = "원장님"
                }
                else if(job == "선생님"){
                    binding.textViewSchool.text = "햇살어린이집"
                    binding.textViewName.text = "선생님"
                }
                else if(job == "부모님"){
                    binding.textViewSchool.text = "햇살어린이집"
                    binding.textViewName.text = "부모님"
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

}