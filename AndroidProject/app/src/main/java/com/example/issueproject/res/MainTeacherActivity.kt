package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.issueproject.databinding.ActivityMainTeacherBinding
import com.example.issueproject.res.Album.AlbumActivity
import com.example.issueproject.res.Album.AlbumTeacherActivity
import com.example.issueproject.res.Calender.DailyActivity
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.DayNotic.DayNoticTeacherActivity
import com.example.issueproject.res.Foodlist.FoodlistActivity
import com.example.issueproject.res.Medicine.TeacherMedicineList
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
        val img_url = intent.getStringExtra("img_url")

        binding.textViewName.text = name
        binding.textViewSchool.text = school
        binding.textViewRoom.text = room

        if(img_url != null){
            Glide.with(this)
                .load("${RetrofitBuilder.servers}/image/teacher/${img_url}")
                .into(binding.imageViewTeacher)
        }

        binding.TeacherRoomchildmanagement.setOnClickListener{
            var intent = Intent(this, RoomChildListActivity::class.java).apply{
                putExtra("school", school)
                putExtra("room",room)
            }
            startActivity(intent)
        }

        binding.TeacherNotic.setOnClickListener {
            var intent = Intent(this, NoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "선생님")
                putExtra("name", name)
                putExtra("menu", "공지사항")
            }
            startActivity(intent)
        }

        binding.TeacherAlbum.setOnClickListener {
            var intent = Intent(this, AlbumTeacherActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "선생님")
            }
            startActivity(intent)
        }
        binding.TeacherDaliy.setOnClickListener {
            var intent = Intent(this, DailyActivity::class.java)
            startActivity(intent)
        }
        binding.TeacherDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticTeacherActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "선생님")
                putExtra("name", name)
                putExtra("menu", "알림장")
            }
            startActivity(intent)
        }
        binding.TeacherFoodList.setOnClickListener {
            var intent = Intent(this, FoodlistActivity::class.java).apply {
                putExtra("school", school)
                putExtra("id", id)
            }
            startActivity(intent)
        }
        binding.TeacherMedicinemanagement.setOnClickListener {
            var intent = Intent(this, TeacherMedicineList::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("img_url",img_url)
            }
            startActivity(intent)
        }

    }
}