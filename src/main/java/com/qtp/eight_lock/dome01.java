package com.qtp.eight_lock;

import java.util.concurrent.TimeUnit;

public class dome01 {
    public static void main(String[] args) throws InterruptedException {
        Phone1 phone = new Phone1();

        new Thread(() -> {
            try {
                phone.sendMs();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> { phone.call(); }).start();
    }
}

class Phone1 {
    public synchronized void sendMs() throws InterruptedException {
        System.out.println("发短信");
    }
    public synchronized void call() {
        System.out.println("打电话");
    }
}
