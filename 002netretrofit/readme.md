get请求
post请求
文件上传
文件下载
开启日志拦截
与RxJava结合使用
https://blog.csdn.net/qq_38998213/article/details/82352104

baseUrl添加的是地址的主域名,@GET 包含的是请求地址，是主域名之后的地址。举个例子，
请求的全地址：http://plus31.366ec.net/Route.axd?method=vast.Store.manager.list，
主域名为：http://plus31.366ec.net/
@GET包含的地址为：/Route.axd?method=vast.Store.manager.list
