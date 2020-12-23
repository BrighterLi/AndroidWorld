1 Activity加入动画
(1) 在onCreate加
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
    overridePendingTransition(R.anim.enter, R.anim.exit);
    setContentView(R.layout.activity_functions);
}
(2) 在Activity的theme加入动画
动画不生效原因：
用的Activity在Manifest中设置了SingleInstance属性，所以Activity处于已经实例化的状态，不会再被实例化，于是Activity动画不会播放，只有Window动画会播放。把这个属性改成SingleTop就好了。
(3) 在startActivity后面加
overridePendingTransition(R.anim.enter , R.anim.exit);

2 Activity动画跳转黑屏\
Activity设置切换动画时黑屏问题的解决:https://blog.csdn.net/tndroid/article/details/47446423