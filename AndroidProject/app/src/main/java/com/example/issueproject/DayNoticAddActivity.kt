package com.example.issueproject

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import com.example.issueproject.databinding.ActivityDayNoticAddBinding
import com.example.issueproject.dto.AddManagement
import com.example.issueproject.dto.AddManagementResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DayNoticAddActivity"
class DayNoticAddActivity : AppCompatActivity() {
    var cal = Calendar.getInstance()
    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int)
        {
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInText()
        }
    }

    private val binding by lazy{
        ActivityDayNoticAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonDatePicker.setOnClickListener {
            showDatePicker()
        }

        binding.buttonAdd.setOnClickListener {
            var title = binding.editTextAddTitle.text.toString()
            var content = binding.editTextAddContent.text.toString()
            var room = binding.editTextAddGroup.text.toString()
            var menu = binding.editTextAddMenu.text.toString()
            var date = binding.textViewDate.text.toString()
            var year = date.substring(0,4).toString()
            var month = date.substring(5,8).toString()
            var day = date.substring(9,12).toString()
            var school = binding.editTextAddSchool.text.toString()
            Log.d(TAG, "onCreate: ${year}-${month}-${day}")
            var addManagement = AddManagement(
                title,
                content,
                year,
                month,
                day,
                school,
                room,
                menu
            )
            insertaddManagement(addManagement)

        }
    }
    fun showDatePicker(){
        DatePickerDialog(this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    @SuppressLint("SimpleDateFormat")
    fun updateDateInText(){
        var formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        binding.textViewDate.text = formatter.format(cal.time)
    }

    fun insertaddManagement(addManagement: AddManagement){
        ResponseService().AddManagementService(addManagement, object: RetrofitCallback<AddManagementResult>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: AddManagementResult) {
                Log.d(TAG, "onSuccess: $responseData")
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}