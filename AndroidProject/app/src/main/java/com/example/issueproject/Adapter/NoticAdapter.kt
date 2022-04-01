import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.AddManagement

class NoticAdapter(var list:MutableList<AddManagement>) : RecyclerView.Adapter<NoticAdapter.NoticeViewHolder>() {

    inner class NoticeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.textViewNotic_item_title)
        private val date: TextView = itemView.findViewById(R.id.textViewNotic_item_date)
        fun bindinfo(data:AddManagement){
            date.text = "${data.year}년${data.month}월${data.day}일"
            title.text = data.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_notic_item,parent,false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bindinfo(list[position])
    }

}