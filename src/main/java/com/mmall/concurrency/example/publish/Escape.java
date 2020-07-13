package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
/**
 * 发布对象：使一个对象能够被当前范围之外的代码所使用
 * 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使他被其他线程所见
 */
public class Escape {

    private int thisCannBeEscape = 0;

    public Escape(){

    }

    private class InnerClass{

        // 在对象为完成构造之前，不能将其发布
        public InnerClass(){
            log.info("{}", Escape.this.thisCannBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
