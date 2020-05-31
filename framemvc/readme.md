Android MVC:https://www.jianshu.com/p/3e7573126514
什么是mvc设计模式 （附代码示例）:https://blog.csdn.net/Detective_/article/details/79260315?utm_medium=distribute.pc_relevant.none-task-blog-OPENSEARCH-6.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-OPENSEARCH-6.nonecase

Android MVP模式简单例子实战:https://blog.csdn.net/qq_33048603/article/details/52824322

理解MVC：
我们通过示意图1来理解MVC是如何在Android上运作起来的。
View：与用户交互，响应用户的操作；
Control：接收View的事件请求、数据等，通知Model；
Model：Model接收到Control指令后，独立运作，将结果通知给View；

例如：User现在要搜索联系人名字为Jack的号码是多少
View：在EditTextView中填写了Jack，点击搜索按钮Button；
Control：接收到交互View发出的搜索请求，和字符串Jack，通知Model进行数据查询；
Model：Model接受到关键字Jack，进行名字匹配，完毕后，通过接口给View发送号码，通知View显示；

MVC特点：
1、通常Android中Activity充当Control，布局中的各种View控件充当View；因为Activity既有Control又有View，所以Control和View有少量的耦合性；
2、MVC把View和Model层很好的分离，启到了很好的解耦作用，耦合性低，减少代码模块之间的相互影响；
3、耦合性低，可方便添加需求，扩展代码，减少代码的修改量；
3、M、V、C三层模块分明，利于代码维护；