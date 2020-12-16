package com.xiaoming.net.download.httpdownload;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {

    String line = null;
    StringBuffer strBuffer = new StringBuffer();
    BufferedReader bufferReader = null;

    //下载小型的文档文件，返回文档的String字符串
    public String downloadFiles(String urlStr) {
        try {
            InputStream inputStream = getInputStreamFromUrl(urlStr);
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferReader.readLine()) != null) {
                strBuffer.append(line + '\n');
            }
        } catch (Exception e) {
            System.out.println("bright8#downloadFiles#读写数据异常:" + e);
            strBuffer.append("something is wrong!!");
            System.out.println("读取数据异常:" + e);
        } finally {
            try {
                if(bufferReader != null) {
                    bufferReader.close();
                }
            } catch (Exception e) {
                System.out.println("bright8#downloadFiles#读写数据异常2:" + e);
                if(bufferReader != null) {
                    strBuffer.append("something is wrong!!");
                }
                e.printStackTrace();
            }
        }
        return strBuffer.toString();
    }

    //可以下载任意文件，例如MP3，并把文件存储在制定目录（-1：下载失败，0：下载成功，1：文件已存在）
    public int downloadFiles(String urlStr, String path, String fileName) {
        int result = -5;
        try {
            FileUtils fileUtils = new FileUtils();
            if (fileUtils.isFileExist(fileName, path)) return 1;//判断文件是否存在
            else {
                InputStream inputStream = getInputStreamFromUrl(urlStr);
                if (inputStream == null)  {
                    result = -4;
                }
                File resultFile = fileUtils.write2SDFromInput(fileName, path, inputStream);
                if (resultFile == null) {
                    result = -3;
                    return result;
                }
            }
        } catch (Exception e) {
            System.out.println("bright8#downloadFiles#读写数据异常3:" + e);
            return -1;
        }
        return 0;
    }

    //网络请求
    public InputStream getInputStreamFromUrl(String urlStr) throws IOException {
        //创建一个URL对象
        URL url = new URL(urlStr);
        //创建一个HTTP链接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        //使用IO流获取数据
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }
}
