package com.qtp.eight_lock;

import java.util.concurrent.TimeUnit;

public class dome05 {
    public static void main(String[] args) throws InterruptedException {
        Phone5 phone1 = new Phone5();

        new Thread(() -> {
            try {
                phone1.sendMs();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> { phone1.call(); }).start();
    }
}

class Phone5 {
    public static synchronized void sendMs() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信");
    }
    public static synchronized void call() {
        System.out.println("打电话");
    }
    public void hello() {
        System.out.println("hello");
    }
}
