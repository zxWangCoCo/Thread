package com.atguigu.exe;

public class SeasonTest {

	public static void main(String[] args) {
		SeasonDemo.getSeason();
	}
}
class SeasonDemo{
	private static final int NUM=4;
		
		public static void getSeason() {
			for (int i =1; i <=NUM; i++) {
				new Thread(() -> {
				//	System.out.println(Thread.currentThread().getName());
				},Seasons.forEachEnum(i).getSea()).start();
			}
			System.out.println(Seasons.SPRI.getWea());
		}
	}
