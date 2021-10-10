package com.xiaoming.a003thread.multithreads.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//深入理解Callable: https://blog.csdn.net/qq_19431333/article/details/77483763
public class CallableDemo {

    static class SumTask implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for(int i=0; i<9000;i++) {
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Start: " + System.nanoTime());
        FutureTask<Long> futureTask = new FutureTask<>(new SumTask());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);
        System.out.println(futureTask.get());
        System.out.println("End: " + System.nanoTime());
    }
}
