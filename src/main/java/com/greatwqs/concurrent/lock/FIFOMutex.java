package com.greatwqs.concurrent.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Here is a sketch of a first-in-first-out non-reentrant lock class:
 * 官方提供了一个FIFO先进先出的非重入锁实现Demo
 *
 * CAS + LockSupport 实现互斥锁
 *  * @author wangqingsong
 */
class FIFOMutex {
	private final AtomicBoolean locked = new AtomicBoolean(false);//锁标记
	private final Queue<Thread> waiters
		= new ConcurrentLinkedQueue<Thread>();//无界线程安全队列，存放等待线程

	public void lock() {
		boolean wasInterrupted = false;//标记中断
		Thread current = Thread.currentThread();
		waiters.add(current);//添加当前线程到队尾(FIFO)
		// Block while not first in queue or cannot acquire lock
		//阻塞条件：当前线程非队列首元素(FIFO) or 没有获得锁
		while (waiters.peek() != current ||
			!locked.compareAndSet(false, true)) {
			LockSupport.park(this);
			//补充一点：调用interrupted方法会将线程真正的中断状态清除，连续调用会返回false
			//这里的作用主要用于在park调用时线程阻塞过程中忽略中断带来的其他影响
			if (Thread.interrupted()) // ignore interrupts while waiting
				wasInterrupted = true;//当前线程若被中断，需要重新标记中断状态
		}
		//当能够获取锁后，将首元素移除(FIFO)，立即返回
		waiters.remove();
		if (wasInterrupted)          // reassert interrupt status on exit
			current.interrupt();//若线程被标记为中断，需要重新声明为中断状态
	}

	public void unlock() {
		locked.set(false);//解锁
		LockSupport.unpark(waiters.peek());//解除队列首元素的阻塞，FIFO
	}
}