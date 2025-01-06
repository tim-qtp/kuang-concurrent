package com.qtp;//基本的卖票例子

//真正的多线程开发，公司中的开发,降低耦合性
//线程就是一个单独的资源类，没有任何附属的操作！
//1、属性、方法
public class SaleTicketDemo01 {

  public static void main(String[] args) {
    //并发：多线程操作同一个资源类，把资源类丢入线程
    final Ticket ticket = new Ticket();

    ///@FunctionalInterface函数式接口，jdk1.8 Lambda表达式（参数）->{ 代码 }
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
class Ticket {
  private int number = 30;

  //卖票的方式
  public synchronized void sale() {
    if (number > 0) {
      System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票剩余" + number + "张票");
    }
  } 
}
