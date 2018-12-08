package com.i27house.autoclockin.api.model.empinfo

data class Data(
    val add_by_id: String,
    val attendance_type: Int,
    val attendance_type_name: String,
    val auto_sys_off: Boolean,
    val auto_wechat: Boolean,
    val card_music: Boolean,
    val card_photo: Boolean,
    val card_type: Int,
    val company_rule_id: String,
    val current_month: Int,
    val current_year: Int,
    val custom_setting_json: Any?,
    val custom_shift: Boolean,
    val daily_time: String,
    val emp_count: Int,
    val fixed_setting_json: List<String>,
    val forbid_old_photo: Boolean,
    val id: String,
    val is_default: Boolean,
    val is_rest: Boolean,
    val mode: Int,
    val name: String,
    val out_card: Boolean,
    val out_card_range: Int,
    val point_list: List<Point>,
    val special_json: List<SpecialJson>,
    val start_month: Int,
    val start_year: Int,
    val wifi_list: List<Wifi>
)