webview课程视频：https://v.youku.com/v_show/id_XMTU3NTcyMjEzMg%3D%3D.html
1 shouldInterceptRequest
WebView拦截请求
WebView 调用 loadUrl 后，会首先根据传入的URL获取响应，然后再将响应显示到页面上，这就是 WebView 的原理。
我们可以在获取响应过程中重新改变请求URL或者直接将响应替换。
该方法用于根据请求去获取响应，如果返回null，那么android会根据请求去获取响应并返回，但是如果你重写了该方法并返回了响应，
那么 WebView 就会使用你的响应数据。其中 WebResourceRequet 封装了请求，WebResourceResponse 封装了响应。

2 shouldoverrideurlloading和shouldinterceptrequest之间的区别？


3 
//覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开  
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });