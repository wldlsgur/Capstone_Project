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

        binding.buttonDayNotice.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }

        binding.buttonNotic.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }
    }

}