package com.i27house.autoclockin.api.model.empinfo

data class EmpInfo(
    val `data`: Data,
    val errormsg: String,
    val msg: String,
    val result: Boolean,
    val resultcode: Int
)