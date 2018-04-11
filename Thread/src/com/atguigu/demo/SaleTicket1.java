
package com.atguigu.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 *线程  操作 资源
 *	强内聚 低耦合
 */
 class Ticket {
	int total = 30;
	private  Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if (total>0) {
				System.out.println(Thread.currentThread().getName()+"卖出第"+(total--)+",剩余"+total);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}
public class SaleTicket1{
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		
		
	new Thread(()->{for (int i = 1; i < 40; i++) ticket.sale();}, "AA")  .start();
	new Thread(()->{for (int i = 1; i < 40; i++) ticket.sale();}, "BB")  .start();
	new Thread(()->{for (int i = 1; i < 40; i++) ticket.sale();}, "CC")  .start();		
	//	for (int i = 1; i < 40; i++) ticket.sale();
			
		
	/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i < 40; i++) {
					ticket.sale();
				}
			}
		}, "AA").start();
		
	new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i < 40; i++) {
					ticket.sale();
				}
			}
		}, "AA").start();
		*/
	}
	
}

