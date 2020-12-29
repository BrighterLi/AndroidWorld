package com.a006designmode.othermode.productandconsume;

import java.util.concurrent.BlockingQueue;

//消费者队列
//消费者线程，每3秒读取一次队列
public class ConsumerThread extends Thread {
    private BlockingQueue<String> queue;
    public ConsumerThread(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("bright8#ConsumerThread#取出："+queue.take());   //queue为空时，take方法会阻塞
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("bright8#ConsumerThread#e："+e.toString());
                e.printStackTrace();
            }


        }
    }

}
