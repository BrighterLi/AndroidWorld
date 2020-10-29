1 onInterceptTouchEvent与onTouchEvent与dispatchTouchEvent
Android中的dispatchTouchEvent()、onInterceptTouchEvent()和onTouchEvent()：
https://blog.csdn.net/xyz_lmn/article/details/12517911
Android中onInterceptTouchEvent与onTouchEvent:https://blog.csdn.net/top_code/article/details/8585777
Android Touch事件分析:https://blog.51cto.com/mikewang/1204944
super.dispatchTouchEvent(event)使用:https://blog.csdn.net/Liu_yunzhao/article/details/80247498

在ViewGroup中，事件分为dispatchTouchEvent（事件的分发），onInterceptTouchEvent（事件的拦截），onTouchEvent（事件的处理）。
在View中，事件分为dispatchTouchEvent（事件的分发），onTouchEvent（事件的处理）。

View里，有两个回调函数:
public boolean dispatchTouchEvent(MotionEvent ev)；  
public boolean onTouchEvent(MotionEvent ev);
ViewGroup里，有三个回调函数 ：
public boolean dispatchTouchEvent(MotionEvent ev)；  
public boolean onInterceptTouchEvent(MotionEvent ev);(只有ViewGroup可进行拦截)
public boolean onTouchEvent(MotionEvent ev);
在Activity里，有两个回调函数 ：
public boolean dispatchTouchEvent(MotionEvent ev)；  
public boolean onTouchEvent(MotionEvent ev);

onInterceptTouchEvent默认返回值是false
ViewGroup里的onTouchEvent默认返回值是false, View里的onTouchEvent默认返回值是true

dispatchTouchEvent、onTouchEvent中
ACTION_DOWN：返回true，说明当前View来处理事件，后续事件也要处理，事件不再向下传递；返回false，说明当前View不处理事件，后续事件直接由上一级View处理，当前View也不会再接收到后续事件
ACTION_MOVE、ACTION_UP：返回true，说明其子View已经接收了DOWN事件，这里返回true只会导致该部分事件不再继续向下传递（但也不会传递当前View的onTouchEvent处理），对于没有返回true的事件还按正常流程传递
注意
super.dispatchTouchEvent(event)的返回值取决于子View或当前View的onTouchEvent对应事件的返回值

onInterceptTouchEvent中
无论在什么时候拦截，接下来的事件都将传递给当前View的onTouchEvent来处理

***
疑问？
(1)ViewGroup都不拦截也不消费，即super默认值；最后的View也super默认值，View的onTouchEvent方法
默认返回true,是否就说明会消费事件？还是只是消费DOWN事件，接下来怎么走？
解答：默认返回true，说明可以消费事件。
(2) ViewGroup都不拦截也不消费，即super默认值；最后的View人为返回false，View的onTouchEvent方法
不会消费事件，那么事件就会向上传？
解答：返回false,则只会消费DOWN事件，上层的onTouchEvent会去消费其他事件。
(3) dispatchTouchEvent方法逻辑与返回?
(4) super.dispatchTouchEvent(event)的返回值取决于子View或当前View的onTouchEvent对应事件的返回值?

2 onMeasure
android中对View的onMeasure()方法的理解:https://blog.csdn.net/lovexieyuan520/article/details/50614670
自定义view，viewgroup的onMeasure 方法：https://blog.csdn.net/wanghao200906/article/details/50906799