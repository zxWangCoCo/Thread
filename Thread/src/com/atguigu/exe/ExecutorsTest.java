package com.atguigu.exe;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *第四种获得线程的方法 
 * 使用线程池 Executors如ThreadPool();
 * 使用线程池均匀时间获取线程
 * 
 *
 */
public class ExecutorsTest {
	public static void main(String[] args) {
	ThreadPool();//使用线程池获得线程
	System.out.println("============================================");
	getPoolThreadByTime();//从线程池中均匀的获取线程
}	
	//使用线程池均匀时间从线程池中获取线程
	private static void getPoolThreadByTime() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> future = null;
		try {
			for (int i =1; i <=15; i++) {
				future = executor.schedule(()->{
					System.out.println(Thread.currentThread().getName());
					//返回 callable的返回值
					return new Random().nextInt(10);
				}, 2, TimeUnit.SECONDS);//表示每2秒从线程池中获取线程
				System.out.println("***********result:"+future.get());
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
				executor.shutdown();
			}
	}
	//使用线程池获取线程
	private static void ThreadPool() {
		//	ExecutorService executor = Executors.newFixedThreadPool(5);//一池固定5线程
		//	ExecutorService executor = Executors.newSingleThreadExecutor();//一池单线程
			ExecutorService executor = Executors.newCachedThreadPool();//一池N线程
			Future<Integer>  future = null;
			try {
				for (int i =1; i <=15; i++) {
					future=executor.submit(()->{//获取线程
						TimeUnit.MILLISECONDS.sleep(400);
						System.out.print(Thread.currentThread().getName());
						return new Random().nextInt(10);
					});
					System.out.println("\t ****result:"+future.get());
				}
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				executor.shutdown();
			}
	}
}
