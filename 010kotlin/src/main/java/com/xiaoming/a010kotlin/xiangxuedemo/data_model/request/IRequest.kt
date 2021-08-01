package com.xiaoming.a010kotlin.xiangxuedemo.data_model.request

interface IRequest {
    /** TODO ********************** 下面这一系列都是 NetWork相关的 ************************/

    // 无参数
    // fun instanceRequestAction(url: String, resultData: NetworkResultData)

    // 1参数
    fun instanceRequestAction(url: String, value: String, resultData: NetworkResultData);

    // 2参数
    fun instanceRequestAction(url: String, value1: String, value2: String, resultData: NetworkResultData);

    // 3参数
    fun instanceRequestAction(url: String, value1: String, value2: String, value3: String, resultData: NetworkResultData);

    // map 参数
    // fun instanceRequestAction(url: String, resultData: NetworkResultData, parameter: Map<String, String>)
}