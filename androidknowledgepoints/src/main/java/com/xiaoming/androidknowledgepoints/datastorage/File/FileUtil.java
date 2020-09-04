package com.xiaoming.androidknowledgepoints.datastorage.File;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;


//Android-----File(文件各种操作):https://www.cnblogs.com/xiobai/p/10839494.html
public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSON_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    //动态申请SD卡读写的权限
    public static void verifyStoragePermission(Activity activity) {
        try {
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if(permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PERMISSON_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建一个文件夹
    public static void createFolder(String folderName) {
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // /storage/emulated/0/
                File sdCard = Environment.getExternalStorageDirectory();
                File newFolder = new File(sdCard + File.separator + folderName);
                if(!newFolder.exists()) {
                    boolean isSuccess = newFolder.mkdir();
                    Log.i(TAG, "文件夹创建状态:" + isSuccess);
                }
                // /storage/emulated/0/folderName
                Log.i(TAG, "文件夹创建目录:" + newFolder.toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "createFolder#e" + e);
        }
    }

    //创建文件
    public static void createFile(String fileName) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File sdCard = Environment.getExternalStorageDirectory();
                File newFile = new File(sdCard.getCanonicalPath() + File.separator  + fileName);
                if(!newFile.exists()) {
                    boolean isSuccess = newFile.createNewFile();
                    Log.i(TAG, "文件创建状态:" + isSuccess);
                    Log.i(TAG, "文件创建目录:" + newFile.toString());
                }
            } catch (Exception e) {
                Log.e(TAG, "createFile#e" + e);
                e.printStackTrace();
            }
        }
    }

    //写数据到文件, 写出
    public static void writeData(String path, String fileData) {
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path);
                FileOutputStream outputStream = new FileOutputStream(file, false);
                //将数据写入到文件中
                outputStream.write(fileData.getBytes("UTF-8"));
                Log.i(TAG,"writeData#将数据写入到文件中：" + fileData);
                outputStream.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "writeData#e" + e);
            e.printStackTrace();
        }
    }

    //续写数据到文件
    public static void continueWrite(String path, String data) {
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path);
                //按读写方式
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                //将文件指针移到文件尾
                raf.seek(file.length());
                //将数据写入到文件中
                raf.write(data.getBytes("UTF-8"));
                Log.i(TAG,"continueWrite#将数据写入到文件中：" + data);
                raf.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "continueWrite#e" + e);
            e.printStackTrace();
        }
    }

    //读取文件中的内容，读入
    public static String readFile(String path) {
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path);
                byte[] buffer = new byte[32 * 1024];
                 FileInputStream fileInputStream = new FileInputStream(file);
                 int len = 0;
                 StringBuffer sb = new StringBuffer("");
                 while ((len = fileInputStream.read(buffer)) > 0) {
                     sb.append(new String(buffer, 0, len));
                 }
                 fileInputStream.close();
                Log.e(TAG, "readFile:" + sb.toString());
                 return sb.toString();
            }
        } catch (Exception e) {
            Log.e(TAG, "readFile#e" + e);
            e.printStackTrace();
        }

        return null;
    }
}