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
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();

	public Object get(String key) {
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	public Object[] allKeys() {
		r.lock();
		try {
			return m.keySet().toArray();
		} finally {
			r.unlock();
		}
	}

	public Object put(String key, Object value) {
		w.lock();
		try {
			return m.put(key, value);
		} finally {
			w.unlock();
		}
	}

	public void clear() {
		w.lock();
		try {
			m.clear();
		} finally {
			w.unlock();
		}
	}

}
