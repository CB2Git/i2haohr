package com.i27house.autoclockin.api.model.clockin

/**
 * {
"type": 1,
"latitude": 22.582217068142363,
"longitude": 113.87301567925347,
"site_name": "",
"ssid": "",
"mac": "",
"model": "MI 8 Lite",
"brand": "Xiaomi",
"timestamp": 1543973251
}
 */

data class ClockInData(
    var latitude: Double,
    var longitude: Double,
    var timestamp: Long,
    var brand: String = "HUAWEI",
    var model: String = "PRO 20",
    var mac: String = "",
    var site_name: String = "",
    var ssid: String = "",
    var type: Int = 1
)