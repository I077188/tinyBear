package com.dba.util;

import com.dba.constants.CONSTAINTS;

public class test {

	public static void main(String[] args) {

		CONSTAINTS.inital();

		ExecuteLOG.info("test, log ------");
		ExecuteLOG.info("test, log ------");

		Result.info("test, result----------");
		Result.info("test, result----------");

		System.out.println("test finished!");

		CONSTAINTS.close();
	}
}
