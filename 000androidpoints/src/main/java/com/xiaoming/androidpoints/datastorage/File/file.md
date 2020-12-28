Android中可以在设备本身的存储设备或外接的存储设备中创建用于保存数据的文件。在默认状态下，文件是不能在不同程序间共享的。
当用户卸载您的应用程序时，这些文件删除。

1 File存储
彻底搞懂Android文件存储---内部存储，外部存储以及各种存储路径解惑：https://blog.csdn.net/u010937230/article/details/73303034
(1) 内部存储
1)注意内部存储不是内存。内部存储位于系统中很特殊的一个位置，如果你想将文件存储于内部存储中，那么文件默认只能被你的应用访问到，
且一个应用所创建的所有文件都在和应用包名相同的目录下。也就是说应用创建于内部存储的文件，与这个应用是关联起来的。当一个应用卸载之后，内部存储中的这些文件也被删除。
内部存储空间十分有限，因而显得可贵，另外，它也是系统本身和系统应用程序主要的数据存储所在地，一旦内部存储空间耗尽，手机也就无法使用了。所以对于内部存储空间，我们要尽量避免使用。
Shared Preferences和SQLite数据库都是存储在内部存储空间上的。内部存储一般用Context来获取和操作。
2)访问内部存储的API方法
1、Environment.getDataDirectory()
2、getFilesDir().getAbsolutePath()
3、getCacheDir().getAbsolutePath()
4、getDir(“myFile”, MODE_PRIVATE).getAbsolutePath()
(2)外部存储
1)
4.4之前：内置存储（机身存储）当做内部存储，而把扩展的SD卡当做是外部存储
4.4之后：机身外部存储;SD卡
2)在4.4以后的系统中，API提供了这样一个方法来遍历手机的外部存储路径
File[] files;
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
    for(File file:files){
        Log.e("main",file);
    }
}
如果你的手机插了SD卡的话，那么它打印的路径就有两条了，例如我的华为荣耀7插了SD卡，它的结果如下：
/storage/emulated/0/Android/data/packname/files/mounted
/storage/B3E4-1711/Android/data/packname/files/mounted
其中/storage/emulated/0目录就是机身存储的外部存储路径
而/storage/B3E4-1711/就是SD卡的路径
3)访问外部存储的API方法
1、Environment.getExternalStorageDirectory().getAbsolutePath()
2、Environment.getExternalStoragePublicDirectory(“”).getAbsolutePath()
3、getExternalFilesDir(“”).getAbsolutePath()
4、getExternalCacheDir().getAbsolutePath
*外部存储需要判定是否有权限
4)不同Android版本下getDataDirectory，getFilesDir，getCacheDir，getDir，getExternalStorageDirectory，getExternalStoragePublicDirectory，getExternalFilesDir，getExternalCacheDir，getExternalCacheDir，getRootDirectory的区别和联系
（以下的打印结果是基于荣耀7的（系统版本6.0）：
1、Environment.getDataDirectory() = /data
这个方法是获取内部存储的根路径
2、getFilesDir().getAbsolutePath() = /data/user/0/packname/files
这个方法是获取某个应用在内部存储中的files路径
3、getCacheDir().getAbsolutePath() = /data/user/0/packname/cache
这个方法是获取某个应用在内部存储中的cache路径
4、getDir(“myFile”, MODE_PRIVATE).getAbsolutePath() = /data/user/0/packname/app_myFile
这个方法是获取某个应用在内部存储中的自定义路径
方法2,3,4的路径中都带有包名，说明他们是属于某个应用
…………………………………………………………………………………………
5、Environment.getExternalStorageDirectory().getAbsolutePath() = /storage/emulated/0
这个方法是获取外部存储的根路径
6、Environment.getExternalStoragePublicDirectory(“”).getAbsolutePath() = /storage/emulated/0
这个方法是获取外部存储的根路径
7、getExternalFilesDir(“”).getAbsolutePath() = /storage/emulated/0/Android/data/packname/files
这个方法是获取某个应用在外部存储中的files路径
8、getExternalCacheDir().getAbsolutePath() = /storage/emulated/0/Android/data/packname/cache
这个方法是获取某个应用在外部存储中的cache路径
注意：其中方法7和方法8如果在4.4以前的系统中getExternalFilesDir(“”)和getExternalCacheDir()将返回null，如果是4.4及以上的系统才会返回上面的结果，也即4.4以前的系统没插SD卡的话，就没有外部存储，它的SD卡就等于外部存储；而4.4及以后的系统外部存储包括两部分，getExternalFilesDir(“”)和getExternalCacheDir()获取的是机身存储的外部存储部分，也即4.4及以后的系统你不插SD卡，它也有外部存储，既然getExternalFilesDir(“”)和getExternalCacheDir()获取的是机身存储的外部存储部分，那么怎么获取SD卡的存储路径呢，还是通过上面提到的getExternalFilesDirs(Environment.MEDIA_MOUNTED)方法来获取了，不知道Android有没有提供相关的API接口来获取SD卡的存储路径，大家可以去查资料。又重复了上面的话，主要是提醒大家要注意不同的Android版本是有差别的，这个最坑了。
…………………………………………………………………………………………
Environment.getDownloadCacheDirectory() = /cache
Environment.getRootDirectory() = /system
这两个方法没什么说的了，每个版本的android系统都一样
…………………………………………………………………………………………
从上面我们很清楚的可以看到上面的方法可以分为三类，我用横线隔开了。第一类是位于根目录/data下；还有一类是位于根目录/storage下，可以看到调用它们的API方法都带了一个External；另外一类不在/data下也不再/storage下，比如系统文件/system，或者缓存文件/cache。
/data目录下的文件物理上存放在我们通常所说的内部存储里面
/storage目录下的文件物理上存放在我们通常所说的外部存储里面
/system用于存放系统文件，/cache用于存放一些缓存文件，物理上它们也是存放在内部存储里面的


2 存储方式
(1) File


3 相关知识
(1)流
1)读：InputStream  FileInputStream BufferInputStream  InputStreamreader ByteArrayInputStream
从硬盘往内存里Input东西就是读取数据
BufferInputStream：
2)写：OutputStream

