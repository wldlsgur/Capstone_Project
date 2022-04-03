package com.example.issueproject.service

import android.util.Log
import com.example.issueproject.dto.*
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MultipartBody

import okhttp3.MediaType

import okhttp3.RequestBody




private const val TAG = "ResponseService"
class ResponseService {
    fun LoginCheckService(userid: String, userpw: String, callback: RetrofitCallback<LoginResult>) {
        RetrofitBuilder.api.checklogin(userid, userpw).enqueue(object : Callback<LoginResult>{
            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
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
    fun AddManagementService(addmanagement: AddManagement, callback: RetrofitCallback<AddManagementResult>){
        RetrofitBuilder.api.Addschoolmanagement(addmanagement).enqueue(object : Callback<AddManagementResult>{
            override fun onFailure(call: Call<AddManagementResult>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
                callback.onError(t)
            }

            override fun onResponse(call: Call<AddManagementResult>,response: Response<AddManagementResult>) {
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

    fun DayNoticInfoShow(menu: String, school: String, room: String, callback: RetrofitCallback<MutableList<AddManagement>>) {
        RetrofitBuilder.api.DayNoticInfo(menu, school, room).enqueue(object : Callback<MutableList<AddManagement>>{
            override fun onResponse(
                call: Call<MutableList<AddManagement>>,
                response: Response<MutableList<AddManagement>>
            ) {
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

            override fun onFailure(call: Call<MutableList<AddManagement>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }

        })
    }
//    fun uploadimage() {
//        val requestFile: RequestBody =
//            RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
//        val body = MultipartBody.Part.createFormData("uploaded_file", imageFileName, requestFile)
//        val retrofitInterface: RetrofitInterface =
//            ApiClient.getApiClient().create(RetrofitInterface::class.java)
//        val call: Call<String> = retrofitInterface.request(body)
//        call.enqueue(object : Callback<String?> {
//            override fun onResponse(call: Call<String?>, response: Response<String?>) {
//                Log.e("uploadChat()", "성공 : ")
//            }
//
//            override fun onFailure(call: Call<String?>, t: Throwable) {
//                Log.e("uploadChat()", "에러 : " + t.message)
//            }
//        })
//    }
}
