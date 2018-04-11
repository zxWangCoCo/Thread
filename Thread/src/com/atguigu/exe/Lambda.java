package com.atguigu.exe;

interface lam {
//	public void sayhello();
	
	//接口中可有多个default
	public  int div( int a , int b);
	
	//使用defalut关键字可以写实现类，并且可以多个
	default int Add(int a , int b ) {
		return a + b;
	};
	
	default int Add2(int a , int b ) {
		return a + b;
	};
	//静态方法，并且可以有多个
	public static int sub(int a , int b) {
		return a * b;
	}
	public static int sub2(int a , int b) {
		return a * b;
	}
}

public class Lambda {
	public static void main(String[] args) {
		lam m = (a,b)->{return a+b;};
		int result = m.div(10, 2);
		System.out.println(result);
		//调用默认实现类方法
		System.out.println(m.Add(8, 6));
		//调用静态的方法
		System.out.println(lam.sub(20, 5));
	}
}
