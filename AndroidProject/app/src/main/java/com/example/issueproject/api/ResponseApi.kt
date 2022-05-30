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

    //회원탈퇴
    @POST("/delete/info")
    fun DeleteInfo(
        @Body deleteinfo: DeleteInfo
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

    //id와 name으로 원생 정보
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

    // 메뉴(공지사항,알림장), 학교, 반에 해당하는 모든 정보
    @GET("/schoolmanagement/info")
    fun DayNoticInfo(
        @Query("menu") menu: String,
        @Query("school") school: String,
        @Query("room") room: String,
    ): Call<MutableList<GetSchoolManagement>>

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

    //어린이집과 반에 해당하는 모든 아이정보
    @GET("/parentinfo/room/allinfo")
    fun RoomChildList(
        @Query("school") school: String,
        @Query("room") room: String
    ): Call<MutableList<RoomChildListResult>>

    //DB상에 존재하는 모든 어린이집 이름
    @GET("/presidentinfo/allschool")
    fun GetSchool(

    ): Call<MutableList<GetSchool>>

    //해당 어린이집에 있는 모든 반 이름
    @GET("/presidentinfo/allroom/{school}")
    fun GetRoom(
        @Path("school") school: String
    ): Call<MutableList<GetRoom>>

    //해당 id로 name과 job 가져옴
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
    @GET("/medicine/selectManage/get/data")
    fun MedicineList(
        @Query("school") school: String,
        @Query("room") room: String
    ): Call<MutableList<MedicineManagementResult>>

    //약리스트 경로변경
    @GET("/medicine/selectManage/get/data/useID")
    fun parentsMedicineList(
        @Query("id") id: String,
        @Query("child_name") child_name: String
    ): Call<MutableList<MedicineManagementResult>>

    @GET("/medicine/select/get/data")
    fun GetMedicineInfo(
        @Query("id") id: String,
        @Query("child_name") child_name: String,
        @Query("m_name") m_name: String

    ): Call<Medicine>

    @POST("/medicine/insert/data")
    fun PostMedicine(
        @Body PostMedicine: PostMedicine
    ): Call<SignUpResult>

    //이미지 한장 통신 (부모, 원장, 선생 등록)
    @Multipart
    @POST("/uploadimage/{target}/{key}")
    fun Uploadimage(
        @Path("target") target: String,
        @Path("key") key: String,
        @Part image: MultipartBody.Part
    ): Call<LoginResult>

    //이미지 여러장 통신 (앨범)
    @Multipart
    @POST("/uploadimages/{school}/{room}/{title}/{date}")
    fun Uploadimages(
        @Path("school") school: String,
        @Path("room") room: String,
        @Path("title") title: String,
        @Path("date") date: String,
        @Part images: ArrayList<MultipartBody.Part>
    ): Call<LoginResult>

    //이미지 가져오기
    @GET("/image/{target}/{key}")
    fun  GetImageUrl(
        @Path("target") target: String,
        @Path("key") key: String
    ): Call<ResponseBody>

    //어린이집과 반에 해당하는 모든 앨범 정보
    @GET("/album/info")
    fun GetAlbumInfo(
        @Query("school") school: String,
        @Query("room") room: String
    ): Call<MutableList<AlbumResult>>

    //선생 리스트 승인 no > yes
    @GET("/staff/updateTeacherinfoAgree")
    fun Teacheragreechange(
        @Query("keyId") keyId: Int
    ): Call<SignUpResult>

    //선생 리스트 삭제
    @GET("/staff/deleteTeacherinfo")
    fun deleteteacherlist(
        @Query("keyId") keyId: Int
    ): Call<SignUpResult>

    //원생 리스트 승인 no > yes
    @POST("/parentinfo/change/check")
    fun agreechange(
        @Body key_id: AgreeChange
    ): Call<SignUpResult>

    //원생 리스트 삭제
    @POST("/parentinfo/delete/info")
    fun deletechildlist(
        @Body key_id: AgreeChange
    ): Call<SignUpResult>
}