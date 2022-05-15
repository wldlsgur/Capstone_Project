package com.example.issueproject.res.DayNotic

import DayNoticAdapter
import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.databinding.ActivityDayNoticBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.GetRoom
import com.example.issueproject.res.Add.AddNoticActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "DayNoticActivity"
class DayNoticActivity : AppCompatActivity() {
    lateinit var DayNoticAdapter: DayNoticAdapter

    private val binding by lazy{
        ActivityDayNoticBinding.inflate(layoutInflater)
    }
    val roomList = mutableListOf<String>()
    var room : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        var name = intent.getStringExtra("name")
        val school = intent.getStringExtra("school")
        val menu = intent.getStringExtra("menu")

        binding.textViewDayNoticSchool.text = school

        GetRoom(school!!)

        Log.d(TAG, "onCreate: $school")
        Log.d(TAG, "onCreate: $menu")


        binding.buttonDaynoticAdd.setOnClickListener {
            var intent = Intent(this, AddNoticActivity::class.java).apply {
                putExtra("name", name)
                putExtra("menu", menu)
                putExtra("school", school)
                putExtra("room", room)
            }
            startActivity(intent)
        }

        binding.spinnerDayNoticSchool.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                room = roomList[position]
                ShowRecycler(menu!!,school!!,room)
            }

        }
    }

    private fun initRecycler(list:MutableList<AddManagement>){
        DayNoticAdapter = DayNoticAdapter(list)

        binding.DayNoticRV.apply {
            adapter = DayNoticAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun ShowRecycler(menu: String, school: String, room: String) {
        ResponseService().DayNoticInfoShow(menu, school, room, object : RetrofitCallback<MutableList<AddManagement>> {
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

    fun GetRoom(school: String){
        ResponseService().GetRoom(school, object: RetrofitCallback<MutableList<GetRoom>> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetRoom>) {
                roomList.clear()
                for(item in responseData) {
                    roomList.add(item.room)
                }

                val adapter = ArrayAdapter(this@DayNoticActivity, R.layout.simple_spinner_dropdown_item, roomList)
                binding.spinnerDayNoticSchool.adapter = adapter
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}