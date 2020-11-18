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
 
 3 缓存
 自己动手实现Android中的三级缓存框架：https://www.jianshu.com/p/70d954168c93
 (1)有时候Android应用中要获取比较大的数据，比如说图片流，短视频流等，如果每次都从网络上去请求，那么响应速度很慢的，用户体验不好。
 (2)二级缓存
 如果把服务器拉下来的数据保存在本地数据库中，在服务器数据并没有发生改变的时候，直接从本地中获取数据，这就是Android中的二级缓存，
 比直接每次从服务器中拉取数据多了本地的存储
 二级缓存缺点:
二级缓存的缺点是每次activity的数据都要从本地数据库中获取，虽然从本地数据库中获取的数据速度要比从服务器获取的速度快，
但是每次读写数据库进行的IO操作也是很花费时间的。
(3)三级缓存
•内存缓存•本地缓存（SD卡缓存）•网络缓存
三级缓存在二级缓存的基础上加了一个内存。从服务器获取的数据库除了存在本地数据库中，同时在内存中也保存一份，
这样当activity请求数据时可以先从内存中获取数据，如果内存中没有数据，或者内存中数据已经脏了的情况下，取本地数据库中的数据。
当本地数据库的数据也脏了的情况下取服务器数据。取回的数据存一份本地数据库，存一份内存中。
(4) 三级缓存框架
LRUcache
(5) demo
Android图片的三级缓存demo：https://blog.csdn.net/Song_74110/article/details/80876435


