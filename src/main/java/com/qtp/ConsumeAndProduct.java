package com.qtp;

/*
线程之间的通信问题：生产者和消费者问题
线程交替执行 A B操作同一个变量 num=0 A让num+1，B让num-1;
 **/
public class ConsumeAndProduct {
  public static void main(String[] args) {
    Data data = new Data();

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
          data.decrement();
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

// 等待，业务，通知
class Data {
  private int num = 0;

  // +1
  public synchronized void increment() throws InterruptedException {
    // 判断需不需要等待
    while (num != 0) { //不等于0，等待被人减掉； 如果为0，那就加上；
      this.wait();
    }
    num++;
    System.out.println(Thread.currentThread().getName() + "=>" + num);
    // 通知其他线程 +1 执行完毕
    this.notifyAll();
  }

  // -1
  public synchronized void decrement() throws InterruptedException {
    // 判断等待
    while (num == 0) {
      this.wait();
    }
    num--;
    System.out.println(Thread.currentThread().getName() + "=>" + num);
    // 通知其他线程 -1 执行完毕
    this.notifyAll();
  }
}

