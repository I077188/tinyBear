package com.dba.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Result {

	private static BufferedWriter bWriter;

	public Result() {

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

		String resultLogPath = currentPath + seprator + "log" + seprator + "ResultLog.log";

		System.out.println(resultLogPath);
		File resultLog = new File(resultLogPath);

		try {
			if (resultLog.exists()) {
				resultLog.delete();
			}
			resultLog.createNewFile();

			FileWriter fw = new FileWriter(resultLog);
			bWriter = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void info(String message) {
		try {
			bWriter.write("[INFO]\t\t" + message + ".");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
