package com.xiaoming.androidpoints.aaajavaknowledge.abstractclass.constructorfun2;

public class Student extends Person {
    //    想调用父类抽象类的有参构造函数必须先显式声明,然后通过super函数调用
    Student(int i) {
        super(i);
        //必须显示的调用父类构造方法,super代表父类对象
        System.out.println("bright9#子类的有参构造函数\n");
    }

    //默认调用父类的无参构造函数,同样必须显式声明
    Student(){
        System.out.println("bright9#子类的无参构造函数");
    }
}

