1缓存
    缓存是在web开发中经常用到的，将程序经常使用到或调用到的对象存在内存中，
或者是耗时较长但又不具有实时性的查询数据放入内存中，在一定程度上可以提高性能和效率。
缓存思路
https://blog.csdn.net/qq_28656671/article/details/50512530
https://www.cnblogs.com/onelikeone/p/9312208.html
产品设计：Android应用-开发技术【数据缓存】:https://blog.csdn.net/lnb333666/article/details/8460159
android中的数据缓存:https://www.cnblogs.com/wlwqnj/p/7676966.html
Android网络缓存的实现思路:https://www.jianshu.com/p/7dfd0e9143fe

缓存类型
(1)Map内存
https://blog.csdn.net/qq_26296197/article/details/79290277
https://blog.csdn.net/cl534854121/article/details/76121182
(2) SQLite
https://blog.csdn.net/asd8635938/article/details/68925297
(3) File
(4) HttpResponseCache
Android 网络数据的缓存HttpResponseCache:https://blog.csdn.net/qq_31726827/article/details/50557377
(5) Retrofit缓存
demo:https://www.jianshu.com/p/2557ef0e48a0

2 Other
(1) BitMap
 https://www.cnblogs.com/hwgt/p/5414397.html
 Bitmap是Android中处理图片的一个重要的类。用它可以获取图片信息，进行图片剪切、
 平移、旋转、缩放等操作，并可以指定格式保存图片文件。

(2) ConcurrentHashMap
 ConcurrentHashMap是Java中的一个线程安全且高效的HashMap实现。平时涉及高并发如果要用map结构