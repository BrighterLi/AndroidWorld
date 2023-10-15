package com.xiaoming.androidpoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.androidpoints.aaabug.BugTestActivity;
import com.xiaoming.androidpoints.aaajavaknowledge.hook.demo2.HookActivity2;
import com.xiaoming.androidpoints.activity.ActivityTestActivity;
import com.xiaoming.androidpoints.aaajavaknowledge.JavaKnowledgeActivity;
import com.xiaoming.androidpoints.activity.activitystack.ActivityStackActivity;
import com.xiaoming.androidpoints.background.SetBackgroundActivity;
import com.xiaoming.androidpoints.broadcast.LocalBroadcastReceiverActivity;
import com.xiaoming.androidpoints.datastorage.DataStorageActivity;
import com.xiaoming.androidpoints.datastorage.File.FileActivity;
import com.xiaoming.androidpoints.deeplink.DeepLinkActivity;
import com.xiaoming.androidpoints.dialog.DialogTestActivity;
import com.xiaoming.androidpoints.floating.floating.FloatingActivity;
import com.xiaoming.androidpoints.frame.mvx.mvvm.mvi.MVIActivity;
import com.xiaoming.androidpoints.innerclass.InnerClassAndVariableActivity;
import com.xiaoming.androidpoints.intent.IntentActivity;
import com.xiaoming.androidpoints.jnidemo.JniActivity;
import com.xiaoming.androidpoints.jnidemo2.Jni2Activity;
import com.xiaoming.androidpoints.json.JsonActivity;
import com.xiaoming.androidpoints.layout.relativelayout.RelativeLayoutDemoActivity;
import com.xiaoming.androidpoints.layout_extend.ExtendActivity;
import com.xiaoming.androidpoints.layout_extend.LayoutBaseActivity;
import com.xiaoming.androidpoints.permission.PermissionActivity;
import com.xiaoming.androidpoints.plugin.demo.PluginActivity;
import com.xiaoming.androidpoints.reflect.ReflectActivity;
import com.xiaoming.androidpoints.regularexpression.RegularExpressionActivity;
import com.xiaoming.androidpoints.scheme.SchemeActivity;
import com.xiaoming.androidpoints.serialization.GoToActivity;
import com.xiaoming.androidpoints.service.ServiceTestActivity;
import com.xiaoming.androidpoints.service.demo2.ServiceTest2Activity;
import com.xiaoming.androidpoints.service.demo4.ServiceTest4Activity;
import com.xiaoming.androidpoints.solib.SoLibEntranceActivity;
import com.xiaoming.androidpoints.uiframe.EnterUiFrameActivity;
import com.xiaoming.androidpoints.aaautils.UtilActivity;
import com.xiaoming.androidpoints.encryption.EncryptionActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView mLvMain;
    private List<String> demoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoData();
        initView();
    }

    private void initView() {
        mLvMain = findViewById(R.id.lv_main);
        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, JavaKnowledgeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, UtilActivity.class));
                    break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Jni2Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, InnerClassAndVariableActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, JsonActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, LocalBroadcastReceiverActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ReflectActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, GoToActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, RegularExpressionActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, EncryptionActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, SoLibEntranceActivity.class));
                        break;


                    case 11:
                        startActivity(new Intent(MainActivity.this, IsRootActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, IsVoLteEnabledActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, StatusBarActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, PhoneOperatorActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, NetworkStrengthActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, FastClickActivity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, DialogShowActivity.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, DataStorageActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, SchemeActivity.class));
                        break;
                    case 20:
                        startActivity(new Intent(MainActivity.this, DeepLinkActivity.class));
                        break;

                    case 21:
                        startActivity(new Intent(MainActivity.this, JniActivity.class));
                        break;
                    case 22:
                        startActivity(new Intent(MainActivity.this, FloatingActivity.class));
                        break;
                    case 23:
                        startActivity(new Intent(MainActivity.this, EnterUiFrameActivity.class));
                        break;
                    case 24:
                        startActivity(new Intent(MainActivity.this, DialogTestActivity.class));
                        break;
                    case 25:
                        startActivity(new Intent(MainActivity.this, ActivityTestActivity.class));
                        break;
                    case 26:
                        startActivity(new Intent(MainActivity.this, FileActivity.class));
                        break;
                    case 27:
                        startActivity(new Intent(MainActivity.this, SetBackgroundActivity.class));
                        break;
                    case 28:
                        startActivity(new Intent(MainActivity.this, PermissionActivity.class));
                        break;
                    case 29:
                        startActivity(new Intent(MainActivity.this, IntentActivity.class));
                        break;
                    case 30:
                        startActivity(new Intent(MainActivity.this, HookActivity2.class));
                        break;

                    case 31:
                        startActivity(new Intent(MainActivity.this, PluginActivity.class));
                        break;

                    case 32:
                        //startActivity(new Intent(MainActivity.this, LayoutBaseActivity.class));
                        startActivity(new Intent(MainActivity.this, ExtendActivity.class));
                        break;
                    case 33:
                        //startActivity(new Intent(MainActivity.this, LayoutBaseActivity.class));
                        startActivity(new Intent(MainActivity.this, BugTestActivity.class));
                        break;
                    case 34: //布局layout
                        //startActivity(new Intent(MainActivity.this, LayoutBaseActivity.class));
                        startActivity(new Intent(MainActivity.this, RelativeLayoutDemoActivity.class));
                        break;
                    case 35: //service
                        //startActivity(new Intent(MainActivity.this, ServiceTestActivity.class));
                        //startActivity(new Intent(MainActivity.this, ServiceTest2Activity.class));
                        startActivity(new Intent(MainActivity.this, ServiceTest4Activity.class));
                        break;
                    case 36: //activity stack
                        startActivity(new Intent(MainActivity.this, ActivityStackActivity.class));
                        break;
                    case 37: //activity stack
                        startActivity(new Intent(MainActivity.this, MVIActivity.class));
                        break;
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoDataList);
        mLvMain.setAdapter(adapter);
    }

    private void initDemoData() {
        demoDataList = new ArrayList<>();
        demoDataList.add("000java知识点"); //0
        demoDataList.add("000Util");
        demoDataList.add("jni:hello world");
        demoDataList.add("java内部类访问局部变量时局部变量必须声明为final");
        demoDataList.add("Json");
        demoDataList.add("LocalBroadcast");
        demoDataList.add("反射");
        demoDataList.add("序列化");
        demoDataList.add("正则");
        demoDataList.add("加密解密");

        demoDataList.add("调用so库的方法"); //10
        demoDataList.add("判断安卓设备是否root");
        demoDataList.add("获取VoLte开关开关状态");
        demoDataList.add("获取状态栏高度，改变状态栏颜色");
        demoDataList.add("获取手机运营商");
        demoDataList.add("获取信号强度");
        demoDataList.add("防止快速重复点击");
        demoDataList.add("Dialog只能在主线程里执行");
        demoDataList.add("数据存储");
        demoDataList.add("通过Scheme跳转");

        demoDataList.add("通过DeepLink跳转"); //20
        demoDataList.add("jni：加减法");
        demoDataList.add("floating悬浮窗");
        demoDataList.add("ui框架");
        demoDataList.add("dialog");
        demoDataList.add("Activity");
        demoDataList.add("File操作");
        demoDataList.add("设置背景Background");
        demoDataList.add("权限申请");
        demoDataList.add("Intent");

        demoDataList.add("Hook"); //30
        demoDataList.add("插件化");
        demoDataList.add("BaseActivity布局继承");
        demoDataList.add("bug");
        demoDataList.add("layout");
        demoDataList.add("service");
        demoDataList.add("activity stack");
        demoDataList.add("mvx");
    }
}
