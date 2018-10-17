package com.greatwqs.lang.interrupt;

/***
 *
 * Example of interrupting a thread that stops working
 *
 * after interrupting the thread, we are propagating it, so it will stop working.
 * If we don't want to stop the thread,
 * we can handle it where sleep() or wait() method is invoked.
 * Let's first see the example where we are propagating the exception.
 *
 * https://www.javatpoint.com/interrupting-a-thread
 */
public class InterruptThread2 extends Thread {
	public void run() {
		try {
			Thread.sleep(10000);
			System.out.println("task");
		} catch (InterruptedException exp) {
			System.out.println("Thread interrupted, "+ exp);
		}
		
		System.out.println("Thread is running, isInterrupted:"+this.isInterrupted());
	}

	public static void main(String args[]) {
		InterruptThread2 t1 = new InterruptThread2();
		t1.start();
		
		t1.interrupt();
	}
}  