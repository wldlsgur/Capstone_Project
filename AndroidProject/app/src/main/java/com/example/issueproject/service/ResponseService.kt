package com.example.issueproject.service

import android.util.Log
import com.example.issueproject.dto.LoginInfo
import com.example.issueproject.dto.LoginResult
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.dto.SingUpInfo
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ResponseService"
class ResponseService {
    fun LoginCheckService(userid: String, userpw: String, callback: RetrofitCallback<LoginResult>) {
        RetrofitBuilder.api.checklogin(userid, userpw).enqueue(object : Callback<LoginResult>{
            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $")
                callback.onError(t)
            }

            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                Log.d(TAG, "onResponse: ..")
                if (response.code() == 200){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: 200")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }
        })
    }

    fun SignUpService(signupinfo: SingUpInfo, callback: RetrofitCallback<SignUpResult>){
        RetrofitBuilder.api.SignUp(signupinfo).enqueue(object : Callback<SignUpResult>{
            override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
                callback.onError(t)
            }

            override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
                Log.d(TAG, "onResponse: ..")
                if (response.code() == 200){
                    Log.d(TAG, "onResponse: 200")
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: body is not null")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }
        })

    }
//
//    fun LoginService(userinfo: LoginInfo, callback: RetrofitCallback<LoginResult>) {
//        RetrofitBuilder.api.PostTest(userinfo).enqueue(object : Callback<LoginResult>{
//            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
//                Log.d(TAG, "onFailure: ...")
//            }
//
//            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
//                Log.d(TAG, "onResponse: ..")
//                if (response.code() == 200){
//                    if(response.body() != null){
//                        Log.d(TAG, "onResponse: 200")
//                        callback.onSuccess(response.code(), response.body()!!)
//                    }
//                }
//            }
//        })
//    }

//    fun LoginQueryService(userid: String, userpw: String, callback: RetrofitCallback<LoginResult>) {
//        RetrofitBuilder.api.GetQueryTest(userid, userpw).enqueue(object : Callback<LoginResult> {
//            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
//                //Toast.makeText(getApplicationContext(), "오류", Toast.LENGTH_LONG).show();
//
//            }
//            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
//                if(response.code() == 200){
//                    if(response != null){
//                        if(response.body() != null){
//                            callback.onSuccess(response.code(), response.body()!!)
//                            Log.d(TAG, "onResponse: 200")
//                        }
//                    }
//                }
//            }
//        })
//    }
}