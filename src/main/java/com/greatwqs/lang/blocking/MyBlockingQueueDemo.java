package com.greatwqs.lang.blocking;

import org.apache.log4j.Logger;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wangqingsong
 * @create 2018/9/28
 */
public class MyBlockingQueueDemo {

	private static Logger log = Logger.getLogger("logWarn");

	public static void main(String[] args) {
		final MyBlockingQueue blockQueue = new MyBlockingQueue(5);

		// take
		for (int i = 1; i <= 3; i++) {
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						Object o1 = blockQueue.take();
						log.warn(Thread.currentThread().getName() + ", 移除的元素为: " + o1);
					}
				}
			}, "Thread-Take-" + i);
			t2.start();
		}

		// put
		for (int i = 1; i <= 2; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					int putNumerLoop = 1;
					while (true) {
						String putNumerString = Thread.currentThread().getName()+"-"+String.valueOf(putNumerLoop++);
						blockQueue.put(putNumerString);
						log.warn(" 插入的元素为:  " + putNumerString);
						try {
							Thread.sleep(1000L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (putNumerLoop > 2) {
							break;
						}
					}
				}
			}, "Thread-Put-" + i);
			t1.start();
		}
	}
}