package com.greatwqs.concurrent.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 * 读多写少的场景
 *
 * ReentrantReadWriteLock Demo
 *
 * @author wangqingsong
 *
 */
public class ReentrantReadWriteLockDemo {

	private final Map<String, Object> m = new TreeMap<String, Object>();
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock readLock = rwl.readLock();
	private final Lock writeLock = rwl.writeLock();

	public Object get(String key) {
		readLock.lock();
		try {
			return m.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public Object[] allKeys() {
		readLock.lock();
		try {
			return m.keySet().toArray();
		} finally {
			readLock.unlock();
		}
	}

	public Object put(String key, Object value) {
		writeLock.lock();
		try {
			return m.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	public void clear() {
		writeLock.lock();
		try {
			m.clear();
		} finally {
			writeLock.unlock();
		}
	}

}
