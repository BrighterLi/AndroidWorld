webview课程视频：https://v.youku.com/v_show/id_XMTU3NTcyMjEzMg%3D%3D.html

那些年在WebView上踩过的坑：https://blog.csdn.net/u012124438/article/details/53401663?utm_medium=distribute.pc_relevant_bbs_down.none-task--2~all~sobaiduend~default-1.nonecase&depth_1-utm_source=distribute.pc_relevant_bbs_down.none-task--2~all~sobaiduend~default-1.nonecase

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


4 shouldOverrideUrlLoading
当加载的网页需要重定向的时候就会回调这个函数告知我们应用程序是否需要接管控制网页加载，如果应用程序接管，
并且return true意味着主程序接管网页加载，如果返回false让webview自己处理。
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