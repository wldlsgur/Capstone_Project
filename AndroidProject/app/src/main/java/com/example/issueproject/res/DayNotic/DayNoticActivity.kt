package com.example.issueproject.res.DayNotic

import DayNoticAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.databinding.ActivityDayNoticBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.res.Add.AddNoticActivity
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

        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")
        val menu = intent.getStringExtra("menu")
        Log.d(TAG, "onCreate: $school")
        Log.d(TAG, "onCreate: $room")
        Log.d(TAG, "onCreate: $menu")
        ShowRecycler(menu!!,school!!,room!!)

        binding.buttonDaynoticAdd.setOnClickListener {
            var intent = Intent(this, AddNoticActivity::class.java).apply {
                putExtra("menu", menu)
            }
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

    private fun ShowRecycler(menu: String, school: String, room: String) {
        ResponseService().DayNoticInfoShow(menu, school, room, object : RetrofitCallback<MutableList<AddManagement>> {
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