1 Android 五种数据存储的方式分别为：
1. SharedPreferences：以Map形式存放简单的配置参数；
2. ContentProvider：将应用的私有数据提供给其他应用使用；
3. 文件存储：以IO流形式存放，可分为手机内部和手机外部（sd卡等）存储，可存放较大数据；
4. SQLite：轻量级、跨平台数据库，将所有数据都是存放在手机上的单一文件内，占用内存小；
5. 网络存储 ：数据存储在服务器上，通过连接网络获取数据；

Sharedpreferences是Android平台上一个轻量级的存储类，用来保存应用程序的各种配置信息，其本质是一个以“键-值”对的方式保存数据的xml文件，其文件保存在/data/data//shared_prefs目录下。在全局变量上看，其优点是不会产生Application 、 静态变量的OOM（out of memory）和空指针问题，其缺点是效率没有上面的两种方法高。

greendao

ContentProvider

room
