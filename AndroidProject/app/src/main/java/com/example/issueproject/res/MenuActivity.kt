package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.issueproject.databinding.ActivityMenuBinding
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.dto.PresidentinfoResult
import com.example.issueproject.dto.UserInfo
import com.example.issueproject.res.Album.AlbumActivity
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.Foodlist.FoodlistActivity
import com.example.issueproject.res.Notic.NoticActivity
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.res.SchoolManager.SchoolTeacherListActivity
import com.example.issueproject.res.viewmodel.MainViewModels
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MenuActivity"
class MenuActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMenuBinding.inflate(layoutInflater)
    }
    var school : String = ""
    var room : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        Log.d(TAG, "id: $id")
        if (id != null) {
            GetPresidentInfo(id)
        }
        val name = intent.getStringExtra("name")
        binding.textViewName.text = name

        binding.PresidentNotic.setOnClickListener {
            var intent = Intent(this, NoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("menu", "공지사항")
            }
            startActivity(intent)
        }

        binding.PresidentSchoolteachermanagement.setOnClickListener{
            var intent = Intent(this, SchoolTeacherListActivity::class.java).apply {
                putExtra("school", school)
            }
            startActivity(intent)
        }
        binding.PresidentAlbum.setOnClickListener {
            var intent = Intent(this, AlbumActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
            }
            startActivity(intent)
        }
        binding.PresidentDaliy.setOnClickListener {
//            var intent = Intent(this, CalenActivity::class.java)
//            startActivity(intent)
        }
        binding.PresidentDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("menu", "알림장")
            }
            startActivity(intent)
        }
        binding.PresidentFoodList.setOnClickListener {
            var intent = Intent(this, FoodlistActivity::class.java)
            startActivity(intent)
        }
        binding.PresidentMedicinemanagement.setOnClickListener {
//            var intent = Intent(this, ::class.java)
//            startActivity(intent)
        }
    }

    fun GetPresidentInfo(id: String) {
    ResponseService().GetPresidentInfo(id, object : RetrofitCallback<MutableList<PresidentinfoResult>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
                binding.textView2.text = ""
                binding.textViewSchool.text = "등록해주세요"
                Toast.makeText(this@MenuActivity, "어린이집을 먼저 등록해주세요", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(code: Int, responseData: MutableList<PresidentinfoResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                if(responseData.isEmpty()){
                    binding.textView2.text = ""
                    binding.textViewSchool.text = "등록해주세요"
                }
                else{
                    school = responseData[0].school
                    room = responseData[0].room
                    binding.textViewSchool.text = responseData[0].school
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
                binding.textView2.text = ""
                binding.textViewSchool.text = "등록해주세요"
            }
        })
    }
}