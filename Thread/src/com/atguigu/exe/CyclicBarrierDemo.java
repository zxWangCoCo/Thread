package com.atguigu.exe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *齐集七颗龙珠召唤神龙，和CountDownLatch是反操作 
 * CountDownLatch是做减法
 * SycicBarrier是做加法
 */
public class CyclicBarrierDemo {
	private static final int NUMBER=7;

	public static void main(String[] args) {
	CyclicBarrier barrier = new CyclicBarrier(NUMBER, ()->{System.out.println("七颗龙珠集齐，召唤神龙");} );
	for (int i =1; i <=NUMBER; i++) {
		int tempInt=i;//这里需要将i转换一下，否则报错
		new Thread(() -> {
		try {
			System.out.println(Thread.currentThread().getName()+"集齐了第"+tempInt+"龙珠");
			//是线程等待，七个线程都完成后最后执行打印召唤神龙操作
			barrier.await();//
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		}, String.valueOf(i)).start();
	}
	}

}
