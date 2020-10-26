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
(3) 在startActivity后面加