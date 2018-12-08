package com.i27house.autoclockin

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.gson.Gson
import com.i27house.autoclockin.api.Api
import com.i27house.autoclockin.api.model.clockin.ClockInData
import com.i27house.autoclockin.databinding.ActivityMainBinding
import com.i27house.autoclockin.http.RetrofitClient
import com.i27house.autoclockin.utils.RC4
import com.i27house.autoclockin.utils.getSpString
import com.i27house.autoclockin.utils.putSpString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody

class MainActivity : AppCompatActivity() {

    private var mClockInData: ClockInData? = null

    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        getSpString(TOKEN_KEY)?.let { main_token.setText(it) }
        //获取员工打卡信息
        main_emp_info.setOnClickListener { getEmpInfo() }
        //获取打卡结果
        main_card_info.setOnClickListener { getCardInfo() }
        //打卡
        main_clock_in.setOnClickListener { clockIn() }
        //存储key
        main_token.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let { putSpString(TOKEN_KEY, it.toString()) }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

    }

    private fun clockIn() {

        if (mClockInData == null) {
            addInfo("需要先获取配置信息")
        } else {
            val toJson = Gson().toJson(mClockInData)
            mClockInData?.timestamp = System.currentTimeMillis() / 1000
            Log.i("123", toJson)
            Log.i("123", toJson.RC4())
            val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson.RC4())
            val subscribe = RetrofitClient.api()
                .clockIn(getSpString(TOKEN_KEY), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ next ->
                    Log.i("123", next.toString())
                    addInfo(next.toString())
                }) { e ->
                    Log.e("123", "error", e)
                    addInfo("打卡失败" + e.message)
                }
            disposable.add(subscribe)
        }
    }

    private fun getCardInfo() {
        val subscribe = RetrofitClient.api().getCardInfo(getSpString(TOKEN_KEY))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ next ->
                Log.i("123", next.toString())
                addInfo(next.toString())
                addInfo("【上班】${next.data.card[0].start_status_name}   【下班】${next.data.card[0].end_status_name}")
            }) { e ->
                addInfo("获取打卡信息失败" + e.message)
            }
        disposable.add(subscribe)
    }

    @SuppressLint("SetTextI18n")
    private fun getEmpInfo() {
        val subscribe = RetrofitClient.instance(Api::class.java).getEmpInfo(getSpString(TOKEN_KEY))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ next ->
                addInfo(next.toString())
                mClockInData = ClockInData(0.0, 0.0, 0L)
                if (!next.data.wifi_list.isNullOrEmpty()) {
                    main_mac.text = "mac:${next.data.wifi_list[0].mac}"
                    main_wifi_ssid.text = "ssid:${next.data.wifi_list[0].ssid}"
                    mClockInData?.mac = next.data.wifi_list[0].mac
                    mClockInData?.ssid = next.data.wifi_list[0].ssid
                }

                if (!next.data.point_list.isNullOrEmpty()) {
                    main_location.text = "name：${next.data.point_list[0].address_name}"
                    main_gps.text = "gps：(${next.data.point_list[0].latitude},${next.data.point_list[0].longitude})"
                    mClockInData?.latitude = next.data.point_list[0].latitude
                    mClockInData?.longitude = next.data.point_list[0].longitude
                }
            }) { e ->
                Log.e("123", "error", e)
                mClockInData = null
                addInfo("获取配置失败" + e.message)
            }
        disposable.add(subscribe)
    }

    @SuppressLint("SetTextI18n")
    private fun addInfo(info: String) {
        val old = main_info.text.toString()

        if (old.isEmpty()) {
            main_info.text = info
        } else {
            main_info.text = "$info\n\n=================\n\n$old"
        }
    }

    companion object {
        const val TOKEN_KEY = "token"
    }
}


