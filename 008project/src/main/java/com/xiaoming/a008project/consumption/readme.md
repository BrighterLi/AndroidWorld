1 ScrollView
2 ViewPager
3 TabLayout
Android原生的Tablayout指示器Indicator自定义空间很有限，能设置颜色，如果想把Tablayout
指示器Indicator的宽和高做调整适应自己产品开发的UI设计要求，就很难办到了，更是如果要
求的Indicator的指示器呈现渐变颜色、圆角，就难上加难了。就必须自己想办法解决这些自定
义问题
(1) 点击前后文字颜色和粗细变化
android tablayout选中的字体颜色及大小改变:https://www.jianshu.com/p/9cd9d604ce46
(2) 下滑线颜色和宽度高度距离形状自定义

4 添加其他功能
(1)下拉刷新
(2)列表；瀑布流
(3)图片，视频
这是一篇Android RecyclerView使用介绍哦:https://www.jianshu.com/p/12ec590f6c76
(4)自动加载更多 
这是一篇Android RecyclerView使用介绍哦:https://www.jianshu.com/p/12ec590f6c76
要想实现滑动到列表某处时自动加载下一页（比如剩最后两个item时），可以通过对RecyclerView设置滑
动监听，获取当前显示的最后一个item在适配器中的位置，如果该item的位置小于或等于适配器item总个数
减2，就加载下一页数据

5 网上数据来源
https://www.jianshu.com/p/12ec590f6c76
API：https://gank.io/api
