package com.example.issueproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.ParentInfo

class RoomChildListAdapter(var list:MutableList<ParentInfo>) : RecyclerView.Adapter<RoomChildListAdapter.RoomListViewHolder>() {

    inner class RoomListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.textViewNotic_item_title)
        private val date: TextView = itemView.findViewById(R.id.textViewNotic_item_date)
        fun bindinfo(data: ParentInfo){
//            date.text = "${data.year}년 ${data.month}월 ${data.day}일"
//            title.text = data.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_room_child_list_item,parent,false)
        return RoomListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RoomListViewHolder, position: Int) {
        holder.bindinfo(list[position])
    }

}