package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 安全发布对象
 *      在静态初始化函数中初始化一个对象引用
 *      将对象的引用保存到vlatile类型域或者AtomicReference对象中
 *      将对象的引用保存到某个正确构造对象的final类型域中
 *      将对象的引用保存到一个有锁保护的域中
 */
/*
    懒汉模式（线程安全）
    单例的实例在第一次使用时进行创建
 */
@ThreadSafe
public class SingleExample3 {

    // 私有的构造函数
    private SingleExample3(){

    }

    // 单例对象
    private static SingleExample3 instance = null;

    // 静态的工厂方法来获取一个单例对象
    public static  synchronized SingleExample3 getInstance(){
        /**
         *  加了synchronized修饰后，里面的所有实现，在同一时刻允许一个线程访问。
         *  所以可以保证是线程安全的。但是性能上的开销较大，所以不推荐
         */
        if(instance == null){
            instance = new SingleExample3();
        }
        return instance;
    }
}
