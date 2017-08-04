package com.dba.test;

import com.dba.constants.CONSTAINTS;
import com.dba.util.ExecuteLOG;
import com.dba.util.Result;

public class test {

	public static void main(String[] args) {

		CONSTAINTS.inital();

		ExecuteLOG.info("test, log ------");

		Result.info("test, result----------");

		System.out.println("test finished!");

		CONSTAINTS.close();
	}
}
