package com.xiaoming.acrossendwebview.longclicktosavepic;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoming.acrossendwebview.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.widget.Toast;

//WebView长按图片保存完全详解:https://xiaozhuanlan.com/topic/9874152630
//安卓webview图片长按保存：https://blog.csdn.net/niuageniuc/article/details/91957448?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-6&spm=1001.2101.3001.4242
public class WebViewActivity2 extends Activity {
    private WebView mWebView;
    private  String mPicUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);

        initView();
    }

    private void initView() {
        String url = "http://www.baidu.com";
        mWebView = findViewById(R.id.webview2);
        //启用支持JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //启用支持DOM Storage
        mWebView.getSettings().setDomStorageEnabled(true);
        //加载web资源
        mWebView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("bright8", "shouldOverrideUrlLoading#url:" + url);
                view.loadUrl(url);
                return true;
            }

        });

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final WebView.HitTestResult hitTestResult = mWebView.getHitTestResult();
                // 如果是图片类型或者是带有图片链接的类型
                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                        hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    // 弹出保存图片的对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity2.this);
                    builder.setTitle("提示");
                    builder.setMessage("保存图片到本地");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mPicUrl = hitTestResult.getExtra();//获取图片链接
                            Log.d("bright8", "onLongClick#picUrl:" + mPicUrl);
                            askPermission();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        // 自动dismiss
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }
                return false;//保持长按可以复制文字
            }

        });
    }

    private void askPermission() {
        int permission = ActivityCompat.checkSelfPermission(WebViewActivity2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(WebViewActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        String[] permissionStrArray = new String[2];
        if(permission != PackageManager.PERMISSION_GRANTED && permission2 != PackageManager.PERMISSION_GRANTED) {
            permissionStrArray[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            permissionStrArray[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        } else if(permission != PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED) {
            permissionStrArray[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        }else if(permission == PackageManager.PERMISSION_GRANTED && permission2 != PackageManager.PERMISSION_GRANTED) {
            permissionStrArray[0] = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        ActivityCompat.requestPermissions(WebViewActivity2.this, permissionStrArray, 1);
    }

    //把url转换为bitmap对象（要在子线程操作）
    public void url2bitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                save2Album(bm);
            }
        } catch (Exception e) {
            Log.d("bright8", "url2bitmap#保存失败#e:" + e.toString());
            new Handler().post (new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WebViewActivity2.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    //保存照片到相册，并通知系统更新相册
    private void save2Album(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "图片");
        if (!appDir.exists()) appDir.mkdir();
        String[] str = mPicUrl.split("/");
        String fileName = str[str.length - 1];
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            onSaveSuccess(file);
        } catch (IOException e) {
            Log.d("bright8", "save2Album#保存失败#e:" + e.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WebViewActivity2.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                Toast.makeText(WebViewActivity2.this, "已成功保存到：相册-图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //保存图片到相册
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(URLUtil.isValidUrl(mPicUrl)) {
                    Log.d("bright8", "URLUtil.isValidUrl(mPicUrl)");
                    url2bitmap(mPicUrl);
                } else {
                    savePicture(mPicUrl);
                }

            }
        }).start();
    }

    private boolean savePicture(String base64DataStr) {
        // 1.去掉base64中的前缀
        String base64Str = base64DataStr.substring(base64DataStr.indexOf(",") + 1, base64DataStr.length());
        // 获取手机相册的路径地址
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
        //创建文件来保存，第二个参数是文件名称，可以根据自己来命名
        File   file     = new File(galleryPath, System.currentTimeMillis() + ".png");
        String fileName = file.toString();
        // 3. 解析保存图片
        byte[] data = Base64.decode(base64Str, Base64.DEFAULT);
        /*for (int i = 0; i&lt; data.length; i++) {
            if (data[i] &lt; 0) {
                data[i] += 256;
            }
        }*/
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileName);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //通知相册更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri    uri    = Uri.fromFile(file);
            intent.setData(uri);
            sendBroadcast(intent);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WebViewActivity2.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
