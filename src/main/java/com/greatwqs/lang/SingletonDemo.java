package com.greatwqs.lang;

/***
 * 
 * 经典单例
 * volatile & synchronized
 * 
 * 思考问题:
 * 这里 volatile 如果去掉, 程序会出BUG吗?
 * 
 * @author wangqingsong
 *
 */
public class SingletonDemo {

	// volatile
	private static volatile SingletonDemo instance;

	private SingletonDemo() {

	}

	public static SingletonDemo getInstance() {
		if (instance != null) { // not synchronized | check
			return instance;
		}

		synchronized (SingletonDemo.class) {
			if (instance != null) { // double check
				return instance;
			}
			
			instance = new SingletonDemo(); // volatile
			//
			return instance;
		}
	}
}