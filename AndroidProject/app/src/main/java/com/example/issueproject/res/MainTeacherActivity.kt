package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.issueproject.databinding.ActivityMainTeacherBinding
import com.example.issueproject.res.Album.AlbumActivity
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.DayNotic.DayNoticTeacherActivity
import com.example.issueproject.res.Foodlist.FoodlistActivity
import com.example.issueproject.res.Notic.NoticActivity
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.res.SchoolManager.SchoolTeacherListActivity
import com.example.issueproject.retrofit.RetrofitBuilder

class MainTeacherActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainTeacherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")

        binding.textViewName.text = name
        binding.textViewSchool.text = school
        binding.textViewRoom.text = room

        binding.TeacherRoomchildmanagement.setOnClickListener{
            var intent = Intent(this, RoomChildListActivity::class.java).apply{
                putExtra("room",room)
            }
            startActivity(intent)
        }

        binding.TeacherNotic.setOnClickListener {
            var intent = Intent(this, NoticActivity::class.java).apply {
                putExtra("id", id)
                putExtra("name", name)
                putExtra("school", school)
                putExtra("menu", "공지사항")
            }
            startActivity(intent)
        }

        binding.TeacherAlbum.setOnClickListener {
            var intent = Intent(this, AlbumActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
            }
            startActivity(intent)
        }
        binding.TeacherDaliy.setOnClickListener {
//            var intent = Intent(this, CalenActivity::class.java)
//            startActivity(intent)
        }
        binding.TeacherDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticTeacherActivity::class.java).apply {
                putExtra("id", id)
                putExtra("name", name)
                putExtra("school", school)
                putExtra("menu", "알림장")
            }
            startActivity(intent)
        }
        binding.TeacherFoodList.setOnClickListener {
            var intent = Intent(this, FoodlistActivity::class.java)
            startActivity(intent)
        }
        binding.TeacherMedicinemanagement.setOnClickListener {
//            var intent = Intent(this, ::class.java)
//            startActivity(intent)
        }

    }
}