package com.atguigu.exe;

import java.util.concurrent.FutureTask;

/**
 * 第三种获得线程的方法，实现Callable接口
 * Runnable接口的run()方法和Callable接口中的Run()方法的区别
 * 1.run方法有返回值 2.run方法有抛异常 3.方法名不同 run方法有泛型(泛型是啥返回啥)
 * 此处的三个知识点：
 * 	1.线程的第三种获得方法，实现Callable
 * 	2.面向节后编程,多态思想，把不关的类绑一块
 * 	3.Callable的异步回调(主线程一直进行，Callable线程异步去处理别的，处理完成后返回结果)
 * 		get获取返回值的方法最好放最后主线程的操作后面，否则会造成主线程阻塞，直到Callable线程处理完成返回结果再执行
 *  4.当只new了一次FutureTask，不管new 了多少接口，run()方法值执行一次
 */

class Thread1 implements Runnable{
	@Override
	public void run() {
		
	}
}
//class Thread2 implements Callable<Integer>{
//	@Override
//	public Integer call() throws Exception {
//		return null;
//	}
//}
public class CallableTest {
	public static void main(String[] args) throws Exception{
		//使用lambda表达式直接传入
		FutureTask<Integer> future = new FutureTask<Integer>(()-> {
			int sum = 0;
			for (int i = 0; i < 195; i++) {
				sum+=i;
			}
			System.out.println("我是Callable===============");
			//让Callable线程睡2秒，模拟异步操作
			Thread.sleep(2000);
			return sum;});
		//获取线程并启动
		new Thread(future,"Callable1").start();
		new Thread(future,"Callable2").start();
		System.out.println("我是主线程========================");
		//此处不能讲get获取返回值的方法放在主线程后面，如过这样会造成结果不返回，主线程不执行的情况
		//不能体现异步操作
		Integer result = future.get();
		System.out.println("result:"+result);
	}

}
