package com.example.issueproject.api

import com.example.issueproject.dto.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ResponseApi {
    @GET("/check/login")
    fun checklogin(
        @Query("id") id: String,
        @Query("pw") pw: String,
    ): Call<LoginResult>

    @POST("/create/user")
    fun SignUp(
        @Body signData: SingUpInfo
    ): Call<SignUpResult>

    @POST("/create/schoolmanagement")
    fun Addschoolmanagement(
        @Body   schoolmanagementData: AddManagement
    ): Call<AddManagementResult>

    @GET("/schoolmanagement/info")
    fun DayNoticInfo(
        @Query("menu") menu: String,
        @Query("school") school: String,
        @Query("room") room: String,
    ): Call<MutableList<AddManagement>>

    @Multipart
    @POST("uploads/")
    fun Upload(
        @Part photo: MultipartBody.Part
    )
}