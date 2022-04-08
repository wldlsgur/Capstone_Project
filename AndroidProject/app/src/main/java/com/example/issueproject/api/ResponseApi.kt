package com.example.issueproject.api

import com.example.issueproject.R
import com.example.issueproject.dto.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
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

    @GET("/presidentinfo/allroom/{school}")
    fun GetRoom(
        @Path("school") school: String
    ): Call<MutableList<GetRoom>>

    @GET("/user/info/{id}")
    fun GetUserInfo(
        @Path("id") id: String
    ): Call<UserInfo>

    @Multipart
    @POST("uploadimage/{target}/{id}/{name}")
    fun Uploadimage(
        @Path("target") target: String,
        @Path("id") id: String,
        @Path("name") name: String
    ): Call<LoginResult>
//
//    @GET("{url}")
//    fun  GetImageUrl(
//        @Path("url") url: String
//    ): Call<ResponseBody>

    @GET("/image/{target}/{name}.jpg")
    fun  GetImageUrl(
        @Path("target") target: String,
        @Path("name") name: String
    ): Call<ResponseBody>
}