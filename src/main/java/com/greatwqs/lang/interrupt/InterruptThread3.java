package com.greatwqs.lang.interrupt;

/***
 *
 * What about isInterrupted and interrupted method?
 *
 * The isInterrupted() method returns the interrupted flag either true or false.
 * The static interrupted() method returns the interrupted flag afterthat it sets the flag to false if it is true.
 *
 *
 * https://www.javatpoint.com/interrupting-a-thread
 */
public class InterruptThread3 extends Thread {

	public void run() {
		for (int index = 1; index <= 10; index++) {
			if (Thread.interrupted()) {
				System.out.println("interrupted thread " + this.getName());
				return; // 正常情况下的响应中断;
			}

			System.out.println("normal thread " + this.getName());
		}
	}

	public static void main(String args[]) {
		InterruptThread3 t1 = new InterruptThread3();
		InterruptThread3 t2 = new InterruptThread3();

		t1.setName("t1");
		t1.start();
		t1.interrupt();

		t2.setName("t2");
		t2.start();
	}
}