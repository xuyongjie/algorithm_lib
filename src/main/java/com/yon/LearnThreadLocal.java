package com.yon;


import java.util.function.Supplier;

public class LearnThreadLocal {
    public static ThreadLocal<Integer> integerThreadLocal=ThreadLocal.withInitial(() -> 1);

    public void f(){
        System.out.printf("default in thread[%d]:%d",Thread.currentThread().getId(), integerThreadLocal.get());
        integerThreadLocal.set(20);
        System.out.printf("after set in thread[%d]:%d",Thread.currentThread().getId(), integerThreadLocal.get());
        Thread thread=new Thread(() -> {
            System.out.printf("default in thread[%d]:%d",Thread.currentThread().getId(), integerThreadLocal.get());
            integerThreadLocal.set(10);
            System.out.printf("after set in thread[%d]:%d",Thread.currentThread().getId(), integerThreadLocal.get());
        });
        thread.start();
    }
}
