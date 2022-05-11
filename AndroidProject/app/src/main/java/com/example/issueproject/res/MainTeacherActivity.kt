package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.issueproject.databinding.ActivityMainTeacherBinding
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.retrofit.RetrofitBuilder

class MainTeacherActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainTeacherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.TeacherRoomchildmanagement.setOnClickListener{
            var intent = Intent(this, RoomChildListActivity::class.java).apply{
                putExtra("room", binding.textViewRoom.toString())
            }
            startActivity(intent)
        }


    }
}