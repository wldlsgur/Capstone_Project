package com.example.issueproject.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issueproject.R
import com.example.issueproject.dto.AlbumResult
import com.example.issueproject.retrofit.RetrofitBuilder

private const val TAG = "AlbumItemAdapter"
class AlbumItemAdapter(var list: List<String>) : RecyclerView.Adapter<AlbumItemAdapter.AlbumItemViewHolder>() {

    inner class AlbumItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.imageView_Album)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_album_item2,parent,false)
        return AlbumItemViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        val imagelist = list[position]

        Glide.with(holder.image.context)
            .load("${RetrofitBuilder.servers}/image/album/${imagelist}")
            .into(holder.image)
    }
}