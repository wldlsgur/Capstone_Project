import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.DayNoticInfo

class DayNoticAdapter(var list:MutableList<AddManagement>) : RecyclerView.Adapter<DayNoticAdapter.NoticeViewHolder>() {

    inner class NoticeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val date: TextView = itemView.findViewById(R.id.textViewDay_notic_item_date)
        private val title: TextView = itemView.findViewById(R.id.textViewDay_notic_item_title)
        fun bindinfo(data:AddManagement){
            date.text = "${data.year}-${data.month}-${data.day}"
            title.text = data.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_day_notic_item,parent,false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bindinfo(list[position])
    }

}