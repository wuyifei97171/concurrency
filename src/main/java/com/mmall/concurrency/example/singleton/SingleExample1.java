package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 安全发布对象
 *      在静态初始化函数中初始化一个对象引用
 *      将对象的引用保存到vlatile类型域或者AtomicReference对象中
 *      将对象的引用保存到某个正确构造对象的final类型域中
 *      将对象的引用保存到一个有锁保护的域中
 */
/*
    懒汉模式
    单例的实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingleExample1 {

    // 私有的构造函数
    private SingleExample1(){

    }

    // 单例对象
    private static SingleExample1 instance = null;

    // 静态的工厂方法来获取一个单例对象
    public static SingleExample1 getInstance(){
        /**
         *  单线程模式下没有问题，但在多线程环境下，会出现问题。
         */
        if(instance == null){
            instance = new SingleExample1();
        }
        return instance;
    }
}
