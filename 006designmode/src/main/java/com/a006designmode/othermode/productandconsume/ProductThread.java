package com.a006designmode.othermode.productandconsume;
import java.util.concurrent.BlockingQueue;

//这里主要使用了LinkBlockingQueue用来做队列，支撑起整个demo中两个线程直接的数据传递，LinkBlockingQueue最大的特点是可以设置的长度，使用put方法存取时，
//如果队列满了，会阻塞住，同样使用take读取删除项时，如果队列为空的时候，也会阻塞住，且put和take方法是并行的，互不干扰，非常适合做消息传递。
//java 实现简单的生产者-消费者的demo：https://blog.csdn.net/wuqingbin/article/details/81238975

//生产者模型
//ProductThread，每两秒生产一条数据
public class ProductThread extends  Thread{
    private BlockingQueue<String> queue;
    public ProductThread(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        int i=1;
        while (true){

            try {
                queue.put("第： "+i+" 个");            //当队列满的时候，这个方法会阻塞住，等待队列有新的位置
                System.out.println("bright8#ProductThread#放了第"+i+" 个--");
                Thread.sleep(2000);
                i++;
            } catch (InterruptedException e) {
                System.out.println("bright8#ProductThread#e："+e.toString());
                e.printStackTrace();
            }

        }

    }


}
