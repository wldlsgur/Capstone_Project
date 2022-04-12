package com.example.issueproject.res.Notic

import DayNoticAdapter
import NoticAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityNoticBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.res.AddNoticActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "NoticActivity"
class NoticActivity : AppCompatActivity() {
    lateinit var NoticAdapter: NoticAdapter

    private val binding by lazy{
        ActivityNoticBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ShowRecycler("공지사항","달서어린이집","인혁반")

        binding.buttonNoticAdd.setOnClickListener {
            var intent = Intent(this, AddNoticActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecycler(list:MutableList<AddManagement>){
        NoticAdapter = NoticAdapter(list)

        binding.NoticRV.apply {
            adapter = NoticAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun ShowRecycler(menu: String, school: String, room: String) {
        ResponseService().DayNoticInfoShow(menu, school, room, object :
            RetrofitCallback<MutableList<AddManagement>> {
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