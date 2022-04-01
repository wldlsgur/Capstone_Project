package com.example.issueproject.res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.issueproject.databinding.ActivityMainBinding
import com.example.issueproject.dto.LoginResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            Log.d(TAG, "onCreate: clicklogin")
            Login(binding.editTextID.text.toString(), binding.editTextPW.text.toString())
        }

        binding.buttonSignUp.setOnClickListener {
            Log.d(TAG, "onCreate: clicksignup")
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun Login(id: String, pw: String){
        ResponseService().LoginCheckService(id, pw, object: RetrofitCallback<LoginResult>{
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
                Toast.makeText(this@MainActivity, "다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            override fun onSuccess(code: Int, responseData: LoginResult) {
                if(!responseData.res){
                    Log.d(TAG, "res: false")
                    if(responseData.msg == "not found"){
                        Log.d(TAG, "msg: not found")
                        Toast.makeText(this@MainActivity, "아이디를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                        binding.editTextID.text = null
                    }else{
                        Log.d(TAG, "msg: failed")
                        Toast.makeText(this@MainActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                    binding.editTextPW.text = null
                }
                else{
                    Log.d(TAG, "res: true")
                    if(responseData.msg == "success"){
                        Log.d(TAG, "msg: success")
                        var intent = Intent(this@MainActivity, MenuActivity::class.java).apply{
                            putExtra("id", id)
                            putExtra("job", responseData.job)
                            Log.d(TAG, "id: $id")
                            Log.d(TAG, "job: $responseData")
                        }
                        startActivity(intent)
                    }
                }
            }
            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }
        })
    }
}
