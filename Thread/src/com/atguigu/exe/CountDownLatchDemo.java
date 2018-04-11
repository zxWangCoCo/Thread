package com.atguigu.exe;

import java.util.concurrent.CountDownLatch;
/**
 * 让一些线程阻塞，直到另一些线程完成另一些线程完成一些操作后才能执行
 * 例：
 * 6名同学都走完后班长才能锁门
 * 秦灭六国之后统一中国
 * 使用CountDownLatch
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws Exception {
		CloseDoor();
		System.out.println("================================");
		Country();
	}
	/**
	 * 同学都走，班长锁门案例
	 */
	private static void CloseDoor() throws Exception {
		CountDownLatch count = new CountDownLatch(6);
		for (int i =1; i <=6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"走了");
				//表示完成一部数量减一，直到减到0为止
				count.countDown();
			},String.valueOf(i)).start();
		}
		//是线面线程zuse，直到上面减到0，结束为止
		count.await();
		System.out.println("班长锁门了");
	}
	
	private static void Country() throws Exception {
		CountDownLatch count = new CountDownLatch(6);
		for (int i =1; i <=6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"被秦灭了");
				count.countDown();
			},CountryEnum.forCountry(i).getRetMsg()).start();
		}
		count.await();
		System.out.println("秦统一华夏");
	}
}
