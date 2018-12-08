package com.i27house.autoclockin.http

import com.i27house.autoclockin.api.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: chenbing
 * date  : 2018/12/7
 * description :
 **/
class RetrofitClient private constructor() {

    companion object {

        private const val baseUrl = "https://api.2haohr.com/"

        private val retrofit: Retrofit by lazy {
            return@lazy Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpManeger.instance)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        fun <T> instance(t: Class<T>): T {
            return retrofit.create(t)
        }

        fun api(): Api = retrofit.create(Api::class.java)
    }
}