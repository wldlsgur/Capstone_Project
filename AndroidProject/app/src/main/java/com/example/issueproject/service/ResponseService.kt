package com.example.issueproject.service

import android.util.Log
import com.example.issueproject.dto.*
import com.example.issueproject.retrofit.RetrofitBuilder
import com.example.issueproject.retrofit.RetrofitCallback
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.File


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
    fun Sameid(userid: String, callback: RetrofitCallback<SignUpResult>){
        RetrofitBuilder.api.sameid(userid).enqueue(object : Callback<SignUpResult>{
            override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                Log.d(TAG, "onResponse: $t")
            }
            override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
                Log.d(TAG, "onResponse: ")
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
    fun CreatePresidentinfo(info: Presidentinfo, callback: RetrofitCallback<SignUpResult>) {
        RetrofitBuilder.api.Presidentinfo(info).enqueue(object : Callback<SignUpResult>{
            override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
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
            override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }
        })
    }
    fun CreateParentinfo(info: ParentInfo, callback: RetrofitCallback<SignUpResult>) {
        RetrofitBuilder.api.ParentInfo(info).enqueue(object : Callback<SignUpResult>{
            override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
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
            override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }
        })
    }
    fun RoomChildListShow(room: String, callback: RetrofitCallback<MutableList<RoomChildListResult>>) {
        RetrofitBuilder.api.RoomChildList(room).enqueue(object : Callback<MutableList<RoomChildListResult>>{
            override fun onResponse(
                call: Call<MutableList<RoomChildListResult>>,
                response: Response<MutableList<RoomChildListResult>>
            ) {
                Log.d(TAG, "RoomChildListShow: ..")
                if (response.code() == 200){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: 200")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<RoomChildListResult>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }

        })

    }

    fun GetSchool(callback: RetrofitCallback<MutableList<GetSchool>>) {
        RetrofitBuilder.api.GetSchool().enqueue(object : Callback<MutableList<GetSchool>>{
            override fun onResponse(
                call: Call<MutableList<GetSchool>>,
                response: Response<MutableList<GetSchool>>
            ) {
                Log.d(TAG, "RoomChildListShow: ..")
                if (response.code() == 200){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: 200")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<GetSchool>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }

        })
    }


    fun GetRoom(school: String, callback: RetrofitCallback<MutableList<GetRoom>>) {
        RetrofitBuilder.api.GetRoom(school).enqueue(object : Callback<MutableList<GetRoom>>{
            override fun onResponse(
                call: Call<MutableList<GetRoom>>,
                response: Response<MutableList<GetRoom>>
            ) {
                Log.d(TAG, "RoomChildListShow: ..")
                if (response.code() == 200){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: 200")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<GetRoom>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }

        })
    }

    fun GetUserInfo(id: String, callback: RetrofitCallback<UserInfo>) {
        RetrofitBuilder.api.GetUserInfo(id).enqueue(object : Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.d(TAG, "RoomChildListShow: ..")
                if (response.code() == 200){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: 200")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }

        })
    }
//    fun uploadimage() {
//        var file = File("${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/tempImg.png")
//        var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//        var body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//
//        RetrofitBuilder.api.Upload(body)
//    }

    fun GetImageUrl(url: String, callback: RetrofitCallback<ResponseBody>) {
        RetrofitBuilder.api.GetImageUrl(url).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d(TAG, "RoomChildListShow: ..")
                if (response.code() == 200){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: 200")
                        callback.onSuccess(response.code(), response.body()!!)
                    } else{
                        callback.onFailure(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onError(t)
            }

        })
    }
}
