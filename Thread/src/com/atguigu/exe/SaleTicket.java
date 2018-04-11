package com.atguigu.exe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 2个售票员卖30张票
 * 对同一个资源同时操作
 * 套路：
 * 	线程 操作 资源
 * 	  高内聚 低耦合
 *
 */
public class SaleTicket {

	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(() -> {for (int i = 1; i <= 500; i++) ticket.sale();}, "AA").start();
		new Thread(() -> {for (int i = 1; i <= 500; i++) ticket.sale();}, "BB").start();
	}
}
class Ticket{
	int number = 500;
	Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if (number >0) {
				System.out.println(Thread.currentThread().getName()+"卖出第"+(number--)+"张,剩余"+number+"张");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			lock.unlock();
		}
	}
}