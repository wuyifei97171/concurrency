package com.mmall.concurrency.example.atomic;


import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * Semaphore        线程池
 * CountDownLatch   信号量
 *
 * LongAdder
 *  在处理 Long Double 这类64位的类型中，Java1.8允许将这64位的类型拆成两个32位的类型
 *  LongAdder 的核心是将热点数据分离 ，可以将AtomicLong 内部的核心数据value分离成一个数组
 *  每个线程访问时，预测到其中一个数字进行技术，而最后的计数结果为这个数字的求和累加.其中，
 *  热点数据value 会被分离成多个数据的cell，每个cell独自维护内部当前对象的实际值。有所有的cell累计合成。
 *  这样的话，热点就进行了有效的分离。这样就提高了并行度。所以在低病发的时候，LongAdder和可以将AtomicLong
 *  运行效率相同。但是在高并发的情况下，前者的运行效率会显著提升。
 *  缺点：
 *  在处理高并发计数的时候，LongAdder在数据录入时会有误差，我们可以使用AtomicLong。
 *  而且开发效率上看，还是AtomicLong更简单更直接一些
 */
@Slf4j
@ThreadSafe
public class AtomicExample3 {

    // 定义线程数
    public static int clientTotal = 5000;

    // 同事并发执行的线程数
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

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
        log.info("count:{}",count);
    }

    public static void add() {
        count.increment();
    }
}
