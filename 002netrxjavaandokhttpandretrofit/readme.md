让我的项目也使用RxJava+OkHttp+Retrofit:https://www.imooc.com/article/44706?block_id=tuijian_wz
聚合平台获取数据：https://www.juhe.cn/
各自的职责：Retrofit 负责请求的数据和请求的结果，使用接口的方式呈现，
OkHttp 负责请求的过程，RxJava 负责异步，各种线程之间的切换。