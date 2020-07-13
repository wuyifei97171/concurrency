package com.mmall.concurrency.example.atomic;


import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @volatile
 * volatile修饰的变量不允许线程内部缓存和重排序，即直接修改内存。所以对其他线程是可见的。
 * 但是这里需要注意一个问题，volatile只能让被他修饰内容具有可见性，但不能保证它具有原子性。
 * 比如 volatile int a = 0；之后有一个操作 a++；这个变量a具有可见性，
 * 但是a++ 依然是一个非原子操作，也就是这个操作同样存在线程安全问题。
 *
 * @AtomicIntegerFieldUpdater
 * AtomicIntegerFieldUpdater 用于原子性地更新某一个实例的某一个字段，
 * 这个字段要用volatile修饰并且是非static修饰的字段。
 *
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100,120)){
            log.info("update success 1,{}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100,120)){
            log.info("update success 2,{}", example5.getCount());
        }else {
            log.info("update failed,{}", example5.getCount());
        }
    }
}
