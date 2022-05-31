package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityMenuNaviBinding
import com.example.issueproject.res.Album.AlbumActivity
import com.example.issueproject.res.Calender.DailyActivity
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.Foodlist.FoodlistActivity
import com.example.issueproject.res.Notic.NoticActivity
import com.example.issueproject.res.SchoolManager.SchoolTeacherListActivity
import com.example.issueproject.retrofit.RetrofitBuilder
import com.google.android.material.navigation.NavigationView

private const val TAG = "MenuActivity"
class MenuActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private val binding by lazy{
        ActivityMenuNaviBinding.inflate(layoutInflater)
    }
    var id: String = ""
    var school: String = ""
    var room: String = ""
    var name: String = ""
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var constraintLayout : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getStringExtra("id").toString()
        name = intent.getStringExtra("name").toString()
        school = intent.getStringExtra("school").toString()
        room = intent.getStringExtra("room").toString()
        val img_url = intent.getStringExtra("img_url")
        constraintLayout = findViewById(R.id.menu)
        binding.menu.textViewName.text = name
        binding.menu.textViewSchool.text = school

        if(img_url != null){
            Glide.with(this)
                .load("${RetrofitBuilder.servers}/image/president/${img_url}")
                .into(binding.menu.imageViewPresident)
        }

        binding.menu.PresidentNotic.setOnClickListener {
            var intent = Intent(this, NoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "원장님")
                putExtra("name", name)
                putExtra("menu", "공지사항")
            }
            startActivity(intent)
        }


        binding.menu.PresidentAlbum.setOnClickListener {
            var intent = Intent(this, AlbumActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "원장님")
            }
            startActivity(intent)
        }
        binding.menu.PresidentDaliy.setOnClickListener {
            var intent = Intent(this, DailyActivity::class.java)
            startActivity(intent)
        }
        binding.menu.PresidentDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java).apply {
                putExtra("school", school)
                putExtra("room", room)
                putExtra("job", "원장님")
                putExtra("name", name)
                putExtra("menu", "알림장")
            }
            startActivity(intent)
        }
        binding.menu.PresidentFoodList.setOnClickListener {
            var intent = Intent(this, FoodlistActivity::class.java).apply {
                putExtra("school", school)
                putExtra("id", id)
            }
            startActivity(intent)
        }
        binding.menu.PresidentMedicinemanagement.setOnClickListener {
//            var intent = Intent(this, ::class.java)
//            startActivity(intent)
        }
//        binding.menu.Buttontest.setOnClickListener {
//        var intent = Intent(this, testActivity::class.java)
//        startActivity(intent)
//    }

        val toolbar = binding.menuAppbar.tool // toolBar를 통해 App Bar 생성
        toolbar.setTitle("알림장")
        setSupportActionBar(toolbar) // 툴바 적용
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(true) // 툴바에 타이틀 안보이게

        // 네비게이션 드로어 생성
        drawerLayout = findViewById(R.id.drawer_layout)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this) //navigation 리스너
    }

    // 툴바 메뉴 버튼이 클릭 됐을 때 실행하는 함수
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // 클릭한 툴바 메뉴 아이템 id 마다 다르게 실행하도록 설정
        when (item!!.itemId) {
            android.R.id.home -> {
                // 햄버거 버튼 클릭시 네비게이션 드로어 열기
                drawerLayout.openDrawer(Gravity.RIGHT)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 드로어 내 아이템 클릭 이벤트 처리하는 함수
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var intent2 = Intent(this, UserInfoChangeActivity::class.java).apply{
            putExtra("id", id)
            putExtra("job", "원장님")
        }
        var intent3 = Intent(this, MainActivity::class.java)

        var schoolManage = Intent(this, SchoolTeacherListActivity::class.java).apply {
            putExtra("school",school)
        }
        when (item.itemId) {
            R.id.menu_item1 -> startActivity(intent2)
            R.id.menu_item2 -> startActivity(schoolManage)
            R.id.menu_item3 -> Toast.makeText(this,"알림",Toast.LENGTH_SHORT).show()
            R.id.menu_item4 -> startActivity(intent3)
        }
        return false
    }


}