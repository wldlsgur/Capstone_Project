package com.example.issueproject.res.RoomManager

import RoomChildListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.databinding.ActivityRoomChildListBinding
import com.example.issueproject.dto.RoomChildListResult
import com.example.issueproject.res.Add.ChildAddActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "RoomChildListActivity"
class RoomChildListActivity : AppCompatActivity() {
    lateinit var RoomChildListAdapter: RoomChildListAdapter

    private val binding by lazy{
        ActivityRoomChildListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")

        ShowRecycler(school!!, room!!)

    }

    private fun initRecycler(list:MutableList<RoomChildListResult>){
        RoomChildListAdapter = RoomChildListAdapter(list)

        binding.RoomChildListRV.apply {
            adapter = RoomChildListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun ShowRecycler(school: String, room: String) {
        ResponseService().RoomChildListShow(school, room, object : RetrofitCallback<MutableList<RoomChildListResult>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<RoomChildListResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                initRecycler(responseData)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}