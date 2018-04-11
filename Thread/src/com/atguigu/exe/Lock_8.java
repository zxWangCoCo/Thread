package com.atguigu.exe;
import java.util.concurrent.TimeUnit;

class Phone//  Phone.class
{
	public static synchronized void sendSMS() throws Exception
	{
		TimeUnit.SECONDS.sleep(4);
		System.out.println("--------------sendSMS");
	}
	
	public  synchronized void sendEmail() throws Exception
	{
		System.out.println("--------------sendEmail");
	}
	
	public void openPC()
	{
		System.out.println("--------------openPC");
	}
}

/**
 * 
 * @Description: 8锁
 * @author zzyy
 * @date 2018年3月15日
 * 笔记见后
 * 1	标准访问,请问先打印短信还是email 先SMS再Email
 * 2	sendSMS()睡觉4秒钟,请问先打印短信还是email 先SMS再Email
 * 3	新增普通方法openPC,请问先打印短信还是openPC 
 * 4	有两部手机,请问先打印短信还是email
 * 5	两个静态同步方法,同一部手机,请问先打印短信还是email
 * 6	两个静态同步方法,2部手机,请问先打印短信还是email
 * 7	1个静态同步方法,1个普通同步方法,同一部手机,请问先打印短信还是email
 * 8	1个静态同步方法,1个普通同步方法,2部手机,请问先打印短信还是email
 * 
 * ---------------------------------------------------
 *  * 笔记
1.一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法，线程并不是同时进来的
锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法(1,2锁例子)
2.加个普通方法后和同步锁无关(各执行各的，3锁例子)
3.换成两个对象后，不是同一把锁了，互不干涉，各自干各自的(4锁例子)。
4.静态同步方法锁的是类模板class,非静态的同步方法锁的是当前对象，这两个是不同的对象
5.当锁的对象是同一个时，遵循只能一个线程访问这些synchronzied方法，其余的等待线程结束再去访问(5,6锁)
6.当锁的对象不是同一个时，则互不干涉，各自执行自己的(7,8锁)

	锁定的是当前对象this,类锁,锁定的是整个class,是两个不同的对象 

都换成静态同步方法后，情况又变化
所有的非静态同步方法用的都是同一把锁——实例对象本身，

也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须
等待获取锁的方法释放锁后才能获取锁，
可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

所有的静态同步方法用的也是同一把锁——类对象本身，
这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，
还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 */
public class Lock_8
{
	public static void main(String[] args) throws Exception
	{
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		
		new Thread(() -> {
			try 
			{
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "A").start();
		
		Thread.sleep(100);//保证每次SendSMS先执行
		
		new Thread(() -> {
			try 
			{
				phone.sendEmail();
				//phone.openPC();
				//phone2.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "B").start();		
	}
}



















/**
 * 笔记
一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法

加个普通方法后发现和同步锁无关
换成两个对象后，不是同一把锁了，情况立刻变化。


都换成静态同步方法后，情况又变化
所有的非静态同步方法用的都是同一把锁——实例对象本身，

也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

所有的静态同步方法用的也是同一把锁——类对象本身，
这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，
还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 */
