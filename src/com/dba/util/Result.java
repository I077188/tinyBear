package com.dba.util;

import java.io.PrintWriter;

import com.dba.constants.CONSTAINTS;

public class Result {

	private final static PrintWriter out = CONSTAINTS.resultOut;

	public static void info(String message) {
		out.write("[Infor]\t\t" + message + "\n");
	}

}
