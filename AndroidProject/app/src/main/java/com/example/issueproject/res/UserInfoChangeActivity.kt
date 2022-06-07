package com.example.issueproject.res

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.issueproject.databinding.ActivityUserInfoChangeBinding
import com.example.issueproject.dto.DeleteInfo
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.retrofit.RetrofitCallback
import com.example.issueproject.service.ResponseService

private const val TAG = "UserInfoChangeActivity"
class UserInfoChangeActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityUserInfoChangeBinding.inflate(layoutInflater)
    }

    var id: String = ""
    var job: String = ""
    var school: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getStringExtra("id").toString()
        job = intent.getStringExtra("job").toString()
        school = intent.getStringExtra("school").toString()

//        binding.buttonUserOut.setOnClickListener {
//            showDialog()
//        }
    }

    fun showDialog(){
        lateinit var dialog: AlertDialog
        val deleteinfo = DeleteInfo(id, job, school)

        val builder =  AlertDialog.Builder(this)
        builder.setTitle("회원 탈퇴")

        builder.setMessage("정말 회원 탈퇴를 하시겠습니까?")

        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> DeleteInfo(deleteinfo)  //yes 클릭시
                //DialogInterface.BUTTON_NEGATIVE -> toast("Negative/No button clicked.") // no 클릭시
                DialogInterface.BUTTON_NEUTRAL -> Toast.makeText(this, "취소하였습니다.", Toast.LENGTH_SHORT).show() // cancel 클릭시
            }
        }
        builder.setPositiveButton("예",dialogClickListener)
        //builder.setNegativeButton("아니오",dialogClickListener)
        builder.setNeutralButton("취소",dialogClickListener)
        dialog = builder.create()
        dialog.show()
    }

    fun DeleteInfo(deleteinfo: DeleteInfo){
        ResponseService().DeleteInfo(deleteinfo, object : RetrofitCallback<SignUpResult> {
            override fun onError(t: Throwable) {
                Log.d(TAG, "onError: $t")
            }

            override fun onSuccess(code: Int, responseData: SignUpResult) {
                Log.d(TAG, "onSuccess: $responseData")
                Toast.makeText(this@UserInfoChangeActivity, "회원탈퇴가 정상적으로 이루어졌습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(code: Int) {
                Log.d(TAG, "onFailure: $code")
            }

        })
    }

}