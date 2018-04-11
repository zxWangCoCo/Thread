package com.atguigu.exe;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 * 停车：
 * 1.6辆车3个车位 同时进 同时出
 * 2.出一辆紧接近一辆
 *
 *
 */
public class SemphoreTest {

	public static void main(String[] args) {
		CarStop();
	}

	private static void CarStop() {
		Semaphore semap = new Semaphore(3);//模拟三个停车位
		for (int i =1; i <=6; i++) {//模拟6部车
			new Thread(() -> {
				try {
					semap.acquire();//抢占到一个资源(车位)
					System.out.println(Thread.currentThread().getName()+"====抢到了停车位");
					//每个线程占用资源的时间
					//TimeUnit.SECONDS.sleep(3);//表示三部车同时停3秒，同时走3个，同时进3个
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));//表示三部车随机停几秒离开一辆，马上补一辆
					System.out.println(Thread.currentThread().getName()+"离开了车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semap.release();//释放资源（车位），供别的线程抢占
				}
			},String.valueOf(i)).start();
		}
	}
}
