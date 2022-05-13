package com.example.issueproject.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.res.MainParentActivity
import com.example.issueproject.retrofit.RetrofitBuilder

class ChildAdapter(var list:MutableList<ParentInfoResult>) : RecyclerView.Adapter<ChildAdapter.ChildListViewHolder>() {

    inner class ChildListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val childschool: TextView = itemView.findViewById(R.id.textViewChildItemSchool)
        private val childroom: TextView = itemView.findViewById(R.id.textViewChildItemRoom)
        private val childname: TextView = itemView.findViewById(R.id.textViewChildItemName)
        private val childimage: ImageView = itemView.findViewById(R.id.imageViewChildItemImage)

        fun bindinfo(data: ParentInfoResult){
            childschool.text = data.school
            childroom.text = data.room
            childname.text = data.child_name

            if(data.image_url != "default"){
                Glide.with(childimage.context)
                    .load("${RetrofitBuilder.servers}/image/parent/${data.image_url}")
                    .into(childimage)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_child_item,parent,false)
        return ChildListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChildListViewHolder, position: Int) {
        holder.bindinfo(list[position])

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}
