
package com.example.issueproject.res.DayNotic

import DayNoticAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityDayNoticBinding
import com.example.issueproject.databinding.ActivityDayNoticTeacherBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.GetRoom
import com.example.issueproject.res.Add.AddNoticActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "DayNoticTeacherActivity"
class DayNoticTeacherActivity : AppCompatActivity() {
    lateinit var DayNoticAdapter: DayNoticAdapter

    private val binding by lazy{
        ActivityDayNoticTeacherBinding.inflate(layoutInflater)
    }
    val roomList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val job = intent.getStringExtra("job")
        val id = intent.getStringExtra("id")
        var name = intent.getStringExtra("name")
        val school = intent.getStringExtra("school")
        val room = intent.getStringExtra("room")
        val menu = intent.getStringExtra("menu")

        if(job == "선생님"){
            binding.buttonDayNoticAddTeacher.visibility = View.VISIBLE
        }
        else if(job == "부모님" || job == "원장님"){
            binding.buttonDayNoticAddTeacher.visibility = View.INVISIBLE
        }

        binding.textViewDayNoticTeacherSchool.text = school
        binding.textViewDayNoticTeacherRoom.text = room
        ShowRecycler(menu!!, school!!, room!!)

        Log.d(TAG, "onCreate: $school")
        Log.d(TAG, "onCreate: $menu")


        binding.buttonDayNoticAddTeacher.setOnClickListener {
            var intent = Intent(this, AddNoticActivity::class.java).apply {
                putExtra("name", name)
                putExtra("menu", menu)
                putExtra("school", school)
                putExtra("room", room)
            }
            startActivity(intent)
        }
    }

    private fun initRecycler(list:MutableList<AddManagement>){
        DayNoticAdapter = DayNoticAdapter(list)

        binding.DayNoticTeacherRV.apply {
            adapter = DayNoticAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun ShowRecycler(menu: String, school: String, room: String) {
        ResponseService().DayNoticInfoShow(menu, school, room, object :
            RetrofitCallback<MutableList<AddManagement>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<AddManagement>) {
                Log.d(TAG, "onSuccess: $responseData")
                initRecycler(responseData)
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

}