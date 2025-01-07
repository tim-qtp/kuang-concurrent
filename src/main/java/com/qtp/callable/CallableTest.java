package com.qtp.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        for (int i = 1; i < 10; i++) {
//            MyThread1 myThread1 = new MyThread1();
//
//            FutureTask<Integer> futureTask = new FutureTask<>(myThread1);
//            // 放入Thread中使用，结果会被缓存
//            new Thread(futureTask,String.valueOf(i)).start();
//            // 这个get方法可能会被阻塞，如果在call方法中是一个耗时的方法，所以一般情况我们会把这个放在最后，或者使用异步通信
//            int a = futureTask.get();
//            System.out.println("返回值:" + a);
//        }
        MyThread1 myThread1 = new MyThread1();
        FutureTask<String> futureTask = new FutureTask<>(myThread1);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        System.out.println(futureTask.get());//这个get方法可能会产生阻塞！把他放到最后，或者使用异步通信来处理
    }
}
class MyThread1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("当前线程名称: " + Thread.currentThread().getName());
        return 1024+":"+Thread.currentThread().getName();

    }
}
