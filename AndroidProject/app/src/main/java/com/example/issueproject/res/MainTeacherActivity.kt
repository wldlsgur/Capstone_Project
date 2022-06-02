package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityMainTeacherNaviBinding
import com.example.issueproject.res.Album.AlbumActivity
import com.example.issueproject.res.Album.AlbumTeacherActivity
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.DayNotic.DayNoticTeacherActivity
import com.example.issueproject.res.Foodlist.FoodlistActivity
import com.example.issueproject.res.Medicine.TeacherMedicineList
import com.example.issueproject.res.Notic.NoticActivity
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.res.SchoolManager.SchoolTeacherListActivity
import com.example.issueproject.retrofit.RetrofitBuilder
import com.google.android.material.navigation.NavigationView

class MainTeacherActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private val binding by lazy {
        ActivityMainTeacherNaviBinding.inflate(layoutInflater)
    }

    lateinit var id: String
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var school : String
    lateinit var room : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getStringExtra("id")!!
        val name = intent.getStringExtra("name")
        school = intent.getStringExtra("school")!!
        room = intent.getStringExtra("room")!!
        val img_url = intent.getStringExtra("img_url")

        binding.mainTeacher.textViewName.text = name
        binding.mainTeacher.textViewSchool.text = school
        binding.mainTeacher.textViewRoom.text = room

        if (img_url != null) {
            Glide.with(this)
                .load("${RetrofitBuilder.servers}/image/teacher/${img_url}")
                .into(binding.mainTeacher.imageViewTeacher)
        }

        binding.mainTeacher.TeacherRoomchildmanagement.setOnClickListener {
            var intent = Intent(this, RoomChildListActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
            }
            startActivity(intent)
        }

        binding.mainTeacher.TeacherNotic.setOnClickListener {
            var intent = Intent(this, NoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "선생님")
                putExtra("name", name)
                putExtra("menu", "공지사항")
            }
            startActivity(intent)
        }

        binding.mainTeacher.TeacherAlbum.setOnClickListener {
            var intent = Intent(this, AlbumTeacherActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "선생님")
            }
            startActivity(intent)
        }
        binding.mainTeacher.TeacherDaliy.setOnClickListener {
//            var intent = Intent(this, CalenActivity::class.java)
//            startActivity(intent)
        }
        binding.mainTeacher.TeacherDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticTeacherActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "선생님")
                putExtra("name", name)
                putExtra("menu", "알림장")
            }
            startActivity(intent)
        }
        binding.mainTeacher.TeacherFoodList.setOnClickListener {
            var intent = Intent(this, FoodlistActivity::class.java)
            startActivity(intent)
        }
        binding.mainTeacher.TeacherMedicinemanagement.setOnClickListener {
            var intent = Intent(this, TeacherMedicineList::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("img_url", img_url)
            }
            startActivity(intent)
        }
        val toolbar = binding.menuAppbarTeacher.tool // toolBar를 통해 App Bar 생성
        toolbar.setTitle("알림장")
        setSupportActionBar(toolbar) // 툴바 적용
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(true) // 툴바에 타이틀 안보이게

        // 네비게이션 드로어 생성
        drawerLayout = findViewById(R.id.drawer_layout_teacher)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        navigationView = findViewById(R.id.nav_view_teacher)
        navigationView.setNavigationItemSelectedListener(this) //navigation 리스너

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // 클릭한 툴바 메뉴 아이템 id 마다 다르게 실행하도록 설정
        when (item!!.itemId) {
            android.R.id.home -> {
                // 햄버거 버튼 클릭시 네비게이션 드로어 열기
                drawerLayout.openDrawer(Gravity.LEFT)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 드로어 내 아이템 클릭 이벤트 처리하는 함수
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var intent2 = Intent(this, UserInfoChangeActivity::class.java).apply{
            putExtra("id", id)
            putExtra("job", "선생님")
        }
        var intent3 = Intent(this, MainActivity::class.java)

        var roomManage = Intent(this, RoomChildListActivity::class.java).apply {
            putExtra("school",school)
            putExtra("room",room)
        }
        when (item.itemId) {
            R.id.menu_item1 -> startActivity(intent2)
            R.id.menu_item2 -> startActivity(roomManage)
            R.id.menu_item3 -> Toast.makeText(this,"알림",Toast.LENGTH_SHORT).show()
            R.id.menu_item4 -> startActivity(intent3)
        }
        return false
    }
}