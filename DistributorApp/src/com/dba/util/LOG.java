package com.dba.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LOG {

	private static Logger logger;
	private static FileHandler fh;

	public LOG() {
		String logPath = ".\\src\\log\\ExecutionLog.log";

		logger = Logger.getLogger("DistributorApp");
		try {
			fh = new FileHandler(logPath);
			logger.addHandler(fh);

			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void info(String message) {
		logger.info("[Infor]\t\t" + message + ".");
	}

	public static void debug(String message) {
		logger.info("[Debug]\t\t" + message + ".");
	}

	public static void error(String exceptionType, String message) {
		logger.severe("[Error]\t\t" + "<" + exceptionType + ">\n" + message + ".");
	}

	public static void warn(String message) {
		logger.warning("[Warn]\t\t" + message + ".");
	}
}
