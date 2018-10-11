package com.greatwqs.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/***
 * 
 * LockSupport
 * @author wangqingsong
 *
 */
public class LockSupportDemo {
	
	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		LockSupport.unpark(thread); //释放许可
		System.out.println("LockSupport.unpark(thread)");
		LockSupport.park();
		System.out.println("LockSupport.park();");
		LockSupport.unpark(thread);//释放许可
		LockSupport.park();
		System.out.println("block.");
	}
}
