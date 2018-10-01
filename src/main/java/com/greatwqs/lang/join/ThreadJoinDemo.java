package com.greatwqs.lang.join;

/**
 * 
 * join 线程实例的 join() 方法调用;
 * @author wangqingsong
 *
 */
public class ThreadJoinDemo {
	
	/**
     * Waits at most {@code millis} milliseconds for this thread to
     * die. A timeout of {@code 0} means to wait forever.
     *
     * <p> This implementation uses a loop of {@code this.wait} calls
     * conditioned on {@code this.isAlive}. As a thread terminates the
     * {@code this.notifyAll} method is invoked. It is recommended that
     * applications not use {@code wait}, {@code notify}, or
     * {@code notifyAll} on {@code Thread} instances.
     * 
     * public final synchronized void join(long millis) throws InterruptedException {}
     */
	public static void main(String[] args) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start.");
		SubThread subThread = new SubThread();
		subThread.setName("SubThread");
		try {
			subThread.start();
			
			// 当前线程等待子线程的终止
			subThread.join();
			
			// not recommended!!!  why? join()
			// subThread.wait(); subThread.notify();
		} catch (Exception e) {
			System.out.println("Exception from main");
		}
		System.out.println(threadName + " end!");
	}
}

class SubThread extends Thread {
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start.");
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(threadName + " sleep at " + i);
				Thread.sleep(1000);
			}
			System.out.println(threadName + " end.");
		} catch (Exception e) {
			System.out.println("Exception from " + threadName + ".run");
		}
	}
}
