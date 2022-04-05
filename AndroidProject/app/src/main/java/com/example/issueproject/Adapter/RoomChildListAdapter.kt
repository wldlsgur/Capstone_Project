import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.RoomChildListResult

class RoomChildListAdapter(var list:MutableList<RoomChildListResult>) : RecyclerView.Adapter<RoomChildListAdapter.RoomListViewHolder>() {

    inner class RoomListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_name)
        private val age: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_age)
        private val parentnumber: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_parentnumber)
        private val spec: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_spec)

        fun bindinfo(data: RoomChildListResult){
            name.text = data.child_name
            age.text = data.child_age
            parentnumber.text = data.parent_num
            spec.text = data.spec
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