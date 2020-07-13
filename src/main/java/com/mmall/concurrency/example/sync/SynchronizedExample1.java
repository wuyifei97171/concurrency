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
public class SynchronizedExample1 {

    /*
     * synchronized 修饰一段代码时，
     * {} 中的代码称作修饰语句块，作用范围是 {} 括起来的代码，
     * 作用对象是 调用这段代码的对象
     */

    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++){
                log.info("test1 {} - {}", j, i);
            }
        }
    }
    /*
    * 修饰一个方法
    * 作用对象是 调用这段代码的对象
    */
    public synchronized void test2(){
        for (int i = 0; i < 10; i++){
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        // 声明一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute( () -> {
//            example1.test1();
//        });
//        executorService.execute( ()->{
//            example1.test1();
//        });
        /*
        输出结果
        17:10:37.592 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 0
        17:10:37.598 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 1
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 2
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 3
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 4
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 5
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 6
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 7
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 8
        17:10:37.599 [pool-1-thread-1] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 9
        17:10:37.600 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 0
        17:10:37.600 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 1
        17:10:37.600 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 2
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 3
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 4
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 5
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 6
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 7
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 8
        17:10:37.601 [pool-1-thread-2] INFO com.mmall.concurrency.example.sync.SynchronizedExample1 - test1 - 9
         */

//        executorService.execute( () -> {
//            example1.test2();
//        });
//        executorService.execute( ()->{
//            example1.test2();
//        });
        /*
            输出结果与上面相同
         */

        // example1 example2 相互不影响
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });


    }
}
