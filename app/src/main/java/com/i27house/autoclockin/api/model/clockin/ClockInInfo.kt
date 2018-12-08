package com.i27house.autoclockin.api.model.clockin

data class ClockInInfo(
    val `data`: Data,
    val errormsg: String,
    val msg: String,
    val result: Boolean,
    val resultcode: Int
)