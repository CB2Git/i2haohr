package com.i27house.autoclockin.http

import com.i27house.autoclockin.BuildConfig
import com.i27house.autoclockin.http.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


/**
 * author: chenbing
 * date  : 2018/12/7
 * description :
 **/
class OkHttpManeger private constructor() {

    companion object {

        private const val DEFAULT_TIME_OUT = 5L

        val instance: OkHttpClient by lazy {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            return@lazy OkHttpClient
                .Builder()
                .retryOnConnectionFailure(true)
                .callTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
    }

}