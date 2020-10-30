1 onInterceptTouchEvent与onTouchEvent与dispatchTouchEvent
视频资源：
https://v.qq.com/x/page/f0934o47gph.html
https://www.56.com/u95/v_MTQ4MTExMjEy.html
https://www.imooc.com/learn/1155/
文字资源：
Android中的dispatchTouchEvent()、onInterceptTouchEvent()和onTouchEvent()：
https://blog.csdn.net/xyz_lmn/article/details/12517911
Android中onInterceptTouchEvent与onTouchEvent:https://blog.csdn.net/top_code/article/details/8585777
Android Touch事件分析:https://blog.51cto.com/mikewang/1204944
super.dispatchTouchEvent(event)使用:https://blog.csdn.net/Liu_yunzhao/article/details/80247498
dispatchTouchEvent:
谈谈对dispatchTouchEvent、onInterceptTouchEvent和onTouchEvent的理解:
https://blog.csdn.net/aaa466412913/article/details/50585891?utm_medium=distribute.pc_relevant.none-task-blog-title-8&spm=1001.2101.3001.4242

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

dispatchTouchEvent:
 触摸事件是一连串ACTION_DOWN，ACTION_MOVE..MOVE…MOVE、最后ACTION_UP，触摸事件还有ACTION_CANCEL事件。事件都是从ACTION_DOWN开始的，Activity的dispatchTouchEvent()首先接收到ACTION_DOWN，执行super.dispatchTouchEvent(ev)，事件向下分发。
    dispatchTouchEvent()返回true，后续事件（ACTION_MOVE、ACTION_UP）会再传递，如果返回false，dispatchTouchEvent()就接收不到ACTION_UP、ACTION_MOVE。

dispatchTouchEvent:
https://blog.csdn.net/aaa466412913/article/details/50585891?utm_medium=distribute.pc_relevant.none-task-blog-title-8&spm=1001.2101.3001.4242
    负责事件分发。必然有人会有疑问，啥是个事件分发？ 说白了，可以理解为每个控件(包括Activity)对于事件监听的
    第一步通知，当然，它自己也可以消耗这个事件，事件被消耗了，就不会引起下边子节点的dispatchTouchEvent了。
    注意，它一般不会被重写，主要即返回值基本上是为  super.dispatchTouchEvent(ev)
     返回值：
          true -> 自己消耗，子节点将不会继续传递事件，注意，是直接消耗掉，即不会有其他事件的介入。
          false -> 若非根节点，则会进入其父节点的Touch事件，否则只会执行根节点的dispatchTouchEvent
          super.dispatchTouchEvent(ev) -> 这样对于ViewGroup来说才能进onInterceptTouchEvent来选择是否拦截对于子节点事件的传递。

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
(5) 事件序列，DOWN事件对后续事件的影响？

2 onMeasure
android中对View的onMeasure()方法的理解:https://blog.csdn.net/lovexieyuan520/article/details/50614670
自定义view，viewgroup的onMeasure 方法：https://blog.csdn.net/wanghao200906/article/details/50906799