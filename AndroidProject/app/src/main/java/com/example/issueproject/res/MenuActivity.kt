package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.issueproject.databinding.ActivityMenuBinding
import com.example.issueproject.res.DayNotic.DayNoticActivity

class MenuActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val job = intent.getStringExtra("job")
        if(job == "원장님"){
            binding.textViewSchool.text = "햇살어린이집"
            binding.textViewName.text = "원장님"
        }
        else if(job == "선생님"){

        }
        else if(job == "부모님"){

        }

        binding.buttonDayNotice.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }

        binding.buttonNotic.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }

        binding.buttonMenuSchoolAdd.setOnClickListener {
            var intent = Intent(this, SchoolAddActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMenuChildAdd.setOnClickListener {
            var intent = Intent(this, ChildAddActivity::class.java)
            startActivity(intent)
        }
//        binding.buttonRoomManager.setOnClickListener {
//            var intent = Intent(this, )
//        }

    }

}