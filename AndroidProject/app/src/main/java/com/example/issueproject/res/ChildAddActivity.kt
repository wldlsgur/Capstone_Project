package com.example.issueproject.res

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.issueproject.databinding.ActivityChildAddBinding
import com.example.issueproject.dto.GetSchool
import com.example.issueproject.dto.ParentInfo
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "ChildAddActivity"
class ChildAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityChildAddBinding.inflate(layoutInflater)
    }
    val school = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        GetSchool()

        binding.buttonChildAdd.setOnClickListener {
            val id = "test"
            val school = "달서어린이집"
            val room = "햇살반"
            val childage = binding.editTextChildAge.text.toString()
            val childname = binding.editTextChildName.text.toString()
            val childspec = binding.editTextChildSpec.text.toString()
            val parentnum = binding.editTextParentNum.text.toString()
            val image = "default"

            var parentinfo = ParentInfo(id, school, room, parentnum, childname, childage, image, childspec)
            ChildAdd(parentinfo)
        }
    }

    fun ChildAdd(info: ParentInfo){
        ResponseService().CreateParentinfo(info, object: RetrofitCallback<SignUpResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
                if(responseData.msg == "success"){
                    Toast.makeText(this@ChildAddActivity, "성공", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }

    fun GetSchool(){
        val itemList = mutableListOf<String>()
        ResponseService().GetSchool(object : RetrofitCallback<MutableList<GetSchool>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: MutableList<GetSchool>) {
                Log.d(TAG, "onSuccess: ${responseData.size}")
                Log.d(TAG, "onSuccess: ${responseData[0].school}")
                Log.d(TAG, "onSuccess: ${responseData[1].school}")
                Log.d(TAG, "onSuccess: ${responseData[2].school}")

                val itemsize: Int = responseData.size-1
                Log.d(TAG, "size: $itemsize")

                for(i: Int in 0..itemsize){
                    itemList.add(responseData[i].school)
                }
//
//                for(item in responseData) {
//                    itemList.add(item.school)
//                }

                val adapter = ArrayAdapter(this@ChildAddActivity, R.layout.simple_spinner_dropdown_item, itemList)
                  binding.spinnerSchool.adapter = adapter

            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }
}