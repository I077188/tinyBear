package com.dba.util;

import java.io.PrintWriter;

import com.dba.constants.CONSTAINTS;

public class ExecuteLOG {

	private final static PrintWriter out = CONSTAINTS.exeOut;

	public static void info(String message) {
		out.write("[Infor]\t\t" + message + "\n");
	}

	public static void debug(String message) {
		out.write("[Debug]\t\t" + message + "\n");
	}

	public static void error(String exceptionType, String message) {
		out.write("[Error]\t\t" + "<" + exceptionType + ">\n" + message + "\n");
	}

	public static void warn(String message) {
		out.write("[Warn]\t\t" + message + "\n");
	}
}
