package com.greatwqs.lang;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestStop {
	private static final int[] array = new int[80000];
	private static final Thread t = new Thread() {
		public void run() {
			int resultDown = -1;
			for(int index=1; index<8000; index ++){
				try {
					Thread.sleep(1L);
				} catch (Error err) {
					err.printStackTrace();
					System.out.println("Error in thread t");
				} catch (InterruptedException exp) {
					exp.printStackTrace();
					System.out.println("in thread t, exp:"+exp.getStackTrace());
				}
				resultDown = index;
			}

			System.out.println("in thread t, resultDown:"+resultDown);
		}
	};

	static {
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(i + 1);
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("go to stop thread t");
		t.stop();
		System.out.println("finish main");
	}
}
