Android中可以在设备本身的存储设备或外接的存储设备中创建用于保存数据的文件。在默认状态下，文件是不能在不同程序间共享的。
当用户卸载您的应用程序时，这些文件删除。

1 存储
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
4、getExternalCacheDir().getAbsolutePath()