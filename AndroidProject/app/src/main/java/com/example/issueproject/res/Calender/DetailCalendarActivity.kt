package com.example.issueproject.res.Calender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.issueproject.databinding.ActivityDetailCalendarBinding

class DetailCalendarActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityDetailCalendarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}