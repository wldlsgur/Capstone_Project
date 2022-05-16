package com.example.issueproject.res.submenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.issueproject.Adapter.ChildAdapter
import com.example.issueproject.databinding.ActivitySubChildMunuBinding
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.res.Add.ChildAddActivity
import com.example.issueproject.res.MainParentActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "SubChildMunuActivity"
class SubChildMunuActivity : AppCompatActivity() {
    lateinit var ChildAdapter: ChildAdapter

    private val binding by lazy{
        ActivitySubChildMunuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")

        ShowRecycler(id!!)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, ChildAddActivity::class.java).apply {
                putExtra("id", id)
                Log.d(TAG, "id: $id")
            }
            startActivity(intent)
        }
    }

    private fun initRecycler(list:MutableList<ParentInfoResult>){
        ChildAdapter = ChildAdapter(list)

        binding.subchildRv.apply {
            adapter = ChildAdapter
            layoutManager = LinearLayoutManager(context)
        }

        ChildAdapter.setItemClickListener(object: ChildAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 클릭 시 이벤트 작성

                val intent = Intent(this@SubChildMunuActivity, MainParentActivity::class.java).apply{
                    putExtra("id", intent.getStringExtra("id"))
                    Log.d(TAG, "position: $position")
                    putExtra("position", position.toString())
                }
                startActivity(intent)
            }
        })
    }

    private fun ShowRecycler(id: String) {
        ResponseService().GetParentInfo(id, object : RetrofitCallback<MutableList<ParentInfoResult>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<ParentInfoResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                initRecycler(responseData)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}