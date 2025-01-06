package com.qtp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 * A 执行完 调用B
 * B 执行完 调用C
 * C 执行完 调用A
 *
 * @author jiaoqianjin
 * Date: 2020/8/11 9:58
 **/

public class C {
  public static void main(String[] args) {
    Data3 data3 = new Data3();

    new Thread(()  -> {
      for (int i = 0; i < 10; i++) {
        data3.printA();
      }
    },"A").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        data3.printB();
      }
    },"B").start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        data3.printC();
      }
    },"C").start();
  }

}
class Data3 {
  private Lock lock = new ReentrantLock();
  private Condition condition1 = lock.newCondition();
  private Condition condition2 = lock.newCondition();
  private Condition condition3 = lock.newCondition();
  private int num = 1; // 1A 2B 3C

  public void printA() {
    lock.lock();
    try {
      // 业务代码 判断 -> 执行 -> 通知
      while (num != 1) {
        condition1.await();
      }
      System.out.println(Thread.currentThread().getName() + "==> AAAA" );
      num = 2;
      condition2.signal();
    }catch (Exception e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
  }
  public void printB() {
    lock.lock();
    try {
      // 业务代码 判断 -> 执行 -> 通知
      while (num != 2) {
        condition2.await();
      }
      System.out.println(Thread.currentThread().getName() + "==> BBBB" );
      num = 3;
      condition3.signal();
    }catch (Exception e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
  }
  public void printC() {
    lock.lock();
    try {
      // 业务代码 判断 -> 执行 -> 通知
      while (num != 3) {
        condition3.await();
      }
      System.out.println(Thread.currentThread().getName() + "==> CCCC" );
      num = 1;
      condition1.signal();
    }catch (Exception e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
  }
}
/*
A==> AAAA
B==> BBBB
C==> CCCC
A==> AAAA
B==> BBBB
C==> CCCC
...
*/