package com.xiaoming.androidknowledgepoints.ajavaknowledge.multithreads;

public class MyRunnable implements Runnable {
    private int num = 5;

    @Override
    public void run() {
       while (true) {
           if(num > 0) {
               try {
                   Thread.sleep(100);
               } catch (Exception e) {
                  System.out.println("bright#thread=" + Thread.currentThread().getName() + "出错了");
               }
               System.out.println("bright#thread=" + Thread.currentThread().getName()+"数字为：" + num--);
           } else {
               System.out.println("bright#thread="+ Thread.currentThread().getName()+"退出了");
               break;
           }
       }
    }
}
