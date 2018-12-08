package com.i27house.autoclockin.utils

import android.content.Context

/**
 * author: chenbing
 * date  : 2018/12/7
 * description :
 **/

fun String.RC4(key: String = "3aLZBS6lHNTNlHtA40jTzPuV5UySBp3PVvKpfjWjkHQPThzbVJmJKaDo5HupfecLjZQcm5BOxUjlENLs"): String {
    return encode(this, key)
}


fun Context.getSpString(key: String, default: String = ""): String? {
    val preferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
    return preferences.getString(key, default)
}

fun Context.putSpString(key: String, value: String) {
    val preferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
    val edit = preferences.edit()
    edit.putString(key, value)
    edit.apply()
}