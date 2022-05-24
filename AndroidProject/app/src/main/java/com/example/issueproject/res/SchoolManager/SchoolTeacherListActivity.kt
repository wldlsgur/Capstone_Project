package com.example.issueproject.res.SchoolManager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issueproject.Adapterimport.SchoolTeacherListAdapter
import com.example.issueproject.databinding.ActivitySchoolTeacherListBinding
import com.example.issueproject.dto.SchoolteacherListResult
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "SchoolTeacherActivity"
class SchoolTeacherListActivity : AppCompatActivity() {
    lateinit var SchoolTeacherListAdapter: SchoolTeacherListAdapter

    lateinit var schoollistinfo : MutableList<SchoolteacherListResult>
    var school: String = ""
    private val binding by lazy {
        ActivitySchoolTeacherListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        school = intent.getStringExtra("school").toString()
        Log.d(TAG, "onCreate: $school")
        binding.textViewTeacherListSchool.text = school

        ShowRecycler(school)
    }

    private fun initRecycler(list: MutableList<SchoolteacherListResult>) {
        SchoolTeacherListAdapter = SchoolTeacherListAdapter(list)

        binding.SchoolteacherListRV.apply {
            adapter = SchoolTeacherListAdapter
            layoutManager = LinearLayoutManager(context)

            SchoolTeacherListAdapter.setItemClickListener(object: SchoolTeacherListAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int) {
                    var intent = Intent(this@SchoolTeacherListActivity, RoomChildListActivity::class.java).apply {
                        Log.d(TAG, "room: ${schoollistinfo[position].room}")

                        putExtra("position", position.toString())
                        putExtra("room", schoollistinfo[position].room)
                        putExtra("school", school)
                    }
                    startActivity(intent)
                }
            })
        }
    }

    private fun ShowRecycler(school: String) {
        ResponseService().SchoolTeacherListShow(school, object : RetrofitCallback<MutableList<SchoolteacherListResult>> {
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }

                override fun onSuccess(code: Int, responseData: MutableList<SchoolteacherListResult>) {
                    Log.d(TAG, "onSuccess: $responseData")
                    schoollistinfo = responseData
                    initRecycler(responseData)

                }

                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }

            })

    }
}
