package com.example.issueproject.res.Medicine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.example.issueproject.R
import com.example.issueproject.databinding.ActivityMedicineBinding
import com.example.issueproject.dto.Medicine
import com.example.issueproject.dto.MedicineManage
import com.example.issueproject.res.viewmodel.MainViewModels
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MedicineInfo"
class Parents_MedicineInfo : AppCompatActivity() {

    private val binding by lazy{
        ActivityMedicineBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val add : Boolean = intent.getStringExtra("add").toBoolean()
        val cname = intent.getStringExtra("cname").toString()

        if(add == false)  GetmedicineInfo(cname)

        binding.medicineButtonSave.setOnClickListener {
            //값 변경 통신
        }
        binding.medicineButtonDelete.setOnClickListener {
            //값 변경 통신
        }
    }
    fun bindinfo(data: Medicine) {
        binding.medicineCname.text = data.cname
        binding.medicineMname.text = data.mname
        binding.mhurt.text = data.mhurt
        binding.mspecial.text = data.mspecial
        binding.mkind.text = data.mkind
        binding.mamount.text = data.mamount
        if(data.mor == true)
            binding.CheckMorning.visibility = View.VISIBLE

        else binding.CheckMorning.visibility = View.INVISIBLE

        if(data.lun == true)
            binding.CheckLunch.visibility = View.VISIBLE

        else binding.CheckLunch.visibility = View.INVISIBLE

        if(data.din == true)
            binding.CheckDinner.visibility = View.VISIBLE

        else binding.CheckDinner.visibility = View.INVISIBLE

        if(data.mplace == "실온") {
            binding.checkBoxOn.visibility = View.VISIBLE
            binding.checkBoxOut.visibility = View.INVISIBLE
        }
        else {
            binding.checkBoxOn.visibility = View.INVISIBLE
            binding.checkBoxOut.visibility = View.VISIBLE
        }

    }

    fun GetmedicineInfo(name: String){
        ResponseService().GetMedcineInfo(
            name,
            object : RetrofitCallback<Medicine> {
                override fun onError(t: Throwable) {
                    Log.d(TAG, "onError: $t")
                }

                override fun onSuccess(code: Int, responseData: Medicine) {
                    Log.d(TAG, "onSuccess: $responseData")
                    this@Parents_MedicineInfo.bindinfo(responseData)
                }

                override fun onFailure(code: Int) {
                    Log.d(TAG, "onFailure: $code")
                }

            })

    }

}