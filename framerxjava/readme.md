框架(函数响应式编程)—RxJava Demo
(1) 观察者、被观察者、订阅
(2) 操作符
    创建操作符：
    https://blog.csdn.net/nicolelili1/article/details/52044330
    create:
    defer:Defer操作符会一直等待直到有观察者订阅它，然后它使用Observable工厂方法生成一个Observable。
 它对每个观察者都这样做，因此尽管每个订阅者都以为自己订阅的是同一个Observable，
 事实上每个订阅者获取的是它们自己的单独的数据序列。
    just:不断地将事件添加到任务队列中进行发射
    from:将数组的内容进行发射

    变换操作符：
    map:
    flatMap:

 Rxjava+Retrofit
    Rxjava、Retrofit返回json数据解析异常处理:https://www.jianshu.com/p/337c06f322c2