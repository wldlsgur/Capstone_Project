package com.example.issueproject.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import java.util.*

class DailyAdapter(var context: Context, val date:ArrayList<String>) : RecyclerView.Adapter<DailyAdapter.MonthViewHolder>(){
    val center = Int.MAX_VALUE/2
    private var calendar = Calendar.getInstance()

    inner class MonthViewHolder(val layout: View):RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_daily_item,parent,false)
        return MonthViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        calendar.time = Date()
        calendar.set(Calendar.DAY_OF_MONTH,1)
        calendar.add(Calendar.MONTH,position-center)

        // Month를 해당 영어로 바꾸기 위한 과정
        holder.layout.findViewById<TextView>(R.id.fragment_calender_dateMonth).text = "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH)+1}월"
        val tmpMonth = calendar.get(Calendar.MONTH)
        var dayList:MutableList<Date> = MutableList(6*7){Date()}

        for(i in 0..5){
            for(j in 0..6){
                calendar.add(Calendar.DAY_OF_MONTH, (1-calendar.get(Calendar.DAY_OF_WEEK))+j)
                dayList[i*7+j] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH,1)
        }

        var dayAdapter = DailyDateAdapter(tmpMonth, dayList, date)
        holder.layout.findViewById<RecyclerView>(R.id.fragment_calender_dayRv).apply {
            layoutManager = GridLayoutManager(holder.layout.context,7)
            adapter = dayAdapter
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
//    fun converEnglishMonth(month:Int) :String{
//        if(month == 1){
//            return "1월"
//        }
//        if(month == 2){
//            return "2월"
//        }
//        if(month == 3){
//            return "3월"
//        }
//        if(month == 4){
//            return "4월"
//        }
//        if(month == 5){
//            return "5월"
//        }
//        if(month == 6){
//            return "6월"
//        }
//        if(month == 7){
//            return "7월"
//        }
//        if(month == 8){
//            return "8월"
//        }
//        if(month == 9){
//            return "9월"
//        }
//        if(month == 10){
//            return "10월"
//        }
//        if(month == 11){
//            return "11월"
//        }
//        if(month == 12){
//            return "12월"
//        }
//        return ""
//    }
}