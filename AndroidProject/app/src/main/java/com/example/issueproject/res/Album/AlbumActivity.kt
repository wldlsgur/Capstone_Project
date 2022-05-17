package com.example.issueproject.res.Album

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapter.AlbumAdapter
import com.example.issueproject.databinding.ActivityAlbumBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.AddManagementResult
import com.example.issueproject.dto.AlbumResult
import com.example.issueproject.res.submenu.SubChildMunuActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "AlbumActivity"
class AlbumActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityAlbumBinding.inflate(layoutInflater)
    }
    private lateinit var albumAdapter : AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")

        ShowRecycler(school!!, room!!)

        binding.buttonAlbumAdd.setOnClickListener {
            var intent = Intent(this, AddAlbumActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAdapter(lists:MutableList<AlbumResult>){
        albumAdapter = AlbumAdapter(lists, this)

        binding.albumRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }
    }

    private fun ShowRecycler(school: String, room: String) {
        ResponseService().GetAlbumInfo(school, room, object : RetrofitCallback<MutableList<AlbumResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<AlbumResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                for (i in 0..responseData.size-1){
                    initAdapter(responseData)
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }

    private fun GetAlbumInfo(school: String, room: String){
        ResponseService().GetAlbumInfo(school, room, object: RetrofitCallback<MutableList<AlbumResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<AlbumResult>) {
                Log.d(TAG, "onSuccess: $code")
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}