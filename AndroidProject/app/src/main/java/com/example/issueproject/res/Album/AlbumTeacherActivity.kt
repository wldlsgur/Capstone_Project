package com.example.issueproject.res.Album

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapter.AlbumAdapter
import com.example.issueproject.databinding.ActivityAlbumBinding
import com.example.issueproject.databinding.ActivityAlbumTeacherBinding
import com.example.issueproject.dto.AlbumResult
import com.example.issueproject.dto.GetRoom
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "AlbumTeacherActivity"
class AlbumTeacherActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityAlbumTeacherBinding.inflate(layoutInflater)
    }

    private lateinit var albumAdapter : AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")
        val job = intent.getStringExtra("job")

        if(job == "선생님"){
            binding.buttonAlbumTeacherAdd.visibility = View.VISIBLE
        }
        else if(job == "부모님"){
            binding.buttonAlbumTeacherAdd.visibility = View.INVISIBLE
        }

        binding.textViewAlbumTeacherSchool.text = school
        binding.textViewAlbumTeacherRoom.text = room

        ShowRecycler(school!!, room!!)

        binding.buttonAlbumTeacherAdd.setOnClickListener {
            var intent = Intent(this, AddAlbumActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", job)
            }
            startActivity(intent)
        }
    }

    private fun initAdapter(lists:MutableList<AlbumResult>){
        albumAdapter = AlbumAdapter(lists, this)

        binding.albumTeacherRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }
    }

    private fun ShowRecycler(school: String, room: String) {
        ResponseService().GetAlbumInfo(school, room, object :
            RetrofitCallback<MutableList<AlbumResult>> {
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
}
