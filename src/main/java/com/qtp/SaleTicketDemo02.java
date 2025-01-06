package com.qtp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02  {

  public static void main(String[] args) {
    final Ticket ticket = new Ticket();

    new Thread(()->{
      for (int i = 0; i < 40; i++) {
        ticket.sale();
      }
    },"A").start();
    new Thread(()->{
      for (int i = 0; i < 40; i++) {
        ticket.sale();
      }
    },"B").start();
    new Thread(()->{
      for (int i = 0; i < 40; i++) {
        ticket.sale();
      }
    },"C").start();
  }
}
// 资源类 OOP 属性、方法
class Ticket2 {

  private int number = 30;
  Lock lock=new ReentrantLock();
  //卖票的方式
  public  void sale() {
    lock.lock();

    try {
      if (number > 0) {
        System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票剩余" + number + "张票");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
