package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 安全发布对象
 *      在静态初始化函数中初始化一个对象引用
 *      将对象的引用保存到vlatile类型域或者AtomicReference对象中
 *      将对象的引用保存到某个正确构造对象的final类型域中
 *      将对象的引用保存到一个有锁保护的域中
 */
/*
    饿汉模式
    单例的实例在类装载时进行实现,
    需要考虑：
        1.私有构造函数没有太多的处理
        2.这个类肯定会被使用
    不然会损失性能
 */
@ThreadSafe
public class SingleExample6 {

    // 私有的构造函数
    private SingleExample6(){

    }

    // 单例对象，注意需要放在static代码块前面
    // 使用 static 修饰时，需要注意代码块的先后顺序
    private static SingleExample6 instance = null;
    static {
        instance = new SingleExample6();
    }

    // 静态的工厂方法来获取一个单例对象
    public static SingleExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
