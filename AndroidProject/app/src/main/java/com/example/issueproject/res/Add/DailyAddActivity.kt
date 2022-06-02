package com.example.issueproject.res.Add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Switch
import android.widget.TextView
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityDailyAddBinding
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DailyAddActivity"
class DailyAddActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityDailyAddBinding.inflate(layoutInflater)
    }
//    val itemList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentTime = System.currentTimeMillis()
        convertTimestampToDate(currentTime)

        var itemList = arrayListOf<String>("검정색", "빨간색", "파란색", "노란색", "초록색")

        val adapter = ArrayAdapter(this@DailyAddActivity, R.layout.spinner, itemList)
        binding.spinnerDailyAddColor.adapter = adapter

        binding.spinnerDailyAddColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == 0){
                    binding.imageViewPoint.setColorFilter(resources.getColor(R.color.black)); // 검정색
                }
                if(position == 1){
                    binding.imageViewPoint.setColorFilter(resources.getColor(R.color.red_)); // 빨간색
                }
                if(position == 2){
                    binding.imageViewPoint.setColorFilter(resources.getColor(R.color.blue_700)); // 파란색
                }
                if(position == 3){
                    binding.imageViewPoint.setColorFilter(resources.getColor(R.color.yellow)); // 노란색
                }
                if(position == 4){
                    binding.imageViewPoint.setColorFilter(resources.getColor(R.color.green)); // 초록색
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

        //시작시간 클릭시
        binding.textViewDailyAddStartTime.setOnClickListener {
            getTime(binding.textViewDailyAddStartTime, this)
        }

        //종료시간 클릭시
        binding.textViewDailyAddEndTime.setOnClickListener {
            getTime(binding.textViewDailyAddEndTime, this)
        }

    }

    private fun insertDaily(){

    }

    fun getTime(date: TextView, context: Context){

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            date.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

    }

    var cal = Calendar.getInstance()
    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInText()
        }

    private fun showDatePicker(){
        DatePickerDialog(this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
    }
    private fun convertTimestampToDate(timespamp: Long){
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일")
        val date = sdf.format(timespamp)

        binding.textViewDailyAddDate.text = date
        var year = date.substring(0,4)
        var month = date.substring(6,8)
        var day = date.substring(10,12)
        Log.d(TAG, "datetest: ${year}-${month}-${day}")
    }

    private fun updateDateInText(){
        var formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        binding.textViewDailyAddDate.text = formatter.format(cal.time)
    }
}