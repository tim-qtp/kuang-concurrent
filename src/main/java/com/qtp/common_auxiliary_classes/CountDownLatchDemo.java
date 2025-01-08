package com.qtp.common_auxiliary_classes;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "==> Go Out");
                countDownLatch.countDown(); // 每个线程都数量 -1
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待计数器归零 然后向下执行
        System.out.println("close door");
    }
}
