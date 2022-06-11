package com.example.issueproject.Adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.CalenderResult
import kotlin.collections.ArrayList

private const val TAG = "DailyItemAdapter"
class DailyItemAdapter(val data: MutableList<CalenderResult>, val title : ArrayList<String>, val color : ArrayList<String>) : RecyclerView.Adapter<DailyItemAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(val layout: View) : RecyclerView.ViewHolder(layout){
        var tit = layout.findViewById<TextView>(R.id.fragment_textitem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_daily_item3,parent,false)
        Log.d(TAG, "hi")
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //var layout = holder.layout.con(R.id.layout_calender_item)


        //for(l in data)
        /*
        {
            Log.d(TAG, l.date)
            if(l.date == day) {
                title.add(l.title)
                color.add(l.color)
            }
        }
*/
        holder.tit.text = title[position]
        if(color[position] =="red") holder.tit.background.setTint(Color.RED)
        else if(color[position] =="blue") holder.tit.background.setTint(Color.BLUE)
        else if(color[position] =="green") holder.tit.background.setTint(Color.GREEN)
        else if(color[position] =="yellow") holder.tit.background.setTint(Color.YELLOW)
        else if(color[position] =="cyan") holder.tit.setBackgroundColor(Color.CYAN)



        //color

    }

    override fun getItemCount(): Int {
        return title.size
    }




}