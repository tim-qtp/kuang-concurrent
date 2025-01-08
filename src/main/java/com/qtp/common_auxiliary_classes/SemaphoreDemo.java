package com.qtp.common_auxiliary_classes;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {

        // 线程数量，停车位，限流
        // 有限的条件下，有序！
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                // acquire() 得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(); // release() 释放
                }
            }).start();
        }
    }
}
