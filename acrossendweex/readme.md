资料：
sdk:https://weex.apache.org/download/download.html#_0-28-0
(1)Weex-初次见到你：https://developer.aliyun.com/article/59341

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
(1) 整个运行框架包括三大部分：JS Bridge、Render、Dom，这三大部分都包含在 WXSDKManager 中。
WXBridgeManager、WXRenderManager、WXDomManager 都可以通过WXSDKManager 获取。
1)JS Bridge：主要用来和 JS Engine（V8）进行双向通信，运行在JSBridge线程中。Weex 的初始化，
Component、Module、DomObject的注册与调用，JSBridge 线程管理最终都会由JS Bridge 的管理类
WXBridgeManager 完成。所有和 Dom 相关的操作都会通知到 Dom 线程，交由 WXDomModule 处理。
2)Render：主要用来操作具体的Native View，包括管理Native View的各种操作（添加／删除Component，
构造Component Tree等）、Native View的布局等，运行在UI线程中。由 WXRenderManager 统一管理，
具体操作由 WXRenderStatement 管理，每一个weex instance 一一对应一个 WXRenderStatement。
WXRenderStatement 具体就是操作 WXComponent。
3)Dom：主要用来操作Dom结构，包括生成对应的Dom Tree，添加／删除Dom 节点（WXDomObject）等操作，
运行在独立的 Dom 线程中。由 WXDomManager 统一管理，具体操作由 WXDomStatement 管理，每一个weex
 instance 一一对应一个 WXDomStatement。WXDomStatement 具体就是操作 WXDomObject。所有的 Dom 操作
 （包括CSSLayout的计算）都在 Dom 线程中，完成后会通知UI线程处理对应的Native Component View。

 Weex中的线程：
 JSBridgeThread：用来java jni层和v8 engine之间进行通信，包括初始化js framework、callJS、callNative等。
 DomThread：用来进行Dom操作，包括Dom解析、设置Dom样式、CSS Layout操作、生成Component Tree等操作。图中可知 DomThread 中的操作都是v8 engine调用上来的，也就是说是js runtime生成dom的各种操作，一旦js bundle过大，会是一个瓶颈。
 UIThread：用来真正的视图渲染，包括设置View Layout、设置View Padding、绑定数据、Add/Remove View等操作。

(2) 框架
Weex之Android端的浅析(一)：https://blog.csdn.net/xuguoli_beyondboy/article/details/53064155
IWXUserTrackAdapter:用来处理日志信息接口，常常拿来做一些用户埋点统计．
IWXImgLoaderAdapter:用来处理View加载图片接口，可以实现其控制如何加载远程和本地图片．
IWXHttpAdapter:用来处理网络请求的接口，常常处理请求一系列过程，默认实现DefaultWXHttpAdapter.
IActivityNavBarSetter:用来处理页面跳转接口，可以实现其接口来控制页面的跳转．
IWXStorageAdapter:用来处理存储接口，例如SQLite存储,默认实现DefaultWXStorage.
IWXDebugAdapter:用来处理调试接口，通常实现其接口来在Chrom上做一些页面的调试．

WXDomManager:专门用来管理Dom节点一些操作，如创建节点对应对象，但真正操作是委托给其他的对象
WXBridgeManager:用来处理Js和Android端的通信，例如Js端调用Android端Native层的方法
WXRenderManager:用来处理一些渲染操作，例如通过WXRenderStatement将Js层标签转到native层的View组件

WXSDKManager

一个weex页面在Android端渲染，分了三大模块，Dom节点操作管理模块，跨端通信模块，渲染模块
weex的绘制流程；weex的组件；weex的module扩展
(3) weex的绘制流程
在没有WebView的情况下，Native层又如何去解析Js代码呢？js和java之间的通信桥梁：
js代码——V8引擎——C++——JNI机制——java
Js如果要与java通信，那么可以通过google v8引擎先与c++通信，然后在通过jni机制来实现与java的通信，从解决了Js页面与Native的通信了
(4) weex的组件
demo:https://blog.csdn.net/xuguoli_beyondboy/article/details/53064155
(5)weex的module扩展
(6)weex 工作原理
阿里的weex框架到底是什么:https://www.cnblogs.com/lvyongbo/archive/2004/01/13/5922448.html
weex 主要就是做了三件事
在服务端用 Transformer 工具把 Vue 代码转换成 Js Bundle。
在客户端运行Js Framework 的 JavaScript 引擎，解释执行Js Bundle生成Virtual DOM。
在客户端设计一套 JS Bridge，能使IOS端（或者Android端）的Object-C语言（或Java语言）与Javascript语言相互调用，把Virtual Dom转换为DOM，渲染到页面。H5端直接和Js Framework 通讯，不需要Js Bridge。

 4 关键类
 WXSDKManager
 WXBridgeManager  jsBrigdge
 WXRenderManager 渲染
 WXDomManager  dom结构操作
 WXSDKEngine
 WXSDKInstance:renderByUrl
 WXManager

 5 源码分析
 (1)源码分析之界面渲染
 Weex Android SDK源码分析:https://blog.csdn.net/nupt123456789/article/details/53691292?utm_medium=distribute.pc_relevant.none-task-blog-title-5&spm=1001.2101.3001.4242
 Weex Android SDK源码分析之界面渲染：https://blog.csdn.net/walid1992/article/details/51705371
 https://blog.csdn.net/walid1992/article/details/51759588?utm_medium=distribute.pc_relevant.none-task-blog-title-2&spm=1001.2101.3001.4242
详解 Weex 页面的渲染过程: https://segmentfault.com/a/1190000010415641
6 native与weex通讯
(1)native—>weex
1)fireGlobalEventCallback 全局事件
weex和Android的交互：https://blog.csdn.net/hellenicguo/article/details/59058065
Map<String,Object> params=new HashMap<>();
params.put("data",stringBuffer);
mWXSDKInstance.fireGlobalEventCallback("phoneBookLocation",params);
2)继承WXModule
callback.invoke回调给native
(2)weex—>native
1)继承WXModule
@JSMethod(uiThread=true);callback.invoke回调给native

7 weex内存
(1) list内存
跨越适配&性能那道坎，企鹅电竞Android weex优化 ：https://www.cnblogs.com/wetest/p/10324926.html
Weex-iOS内存分析：https://www.jianshu.com/p/5624f766bf1b

8 Weex预加载
(1)
记录weex接入过程：https://blog.csdn.net/zhuweideng/article/details/53995737
Weex实践：https://blog.csdn.net/sinat_17775997/article/details/78771805
懒加载和预加载最简单demo演示: https://www.cnblogs.com/smileyqp/p/12675286.html
(2)
预加载解决了 1 个问题：
用户访问页面（H5/Weex）之前，将页面静态资源（HTML/JS/CSS/IMG...）打包提前下载到客户端；用户访问页面时，将网络 IO 拦截并替换为本地文件IO；从而实现加载性能的大幅度提升。
懒加载：即按非必要资源延后加载；是指在资源需要的时候才去用js加载资源;一般不是首屏要看到的；非核心功能的懒加载，按需加载
   懒加载时机：首屏内容过大，一次加载完太耗时，想让用户尽快的看到页面；用户后续使用的某个功能需要用到大量的资源(比如图片，聊天)
预加载：是指在加载完成之后才对用户进行显示
   原理是先利用遮罩遮住页面，js加载资源，判断加载完成去掉遮罩
   预加载应用场景：需要流畅体验的应用
一个页面花了哪些时间：Html页面请求，页面资源请求，页面绘制
页面加载流程：Html文件下载，Head要求资源解析完毕，资源加载，Dom树渲染
资源加载细节：下载html，解析资源需要给资源排队，按照队列,依次发出请求(但不用等上个请求回来)
加载的js bundle 虽然也不大，duration也很短。但是为了让速度更进一步，还是做预加载方案。
url：js链接，可以是本地的存储地址/sdcard/com.showjoy.shop/weex/order.js，也可以是线上链接 https://xxxxx/0.4.3/order.js

9 Weex机制
Weex实践：https://blog.csdn.net/sinat_17775997/article/details/78771805
说到底，最后的渲染结果都是返回一个View，理论上根据业务需求，可以将view放置在页面的任何地方。
跳转规则：
Native 渲染weex页面的时候，需要传入构建出来的js bundle，即一个js文件。但是，不管是Native的日常写法还是前端的惯常用法，都不会直接跳转到一个js文件。
所以，考虑到符合前端的日常写法，跳转时，统一跳转到url。
不管是weex，native，webview里的跳转都是url，然后再根据一定的规则进行match，根据match结果来决定是用weex、native还是webview来打开。

10 Weex调试
(1) 
WEEX系列 我的第一个WEEX DEMO:https://blog.csdn.net/weixin_33757911/article/details/88950786
(2)weex-inspector/Weex debug
Weex入门教程之5，debug调试，集成 Devtools 到 Android: https://blog.csdn.net/u013474104/article/details/55051018
集成Devtool到Android: https://weex.apache.org/zh/guide/debug/integrate-devtool-to-android.html#android接入指南
weexteam /android-devtools-for-Apache-Weex : https://github.com/weexteam/android-devtools-for-Apache-Weex/tree/master/inspector?spm=a2c7j.-zh-guide-debug-integrate-devtool-to-android.0.0.2ce15ef7XonDRW
[Weex 学习]Weex Debug模式:https://blog.csdn.net/Lebron_xia/article/details/85145978
Weex调试神器——Weex Devtools使用手册: https://blog.csdn.net/Taobaojishu/article/details/99829673
使用 Devtools 调试 Weex 页面: htps://www.cnblogs.com/hehey/articles/6231379.html
命令：weex debug ...vue

11
(1)图片加载 IWXImgLoaderAdapter
Weex使用Image控件加载图片,但是并不能直接显示出来图片,主要还是要Native端实现图片加载
(2) IWXHttpAdapter

12文字加载
Weex初体验之加载文字库：https://blog.csdn.net/q957789074/article/details/80729591

13 weex
命令：
cd进入home.we的所在文件夹内部->将.we/.vue文件转成.js文件：weex compile home.we home.js

14 工具
Playground : Playground在线，直接在线编写代码并预览效果：
http://dotwe.org/vue
android weex module扩展 component扩展 学习，调试工具：https://blog.csdn.net/ligaozeng/article/details/88896578
IWeex: https://liuxinyea.github.io/IWeex/

15 资料
全球的weex资源都在这里：https://blog.csdn.net/weixin_33913377/article/details/88586939

16 Demo
renderByUrl Demo：https://www.mobibrw.com/2019/21684
