package com.example.issueproject.res.Calender;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.issueproject.R
import com.example.issueproject.dto.*
import com.example.issueproject.res.Calender.Calender_textview
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MedicineInfo"
class Calender : AppCompatActivity() {

    var school : String = "School"
    lateinit var date: String
    lateinit var str: String
    lateinit var calendarView: CalendarView
    lateinit var updateBtn: Button
    lateinit var deleteBtn:Button
    lateinit var saveBtn:Button
    lateinit var diaryContent:TextView
    lateinit var title:TextView
    lateinit var contextEditText: EditText
    lateinit var textviewbtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        // UI값 생성
        calendarView=findViewById(R.id.calendarView)
        saveBtn=findViewById(R.id.saveBtn)
        deleteBtn=findViewById(R.id.deleteBtn)
        updateBtn=findViewById(R.id.updateBtn)
        diaryContent=findViewById(R.id.diaryContent)
        title=findViewById(R.id.title)
        contextEditText=findViewById(R.id.contextEditText)
        textviewbtn=findViewById(R.id.TextViewbtn)
        title.text = "일정"

        textviewbtn.setOnClickListener {
            var intent = Intent(this@Calender, Calender_textview::class.java)
            startActivity(intent)
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            saveBtn.visibility = View.VISIBLE
            contextEditText.visibility = View.VISIBLE
            diaryContent.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            contextEditText.setText("")
            checkDay(year, month, dayOfMonth, school)
        }

        saveBtn.setOnClickListener {
            var cal = CalenderInfo(date,contextEditText.text.toString())
            saveCalender(cal)
            contextEditText.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            updateBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE
            str = contextEditText.text.toString()
            diaryContent.text = str
            diaryContent.visibility = View.VISIBLE
        }
    }

    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, school: String) {
        //저장할 파일 이름설정
        date = "" + school + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"

        try {
            GetCalender(date)
            contextEditText.visibility = View.INVISIBLE
            diaryContent.visibility = View.VISIBLE
            diaryContent.text = str
            saveBtn.visibility = View.INVISIBLE
            updateBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE

            updateBtn.setOnClickListener {
                contextEditText.visibility = View.VISIBLE
                diaryContent.visibility = View.INVISIBLE
                contextEditText.setText(str)
                saveBtn.visibility = View.VISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                diaryContent.text = contextEditText.text
            }

            deleteBtn.setOnClickListener {
                diaryContent.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                contextEditText.setText("")
                contextEditText.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                var cal = CalenderInfo(date,"")
                saveCalender(cal)
            }
            if (diaryContent.text == null) {
                diaryContent.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                saveBtn.visibility = View.VISIBLE
                contextEditText.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun GetCalender(data: String){
        ResponseService().getCalender(data, object : RetrofitCallback<CalenderInfo> {

                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }

                override fun onSuccess(code: Int, responseData: CalenderInfo) {
                    Log.d(TAG, "onSuccess: $responseData")
                    str = responseData.context

                }
                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }

            })

    }
    fun saveCalender(data : CalenderInfo){

        ResponseService().saveCalender(data, object: RetrofitCallback<CalenderResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: CalenderResult) {
                Log.d(TAG, "onSuccess: $code")
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }


}