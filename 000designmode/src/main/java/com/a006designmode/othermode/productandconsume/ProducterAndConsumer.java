package com.a006designmode.othermode.productandconsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducterAndConsumer {

    //启动两个线程
    public static void main() {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(100);  //队列长度为100
        new ProductThread(queue).start();
        new ConsumerThread(queue).start();
    }
}
