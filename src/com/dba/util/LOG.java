package com.dba.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LOG {

	private static BufferedWriter bWriter;

	public LOG() {
		String seprator = "/";
		if (!File.separator.equals("/"))
			seprator = "\\";
		String currentPath = "";
		try {
			currentPath = new File(".").getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String executeLogPath = currentPath + seprator + "log" + seprator + "ExecutionLog.log";

		System.out.println(executeLogPath);

		File executionLog = new File(executeLogPath);
		try {
			if (executionLog.exists()) {
				executionLog.delete();
			}
			executionLog.createNewFile();
			FileWriter fw = new FileWriter(executionLog);
			bWriter = new BufferedWriter(fw);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void info(String message) {
		try {
			bWriter.write("[Infor]\t\t" + message + ".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void debug(String message) {
		try {
			bWriter.write("[Debug]\t\t" + message + ".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void error(String exceptionType, String message) {
		try {
			bWriter.write("[Error]\t\t" + "<" + exceptionType + ">\n" + message + ".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void warn(String message) {
		try {
			bWriter.write("[Warn]\t\t" + message + ".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
