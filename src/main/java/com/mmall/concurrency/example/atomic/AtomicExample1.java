package com.mmall.concurrency.example.atomic;


import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Semaphore        线程池
 * CountDownLatch   信号量
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {

    // 定义线程数
    public static int clientTotal = 5000;

    // 同事并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);

        // 定义计数器 闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for( int i = 0 ; i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch(Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count.get());
    }

    public static void add() {
        // 一个类似于 oount ++ 一个类似于 ++count
        count.incrementAndGet();
//        count.getAndIncrement();
    }
}
