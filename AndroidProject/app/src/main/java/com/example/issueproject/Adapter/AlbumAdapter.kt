package com.example.issueproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.issueproject.R
import com.example.issueproject.dto.AlbumResult

private const val TAG = "AlbumItemAdapter"
class AlbumAdapter(var list: MutableList<AlbumResult>,var context: Context) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.Album_textview_title)
        private val date: TextView = itemView.findViewById(R.id.Album_textview_date)
//        private val rv: RecyclerView = itemView.findViewById(R.id.Album_image_RV)
//                ImageView = itemView.findViewById(R.id.imageView_Album)

        fun bindinfo(data: AlbumResult){
            title.text = data.title
            date.text = data.date
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_album_item2,parent,false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.apply {
            bindinfo(list[position])
//            var lists = mutableListOf<AlbumResult>()
            var albumItemAdapter = AlbumItemAdapter(list)
            itemView.findViewById<RecyclerView>(R.id.Album_image_RV).apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                adapter = albumItemAdapter
            }
        }

    }

}