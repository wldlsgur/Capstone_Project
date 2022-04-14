package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.issueproject.databinding.ActivityMenuBinding
import com.example.issueproject.dto.ParentInfoResult
import com.example.issueproject.dto.PresidentinfoResult
import com.example.issueproject.dto.UserInfo
import com.example.issueproject.res.DayNotic.DayNoticActivity
import com.example.issueproject.res.Notic.NoticActivity
import com.example.issueproject.res.RoomManager.RoomChildListActivity
import com.example.issueproject.res.viewmodel.MainViewModels
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MenuActivity"
class MenuActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        Log.d(TAG, "id: $id")
        if (id != null) {
            GetPresidentInfo(id)
        }
        val name = intent.getStringExtra("name")
        binding.textViewName.text = name

        binding.PresidentDayNotic.setOnClickListener {
            var intent = Intent(this, DayNoticActivity::class.java)
            startActivity(intent)
        }
        binding.PresidentNotic.setOnClickListener {
            var intent = Intent(this, NoticActivity::class.java)
            startActivity(intent)
        }
        binding.PresidentSchoolManager.setOnClickListener {
            var intent = Intent(this, RoomChildListActivity::class.java)
            startActivity(intent)
        }
//
//        binding.buttonMenuSchoolAdd.setOnClickListener {
//            var intent = Intent(this, SchoolAddActivity::class.java)
//            startActivity(intent)
//        }
//        binding.buttonMenuChildAdd.setOnClickListener {
//            var intent = Intent(this, ChildAddActivity::class.java)
//            startActivity(intent)
//        }


    }
//    fun GetUserInfo(id: String){
//        ResponseService().GetUserInfo(id, object : RetrofitCallback<UserInfo> {
//            override fun onError(t: Throwable) {
//                Log.d(TAG, "onError: $t")
//            }
//
//            override fun onSuccess(code: Int, responseData: UserInfo) {
//                Log.d(TAG, "onSuccess: $responseData")
//                binding.textViewName.text = responseData.name
//
//                val job = responseData.job
//
//                if(job == "원장님"){
//                    Log.d(TAG, "id: $id")
//                    GetPresidentInfo(id)
//                }
//                else if(job == "선생님"){
//                    binding.textViewSchool.text = "햇살어린이집"
//                }
//                else if(job == "부모님"){
//                    GetParentInfo(id)
//                }
//            }
//            override fun onFailure(code: Int) {
//                Log.d(TAG, "onFailure: $code")
//            }
//        })
//    }
    fun GetPresidentInfo(id: String){
        ResponseService().GetPresidentInfo(id, object : RetrofitCallback<MutableList<PresidentinfoResult>>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
                binding.textView2.text = ""
                binding.textViewSchool.text = "어린이집을 등록해주세요"
            }

            override fun onSuccess(code: Int, responseData: MutableList<PresidentinfoResult>) {
                Log.d(TAG, "onSuccess: $responseData")
                binding.textViewSchool.text = responseData[0].school
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
                binding.textView2.text = ""
                binding.textViewSchool.text = "어린이집을 등록해주세요"
            }
        })
    }
//    fun GetParentInfo(id: String){
//        ResponseService().GetParentInfo(id, object : RetrofitCallback<ParentInfoResult>{
//            override fun onError(t: Throwable) {
//                Log.d(TAG, "onError: $t")
//                binding.textViewSchool.text = "어린이집을 등록해주세요"
//            }
//
//            override fun onSuccess(code: Int, responseData: ParentInfoResult) {
//                Log.d(TAG, "onSuccess: $responseData")
//                binding.textViewSchool.text = responseData.school
//            }
//
//            override fun onFailure(code: Int) {
//                Log.d(TAG, "onFailure: $code")
//                binding.textViewSchool.text = "어린이집을 등록해주세요"
//            }
//
//        })
//    }
}