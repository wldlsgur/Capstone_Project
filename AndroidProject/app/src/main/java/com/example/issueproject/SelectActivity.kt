package com.example.issueproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.issueproject.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySelectBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}