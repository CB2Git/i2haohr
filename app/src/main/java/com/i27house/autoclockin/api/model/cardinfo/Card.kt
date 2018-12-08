package com.i27house.autoclockin.api.model.cardinfo

data class Card(
    val end: String,
    val end_remark: String,
    val end_state: Int,
    val end_status: Int,
    val end_status_name: String,
    val end_val: Int,
    val shift_end: String,
    val shift_start: String,
    val start: String,
    val start_remark: String,
    val start_state: Int,
    val start_status: Int,
    val start_status_name: String,
    val start_val: Int
)