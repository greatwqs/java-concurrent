package com.greatwqs.lang;

public class SynchronizedDemo1{
	
	private static final Object LOCK = new Object();
	
	public static synchronized String method1() {
		Thread.yield();
		return "";
	}
	
	public synchronized String method2() {
		return "";
	}
	
	public String method3() {
		synchronized(LOCK){
			return "";
		}
	}
}

