package com.example.issueproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.issueproject.databinding.ActivitySignUpBinding
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.dto.SingUpInfo
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "SignUpActivity"
class SignUpActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var job = ""

        binding.radioGroup.setOnCheckedChangeListener{ group, checkedid ->
            when(checkedid){
                R.id.radioButtonCaptain -> job = "원장"
                R.id.radioButtonTeacher -> job = "교사"
                R.id.radioButtonParrent -> job = "학부모"
            }
        }

        binding.buttonSingUpCheck.setOnClickListener {
            var id = binding.editTextISignUpID.text.toString()
            var pw = binding.editTextISignUpPW.text.toString()
            var name = binding.editTextISignUpName.text.toString()

            var signupinfo = SingUpInfo(id,pw,name,job)
            SignUp(signupinfo)
        }
    }

    fun SignUp(data: SingUpInfo){
        ResponseService().SignUpService(data, object: RetrofitCallback<SignUpResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $code")
                if(responseData.msg == "success"){
                    var intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                }else if(responseData.msg == "failed"){

                }
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}