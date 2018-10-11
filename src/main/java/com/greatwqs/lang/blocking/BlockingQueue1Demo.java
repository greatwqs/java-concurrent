package com.greatwqs.lang.blocking;

import org.apache.log4j.Logger;

/**
 *
 * @author wangqingsong
 * @create 2018/9/28
 */
public class BlockingQueue1Demo {

	private static Logger log = Logger.getLogger("logWarn");
	
	static class TakeThread extends Thread{
		int id;
		BlockingQueue1 blockQueue;
		
		public TakeThread(int id, BlockingQueue1 blockQueue) {
			super();
			this.id = id;
			this.blockQueue = blockQueue;
		}

		public void run() {
			while (true) {
				Object o1 = null;
				try {
					o1 = blockQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.warn(" take: " + o1);
			}
		}
	}
	
	static class PutThread extends Thread{
		int id;
		BlockingQueue1 blockQueue;
		
		public PutThread(int id, BlockingQueue1 blockQueue) {
			super();
			this.id = id;
			this.blockQueue = blockQueue;
		}

		public void run() {
			int putNumerLoop = 1;
			while (true) {
				String putObj = id + "-" + putNumerLoop++;
				try {
					blockQueue.put(putObj);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				log.warn(" put: " + putObj);
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (putNumerLoop > 2) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		final BlockingQueue1 blockQueue = new BlockingQueue1();

		for (int id = 1; id <= 2; id++) { // put
			PutThread thread = new PutThread(id, blockQueue);
			thread.setName("t-put-"+id);
			thread.start();
		}
		
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int id = 1; id <= 2; id++) { // take
			TakeThread thread = new TakeThread(id, blockQueue);
			thread.setName("t-take-"+id);
			thread.start();
		}
	}
}