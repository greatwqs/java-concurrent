package com.greatwqs.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 线程池使用测试
 *
 * @author wangqingsong
 * @create 2018/9/28
 */
public class ThreadPoolDemo {
	
	public static void main(String[] args) throws InterruptedException {
		final boolean isFair = false;
		ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(10, isFair);
//		arrayBlockingQueue.add(new MyThreadTask(10086));
		
		final int corePoolSize = 3;
		final int maximumPoolSize = 6;
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
		        1, TimeUnit.SECONDS,
		        arrayBlockingQueue,
		        new ThreadPoolExecutor.CallerRunsPolicy());
		
//		threadPool.allowCoreThreadTimeOut(true);
//		Integer result = 9;
		for (int index = 1; index <= 10; index++) {
			Thread tempNewThread = new MyThreadTask(index);
		    threadPool.execute(tempNewThread);
//			result = threadPool.submit(new MyThreadTask(i), result);
		}
	    
//	    threadPool.shutdown();
	}
	
	/***
	 * 测试线程
	 * @author wangqingsong
	 */
	static class MyThreadTask extends Thread {
		public MyThreadTask(int x) {
			this.setName("name:"+x);
			System.out.println("MyThreadTask, currentTid:"+Thread.currentThread().getId()+", thisId:"+this.getId());
		}

		public void run() {
			System.out.println("MyThreadTask, RUN, currentTid:"+Thread.currentThread().getId()+", thisId:"+this.getId());
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}