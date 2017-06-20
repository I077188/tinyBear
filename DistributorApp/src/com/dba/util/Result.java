package com.dba.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Result {

	private static BufferedWriter bWriter;

	public Result() {

		String logPath = ".\\log\\ResultLog.log";

		File resultLog = new File(logPath);

		try {
			if (!resultLog.exists()) {
					resultLog.createNewFile();
			} else {
				resultLog.delete();
				resultLog.createNewFile();
			}

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
	
	public static void close(){
		try {
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
