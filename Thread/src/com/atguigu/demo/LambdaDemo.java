package com.atguigu.demo;

interface Foo{
	public int syaHello(int a ,int b);
	
	default int  add(int a , int b ) {
	return a+b;
	}
	
	public static int div(int a , int b ) {
		return a/b;
	}
}

public class LambdaDemo {
	public static void main(String[] args) {
//		Foo foo = new Foo() {
//			
//			@Override
//			public void syaHello() {
//				System.out.println("Hello");
//			}
//		};
	//	foo.syaHello();
		
		Foo foo=(a,b)->{return a+b;};	
		int result = foo.syaHello(5,8);
		System.out.println(result);
		
		int add = foo.add(5, 6);
		System.out.println(add);
		 int div = Foo.div(10, 2);
		 System.out.println(div);
	}
}
