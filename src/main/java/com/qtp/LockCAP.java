package com.qtp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCAP {
  public static void main(String[] args) {
    Data2 data = new Data2();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {

        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }, "A").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          data.decrement();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "B").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "C").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          data.decrement();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "D").start();
  }
}

class Data2 {
  private int num = 0;
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  // +1
  public  void increment() throws InterruptedException {
    lock.lock();
    try {
      // 判断等待
      while (num != 0) {
        condition.await(); //等待
      }
      num++;
      System.out.println(Thread.currentThread().getName() + "=>" + num);
      // 通知其他线程 +1 执行完毕
      condition.signalAll(); //唤醒全部
    }finally {
      lock.unlock();
    }
  }

  // -1
  public  void decrement() throws InterruptedException {
    lock.lock();
    try {
      // 判断等待
      while (num == 0) {
        condition.await();
      }
      num--;
      System.out.println(Thread.currentThread().getName() + "=>" + num);
      // 通知其他线程 +1 执行完毕
      condition.signalAll();
    }finally {
      lock.unlock();
    }

  }
}

