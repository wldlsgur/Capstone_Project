package com.example.issueproject.res

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityMainParentBinding
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.res.Album.AlbumActivity
import com.example.issueproject.res.Album.AlbumTeacherActivity
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.DayNotic.DayNoticTeacherActivity
import com.example.issueproject.res.Foodlist.FoodlistActivity
import com.example.issueproject.res.Medicine.ParentsMedicineList
import com.example.issueproject.res.Notic.NoticActivity
import com.example.issueproject.res.SchoolManager.SchoolTeacherListActivity
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.ResponseBody

private const val TAG = "MainParentActivity"
class MainParentActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainParentBinding.inflate(layoutInflater)
    }

    var school : String = ""
    var room : String = ""
    var img_url : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val position = intent.getStringExtra("position")!!.toInt()
        val id = intent.getStringExtra("id")!!

        GetParentInfo(id, position)

        Log.d(TAG, "school: $school")
        Log.d(TAG, "room: $room")
        binding.ParentNotic.setOnClickListener{
            var intent = Intent(this, NoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "부모님")
                putExtra("name", binding.textViewName.text)
                putExtra("menu", "공지사항")
            }
            startActivity(intent)
        }

        binding.ParentAlbum.setOnClickListener {
            var intent = Intent(this, AlbumTeacherActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "부모님")
            }
            startActivity(intent)
        }
        binding.ParentDaliy.setOnClickListener {
//            var intent = Intent(this, CalenActivity::class.java)
//            startActivity(intent)
        }
        binding.ParentDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticTeacherActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "부모님")
                putExtra("name", binding.textViewName.text)
                putExtra("menu", "알림장")
            }
            startActivity(intent)
        }
        binding.ParentFoodList.setOnClickListener {
            var intent = Intent(this, FoodlistActivity::class.java)
            startActivity(intent)
        }
        binding.ParentMedicinemanagement.setOnClickListener {
            var intent = Intent(this, ParentsMedicineList::class.java).apply {
                putExtra("id", id.toString())
                putExtra("cname", binding.textViewName.text)
                putExtra("school", school)
                putExtra("room", room)
                putExtra("img_url",img_url)
            }
            startActivity(intent)
        }
    }

    fun GetParentInfo(id: String, position: Int){
        ResponseService().GetParentInfo(id, object : RetrofitCallback<MutableList<ParentInfoResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<ParentInfoResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                binding.textViewSchool.text = responseData[position].school
                binding.textViewRoom.text = responseData[position].room
                binding.textViewName.text = responseData[position].child_name
                img_url = responseData[position].image_url

                if(img_url != null){
                    Glide.with(this@MainParentActivity)
                        .load("${RetrofitBuilder.servers}/image/parent/${img_url}")
                        .into(binding.imageViewChild)
                }
                school = binding.textViewSchool.text.toString()
                room = binding.textViewRoom.text.toString()
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}