package com.example.issueproject.res.Calender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.Adapter.DailyAdapter
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityDailyBinding
import com.example.issueproject.databinding.ActivityMenuBinding
import com.example.issueproject.res.Add.DailyAddActivity

class DailyActivity : AppCompatActivity() {
    private lateinit var dailyAdapter: DailyAdapter
    private val binding by lazy{
        ActivityDailyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()

        binding.floatingActionButtonAddDaily.setOnClickListener {
            var intent = Intent(this, DailyAddActivity::class.java)
            startActivity(intent)
        }
    }
    fun setListener(){
        initCalendar()
    }
    fun initCalendar(){
        var date = arrayListOf<String>("2022년 05월 24일","2021년 05월 28일","2021년 05월 12일","2021년 05월 18일")

        dailyAdapter = DailyAdapter(this, date)
        findViewById<RecyclerView>(R.id.customCalendar).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            adapter = dailyAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }
        val snap = PagerSnapHelper()
        if(findViewById<RecyclerView>(R.id.customCalendar).onFlingListener == null){
            snap.attachToRecyclerView(findViewById<RecyclerView>(R.id.customCalendar))
        }
    }

}