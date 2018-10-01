package com.greatwqs.lang;

/**
 * synchronized demo
 *
 * 修饰方法: static 锁类, 非static 锁this
 *
 * @author wangqingsong
 * @create 2018/9/28
 */
public class SynchronizedDemo {

	/***
	 * synchronized 在静态方法之上, 锁的资源为 SynchronizedDemo.class
	 * staticMethodMS1() 与 staticMethodMS2() 一致
	 * @return
	 */
	public static synchronized long staticMethodMS1() {
		return commonMethod();
	}

	public static long staticMethodMS2() {
		synchronized (SynchronizedDemo.class) {
			return commonMethod();
		}
	}

	/***
	 * synchronized 在非静态方法之上, 锁的资源为 this
	 * normalMethod1() 与 normalMethod2() 一致
	 * @return
	 */
	public synchronized long normalMethod1() {
		return commonMethod();
	}

	public long normalMethod2() {
		synchronized (this) {
			return commonMethod();
		}
	}

	/***
	 * @return
	 */
	private static long commonMethod() {
		System.out.println("commonMethod start time: " + System.currentTimeMillis());
		try {
			Thread.sleep(SLEEP_TIME_MS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("commonMethod end time: " + System.currentTimeMillis());
		return System.currentTimeMillis();
	}

	private static final long SLEEP_TIME_MS = 2000L;

	public static void main(String[] args) {

	}
}
