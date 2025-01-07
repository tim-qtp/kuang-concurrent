package com.qtp.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 1; i < 10; i++) {
            MyThread2 myThread2 = new MyThread2();

            FutureTask<Integer> futureTask = new FutureTask<>(myThread2);
            // 放入Thread中使用，结果会被缓存
            new Thread(futureTask,String.valueOf(i)).start();
            // 这个get方法可能会被阻塞，如果在call方法中是一个耗时的方法，所以一般情况我们会把这个放在最后，或者使用异步通信
            int a = futureTask.get();
            System.out.println("返回值:" + a);
        }

    }

}
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
