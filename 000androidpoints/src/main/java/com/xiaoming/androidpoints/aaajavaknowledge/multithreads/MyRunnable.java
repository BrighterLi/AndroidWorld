package com.xiaoming.androidpoints.aaajavaknowledge.multithreads;

public class MyRunnable implements Runnable {
    private int num = 5;

    @Override
    public void run() {
       while (true) {
           synchronized (this) { //对共享资源加锁，实现资源同步的目的
               if (num > 0) {
                   try {
                       Thread.sleep(100);
                   } catch (Exception e) {
                       System.out.println("bright#thread=" + Thread.currentThread().getName() + "#出错了");
                   }
                   System.out.println("bright#thread=" + Thread.currentThread().getName() + "#数字为：" + num--);
               } else {
                   System.out.println("bright#thread=" + Thread.currentThread().getName() + "#退出了");
                   break;
               }
           }
       }
    }
}
