package com.i27house.autoclockin.utils

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * author: chenbing
 * date  : 2018/12/6
 * description :
 **/
fun decode(a: String, cc: String = ""): String {
    val c = toStr(cc)
    val e = rc4(hex2str(a), c)
    val sb = StringBuffer()

    for (i in e.indices) {
        val d = Integer.toString(e[i].toInt(), 16)
        sb.append("%$d")
    }

    return URLDecoder.decode(sb.toString(), "utf-8")
}


fun encode(origin: String, key: String = ""): String {
    val a = toStr(origin)
    val c = toStr(key)
    return str2hex(rc4(a, c))
}


fun str2hex(str: String): String {
    val sb = StringBuilder()
    (0 until str.length)
        .map { str[it].toInt() }
        .forEach {
            val temp = Integer.toHexString(it)
            if (temp.length == 1) sb.append("0")
            sb.append(temp)
        }
    return sb.toString()
}

fun hex2str(hex: String): String {
    val sb = StringBuilder()
    for (i in 0 until hex.length / 2) {
        sb.append(
            Integer.valueOf(
                hex.substring(i * 2, i * 2 + 2),
                16
            ).toChar()
        )
    }
    return sb.toString()
}


fun toStr(str: String): String {
    val sb = StringBuffer()
    val d = URLEncoder.encode(str, "utf-8").replace("+", "%20")

    var i = 0
    while (i < d.length) {
        var result: String
        if ('%' == d[i]) {
            result = d.substring(i + 1, i + 3)
            result = Integer.parseInt(result, 16).toChar().toString()
            i += 3
        } else {
            result = d[i].toString()
            i++
        }
        sb.append(result)
    }
    return sb.toString()
}

fun rc4(origin: String, key: String = ""): String {
    val e = CharArray(256)
    val f = CharArray(origin.length)

    for (index in e.indices) {
        e[index] = index.toChar()
    }

    var d = 0
    for (h in e.indices) {
        d = (d + e[h].toInt() + key[h % key.length].toInt()) % 256
        val l = e[h]
        e[h] = e[d]
        e[d] = l
    }

    for (m in origin.indices) f[m] = origin[m]


    var n = 0
    var i = 0
    for (j in f.indices) {
        n = (n + 1) % 256
        i = (i + e[n].toInt()) % 256
        val o = e[n]
        e[n] = e[i]
        e[i] = o
        val p = (e[n].toInt() + e[i].toInt() % 256) % 256
        f[j] = (f[j].toInt() xor e[p].toInt()).toChar()
    }

    return String(f)
}