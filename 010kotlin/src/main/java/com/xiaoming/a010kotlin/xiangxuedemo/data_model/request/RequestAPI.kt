package com.xiaoming.a010kotlin.xiangxuedemo.data_model.request

import com.xiaoming.a010kotlin.xiangxuedemo.config.Flag
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request

// 派生 和 object 尽量选择 object
object RequestAPI : IRequest {

    // 单例
    /*companion object {
        fun instanceRequestAPI() : IRequest = RequestAPI()

        // 如果是这样就需要派生
        fun instanceRequestAPI(context: Context) : IRequest = RequestAPI()
    }*/

    // 无参数
    //  override fun instanceRequestAction(url: String, resultData: NetworkResultData) {}

    // 1参数
    override fun instanceRequestAction(url: String, value: String, resultData: NetworkResultData) {
        commonOKHttpRequestAction(url, resultData, value)
    }

    // 2参数
    override fun instanceRequestAction(
            url: String,
            value1: String,
            value2: String,
            resultData: NetworkResultData
    ) {
        commonOKHttpRequestAction(url, resultData, value1, value2)
    }

    // 3参数
    override fun instanceRequestAction(
            url: String,
            value1: String,
            value2: String,
            value3: String,
            resultData: NetworkResultData
    ) {
        commonOKHttpRequestAction(url, resultData, value1, value2, value3)
    }

    /*override fun instanceRequestAction(
        url: String,
        resultData: NetworkResultData,
        parameter: Map<String, String>
    ) {}*/

    /** TODO ********************** 下面这一系列都是 OKHTTP 执行请求逻辑相关的 ************************/
    // 可变参数
    // 可变参数 ---> 具体 OkHttp 执行
    private fun commonOKHttpRequestAction(url: String, resultData: NetworkResultData, vararg values: String) {
        // TODO OKHTTP 请求
        // 1.创建一个OkhttpClient对象
        val okHttpClient = OkHttpClient()

        // 2.构建参数的body  MultipartBody.FORM 表单形式
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        // 参数根据可变参数的 数量变化而变化
        for (value in values) {
            // 2.2 封装参数
            builder.addFormDataPart(Flag.PART, value)
        }

        // 3.构建一个请求  post 提交里面是参数的builder   url()请求路径
        val request: Request = Request.Builder().url(url)
                .post(builder.build()).build()

        // 4.发送一个请求  给服务器
        okHttpClient.newCall(request).enqueue(resultData)
    }
}