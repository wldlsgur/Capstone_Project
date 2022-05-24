import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.dto.RoomChildListResult
import com.example.issueproject.retrofit.RetrofitBuilder

private const val TAG = "RoomChildListAdapter"
class RoomChildListAdapter(var list:MutableList<RoomChildListResult>) : RecyclerView.Adapter<RoomChildListAdapter.RoomListViewHolder>() {

    inner class RoomListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_name)
        private val age: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_age)
        private val parentnumber: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_parentnumber)
        private val spec: TextView = itemView.findViewById(R.id.textViewRoom_child_list_item_spec)
        private val childimage: ImageView = itemView.findViewById(R.id.imageViewChildImage)

        fun bindinfo(data: RoomChildListResult){
            name.text = data.child_name
            age.text = data.child_age
            parentnumber.text = data.number
            spec.text = data.spec

            Glide.with(childimage.context)
                .load("${RetrofitBuilder.servers}/image/parent/${data.image_url}")
                .into(childimage)

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