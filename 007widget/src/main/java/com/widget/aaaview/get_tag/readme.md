1 View.setTag()与View.getTag()的作用
android：View.setTag()与View.getTag()的作用: https://blog.csdn.net/darry_r/article/details/78852569
android中view控件的setTag()和getTag()用法和功能介绍: https://my.oschina.net/chuiyuan/blog/263173

View中的setTag(Onbect)表示给View添加一个格外的数据，以后可以用getTag()将这个数据取出来。
(1) 用于区分很多相似的View
button1.setOnClickListener(new OnClickListener ... );
button2.setOnClickListener(new OnClickListener ... );
它们可能执行相似的逻辑，但你必须分别为两个Button设置两个独立的OnClick事件
public void onClick(View v) {
    doAction(1); // 1 for button1, 2 for button2, etc.
}
之所以这样做，因为onClick只有一个参数View。我们可以通过setTag和getTag来完成：
button1.setTag(1);
button2.setTag(2);
我们可以将两个button设置同一个OnClickListener，比如：
listener = new OnClickListener() {
    @Override
    public void onClick(View v) {
        doAction(v.getTag());
    }
};

(2) 用于ListView的复用
tatic class ViewHolder {
    TextView tvPost;
    TextView tvDate;
    ImageView thumb;
}

public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        convertView = inflater.inflate(R.layout.postitem, null);

        ViewHolder vh = new ViewHolder();
        vh.tvPost = (TextView)convertView.findViewById(R.id.postTitleLabel);
        vh.tvDate = (TextView)convertView.findViewById(R.id.postDateLabel);
        vh.thumb = (ImageView)convertView.findViewById(R.id.postThumb);
        convertView.setTag(vh);
    } else {
    　　vh = (ViewHolder) convertView.getTag();
    }
}

注意：
除了上述情况以外，我们尽量不要直接使用，原因：
1.代码可读性：会给其他的程序员造成困扰
2.由于setTag和getTag设置的是一个Object对象，可能会出现类的转换异常
不过，android4.0以后，有一个更好的方法：setTag(int key, Object tag)可以通过类似<k,v>键值对的方式存取。























