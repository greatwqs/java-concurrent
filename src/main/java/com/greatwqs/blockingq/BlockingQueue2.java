package com.greatwqs.blockingq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 两个 Object 的加锁注意顺序, 否则容易产生死锁
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