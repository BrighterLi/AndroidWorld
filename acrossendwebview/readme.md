资料：
webview课程视频：https://v.youku.com/v_show/id_XMTU3NTcyMjEzMg%3D%3D.html

那些年在WebView上踩过的坑：https://blog.csdn.net/u012124438/article/details/53401663?utm_medium=distribute.pc_relevant_bbs_down.none-task--2~all~sobaiduend~default-1.nonecase&depth_1-utm_source=distribute.pc_relevant_bbs_down.none-task--2~all~sobaiduend~default-1.nonecase
Android的WebView简单使用实例(附Demo):https://blog.csdn.net/qq_36243942/article/details/82252289?utm_medium=distribute.pc_relevant.none-task-blog-title-2&spm=1001.2101.3001.4242
工具：
vue线上调试环境：http://jsrun.net/n7fKp/edit

1 webview
webview是SDK中封装的一款基于webkit内核(后面开始基于Chromium内核)的浏览器组件，通过它我们可以实现网页的展示，
实现某些实时性强的内容的更新，现在流行的混合型移动端其实最大一点好处就是可以实现热更新，webview其实也有那么一点意味在里面。
https://blog.csdn.net/qq_28057577/article/details/56483798

2 shouldInterceptRequest
通知应用程序内核即将加载url制定的资源，应用程序可以返回本地的资源提供给内核，若本地处理返回数据，内核不从网络上获取数据。
WebView拦截请求
WebView 调用 loadUrl 后，会首先根据传入的URL获取响应，然后再将响应显示到页面上，这就是 WebView 的原理。
我们可以在获取响应过程中重新改变请求URL或者直接将响应替换。
该方法用于根据请求去获取响应，如果返回null，那么android会根据请求去获取响应并返回，但是如果你重写了该方法并返回了响应，
那么 WebView 就会使用你的响应数据。其中 WebResourceRequet 封装了请求，WebResourceResponse 封装了响应。

3 shouldoverrideurlloading和shouldinterceptrequest之间的区别？
WebView拦截url: https://www.jianshu.com/p/55fd544246c2
WebView加载原理从 WebView设置url开始，然后请求响应实体，最后将结果显示到ui屏幕上。
知道了大致原理，然后在拦截的时候，可以从两个方面着手：
第一个是在设置url时修改url
第二个是在响应实体替换实体
这样就可以达到拦截webview加载的功能了。要在url加载期拦截，则可以重写WebViewClient的shouldOverrideUrlLoading方法。如果要在响应实体阶段拦截，可以重写WebViewClient的shouldInterceptRequest方法。
shouldOverrideUrlLoading拦截的是url加载阶段;shouldInterceptRequest加载的是响应主体阶段
shouldOverrideUrlLoading主要拦截url;shouldInterceptRequest可拦截url,js，css等
也就是说整体而言shouldInterceptRequest拦截的范围比shouldOverrideUrlLoading广。
但是shouldOverrideUrlLoading能响应本地html文件加载，如assets文件夹下的html加载，而shouldInterceptRequest只能响应url之类的，而不响应本地文件加载。
还有一个需要注意的是，shouldOverrideUrlLoading的拦截处在shouldInterceptRequest上游(由webView加载原理决定)，
所以在shouldInterceptRequest拦截的时候，我们一般不重写shouldOverrideUrlLoading，这是为了保证shouldOverrideUrlLoading方法返回为false，
若shouldOverrideUrlLoading方法返回为true，则表示"上游"已经拦截了，那这时再在shouldInterceptRequest进行拦截已经不起作用了。

shouldoverrideurlloading:
shouldOverrideUrlLoading是在webView加载url阶段执行拦截的。
shouldinterceptrequest:
shouldInterceptRequest是在响应实体阶段拦截webview加载的实例。
其拦截原理是在响应阶段拦截下html数据，然后用本地Html或网络获取的html进行替换，重新加载。

4 shouldOverrideUrlLoading
当加载的网页需要重定向的时候就会回调这个函数告知我们应用程序是否需要接管控制网页加载，如果应用程序接管:
?默认返回：return super.shouldOverrideUrlLoading(view, url); 这个返回的方法会调用父类方法，也就是跳转至手机浏览器
return true意味着主程序接管网页加载
如果返回false让webview自己处理,是在webview内部执行
//覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


       @Override
       	public boolean shouldOverrideUrlLoading(WebView view, String url) {
       		// TODO Auto-generated method stub
       		Log.i(TAG, url + "===");
       		if (Uri.parse(url).getHost().equals("www.baidu.com")) {
       			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
       			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       			context.startActivity(intent);
       			return true;
       		}
       		return false;
       	}

5 WebView.loadUrl过程
WebView渲染流程
6 WebView预加载
7 WebView页面自动打开另外的url
8 WebView-WebViewClient
WebView-WebViewClient详解：https://blog.csdn.net/niuba123456/article/details/81177567
shouldOverrideUrlLoading（重定向时回调）
onPageStarted(开始加载网络)
onPageFinished(网页加载完成回调)
onLoadResoure(加载Url资源回调)
shoudIdInterceptRequest
onReceivedError（访问地址错误回调）
onFormResubm（post请求）
doUpdateVisitedHistory（更新数据库）
onReceivedSsslError（加载SSL错误回调）
onReceivedHttpAuthRequest(auth请求回调)
shouldOverrideKeyEvent(按键处理回调)
onScaleChanged（WebView比例改变）
onReceivedLoginRequest(账户自动登录

6 WebViewClient与WebChromeClient
WebViewClient:在影响View的事件到来时，会通过WebViewClient中的方法回调通知用户
WebViewClient帮助WebView处理各种通知、请求事件的
WebChromeClient：当影响浏览器的事件到来时，就会通过WebChromeClient中的方法回调通知用法。
WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等

7 Webview解析html源码
Android WebKit消息处理：https://blog.csdn.net/feiyinzilgd/article/details/19019413
(1)Android WebKit HTML主资源加载过程
https://blog.csdn.net/feiyinzilgd/article/details/20015281?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
在浏览器里面输入网址，最终浏览器会调用WebView的loadUrl()，然后就开始加载整个网页。整个加载过程中，最重要的一步就是HTML主资源的加载。WebKit将网页的资源分为主资源(MainResource)和子资源(SubResource)。
(2)webkit 子资源加载流程
一个网页由主文档和子资源组成。主文档描述网页的框架，布局。子资源是组成网页的子元素，
包括图片、CSS、JS等。为了显示网页，先要把资源加载到内存。 加载就是指把需要的资源加载到内存这一过程。
Webkit用到很多缓存机制，加载可能是从网络加载，也可能是从本地缓存加载。Webkit的加载分为两条线，一条是主文档的加载，一条是子资源的加载。
(3)WebKit资源分类
主资源：HTML文件。
子资源：CSS, JS, JPG等等，除了HTML文件之外的所有资源都称之为子资源

8 WebView中获取网页资源
http://www.360doc.com/content/12/0101/21/2631212_176555498.shtml
view.loadUrl("javascript:window.local_obj.showSource('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {}}

9 WebView性能优化
手把手教你构建 Android WebView 的缓存机制 & 资源预加载方案:
https://blog.csdn.net/carson_ho/article/details/71402764
(1)H5页面加载速度慢
1) 渲染速度慢
前端H5页面渲染的速度取决于 两个方面：
Js 解析效率
Js 本身的解析过程复杂、解析速度不快 & 前端页面涉及较多 JS 代码文件，所以叠加起来会导致 Js 解析效率非常低
手机硬件设备的性能
由于Android机型碎片化，这导致手机硬件设备的性能不可控，而大多数的Android手机硬件设备无法达到很好很好的硬件性能
总结：上述两个原因 导致 H5页面的渲染速度慢。
2)  页面资源加载缓慢
H5 页面从服务器获得，并存储在 Android手机内存里：
H5页面一般会比较多
每加载一个 H5页面，都会产生较多网络请求：
HTML 主 URL 自身的请求；
HTML外部引用的JS、CSS、字体文件，图片也是一个独立的 HTTP 请求
每一个请求都串行的，这么多请求串起来，这导致 H5页面资源加载缓慢
总结：H5页面加载速度慢的原因：渲染速度慢 & 页面资源加载缓慢 导致。
(2)耗费流量
每次使用 H5页面时，用户都需要重新加载 Android WebView的H5 页面
每加载一个 H5页面，都会产生较多网络请求（上面提到）
每一个请求都串行的，这么多请求串起来，这导致消耗的流量也会越多

10 html
(1)h5
1) <p> 标签  标签定义段落
<p>This is some text in a very short paragraph</p>

(2)javascript
1)window.location.href
avascript中的location.href有很多种用法，主要如下：
self.location.href="/url" 当前页面打开URL页面
location.href="/url" 当前页面打开URL页面
windows.location.href="/url" 当前页面打开URL页面，前面三个用法相同。
this.location.href="/url" 当前页面打开URL页面
parent.location.href="/url" 在父页面打开新页面
top.location.href="/url" 在顶层页面打开新页面

11 html原生跳转
(1)html自动跳转到指定的url
三种简单的html网页自动跳转方法：https://blog.csdn.net/aflyeaglenku/article/details/51699242?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
(2)html跳转到原生页面
通过Uri跳转Activity(当然对于WebView中的网页同样适用): https://blog.csdn.net/vaechr/article/details/77802555

12 JSBridge
Hybrid开发中JSBridge的实现: https://zhuanlan.zhihu.com/p/32899522
JS引擎工作原理: https://www.jianshu.com/p/c89ec6802d1f
(1)什么是JSBridge
主要是给 JavaScript 提供调用 Native 功能的接口，让混合开发中的前端部分可以方便地使用 Native 的功能（例如：地址位置、摄像头）。
而且 JSBridge 的功能不止调用 Native 功能这么简单宽泛。实际上，JSBridge 就像其名称中的Bridge的意义一样，是 Native 和非 Native 之间的桥梁，
它的核心是构建 Native 和非 Native 间消息通信的通道，而且这个通信的通道是双向的。
(2)





