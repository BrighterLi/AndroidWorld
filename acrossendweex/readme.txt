1 weex 提供了扩展机制，可以根据自己的业务进行定制自己的功能。
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


2 跨端交互(Across End)
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

自定义Component
https://www.bookstack.cn/read/weex/guide-extend-android.md

3 weex框架
整个运行框架包括三大部分：JS Bridge、Render、Dom，这三大部分都包含在 WXSDKManager 中。
WXBridgeManager、WXRenderManager、WXDomManager 都可以通过WXSDKManager 获取。
(1)JS Bridge：主要用来和 JS Engine（V8）进行双向通信，运行在JSBridge线程中。Weex 的初始化，
Component、Module、DomObject的注册与调用，JSBridge 线程管理最终都会由JS Bridge 的管理类
WXBridgeManager 完成。所有和 Dom 相关的操作都会通知到 Dom 线程，交由 WXDomModule 处理。
(2)Render：主要用来操作具体的Native View，包括管理Native View的各种操作（添加／删除Component，
构造Component Tree等）、Native View的布局等，运行在UI线程中。由 WXRenderManager 统一管理，
具体操作由 WXRenderStatement 管理，每一个weex instance 一一对应一个 WXRenderStatement。
WXRenderStatement 具体就是操作 WXComponent。
(3)Dom：主要用来操作Dom结构，包括生成对应的Dom Tree，添加／删除Dom 节点（WXDomObject）等操作，
运行在独立的 Dom 线程中。由 WXDomManager 统一管理，具体操作由 WXDomStatement 管理，每一个weex
 instance 一一对应一个 WXDomStatement。WXDomStatement 具体就是操作 WXDomObject。所有的 Dom 操作
 （包括CSSLayout的计算）都在 Dom 线程中，完成后会通知UI线程处理对应的Native Component View。

 4 关键类
 WXSDKManager
 WXBridgeManager  jsBrigdge
 WXRenderManager 渲染
 WXDomManager  dom结构操作
 WXSDKEngine
 WXSDKInstance:renderByUrl
 WXManager

 5 源码分析之界面渲染
 Weex Android SDK源码分析之界面渲染：https://blog.csdn.net/walid1992/article/details/51705371

6 native与weex通讯
(1)native—>weex
1)fireGlobalEventCallback
Map<String,Object> params=new HashMap<>();
params.put("data",stringBuffer);
mWXSDKInstance.fireGlobalEventCallback("phoneBookLocation",params);
2)继承WXModule
callback.invoke回调给native
(2)weex—>native
1)继承WXModule
@JSMethod(uiThread=true);callback.invoke回调给native