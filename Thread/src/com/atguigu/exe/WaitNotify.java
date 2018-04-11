package com.atguigu.exe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 * 套路上：线程  操作 资源
 * 		高内聚 低耦合
 * 套路下：判断 干活 唤醒
 * 
 * 一变量，一次加一 一次减一 5次循环
 *
 */
public class WaitNotify {

	public static void main(String[] args) {
		Data data = new Data();
		new Thread(() -> {for (int i = 0; i <10; i++) data.incrData();}, "AA").start();
		new Thread(() -> {for (int i = 0; i <10; i++) data.decrData();}, "BB").start();
		new Thread(() -> {for (int i = 0; i <10; i++) data.incrData();}, "CC").start();
		new Thread(() -> {for (int i = 0; i <10; i++) data.decrData();}, "DD").start();
	}

}

class Data{
	int data = 0;
	Lock lock =new ReentrantLock();
	Condition condition = lock.newCondition();
	//加一操作
	public void incrData() {
		lock.lock();
		try {
			//1.判断(多线程的判断必须使用while,防止出现线程的虚假唤醒)
			while (data != 0) {
			//	this.wait();
				condition.await();
			}
			//2.干活
			++data;
			System.out.println(Thread.currentThread().getName()+"\t"+data);
			//3.唤醒
			//this.notifyAll();
			condition.signalAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	//减一操作
		public void decrData() {
			lock.lock();
			try {
				//1.判断(多线程的判断必须要用while,避免出现线程的虚假唤醒)
				while (data == 0) {
					//this.wait();
					condition.await();
				}
				//2.干活
				--data;
				System.out.println(Thread.currentThread().getName()+"\t"+data);
				//3.唤醒
				//this.notifyAll();
				condition.signalAll();
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				lock.unlock();
			}
		}
}
