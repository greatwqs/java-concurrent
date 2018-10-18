package com.greatwqs.blockingq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 解决 BlockingQueue1 中不合理唤醒
 * 
 * 1. 两个 Object 的加锁注意顺序, 否则容易产生死锁
 * 2. 嵌套 synchronized, 操作系统两把独占锁, 性能消耗大!
 * 
 * BlockingQueue1 和 BlockingQueue1中怎么再优化?
 * 
 * @author wangqingsong
 * @create 2018/9/28
 */
public class BlockingQueue2 {
	// 非空 -> 可读 (take())
	private Object notEmpty = new Object();
	// 没满 -> 可写 (offer())
	private Object notFull = new Object();
	
	private Queue<Object> linkedList = new LinkedList<Object>();
	private int maxLength = 10;

	public Object take() throws InterruptedException {
		synchronized (notEmpty) {
			while (linkedList.size() == 0) {
				notEmpty.wait();
			}
			
			synchronized (notFull) {
				if (linkedList.size() < maxLength) { 
					notFull.notifyAll();
				}
				return linkedList.poll();
			}
		}
	}

	public void put(Object object) throws InterruptedException {
		synchronized (notEmpty) {
			synchronized (notFull) {
				while (linkedList.size() == maxLength) {
					notFull.wait();
				}
				linkedList.add(object);
			}
			
			// 添加完了, 通知消费者去take
			if (linkedList.size() > 0) {
				notEmpty.notifyAll();
			}
		}
	}
}