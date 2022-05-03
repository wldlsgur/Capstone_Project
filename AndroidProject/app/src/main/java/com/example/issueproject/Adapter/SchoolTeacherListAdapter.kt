package com.example.issueproject.Adapterimport
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.SchoolteacherListResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.ResponseBody

private const val TAG = "SchoolTeacherAdapter"
class SchoolTeacherListAdapter(var list: MutableList<SchoolteacherListResult>) : RecyclerView.Adapter<SchoolTeacherListAdapter.SchoolListViewHolder>() {

    inner class SchoolListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //변수 설정 name, room
        val name: TextView = itemView.findViewById(R.id.textViewSchool_teacher_list_item_name)
        val room: TextView = itemView.findViewById(R.id.textViewSchool_teacher_list_item_room)
        private val image: ImageView = itemView.findViewById(R.id.imageViewTeacherImage)

        fun bindinfo(data: SchoolteacherListResult){
            name.text = data.name
            room.text = data.room
            //GetImageUrl(data.teacher_image)
            //val text= "/image/parents/이정은.jpg"
            //GetImageUrl(text)
            //이미지 target School로 변경
            GetImageUrl("parents", "이정은")

        }

        fun GetImageUrl(target: String, name: String){
            ResponseService().GetImageUrl(target, name, object: RetrofitCallback<ResponseBody>{
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: ")
                }

                override fun onSuccess(code: Int, responseData: ResponseBody) {
                    Log.d(TAG, "onSuccess: $responseData")

                    val bitmap: Bitmap = BitmapFactory.decodeStream(responseData.byteStream())
                    image.setImageBitmap(bitmap)
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