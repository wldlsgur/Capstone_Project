package com.example.issueproject.Adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import okhttp3.ResponseBody

private const val TAG = "AlbumItemAdapter"
class AlbumItemAdapter(var list: MutableList<AlbumResult>) : RecyclerView.Adapter<AlbumItemAdapter.AlbumItemViewHolder>() {

    inner class AlbumItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val image: ImageView = itemView.findViewById(R.id.imageView_Album)

        fun bindinfo(data: AlbumResult){
            for(item in data.image_url){
                for (i in 0..item.length){
                    Glide.with(image.context)
                        .load("${RetrofitBuilder.servers}/album/${item[i]}")
                        .into(image)

//                    GetImageUrl("album", item[i].toString())
                }
            }
        }

//        fun GetImageUrl(target: String, name: String){
//            ResponseService().GetImageUrl(target, name, object: RetrofitCallback<ResponseBody> {
//                override fun onError(t: Throwable) {
//                    Log.d(TAG, "onError: $t")
//                }
//
//                override fun onSuccess(code: Int, responseData: ResponseBody) {
//                    Log.d(TAG, "onSuccess: $code")
//                    val bitmap: Bitmap = BitmapFactory.decodeStream(responseData.byteStream())
//                    image.setImageBitmap(bitmap)
//                }
//
//                override fun onFailure(code: Int) {
//                    Log.d(TAG, "onFailure: $code")
//                }
//            })
//        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_album_item2,parent,false)
        return AlbumItemViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder.bindinfo(list[position])
    }

}