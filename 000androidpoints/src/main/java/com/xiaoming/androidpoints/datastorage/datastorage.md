1 Android 五种数据存储的方式分别为：
1. SharedPreferences：以Map形式存放简单的配置参数；
2. ContentProvider：将应用的私有数据提供给其他应用使用；
3. 文件存储：以IO流形式存放，可分为手机内部和手机外部（sd卡等）存储，可存放较大数据；
4. SQLite：轻量级、跨平台数据库，将所有数据都是存放在手机上的单一文件内，占用内存小；
5. 网络存储 ：数据存储在服务器上，通过连接网络获取数据；


Sharedpreferences是Android平台上一个轻量级的存储类，用来保存应用程序的各种配置信息，其本质是一个以“键-值”对的方式保存数据的xml文件，其文件保存在/data/data//shared_prefs目录下。在全局变量上看，其优点是不会产生Application 、 静态变量的OOM（out of memory）和空指针问题，其缺点是效率没有上面的两种方法高。

Sqlite

greendao

ContentProvider
Android ContentProvider 完全解析及简单DEMO:https://blog.csdn.net/yhaolpz/article/details/51304345
ContentProvider 本来它的作用只是提供内容性质的跨进程访问。APP-A通过APP-B的ContentProvider相关实现，可以获取B的向外暴露的SQL或者SP数据

在创建ContentProvider前，首先要实现底层的数据源，数据源包括数据库、文件系统或网络等，
然后继承ContentProvider类中实现基本数据操作的接口函数。调用者不能直接调用ContentProvider
的接口函数，需要通过ContentResolver对象，通过URI间接调用ContentProvider。


ContentProvider：一般是成熟的App暴露自己的数据，其他app可以获取到数据，数据本身不是实时的。
Broadcast、Aidl、Messager中数据的实时更新的
采用ContentProvider方式，其解耦了底层数据的存储方式，使得无论底层数据存储采用何种方式，外界对数据的访问方式都是统一的，这使得访问简单高效
如一开始数据存储方式 采用 SQLite 数据库，后来把数据库换成MongoDB ，也不会对上层数据 ContentProvider 使用代码产生影响

ContentProvider 也分为三种，一，作为数据的存储和查询，也就是别人来调用你ContentProvider。二，调用者ContentResolver ，用来调用插入和查询数据。三，观察者ContentObserver ,比如监控短信的变化。


room
+flow 监听
数据库升级
