/**
 * 
 */
package com.greatwqs.lang;

/**
 * @author wangqingsong
 *
 */
public class ThreadInterruptDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new TestThread2();
		thread.start();
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException exp) {
			exp.printStackTrace();
		}
		thread.interrupt();
//		thread.interrupt();
	}
	
	/**
	 * not exception
	 * @author wangqingsong
	 *
	 */
	static class TestThread extends Thread{
		@Override
		public void run() {
			while(true) {
				if(! this.isInterrupted()) {
					break;
				}
				
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException exp) {
					exp.printStackTrace();
				}
			}
		}
	}
	
	/***
	 * 
	 * java.lang.InterruptedException: sleep interrupted
	 * 	at java.lang.Thread.sleep(Native Method)
	 * 	at com.greatwqs.lang.ThreadInterruptDemo$TestThread2.run(ThreadInterruptDemo.java:50)
	 * @author wangqingsong
	 *
	 */
	static class TestThread2 extends Thread{
		@Override
		public void run() {
			while(! this.isInterrupted()) {
				
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException exp) {
					exp.printStackTrace();
				}
			}
		}
	}

}
