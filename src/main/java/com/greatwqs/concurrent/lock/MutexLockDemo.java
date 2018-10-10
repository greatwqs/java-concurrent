/**
 * 
 */
package com.greatwqs.concurrent.lock;

/**
 * @author wangqingsong
 *
 */
public class MutexLockDemo {
	
	private static final MutexLock lock = new MutexLock();

	public static void main(String[] args) {
		Thread thread1 = new LockThread("MutexLockDemo-Thread-1") ;
		thread1.start();
		Thread thread2 = new LockThread("MutexLockDemo-Thread-2") ;
		thread2.start();
	}
	
	static class LockThread extends Thread{
		public LockThread(String name) {
			super(name);
		}
		
		public void run() {
			System.out.println(this.getName()+", try lock, time:"+System.currentTimeMillis());
			lock.lock();
			System.out.println(this.getName()+", get lock, time:"+System.currentTimeMillis());
			try {
				Thread.sleep(2000000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
			System.out.println(this.getName()+", unlock lock, time:"+System.currentTimeMillis());
		}
	}

}
