1 URL Scheme是Android中的自定义的URL Scheme是一种页面内跳转协议，也可以被称为URLRouter，就是通过类似打开网页的方式去通过路由打开一个Activity，而非直接通过显式Intent方式去进行跳转。
URL Scheme就是一个可以让app相互之间可以跳转的对外接口。通过给APP定义一个唯一的URL路径来从外部快速的打开这个指定的APP，每个app的URL Scheme都是不一样的，如果存在一样的URL Scheme，
那么系统就会响应先安装那个app的URL Scheme，因为后安装的app的URL Scheme被覆盖掉了，是不能被调用的。

协议格式
协议格式：由scheme、host、port、path、query组成。
<scheme>://<host>:<port>/<path>?<query>
例如：jqchen://android:8088/urlscheme?id=3919

jqchen，即为Scheme，代表该Scheme 协议名称
android，即为Host，代表Scheme作用于哪个地址域
8088，即为port，代表该路径的端口号
urlscheme，即为path， 代表Scheme指定的页面
id，即为query，代表传递的参数

2 URL Scheme
(1)URL Scheme是什么
由于苹果的app都是在沙盒中，相互是不能访问数据的。但是苹果还是给出了一个可以在app之间跳转的方法：URL Scheme。简单的说，URL Scheme就是一个可以让app相互之间可以跳转的协议。
每个app的URL Scheme都是不一样的，如果存在一样的URL Scheme，那么系统就会响应先安装那个app的URL Scheme，因为后安装的app的URL Scheme被覆盖掉了，是不能被调用的。

(2)URL Scheme有什么作用
那么app之间的跳转有什么作用呢？我们所使用的每一个app就相当于一个功能，app的跳转可以使得每个app就像一个功能组件一样，帮助我们完成需要做的事情，比如三方支付，搜索，导航，分享等等。

(3)URL Scheme怎么使用
要跳转到别人的app，就要知道别人的app的跳转协议是什么，需要传入什么参数