package com.atguigu.exe;
/**
 * 一个线程写  100个线程读
 * 读写 分离操作
 *
 */

import java.util.concurrent.locks.ReentrantReadWriteLock;

class Myqueue{
	private Object obj;
	//使用读写锁
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	//写方法
	public void Write(Object obj) {
		//加写锁
		lock.writeLock().lock();
		try {
			//接收传过来的变量
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"线程写了 \t"+obj);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			//解读锁
			lock.writeLock().unlock();
		}
	}
	
	//读方法
	public void Read() {
		//加读锁
		lock.readLock().lock();
		try {
			//读
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			//解读锁
			lock.readLock().unlock();
		}
	}
}
public class ReadWriteLock {
	public static void main(String[] args) throws Exception {
		Myqueue queue = new Myqueue();
		//一个线程写
		new Thread(() -> {
			queue.Write("今天天气真好");
		}, "Write Thread").start();
		
		//TimeUnit.SECONDS.toMillis(4);
		Thread.sleep(2000);
		
		//100个线程读
		for (int i =1; i <=100; i++) {
			new Thread(() -> {
				queue.Read();
			}, String.valueOf(i)).start();
			//不建议用变量+""变成字符串，防止在堆里创建对象
		}
	}
}