package com.example.issueproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.dto.GetFoodList
import com.example.issueproject.dto.GetSchoolManagement
import com.example.issueproject.dto.SchoolteacherListResult
import com.example.issueproject.retrofit.RetrofitBuilder

class FoodListAdapter(val context: Context) : RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {

    var list: MutableList<GetFoodList> = mutableListOf()

    inner class FoodListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val FoodListImage: ImageView = itemView.findViewById(R.id.FoodListImage)
        val FoodListdate: TextView = itemView.findViewById(R.id.textViewFoodListDate)
        val deleteFoodlist: ImageView = itemView.findViewById(R.id.deleteFoodList)

        fun bindinfo(data: GetFoodList) {
            FoodListdate.text = data.date

            val image_url = data.image_url

            if(data.image_url != "default") {
                Glide.with(FoodListImage.context)
                    .load("${RetrofitBuilder.servers}/image/food/${image_url}")
                    .into(FoodListImage)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_food_list_item, parent, false)
        return FoodListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FoodListAdapter.FoodListViewHolder, position: Int) {
//        holder.bindinfo(list[position])
        val item = list[position]

        holder.apply {
            bindinfo(item)

            deleteFoodlist.setOnClickListener{
                deleteItemClickListener.onClick(position, item)
            }
        }


    }

    interface MenuClickListener {
        fun onClick(position: Int, item : GetFoodList)
    }

    private lateinit var deleteItemClickListener : MenuClickListener
    fun setDeleteItemClickListener(deleteClickListener: MenuClickListener) {
        this.deleteItemClickListener = deleteClickListener
    }
}