package com.example.issueproject.res.RoomManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapter.RoomChildListAdapter
import com.example.issueproject.databinding.ActivityRoomChildListBinding
import com.example.issueproject.dto.ParentInfo
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
        ShowRecycler("햇살어린이집")
    }

    private fun initRecycler(list:MutableList<ParentInfo>){
        RoomChildListAdapter = RoomChildListAdapter(list)

        binding.RoomChildListRV.apply {
            adapter = RoomChildListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun ShowRecycler(school: String) {
        ResponseService().RoomChildListShow(school, object : RetrofitCallback<MutableList<ParentInfo>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<ParentInfo>) {
                Log.d(TAG, "onSuccess: $responseData")
                initRecycler(responseData)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
        
    }
}