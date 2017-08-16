package com.dba.constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public static int waitTime;

	// log part
	private static BufferedWriter exeBWriter;
	private static BufferedWriter resultBWriter;

	public static FileWriter exeFw;
	public static PrintWriter exeOut;
	public static FileWriter resultFw;
	public static PrintWriter resultOut;
	public static Logger LOG = LogManager.getLogger("CONSTAINTS.class");

	public static void inital() {
		Inital inital = new Inital();
		searchContent = inital.initialSearchContent();
		countries = inital.initialCountries();
		driver = inital.initalExplorerDriver();
		driver.manage().window().maximize();
		page = inital.initialpage();
		searchEngine = inital.initialEngine();
		waitTime = inital.initialWaitTime();

		senderUserName = inital.initialSenderName();
		senderUserMail = inital.initialSenderMail();
		senderUserPass = inital.initialSenderPass();
		senderUserSmtpHost = inital.initialSenderSmtpHost();
		senderUserSmtpPort = inital.initialSenderSmtpPort();
		mailTitle = inital.initialMailTitle();

	}

	public static void close() {
		
		try {
			if (driver!= null) {
				driver.close();
			}
			if (exeBWriter!= null) {
				exeBWriter.close();
			}
			if (resultBWriter!= null) {
				resultBWriter.close();
			}
			if (exeFw!= null) {
				exeFw.close();
			}
			if (exeOut!= null) {
				exeOut.close();
			}
			if (resultFw!= null) {
				resultFw.close();
			}
			if (resultOut!= null) {
				resultOut.close();
			}
			
			CONSTAINTS.LOG.info("Searching finished!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
