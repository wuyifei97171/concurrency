package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;

@ThreadSafe
@Recommend
/**
 * 枚举模式:最安全
 * 相比懒汉模式更加安全
 * 相比饿汉模式可以在实际调用时，才进行实例化。不会造成资源的浪费
 */
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
