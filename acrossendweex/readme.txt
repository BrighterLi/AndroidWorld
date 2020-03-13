weex 提供了扩展机制，可以根据自己的业务进行定制自己的功能。
主要分为三类扩展：
Module 扩展 非 UI 的特定功能。例如 sendHttp、openURL 等。
Component 扩展 实现特别功能的 Native 控件。例如：RichTextview，RefreshListview 等。
Adapter 扩展 Weex 对一些基础功能实现了统一的接口，可实现这些接口来定制自己的业务。例如：图片下载等。

WeexContainer-Android
https://www.ctolib.com/topics-131448.html
Native跳转Weex
Native通知Weex
Weex跳转Weex（实现MPA多页面应用）
Weex跳转、调用Native
Bundle缓存功能
Bundle验证签名
开启调试器ChromeDebugger
新网络模块


跨端交互(Across End)
  <1> WebView
   (1) android.annotation.SuppressLint
       Lint是一个静态检查器，它围绕Android项目的正确性、安全性、性能、可用性以及可访问性进行分析。
       它检查的对象包括XML资源、位图、ProGuard配置文件、源文件甚至编译后的字节码。
       Lint包含了API版本检查、性能检查以及其他诸多特性。
       其中还有一个重要的改动是Lint可以使用@SuppressLint标注忽略指定的警告。

   <2> Weex
   (1) Android—>Weex  Android打开Weex页面
   加载weex分为两种情况，一种是加载本地，一种是加载网络。
   //加载网络资源url
   mWXSDKInstance.renderByUrl(url,url,options,jsonInitData,WXRenderStrategy.APPEND_ASYNC);}
   //加载本地资源
   mWXSDKInstance.render(template,template,options,jsonInitData,WXRenderStrategy.APPEND_ASYNC);}
   (2) Android弹出Weex弹窗