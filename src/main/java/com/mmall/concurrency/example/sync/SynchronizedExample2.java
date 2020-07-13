package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
/**
 *  test1 与 test2 效果是等同的
 *  注意： SynchronizedExample1 如果作为父类被继承， 这时里面的test1 test2 是不会有synchronized效果的
 *  因为此时 Synchronized 不属于方法被声明的部分,如果子类要Synchronized，需要自己声明
 */
public class SynchronizedExample2 {

    // 修饰一个雷
    public static void test1(int j){
        synchronized (SynchronizedExample2.class){
            for (int i = 0; i < 10; i++){
                log.info("test1 {} - {}", j, i);
            }
        }
    }
    /*
    * 修饰一个静态方法
    */
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++){
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        // 声明一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(() ->{
            example2.test1(2);
        });


    }
}
