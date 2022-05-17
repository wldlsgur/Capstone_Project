package com.example.issueproject.res.Album

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.issueproject.databinding.ActivityAddAlbumBinding
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.example.issueproject.Adapter.AddAlbumAdapter
import java.lang.Exception

private const val TAG = "AddAlbumActivity"
class AddAlbumActivity : AppCompatActivity() {
    lateinit var AddAlbumAdapter: AddAlbumAdapter

    private val binding by lazy{
        ActivityAddAlbumBinding.inflate(layoutInflater)
    }
    val itemList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageViewAlbumImageAdd.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(intent, 200)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {   // 어떤 이미지도 선택하지 않은 경우
            Toast.makeText(this, "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show()
        } else {   // 이미지를 하나라도 선택한 경우
            if (data.clipData == null) {     // 이미지를 하나만 선택한 경우
                Log.e("single choice: ", data.data.toString())
                val imageUri = data.data
                itemList.add(imageUri.toString())

                AddAlbumAdapter = AddAlbumAdapter(itemList)

                binding.imageRV.apply {
                    adapter = AddAlbumAdapter
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                }

            } else {      // 이미지를 여러장 선택한 경우
                val clipData = data.clipData
                Log.e("clipData", clipData!!.itemCount.toString())
                if (clipData.itemCount > 10) {   // 선택한 이미지가 11장 이상인 경우
                    Toast.makeText(this, "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG)
                        .show()
                } else {   // 선택한 이미지가 1장 이상 10장 이하인 경우
                    Log.e(TAG, "multiple choice")
                    for (i in 0 until clipData.itemCount) {
                        val imageUri = clipData.getItemAt(i).uri // 선택한 이미지들의 uri를 가져온다.
                        try {
                            itemList.add(imageUri.toString()) //uri를 list에 담는다.
                        } catch (e: Exception) {
                            Log.e(TAG, "File select error", e)
                        }
                    }
                    AddAlbumAdapter = AddAlbumAdapter(itemList)

                    binding.imageRV.apply {
                        adapter = AddAlbumAdapter
                        layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    }
                     // 리사이클러뷰 수평 스크롤 적용
                }
            }
        }
    }
}