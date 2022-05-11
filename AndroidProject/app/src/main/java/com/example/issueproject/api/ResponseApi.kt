package com.example.issueproject.api

import com.example.issueproject.dto.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ResponseApi {
    // 로그인 확인
    @GET("/check/login")
    fun checklogin(
        @Query("id") id: String,
        @Query("pw") pw: String,
    ): Call<LoginResult>

    // 아이디 중복 체크
    @GET("/check/sameid")
    fun sameid(
        @Query("id") id: String
    ): Call<SignUpResult>

    //회원가입
    @POST("/create/user")
    fun SignUp(
        @Body signData: SingUpInfo
    ): Call<SignUpResult>

    //id로 원장의 모든 정보
    @GET("/staff/presidentinfo/useid")
    fun GetPresidentInfo(
        @Query("id") id: String
    ): Call<MutableList<PresidentinfoResult>>

    //id로 선생의 모든 정보
    @GET("/staff/teacherinfo/useid")
    fun GetTeacherInfo(
        @Query("id") id: String
    ): Call<MutableList<TeacherinfoResult>>

    //id로 부모의 모든 정보
    @GET("/parentinfo/info")
    fun GetParentInfo(
        @Query("id") id: String
    ): Call<MutableList<ParentInfoResult>>

    //
    @GET("/parentinfo/child/info")
    fun GetChildInfo(
        @Query("id") id: String,
        @Query("name") name: String,
    ): Call<ParentInfoResult>

    // 알림장 및 공지사항 추가
    @POST("/create/schoolmanagement")
    fun Addschoolmanagement(
        @Body schoolmanagementData: AddManagement
    ): Call<AddManagementResult>

    // 메뉴, 학교, 반에 해당하는 모든 정보
    @GET("/schoolmanagement/info")
    fun DayNoticInfo(
        @Query("menu") menu: String,
        @Query("school") school: String,
        @Query("room") room: String,
    ): Call<MutableList<AddManagement>>

    //원장 추가
    @POST("/create/presidentinfo")
    fun Presidentinfo(
        @Body presidentinfo: Presidentinfo
    ): Call<SignUpResult>

    //부모 추가
    @POST("/create/parentinfo")
    fun ParentInfo(
        @Body parentInfo: ParentInfo
    ): Call<SignUpResult>

    //선생 추가
    @POST("/create/teacherinfo")
    fun TeacherInfo(
        @Body teacherInfo: TeacherInfo
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

    //캘린더로 변경
    @GET("/calenderinfo/info")
    fun getCalender(
        @Query("date") date: String
    ): Call<CalenderInfo>

    //캘린더로변경
    @POST("/calenderinfo/info")
    fun saveCalender(
        @Body Calenderdata: CalenderInfo
    ): Call<CalenderResult>

    //선생리스트
    @GET("/staff/teacherinfo/useschool")
    fun SchoolteacherList(
        @Query("school") school: String
    ): Call<MutableList<SchoolteacherListResult>>

    //약리스트 경로변경
    @GET("/parentinfo/room/allinfo")
    fun MedicineList(
        @Query("room") room: String
    ): Call<MutableList<MedicineManage>>

    //약 정보 경로변경
    @GET("/medicine/info/{name}")
    fun GetMedicineInfo(
        @Path("name") name: String
    ): Call<Medicine>

    @Multipart
    @POST("/uploadimage/{target}/{key}")
    fun Uploadimage(
        @Path("target") data: String,
        @Path("key") key: String,
        @Part image: MultipartBody.Part
    ): Call<LoginResult>

    @GET("/image/{target}/{name}")
    fun  GetImageUrl(
        @Path("target") target: String,
        @Path("name") name: String
    ): Call<ResponseBody>

    @GET("/album/info")
    fun GetAlbumInfo(
        @Query("school") school: String,
        @Query("room") room: String
    ): Call<MutableList<AlbumResult>>
}