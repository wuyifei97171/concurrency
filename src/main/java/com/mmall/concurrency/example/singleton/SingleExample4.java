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
    懒汉模式（线程安全) -> 双重同步锁单例模式
    单例的实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingleExample4 {

    // 私有的构造函数
    private SingleExample4(){

    }

    // 1.memory = allocate()    分配对象的内存空间
    // 2.ctorInstance()         初始化对象
    // 3.instance = memory      设置Instance指向刚分配的内存

    // JVM和CPU优化，发生了指令重排
    // 1.memory = allocate()    分配对象的内存空间
    // 3.instance = memory      设置Instance指向刚分配的内存
    // 2.ctorInstance()         初始化对象

    // 单例对象
    private static SingleExample4 instance = null;

    // 静态的工厂方法来获取一个单例对象
    public static SingleExample4 getInstance(){
        /**
         *  使用了双重监测机制和同步锁
         *  但是在多线程的情况下会发生指令重排，
         */
        if(instance == null){
            synchronized (SingleExample4.class) {   // 同步锁
                if (instance == null) {
                    instance = new SingleExample4();
                }
            }
        }
        return instance;
    }
}
