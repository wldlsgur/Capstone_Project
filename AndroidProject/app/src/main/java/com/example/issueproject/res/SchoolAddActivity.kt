package com.example.issueproject.res

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.issueproject.databinding.ActivitySchoolAddBinding

class SchoolAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySchoolAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//
//    binding.
    }
}