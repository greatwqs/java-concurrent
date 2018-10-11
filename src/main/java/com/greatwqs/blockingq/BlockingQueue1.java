package com.greatwqs.blockingq;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

/**
 * 用 wait / notify 实现 blockingQueue
 * 
 * wait 与 notify 方法使用, 实现多线程调用的阻塞队列, 先进先出
 * 
 * BlockingQueue1 缺陷:
 * Object lock = new Object(); 
 * 条件限制没有区分开, 多线程都在监听lock 处于等待状态, 一个线程notyfy后, 其他线程会拿到锁; 
 * 但是没有达到状态, 后面会继续等待, 让其他线程拿到锁, 一直到正确的线程拿到锁!
 * 
 * 如本例:
 * 当多个put线程来进行put, 执行到lock.notify();时 可能唤醒的是put线程 (但是这里我们的想要是唤醒take的消费线程)
 * 怎么办?  加while, 拿到锁后再判断状态, 不合适再lock.wait();
 * 这样这把锁能确定被take的消费线程 拿到, 但是性能下降了!!
 *
 * @author wangqingsong
 * @create 2018/9/28
 */
public class BlockingQueue1 {

	// 允许集合的最大容量
	private final int maxSize = 10;
	// 锁
	private final Object lock = new Object();
	// 集合, 装东西
	private Queue<Object> linkedList = new LinkedList<Object>();

	private Logger log = Logger.getLogger("logInfo");

	public void put(Object obj) throws InterruptedException {
		log.info(" put try lock..");
		synchronized (lock) {
			log.info(" put get lock");
			while (linkedList.size() == this.maxSize) { // while
				log.info(" put waiting, release lock");
				lock.wait(); // 达到最大, 等待
			}

			log.info(" put get lock & put obj:" + obj.toString());

			/**
			 * 一旦添加, 释放锁
			 * 通知take来拿, 但是实际上也会notify其他put的wait中的线程
			 */
			linkedList.add(obj);
			lock.notify();
		}
	}

	public Object take() throws InterruptedException {
		log.info(" take try lock..");
		Object returnVal = null;
		synchronized (lock) {
			log.info(" take get lock");
			while (linkedList.size() == 0) { // while
				log.info(" take waiting, release lock");
				lock.wait();
			}

			/**
			 * 获取最前面的 & 删除 -> 释放锁
			 * 通知take来拿, 但是实际上也会notify其他take的wait中的线程
			 */
			returnVal = linkedList.poll();
			log.info(" take get lock & take obj:" + returnVal.toString());
			lock.notify();
		}
		return returnVal;
	}
}
