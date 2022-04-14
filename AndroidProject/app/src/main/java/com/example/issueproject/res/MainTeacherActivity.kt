package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.issueproject.databinding.ActivityMainTeacherBinding

class MainTeacherActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainTeacherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonTeacherChildAdd.setOnClickListener {
            var intent = Intent(this, ChildAddActivity::class.java)
            startActivity(intent)
        }
    }
}