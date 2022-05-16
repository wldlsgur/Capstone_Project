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
import com.example.issueproject.dto.*
import com.example.issueproject.res.MainActivity
import com.example.issueproject.res.viewmodel.MainViewModels
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "Parents_MedicineInfo"
class Parents_MedicineInfo : AppCompatActivity() {

    private val binding by lazy{
        ActivityMedicineBinding.inflate(layoutInflater)
    }
    val id = intent.getStringExtra("id").toString()
    val cname = intent.getStringExtra("cname").toString()
    val mname = intent.getStringExtra("mname").toString()
    val add : Boolean = intent.getStringExtra("add").toBoolean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(binding.root)

        binding.medicineButtonSave.setOnClickListener {
            // update 값 변경 통신
        }
        binding.medicineButtonDelete.setOnClickListener {
            // delete값 변경 통신
        }
        binding.buttonAdd.setOnClickListener {
            // 추가값 변경 통신
        }
    }

    fun init(){
        binding.medicineContent.visibility = View.INVISIBLE
        binding.medicineMname.visibility = View.INVISIBLE
        if(add == false) {
            binding.buttonAdd.visibility = View.INVISIBLE
            GetmedicineInfo(id,cname,mname)
        }
        else if(add == true){
            binding.medicineButtonSave.visibility = View.INVISIBLE
            binding.medicineButtonDelete.visibility = View.INVISIBLE
        }
    }
    fun bindinfo(data: Medicine) {
        binding.medicineCname.text = data.child_name
        binding.EditMedicineMname.setText(data.m_name)
        binding.EditMedicineContent.setText(data.content)

        if(data.morning == "true") binding.CheckMorning.isChecked = true

        else binding.CheckMorning.isChecked = false


        if(data.lunch == "true") binding.CheckLunch.isChecked = true

        else binding.CheckLunch.isChecked = false


        if(data.dinner == "true") binding.CheckDinner.isChecked = true

        else binding.CheckDinner.isChecked = false


        if(data.mPlace == "실온") {
            binding.checkBoxOn.isChecked = true
            binding.checkBoxOut.isChecked = false

        }
        else {
            binding.checkBoxOn.isChecked = false

            binding.checkBoxOut.isChecked = true

        }

    }

    fun GetmedicineInfo(id: String, child_name: String, m_name: String){
        ResponseService().GetMedcineInfo(
            id,child_name,m_name,
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

    fun CreateMedicine(data: PostMedicine){
        ResponseService().CreateMedicine(data, object: RetrofitCallback<SignUpResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $code")
                if(responseData.msg == "success"){

                    var intent = Intent(this@Parents_MedicineInfo, ParentsMedicineList::class.java).apply{
                        putExtra("id",id)
                        putExtra("cname", cname)
                    }

                    startActivity(intent)
                }else if(responseData.msg == "failed"){
                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
    //CreateMedicine

}