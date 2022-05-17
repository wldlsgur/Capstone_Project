package com.example.issueproject.Adapterimport

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.dto.SchoolteacherListResult
import com.example.issueproject.dto.UserInfo
import com.example.issueproject.res.submenu.SubChildMunuActivity
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "SchoolTeacherAdapter"
class SchoolTeacherListAdapter(var list: MutableList<SchoolteacherListResult>) : RecyclerView.Adapter<SchoolTeacherListAdapter.SchoolListViewHolder>() {

    inner class SchoolListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //변수 설정 room, number
        val number: TextView = itemView.findViewById(R.id.textViewSchool_teacher_list_item_number)
        val room: TextView = itemView.findViewById(R.id.textViewSchool_teacher_list_item_room)
        val name: TextView = itemView.findViewById(R.id.textViewSchool_teacher_list_item_name)
        private val image: ImageView = itemView.findViewById(R.id.imageViewTeacherImage)

        fun bindinfo(data: SchoolteacherListResult){
            room.text = data.room
            number.text = data.number

            GetUserInfo(data.id)

            Glide.with(image.context)
                .load("${RetrofitBuilder.servers}/image/teacher/${data.image_url}")
                .into(image)

        }

        fun GetUserInfo(id: String){
            ResponseService().GetUserInfo(id, object : RetrofitCallback<UserInfo> {
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }

                override fun onSuccess(code: Int, responseData: UserInfo) {
                    Log.d(TAG, "onSuccess: $responseData")
                    name.text = responseData.name
                }
                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }
            })
        }
    }

    override fun onCreateViewHolder(teacher: ViewGroup, viewType: Int): SchoolListViewHolder {
        val view = LayoutInflater.from(teacher.context).inflate(R.layout.activity_school_teacher_list_item,teacher,false)
        return SchoolListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SchoolListViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
        holder.bindinfo(list[position])
    }
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