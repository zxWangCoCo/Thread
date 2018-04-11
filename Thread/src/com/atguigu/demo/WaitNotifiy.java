package com.atguigu.demo;
/**
 * 有两个线程
 * 可以操作初始值为0一个变量
 * 一个线程+1，一个线程-1
 * 交替5轮，初始值为0
 * 1.线程 操作 资源  高内聚 低耦合
 * 2.判断 干活 唤醒
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data{
	private int data = 0;
	Lock lock = new ReentrantLock();
	//使用codition替换wait和notifiyall
	Condition codition  = lock.newCondition(); 
	
	//生产
	public  void incoData() throws Exception {
		lock.lock();
		try {
			//判断
			while (data != 0) {
				//this.wait();
				codition.await();
			}
			//干活
			++data;
			System.out.println(Thread.currentThread().getName()+"\t"+data);
			//通知消费
			//this.notifyAll();
			codition.signalAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			//解锁
			lock.unlock();
		}
	}
	
	public  void decrData() throws Exception {
		lock.lock();
		try {
			//判断
			 while (data == 0) {
				//this.wait();
				 codition.await();
			}
			//干活
			--data;
			System.out.println(Thread.currentThread().getName()+"\t"+data);
			//通知生产
		 //	this.notifyAll();
			codition.signalAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			//解锁
			lock.unlock();
		}
	}
	
	/*//生产
	public synchronized void incoData() throws Exception {
		//判断
		while (data != 0) {
			this.wait();
		}
		//干活
		++data;
		System.out.println(Thread.currentThread().getName()+"\t"+data);
		//通知消费
		this.notifyAll();
	}
	//消费
	public synchronized void decrData() throws Exception {
		//判断
		 while (data == 0) {
			this.wait();
		}
		//干活
		--data;
		System.out.println(Thread.currentThread().getName()+"\t"+data);
		//通知生产
		this.notifyAll();
	}*/
}


public class WaitNotifiy {

	public static void main(String[] args) {
		
		Data data = new Data();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 0; i < 10; i++) {
//					try {
//						data.incoData();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		},"AA").start();
		
		new Thread(() -> {for (int i = 0; i < 10; i++)
			try {
				data.incoData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		new Thread(()-> {for (int i = 0; i < 10; i++)
			try {
				data.decrData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"BB").start();
		
		new Thread(() -> {for (int i = 0; i < 10; i++)
			try {
				data.incoData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "CC").start();
		
		new Thread(() -> {for (int i = 0; i < 10; i++)
			try {
				data.decrData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "DD").start();
	}
}
