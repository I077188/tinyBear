package com.dba.constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.WebDriver;

import com.dba.util.Inital;

public class CONSTAINTS {
	public static String searchContent;
	public static String[] countries;
	public static String searchEngine;
	public static int page;
	public static WebDriver driver;
	public static String senderUserName;
	public static String senderUserMail;
	public static String senderUserPass;
	public static String senderUserSmtpHost;
	public static String senderUserSmtpPort;
	public static String mailTitle;
	public static String separator = File.separator;
	public static String currentPath = System.getProperty("user.dir");
	public static File executeLogFile;
	public static File resultLogFile;

	// log part
	private static BufferedWriter exeBWriter;
	private static BufferedWriter resultBWriter;

	public static FileWriter exeFw;
	public static PrintWriter exeOut;
	public static FileWriter resultFw;
	public static PrintWriter resultOut;

	public static void inital() {
		// create execute log file
		executeLogFile = new File(currentPath + separator + "log" + separator + "ExecutionLog.log");
		if (!executeLogFile.exists()) {
			try {
				executeLogFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			exeFw = new FileWriter(executeLogFile, true);
			exeBWriter = new BufferedWriter(exeFw);
			exeOut = new PrintWriter(exeBWriter);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// create result log file
		resultLogFile = new File(currentPath + separator + "log" + separator + "ResultLog.log");
		if (!resultLogFile.exists()) {
			try {
				resultLogFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			resultFw = new FileWriter(resultLogFile, true);
			resultBWriter = new BufferedWriter(resultFw);
			resultOut = new PrintWriter(resultBWriter);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Inital inital = new Inital();

		senderUserName = inital.initialSenderName();
		senderUserMail = inital.initialSenderMail();
		senderUserPass = inital.initialSenderPass();
		senderUserSmtpHost = inital.initialSenderSmtpHost();
		senderUserSmtpPort = inital.initialSenderSmtpPort();

		mailTitle = inital.initialMailTitle();

	}

	public static void close() {
		try {
			exeBWriter.close();
			resultBWriter.close();
			exeFw.close();
			exeOut.close();
			resultFw.close();
			resultOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
