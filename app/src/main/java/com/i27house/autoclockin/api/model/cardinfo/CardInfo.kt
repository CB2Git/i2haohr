package com.i27house.autoclockin.api.model.cardinfo

data class CardInfo(
    val `data`: Data,
    val errormsg: String,
    val msg: String,
    val result: Boolean,
    val resultcode: Int
)