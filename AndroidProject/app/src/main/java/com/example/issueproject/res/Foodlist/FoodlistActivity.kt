package com.example.issueproject.res.Foodlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapter.FoodListAdapter
import com.example.issueproject.Adapterimport.SchoolTeacherListAdapter
import com.example.issueproject.databinding.ActivityFoodlistBinding
import com.example.issueproject.dto.DeleteImage
import com.example.issueproject.dto.GetFoodList
import com.example.issueproject.dto.SchoolteacherListResult
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.res.Add.FoodAddActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import kotlinx.coroutines.runBlocking

private const val TAG = "FoodlistActivity"
class FoodlistActivity : AppCompatActivity() {
    lateinit var FoodListAdapter: FoodListAdapter
    var school :String = ""
    var job: String = ""

    private val binding by lazy{
        ActivityFoodlistBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        school = intent.getStringExtra("school").toString()
        job = intent.getStringExtra("job").toString()

        if(job == "부모님"){
            binding.foodListAddBtn.visibility = View.INVISIBLE
        }else{
            binding.foodListAddBtn.visibility = View.VISIBLE
        }


        FoodListAdapter = FoodListAdapter(this)

        runBlocking {
            ShowRecycler(school!!)
        }

        initAdapter()


        binding.foodListAddBtn.setOnClickListener{
            var intent = Intent(this, FoodAddActivity::class.java).apply {
                putExtra("school", school)
                putExtra("job", job)
            }
            startActivity(intent)
        }

    }

    private fun initAdapter(){

        binding.foodListRvMonth.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            adapter = FoodListAdapter
        }

        // item 삭제 클릭 이벤트
        FoodListAdapter.setDeleteItemClickListener(object : FoodListAdapter.MenuClickListener {
            override fun onClick(position: Int, item: GetFoodList) {
                val img_url = item.image_url
                val key = item.key_id

                val deleteimageinfo = DeleteImage("food", img_url, key)
                DeleteImageInfo(deleteimageinfo)
            }
        })
    }

    private fun ShowRecycler(school: String) {
        ResponseService().GetFoodListInfo(school, object : RetrofitCallback<MutableList<GetFoodList>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetFoodList>) {
                Log.d(TAG, "onSuccess: $responseData")
                FoodListAdapter.list = responseData
                FoodListAdapter.notifyDataSetChanged()
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }

    fun DeleteImageInfo(deleteimage: DeleteImage){
        ResponseService().DeleteImageInfo(deleteimage, object : RetrofitCallback<SignUpResult>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")

            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}