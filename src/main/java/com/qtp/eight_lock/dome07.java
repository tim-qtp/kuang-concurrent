package com.qtp.eight_lock;

import java.util.concurrent.TimeUnit;

public class dome07 {
    public static void main(String[] args) throws InterruptedException {
        Phone7 phone1 = new Phone7();
//        Phone phone2 = new Phone();

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

class Phone7 {
    public static synchronized void sendMs() throws InterruptedException {
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
