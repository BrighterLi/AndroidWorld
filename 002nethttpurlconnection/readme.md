1 网络接口（Http接口，Apache接口，Android接口）
目前Android平台有三种网络接口可以使用
(1)Java.NET.*(标准Java接口)      HttpUrlConnection
HttpURLConnection继承自URLConnection类，用它可以发送和接口任何类型和长度的数据，且预先不用知道数据流的长度，可以设置请求方式get或post、超时时间。
(2)org.apache(Apache接口)     HttpClient(废弃） okhttp
(3)android.Net.*（android网络接口）  Socket
     大通常在 Android 中进行网络连接一般使用 Scoket 和 HTTP两种方式。而 HTTP 请求方式比 Scoket 多得多，HTTP 请求一般采用原生的 HttpClient 和 HttpUrlConnection
的两种网络访问方式。可是在 Android 5.0 的时候 Google 就不推荐使用 HttpClient 了，到了 Android 6.0 (api 23) SDK，不再提供 org.apache.http.* (只保留几个类),
如果使用了 httpClient 相关类的库项目，如 android-async-http 等等，会出现有一些类找不到的错误。而从Android4.4开始HttpURLConnection的底层实现采用的是okHttp。

2 HttpURLConnection
特点：
比较轻便，灵活，易于扩展。
在3.0后以及4.0中都进行了改善，如对HTTPS的支持。
在4.0中，还增加了对缓存的支持。
使用：
首先我们需要获取到一个HttpURLConnection实例，一般需要new出一个URL对象，并传入目标网络地址，通过调用openConnection()方法获得HttpURLConnection实例。
得到该实例后。我们需要设置一下http请求的的方法，这里我们主要研究get和post，默认是使用get方法。get一般用于从服务器获取数据，post一般用于向服务器提交数据，设置请求方法使用函数setRequestMethod(“POST”)进行设置。
此外可以进行一些请求的限制，比如连接超时的时间等，可以通过setConnectTimeout设置超时时间。
获取服务器返回的输入流，使用getInputStream方法获取。
读取内容并处理。
关闭连接，通过调用disconnect方法关闭当前的连接。 
关键代码如下 。
使用过程中不要忘记添加权限。
