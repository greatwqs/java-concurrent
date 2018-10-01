package com.greatwqs;

import org.apache.log4j.Logger;

public class TestLog4j {
	private static Logger logger = Logger.getLogger(TestLog4j.class);

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			logger.info("i=" + i);
		}

		logger.debug("#####logger.debug#####");

		logger.error("#####logger.error#####");
	}
}
