package com.example.issueproject.res.Notic

import DayNoticAdapter
import NoticAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityNoticBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.GetSchoolManagement
import com.example.issueproject.res.Add.AddNoticActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "NoticActivity"
class NoticActivity : AppCompatActivity() {
    lateinit var NoticAdapter: NoticAdapter

    private val binding by lazy{
        ActivityNoticBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val job = intent.getStringExtra("job")
        var name = intent.getStringExtra("name")
        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")
        val menu = intent.getStringExtra("menu")

        binding.textViewNoticSchool.text = school

        if(job == "선생님" || job== "원장님"){
            binding.buttonNoticAdd.visibility = View.VISIBLE
        }
        else if(job == "부모님"){
            binding.buttonNoticAdd.visibility = View.INVISIBLE
        }

        Log.d(TAG, "onCreate: $school")
        Log.d(TAG, "onCreate: $room")
        Log.d(TAG, "onCreate: $menu")
        ShowRecycler(menu!!,school!!,room!!)

        binding.buttonNoticAdd.setOnClickListener {
            var intent = Intent(this, AddNoticActivity::class.java).apply {
                putExtra("name", name)
                putExtra("menu", menu)
                putExtra("school", school)
                putExtra("room", room)
            }
            startActivity(intent)
        }
    }

    private fun initRecycler(list:MutableList<GetSchoolManagement>){
        NoticAdapter = NoticAdapter(list)

        binding.NoticRV.apply {
            adapter = NoticAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun ShowRecycler(menu: String, school: String, room: String) {
        ResponseService().DayNoticInfoShow(menu, school, room, object :
            RetrofitCallback<MutableList<GetSchoolManagement>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetSchoolManagement>) {
                Log.d(TAG, "onSuccess: $responseData")
                initRecycler(responseData)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}