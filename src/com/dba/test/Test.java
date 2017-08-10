package com.dba.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
	private static Logger LOG = LogManager.getLogger("Result.class");
	public static void main(String[] args) {
		LOG.info("test info");
		LOG.error("test error");
	}
}
