package com.i27house.autoclockin.api

import com.i27house.autoclockin.api.model.cardinfo.CardInfo
import com.i27house.autoclockin.api.model.clockin.ClockInInfo
import com.i27house.autoclockin.api.model.empinfo.EmpInfo
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * author: chenbing
 * date  : 2018/12/7
 * description :
 **/
interface Api {

    @GET("attendance2/api/group/emp/")
    fun getEmpInfo(@Header("accesstoken") token: String?): Observable<EmpInfo>

    @GET("attendance2/api/employee/card/detail/")
    fun getCardInfo(@Header("accesstoken") token: String?): Observable<CardInfo>

    @POST("attendance2/api/employee/card/add_one/")
    fun clockIn(@Header("accesstoken") token: String?, @Body rc4: RequestBody): Observable<ClockInInfo>
}