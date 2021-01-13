1、混合开发集成方式
(1)Android 混合Flutter之源码集成方式
Android 混合Flutter之源码集成方式：https://juejin.cn/post/6844903924315471880
Android 混合Flutter之源码集成方式：https://blog.csdn.net/qq_33453910/article/details/94982726
原理就是Flutter作为Android Module出现在项目中，这样就可以在已有的项目中使用，Android项目也是一个工程，Flutter项目也是一个工程，这样就互不相关，也很好进行管理。
源码分析
在Android原生调用Flutter页面之前，先知道FlutterActivity这个类。FlutterActivity—>FlutterActivityDelegate—FlutterMain—FlutterView—FlutterNativeView
FlutterActivity的生命周期各个方法实际由FlutterActivityDelegate代理执行，并且知道FlutterActivity通过委托代理的方式解决来生命周期的回调，
插件管理和FlutterView的创建，是Android原生调Flutter页面的中间桥梁。
优点
1.简单快捷，Google原生支持
2.开发调试方便，和原生交互较多或需要依赖原生数据环境的时候特别能体现出来
缺点
1.团队所有人都可能要会Flutter并且都要安装Flutter环境
2.需要对现有的编译体系做出修改，也就是要同时编译Flutter项目和Native项目
3.Flutter会直接侵入到Native项目中去
4.编译速度慢
(2)Android 混合Flutter之产物集成方式
Android 混合Flutter之产物集成方式:https://blog.csdn.net/qq_33453910/article/details/100189520?utm_medium=distribute.pc_feed_404.none-task-blog-BlogCommendFromBaidu-2.nonecase&depth_1-utm_source=distribute.pc_feed_404.none-task-blog-BlogCommendFromBaidu-2.nonecas
把flutter项目作为aar添加到已有的Android工程上:http://www.mamicode.com/info-detail-3027964.html
将开发的Flutter项目单独编译成aar文件，然后以组件的形式被主工程(Native工程)依赖，aar文件可以以maven方式(远程方式)的依赖

2 脚本
采用了调试模式使用源码的方式，打包的时候使用aar的方式，这样做的好处是，既能够保留开发期间的可调试行，也能保障构建环境不依赖Flutter环境。
为此，我们团队双端各写了一个脚本，来切换接入模式，且自动将Flutter产物提提取并推送到原生工程以便打包。

3 热更新
继腾讯之后，又有大佬向Flutter热更新动手了：https://www.360kuai.com/pc/95b4f4a3d97af423c?cota=3&kuai_so=1&sign=360_57c3bbd1&refer_scene=so_1