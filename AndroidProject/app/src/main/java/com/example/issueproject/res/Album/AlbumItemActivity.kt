package com.example.issueproject.res.Album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapter.AlbumItemAdapter
import com.example.issueproject.databinding.ActivityAlbumItemBinding
import com.example.issueproject.dto.AlbumResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "AlbumItemActivity"
class AlbumItemActivity : AppCompatActivity() {
    lateinit var AlbumItemAdapter: AlbumItemAdapter

    private val binding by lazy{
        ActivityAlbumItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")
        ShowRecycler(school!!, room!!)
    }

    private fun initRecycler(list:MutableList<AlbumResult>){
        AlbumItemAdapter = AlbumItemAdapter(list)

        binding.AlbumImageRV.apply {
            adapter = AlbumItemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun ShowRecycler(school: String, room: String) {
        ResponseService().GetAlbumInfo(school, room, object : RetrofitCallback<MutableList<AlbumResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<AlbumResult>) {
                initRecycler(responseData)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }


        })
    }
}