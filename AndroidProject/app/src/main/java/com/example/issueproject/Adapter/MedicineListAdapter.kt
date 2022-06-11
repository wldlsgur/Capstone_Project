package com.example.issueproject.Adapter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.dto.MedicineManagementResult
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.ResponseBody

private const val TAG = "MedicineListAdapter"
class MedicineListAdapter(var list:MutableList<MedicineManagementResult>, var job : String) : RecyclerView.Adapter<MedicineListAdapter.MedicineListViewHolder>() {
    var inv : Boolean = false
    inner class MedicineListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cname: TextView = itemView.findViewById(R.id.stu_cname)
        private val date: TextView = itemView.findViewById(R.id.stu_date)
        val mname: TextView = itemView.findViewById(R.id.stu_mname)
         val childimage: ImageView = itemView.findViewById(R.id.imageViewmedicinelist)
        private val school: TextView = itemView.findViewById(R.id.stu_school)
        private val room: TextView = itemView.findViewById(R.id.stu_room)
        private val con : ConstraintLayout = itemView.findViewById(R.id.ConstraintLayoutmlist)
        val btn: Button = itemView.findViewById(R.id.button_apply)
        var id : String = ""
        val layout : ConstraintLayout = itemView.findViewById(R.id.ConstraintLayoutmlist)
        var checktext : TextView = itemView.findViewById((R.id.textCheck))



        fun bindinfo(data: MedicineManagementResult){
            if(inv == true) layout.visibility = View.INVISIBLE
            checktext.visibility = View.INVISIBLE
            cname.text = data.child_name
            date.text = data.date
            mname.text = data.m_name
            school.text = data.school
            room.text = data.room
            if(job=="학부모") btn.visibility = View.INVISIBLE
            ChildInfo(data.id, data.child_name)
        }

        fun ChildInfo(id: String, name: String){
            ResponseService().ChildInfo(id, name, object : RetrofitCallback<ParentInfoResult> {
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }
                override fun onSuccess(code: Int, responseData: ParentInfoResult) {
                    Log.d(TAG, "onSuccess: $responseData")

                    if(responseData.image_url != "default"){
                        Glide.with(childimage.context)
                            .load("${RetrofitBuilder.servers}/image/parent/${responseData.image_url}")
                            .into(childimage)
                    }
                }

                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }
            })
        }
    }


    override fun onCreateViewHolder(child: ViewGroup, viewType: Int): MedicineListViewHolder {
        val view = LayoutInflater.from(child.context).inflate(R.layout.activity_medicine_list_item,child,false)
        return MedicineListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MedicineListViewHolder, position: Int) {

        holder.childimage.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
        holder.bindinfo(list[position])

        holder.btn.setOnClickListener {
            buttonclickListener.onClick(it, position)
        }
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)

        //fun onClickbtn(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }// (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener


    private lateinit var buttonclickListener : OnItemClickListener
    fun setButtonClickListener(onButtonClickListener: OnItemClickListener) {
        this.buttonclickListener = onButtonClickListener
    }// (4) setItemClickListener로 설정한 함수 실행
}

