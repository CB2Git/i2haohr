package com.i27house.autoclockin.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * author: chenbing
 * date  : 2018/12/7
 * description :
 **/
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("os", "wechatmini")
            .addHeader("referer", "https://servicewechat.com/wx7587a44a415f95fd/23/page-frame.html")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 9.1.0; MI MIX2 Build/OPM1.171019.019; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x2607033B) NetType/WIFI Language/zh_CN Process/appbrand0"
            )
            .build()
        return chain.proceed(request)
    }
}