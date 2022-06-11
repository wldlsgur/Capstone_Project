package com.example.issueproject.res.Add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityDailyAddBinding
import com.example.issueproject.dto.Calenderinfo
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DailyAddActivity"
class DailyAddActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityDailyAddBinding.inflate(layoutInflater)
    }
//    val itemList = mutableListOf<String>()
    lateinit var Calenderinfo : Calenderinfo
    val school = intent.getStringExtra("school").toString()
    val id = intent.getStringExtra("id").toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentTime = System.currentTimeMillis()
        convertTimestampToDate(currentTime)
        var itemList = arrayListOf<String>("빨간색", "파란색", "초록색", "노란색", "케인색")
        val adapter = ArrayAdapter(this@DailyAddActivity, R.layout.spinner, itemList)
        binding.spinnerDailyAddColor.adapter = adapter
        binding.spinnerDailyAddColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == 0){
                    binding.imageViewPoint.setColorFilter(Color.RED); // 검정색
                    Calenderinfo.color = "red"
                }
                if(position == 1){
                    //resources.getColor(R.color.red_)
                    binding.imageViewPoint.setColorFilter(Color.BLUE); // 빨간색
                    Calenderinfo.color = "blue"
                }
                if(position == 2){
                    binding.imageViewPoint.setColorFilter(Color.GREEN); // 파란색
                    Calenderinfo.color = "green"
                }
                if(position == 3){
                    binding.imageViewPoint.setColorFilter(Color.YELLOW); // 노란색
                    Calenderinfo.color = "yellow"
                }
                if(position == 4){
                    binding.imageViewPoint.setColorFilter(Color.CYAN); // 초록색
                    Calenderinfo.color = "cyan"
                }
            }
        }

        //추가버튼 클릭시
        binding.textViewDailyAddBtn.setOnClickListener {
            insertDaily()
        }

        //DatePicker 클릭시
        binding.DailyDatePicker.setOnClickListener {
            showDatePicker()
        }
        binding.DailyEndDatePicker.setOnClickListener {
            showDatePicker2()
        }

        //시작시간 클릭시
        binding.textViewDailyAddStartTime.setOnClickListener {
            getTime(binding.textViewDailyAddStartTime, this)
        }

        //종료시간 클릭시
        binding.textViewDailyAddEndTime.setOnClickListener {
            getTime(binding.textViewDailyAddEndTime, this)
        }

    }

    private fun bindinfo(){
        Calenderinfo.title = binding.editTextDailyTitle.toString()
        Calenderinfo.content = binding.editTextDailyContent.toString()
        Calenderinfo.startDate = start_date
        Calenderinfo.endDate = end_date
        Calenderinfo.startTime = binding.textViewDailyAddStartTime.toString()
        Calenderinfo.endTime = binding.textViewDailyAddEndTime.toString()
        Calenderinfo.school = school
        Calenderinfo.id = id

    }
    private fun insertDaily(){
        bindinfo()
        //통신
    }


    fun getTime(date: TextView, context: Context){

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute  ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            date.text = SimpleDateFormat("HH : mm a").format(cal.time)
        }

        TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

    }

    var cal = Calendar.getInstance()
    lateinit var start_date : String
    lateinit var end_date : String
    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        }

    private fun showDatePicker(){
        DatePickerDialog(this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
        updateDateInText()
    }
    private fun showDatePicker2(){
        DatePickerDialog(this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
        updateDateInText2()
    }
    private fun convertTimestampToDate(timestamp: Long){
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일")
        val date = sdf.format(timestamp)

        binding.textViewDailyAddDate.text = date
        var year = date.substring(0,4)
        var month = date.substring(6,8)
        var day = date.substring(10,12)
        Log.d(TAG, "datetest: ${year}-${month}-${day}")
    }

    private fun updateDateInText(){
        var formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        val send = SimpleDateFormat("yyyy-MM-dd")
        start_date = send.format(cal.time)
        binding.textViewDailyAddDate.text = formatter.format(cal.time)

    }
    private fun updateDateInText2(){
        var formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        val send = SimpleDateFormat("yyyy-MM-dd")
        end_date = send.format(cal.time)
        binding.textViewDailyEndDate.text = formatter.format(cal.time)

    }
}