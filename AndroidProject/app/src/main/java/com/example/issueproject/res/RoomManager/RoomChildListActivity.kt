package com.example.issueproject.res.RoomManager

import RoomChildListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapterimport.SchoolTeacherListAdapter
import com.example.issueproject.databinding.ActivityRoomChildListBinding
import com.example.issueproject.dto.AgreeChange
import com.example.issueproject.dto.RoomChildListResult
import com.example.issueproject.dto.SchoolteacherListResult
import com.example.issueproject.dto.SignUpResult
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

        binding.textViewChildListRoom.text = room

        ShowRecycler(school!!, room!!)

    }

    private fun initRecycler(list:MutableList<RoomChildListResult>){
        RoomChildListAdapter = RoomChildListAdapter(this, list)

        binding.RoomChildListRV.apply {
            adapter = RoomChildListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        // item 승인 버튼 클릭 이벤트
        RoomChildListAdapter.setApprovalItemClickListener(object : RoomChildListAdapter.MenuClickListener {
            override fun onClick(position: Int, item: RoomChildListResult) {
                val key_id = AgreeChange(item.key_id)
                Agreechange(key_id)
            }
        })

        // item 승인취소 버튼 클릭 이벤트
        RoomChildListAdapter.setCancelApprovalItemClickListener(object : RoomChildListAdapter.MenuClickListener {
            override fun onClick(position: Int, item: RoomChildListResult) {
                val key_id = AgreeChange(item.key_id)
                Deletechildlist(key_id)
            }
        })

        // item 수정 클릭 이벤트
        RoomChildListAdapter.setModifyItemClickListener(object : RoomChildListAdapter.MenuClickListener {
            override fun onClick(position: Int, item: RoomChildListResult) {

            }
        })

        // item 삭제 클릭 이벤트
        RoomChildListAdapter.setDeleteItemClickListener(object : RoomChildListAdapter.MenuClickListener {
            override fun onClick(position: Int, item: RoomChildListResult) {
                val key_id = AgreeChange(item.key_id)
                Deletechildlist(key_id)
            }
        })
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

    private fun Agreechange(agreechange : AgreeChange) {
        ResponseService().Agreechange(agreechange, object : RetrofitCallback<SignUpResult>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

    private fun Deletechildlist(agreechange : AgreeChange) {
        ResponseService().Deletechildlist(agreechange, object : RetrofitCallback<SignUpResult>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}