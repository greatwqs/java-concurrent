package com.greatwqs.lang.interrupt;

/***
 *
 * Example of interrupting thread that behaves normally
 *
 *
 * If thread is not in sleeping or waiting state,
 * calling the interrupt() method sets the interrupted flag to true
 * that can be used to stop the thread by the java programmer later.
 *
 * https://www.javatpoint.com/interrupting-a-thread
 */
public class InterruptThread1 extends Thread {

	public void run() {
		for (int i = 1; i <= 1000; i++) {
			System.out.print(i+",");
			if(i % 100 == 0) {
				System.out.println();
			}
		}
		
		System.out.println("Thread is running, isInterrupted:"+this.isInterrupted());
	}

	public static void main(String args[]) {
		InterruptThread1 t1 = new InterruptThread1();
		t1.start();
		
		t1.interrupt();
	}
}  