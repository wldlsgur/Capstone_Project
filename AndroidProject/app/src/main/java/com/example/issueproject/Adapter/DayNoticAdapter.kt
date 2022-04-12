import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.AddManagement

class DayNoticAdapter(var list:MutableList<AddManagement>) : RecyclerView.Adapter<DayNoticAdapter.NoticeViewHolder>() {

    inner class NoticeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val date: TextView = itemView.findViewById(R.id.textViewDay_notic_item_date)
        private val title: TextView = itemView.findViewById(R.id.textViewDay_notic_item_title)
        private val content: TextView = itemView.findViewById(R.id.textViewDay_notic_item_content)

        fun bindinfo(data:AddManagement){
            //"${data.year}년 ${data.month}월 ${data.day}일"
            date.text = data.date
            title.text = "제목: " + data.title
            content.text = data.content
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