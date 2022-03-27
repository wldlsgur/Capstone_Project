package com.example.issueproject.api

import com.example.issueproject.dto.LoginInfo
import com.example.issueproject.dto.LoginResult
import com.example.issueproject.dto.SignUpResult
import com.example.issueproject.dto.SingUpInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ResponseApi {
    @GET("/")
    fun GetQueryTest(
        @Query("id") id: String,
        @Query("pw") pw: String,
    ): Call<LoginResult>

    @POST("/test/info")
    fun PostTest(
        @Body postData: LoginInfo
    ): Call<LoginResult>

    @GET("/check/login")
    fun checklogin(
        @Query("id") id: String,
        @Query("pw") pw: String,
    ): Call<LoginResult>

    @POST("/create/user")
    fun SignUp(
        @Body signData: SingUpInfo
    ): Call<SignUpResult>
}