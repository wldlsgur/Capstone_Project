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

        binding.buttonSingUpCheck.setOnClickListener {
//            if()
            SignUp(binding.editTextISignUpID.text.toString(), binding.editTextISignUpPW.text.toString(), binding.editTextISignUpName.text.toString())
        }
    }

    fun SignUp(id: String, pw: String, name: String){
        val data = SingUpInfo(id, pw, name)
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