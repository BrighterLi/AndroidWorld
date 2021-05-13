package com.xiaoming.net.frame.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.net.MainActivity;
import com.xiaoming.net.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUrlConnectionActivity extends AppCompatActivity {
    private Button btnGet;
    private Button btnPost;
    private TextView tvShow;
    String resultStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);

        initView();
    }
    private void initView() {
        btnGet = findViewById(R.id.btn_get);
        btnPost = findViewById(R.id.btn_post);
        tvShow = findViewById(R.id.tv_show);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        resultStr = httpGet("http://www.baidu.com");
                        HttpUrlConnectionActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvShow.setText(resultStr);
                            }
                        });
                    }
                }).start();

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            httpPost();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    //Get请求，从服务器获得数据
    //https://blog.csdn.net/csdn_aiyang/article/details/64913282
    public String httpGet(String urlPath) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            //获得URL对象
            URL url = new URL(urlPath);
            //获得HttpURLConnection对象
            connection = ((HttpURLConnection) url.openConnection());
            //默认为GET
            connection.setRequestMethod("GET");
            //不使用缓存
            connection.setUseCaches(false);
            //设置超时时间
            connection.setConnectTimeout(10000);
            //设置读取超时时间
            connection.setReadTimeout(10000);
            //设置是否从httpUrlConnection读入，默认情况下是true
            connection.setDoInput(true);
            //相应码是否为200
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //获得输入流
                inputStream = connection.getInputStream();
                //包装字节流为字符流
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                connection = null;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //Get请求，获取图片资源
    private void httpImageGet() {
        String urlpath = "http://i2.sinaimg.cn/dy/dsgb/20083.jpg";
        try {
            URL url = new URL(urlpath);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(6000);
            con.setRequestMethod("GET");

            if (con.getResponseCode() == 200) {
                byte[] imagebytes = readStreamtoBytes(con.getInputStream());

                File file = new File("pic.jpg");

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(imagebytes);
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readStreamtoBytes(InputStream instream) throws IOException {

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();

        int len = -1;
        byte[] b = new byte[1024];
        while ((len = instream.read(b)) != -1) {

            outstream.write(b, 0, len);
        }
        outstream.flush();
        outstream.close();
        instream.close();

        return outstream.toByteArray();

    }

    //报异常：java.net.ConnectException: Connection refused
    //Post,往服务器发送数据
    //https://www.jb51.net/article/73621.htm
    private void httpPost() throws Exception{
        //String urlPath = new String("http://localhost:8080/Test1/HelloWorld?name=丁丁"
        String urlPath = new String("http://localhost:8080/Test1/HelloWorld");
        String param = "name="+ URLEncoder.encode("丁丁","UTF-8");
        //建立连接
        URL url = new URL(urlPath);
        HttpURLConnection connection = ((HttpURLConnection) url.openConnection());
        //设置参数
        connection.setDoInput(true); //需要输入
        connection.setDoOutput(true); //输入输出
        connection.setUseCaches(false); //不允许缓存
        connection.setRequestMethod("POST"); //设置POST方式连接
        //设置请求属性
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        connection.setRequestProperty("Charset", "UTF-8");
        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        connection.connect();
        //建立输入流，向指向的URL传入参数
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes(param);
        dataOutputStream.flush();
        dataOutputStream.close();
        //获得响应状态
        int resultCode = connection.getResponseCode();
        if(HttpURLConnection.HTTP_OK == resultCode) {
            StringBuffer stringBuffer = new StringBuffer();
            String readLine = new String();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            while ((readLine = responseReader.readLine()) != null) {
                stringBuffer.append(readLine).append("\n");
            }
            responseReader.close();
        }
    }
}

