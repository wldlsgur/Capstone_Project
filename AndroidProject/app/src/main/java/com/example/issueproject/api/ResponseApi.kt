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

    @GET("/check/sameid")
    fun sameid(
        @Query("id") id: String
    ): Call<SignUpResult>

    @POST("/create/user")
    fun SignUp(
        @Body signData: SingUpInfo
    ): Call<SignUpResult>

    @POST("/create/schoolmanagement")
    fun Addschoolmanagement(
        @Body schoolmanagementData: AddManagement
    ): Call<AddManagementResult>

    @GET("/schoolmanagement/info")
    fun DayNoticInfo(
        @Query("menu") menu: String,
        @Query("school") school: String,
        @Query("room") room: String,
    ): Call<MutableList<AddManagement>>

    @POST("/create/presidentinfo")
    fun Presidentinfo(
        @Body presidentinfo: Presidentinfo
    ): Call<SignUpResult>

    @POST("/create/parentinfo")
    fun ParentInfo(
        @Body parentInfo: ParentInfo
    ): Call<SignUpResult>

    @GET("/parentinfo/room/allinfo")
    fun RoomChildList(
        @Query("room") room: String
    ): Call<MutableList<RoomChildListResult>>

    @GET("/presidentinfo/allschool")
    fun GetSchool(

    ): Call<MutableList<GetSchool>>

    @GET("/user/info/{id}")
    fun GetUserInfo(
        @Path("id") id: String
    ): Call<UserInfo>

//    @Multipart
//    @POST("uploads/")
//    fun Upload(
//        @Part photo: MultipartBody.Part
//    )
}