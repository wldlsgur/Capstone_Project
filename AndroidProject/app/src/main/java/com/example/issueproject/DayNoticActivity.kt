package com.example.issueproject

import DayNoticAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.databinding.ActivityDayNoticBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.DayNoticInfo
import com.example.issueproject.dto.LoginResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "DayNoticActivity"
class DayNoticActivity : AppCompatActivity() {
    lateinit var DayNoticAdapter: DayNoticAdapter

    private val binding by lazy{
        ActivityDayNoticBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ShowRecycler("알림장","달서어린이집","햇님반")

        binding.buttonTeacherAdd.setOnClickListener {
            var intent = Intent(this, DayNoticAddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecycler(list:MutableList<AddManagement>){
        DayNoticAdapter = DayNoticAdapter(list)

        binding.DayNoticRV.apply {
            adapter = DayNoticAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun ShowRecycler(menu: String, school: String, room: String) {
        ResponseService().DayNoticInfoShow(
            menu,
            school,
            room,
            object : RetrofitCallback<MutableList<AddManagement>> {
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }

                override fun onSuccess(code: Int, responseData: MutableList<AddManagement>) {
                    Log.d(TAG, "onSuccess: $responseData")
                    initRecycler(responseData)
                }

                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }

            })
    }
}