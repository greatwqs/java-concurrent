package com.greatwqs.lang.blocking;

import java.util.LinkedList;

import org.apache.log4j.Logger;

/**
 * 用 wait / notify 实现 blockingQueue
 * 
 * wait 与 notify 方法使用, 实现多线程调用的阻塞队列, 先进先出
 *
 * @author wangqingsong
 * @create 2018/9/28
 */
public class MyBlockingQueue {

	// 允许集合的最大容量
	private final int maxSize;
	// 锁
	private final Object lock = new Object();
	// 集合, 装东西
	private LinkedList<Object> list = new LinkedList<Object>();

	private Logger log = Logger.getLogger("logInfo");

	public MyBlockingQueue(int size) {
		this.maxSize = size;
	}

	public void put(Object obj) {
		log.info(" put try lock..");
		synchronized (lock) {
			log.info(" put get lock");
			while (list.size() == this.maxSize) { // while
				try {
					log.info(" put waiting, release lock");
					lock.wait(); // 达到最大, 等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			log.info(" put get lock & put obj:" + obj.toString());

			/**
			 * 一旦添加, 释放锁
			 * 通知take来拿, 但是实际上也会notify其他put的wait中的线程
			 */
			list.add(obj);
			lock.notify();
		}
	}

	public Object take() {
		log.info(" take try lock..");
		Object returnVal = null;
		synchronized (lock) {
			log.info(" take get lock");
			while (list.size() == 0) { // while
				log.info(" take waiting, release lock");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			/**
			 * 获取最前面的 & 删除 -> 释放锁
			 * 通知take来拿, 但是实际上也会notify其他take的wait中的线程
			 */
			returnVal = list.removeFirst();
			log.info(" take get lock & take obj:" + returnVal.toString());
			lock.notify();
		}
		return returnVal;
	}
}
