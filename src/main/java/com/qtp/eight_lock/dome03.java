package com.qtp.eight_lock;

import java.util.concurrent.TimeUnit;

public class dome03 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 phone = new Phone3();

        new Thread(() -> {
            try {
                phone.sendMs();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> { phone.hello(); },"B").start();
    }
}

class Phone3 {
    public synchronized void sendMs() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信");
    }
    public synchronized void call() {
        System.out.println("打电话");
    }
    public void hello() {
        System.out.println("hello");
    }
}
