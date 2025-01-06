package com.qtp.eight_lock;

import java.util.concurrent.TimeUnit;

public class dome02 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone = new Phone2();

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

class Phone2 {
    public synchronized void sendMs() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信");
    }
    public synchronized void call() {
        System.out.println("打电话");
    }
}
