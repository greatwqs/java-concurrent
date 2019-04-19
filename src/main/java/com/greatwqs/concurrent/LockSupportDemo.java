package com.greatwqs.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 *
 * LockSupportDemo
 *
 * @author wangqingsong
 * @create 2018/11/2
 */
public class LockSupportDemo {

	public static void main(String[] fds){
		LockSupport.unpark(Thread.currentThread());
		System.out.println("LockSupport.park(); 1");
		LockSupport.park();

		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		System.out.println("LockSupport.park(); 2");
		LockSupport.park();


		System.out.println("LockSupport.park(); 3");
		LockSupport.park();
	}

}