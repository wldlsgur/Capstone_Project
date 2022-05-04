package com.example.issueproject.Adapterimport
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.MedicineManage
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.ResponseBody

private const val TAG = "MedicineListAdapter"
class MedicineListAdapter(var list:MutableList<MedicineManage>) : RecyclerView.Adapter<MedicineListAdapter.MedicineListViewHolder>() {

    inner class MedicineListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cname: TextView = itemView.findViewById(R.id.stu_cname)
        private val date: TextView = itemView.findViewById(R.id.medicine_date)
        val mname: TextView = itemView.findViewById(R.id.stu_mname)
        private val din: CheckBox = itemView.findViewById(R.id.check_din)
        private val mor: CheckBox = itemView.findViewById(R.id.check_mor)
        private val lun: CheckBox = itemView.findViewById(R.id.check_lun)
        private val childimage: ImageView = itemView.findViewById(R.id.imageViewmedicinelist)
        private val con : ConstraintLayout = itemView.findViewById(R.id.ConstraintLayoutmlist)
        private val btn: Button = itemView.findViewById(R.id.button_apply)



        fun bindinfo(data: MedicineManage){
            cname.text = data.cname
            date.text = data.date
            mname.text = data.mname
            if(data.mor == true)
                mor.visibility = View.VISIBLE
            else mor.visibility = View.INVISIBLE

            if(data.lun == true)
                lun.visibility = View.VISIBLE
            else lun.visibility = View.INVISIBLE

            if(data.din == true)
                din.visibility = View.VISIBLE
            else din.visibility = View.INVISIBLE





            //GetImageUrl(data.child_image)
            //val text= "/image/parents/이정은.jpg"
            //GetImageUrl(text)
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
                    childimage.setImageBitmap(bitmap)
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
