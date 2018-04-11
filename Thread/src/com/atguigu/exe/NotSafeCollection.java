package com.atguigu.exe;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 一：
 * 	演示集合类线程不安全
 * 二：
 *  解决集合线程不安全问题
 *
 *
 */
public class NotSafeCollection {

	public static void main(String[] args) {
		CollectionNotSafe();
//	ArraySafe();
//		System.out.println("====================================================");
//	//	HashSetSafe();
//		
//		HashMapSafe();
	}
	
	//说明线程不安全的例子
	private static void CollectionNotSafe() {
		List<String> list = new ArrayList<String>();
//		list = Arrays.asList("a","b","c");
		//30个线程同时向list添加，导致并发修正异常
		for (int i =1; i <=3000; i++) {
			new Thread(() -> {
			list.add(UUID.randomUUID().toString().substring(0, 4));
			System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
	//ArraryList解决并发修正异常★★
	public static void ArraySafe() {
		//第一种方法(读多写少)：使用CopyOnWriteArrayList解决,使用了写时复制技术
		List<String> list = new CopyOnWriteArrayList<String>();
		for (int i =1; i <=300; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 4));
				System.out.println(list);
			},String.valueOf(i)).start();
		}
		//第二种方法：使用Collections.synchronizedList(),将list变成一个加同步锁的集合
		List<String> list2 = Collections.synchronizedList(list);
	}
	
	//解决HashSet集合线程不安全的问题
	public static void HashSetSafe() {
		Set<String> set = new CopyOnWriteArraySet<String>();
		for (int i =1; i <=30; i++) {
			set.add(UUID.randomUUID().toString().substring(0, 4));
			System.out.println(set);
		}
	}
	
	//解决HashMap线程不安全问题,使用ConcurentHashMap
	public static void HashMapSafe() {
		Map<String, Object> map = new ConcurrentHashMap<String , Object>();
		for (int i =1; i <=300; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 4));
				System.out.println(map);
			},String.valueOf(i)).start();
		}
	}
}
