package com.atguigu.exe;
/**
 * 多线程之间的顺序调用 A->B->C(呼啦圈式)
 * 启动三个线程，要求如下
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 打印10轮
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//资源类
class ShatrSource{
	private int munber = 1;//1->AA,2->BB,3->CC
	private Lock lock = new ReentrantLock();
	//con1~con2对应的三个线程的等待和通知，相当于一把锁有三把备用钥匙
	Condition con1 = lock.newCondition();
	Condition con2 = lock.newCondition();
	Condition con3 = lock.newCondition();
	
	public void print5(int totalLoop) {
		lock.lock();
		try {
			//1 判断
			if (munber != 1) {
				//con1得等待
				con1.await();
			}
			//2 干活
			for (int i = 1; i <=5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t 第"+totalLoop+"轮");
			}
			//3 通知
			//3.1修改标志位
			munber=2;
			//3.2定点通知2号线程，只通知一个
			con2.signal();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void print10(int totalLoop) {
		lock.lock();
		try {
			//1 判断
			if (munber != 2) {
				//con1得等待
				con2.await();
			}
			//2 干活
			for (int i = 1; i <=10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t 第"+totalLoop+"轮");
			}
			//3 通知
			//3.1修改标志位
			munber=3;
			//3.2定点通知3号线程，只通知一个
			con3.signal();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void print15(int totalLoop) {
		lock.lock();
		try {
			//1 判断
			if (munber != 3) {
				//con1得等待
				con3.await();
			}
			//2 干活
			for (int i = 1; i <=15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t 第"+totalLoop+"轮");
			}
			//3 通知
			//3.1修改标志位
			munber=1;
			//3.2定点通知1号线程，只通知一个
			con1.signal();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
public class ThreadOrder {
	public static void main(String[] args) {
		ShatrSource source = new ShatrSource();
		new Thread(() -> {for (int i =1; i <=10; i++) source.print5(i); }, "AA").start();
		new Thread(() -> {for (int i =1; i <=10; i++) source.print10(i); }, "BB").start();
		new Thread(() -> {for (int i =1; i <=10; i++) source.print15(i); }, "CC").start();
	}
}
