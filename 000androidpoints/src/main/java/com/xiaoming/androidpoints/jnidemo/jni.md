1 相关知识
Java代码通过JNI接口 调用 C/C++方法：https://www.jianshu.com/p/6cbdda111570
(1)Java代码通过JNI接口 调用 C/C++方法
1)在Java代码中声明Natvie方法原型
public native void helloJNI(String msg);
2)在C/C++代码里声明JNI方法的原型
extern "C"
   JNIEXPORT void JNICALL
   Java_com_kgdwbb_jnistudy_MainActivity_helloJNI(JNIEnv* env, jobject thiz,jstring msg) {
       //do something
   }

extern "C"。JNI函数声明声明代码是用C++语言写的，所以需要添加extern "C"声明；如果源代码是C语言声明，则不需要添加这个声明
JNIEXPORT。这个关键字表明这个函数是一个可导出函数。每一个C/C++库都有一个导出函数列表，只有在这个列表里面的函数才可以被外部直接调用，类似Java的public函数和private函数的区别。
JNICALL。说明这个函数是一个JNI函数，用来和普通的C/C++函数进行区别。
Void 返回值类型
JNI函数名原型：Java_ + JNI方法所在的完整的类名，把类名里面的”.”替换成”_” + 真实的JNI方法名，这个方法名要和Java代码里面声明的JNI方法名一样。
env 参数 是一个执行JNIENV函数表的指针。
thiz 参数 代表的是声明这个JNI方法的Java类的引用。
msg 参数就是和Java声明的JNI函数的msg参数对于的JNI函数参数

(2) JNI 访问Java类的方法和字段
JNI 中访问java类的方法和字段都是 通过反射来实现的。
