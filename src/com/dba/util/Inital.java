package com.dba.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.dba.constants.CONSTAINTS;

public class Inital {

	private static Properties properties = new Properties();

	public Inital() {

		Result.info("Couldn't find mail box information in following web sites!");

		try {
			FileInputStream inputStream = new FileInputStream(new File("config.properties"));
			properties.load(inputStream);
			ExecuteLOG.info(properties.getProperty("countries").toString());
		} catch (IOException e) {
			ExecuteLOG.error(e.getClass().toString(), e.getMessage());
		}
	}

	public String initialSearchContent() {
		if (properties == null)
			return null;
		return properties.getProperty("SearchContent");
	}

	public int initialpage() {
		if (properties == null)
			return 0;
		return Integer.valueOf(properties.getProperty("page"));
	}

	public String initialEngine() {
		if (properties == null)
			return null;
		return properties.getProperty("searchEngine");
	}

	public String[] initialCountries() {

		if (properties == null)
			return null;
		return properties.getProperty("countries").split(",");
	}

	// only support Chrome and Firefox
	public WebDriver initalExplorerDriver() {

		// get system version
		String osType = System.getProperty("os.name");
		boolean isWin = osType.toLowerCase().startsWith("windows");

		String requireFilePath = "." + CONSTAINTS.separator + "src" + CONSTAINTS.separator + "com"
				+ CONSTAINTS.separator + "dba" + CONSTAINTS.separator + "requireFile" + CONSTAINTS.separator;
		// required file path
		String geckoDriver = (isWin) ? "geckodriver.exe" : "geckodriver";
		String chromeDriver = (isWin) ? "chromedriver.exe" : "chromedriver";
		String fireFoxExe = (isWin) ? "firefox.exe" : "firefox";

		String explorerType = properties.getProperty("explorerType").toLowerCase();

		if (explorerType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", requireFilePath + chromeDriver);
			return new ChromeDriver();
		} else {
			// system properties setting
			System.setProperty("webdriver.gecko.driver", requireFilePath + geckoDriver);

			String firefoxPathValue = properties.getProperty("FirefoxInstallPath");
			String firefoxPath = firefoxPathValue.endsWith(CONSTAINTS.separator) ? firefoxPathValue
					: (firefoxPathValue + CONSTAINTS.separator);

			String firefoxFullPath = (isWin) ? (firefoxPath.replace("\\", "\\\\") + fireFoxExe)
					: (firefoxPath + fireFoxExe);

			ExecuteLOG.debug(firefoxFullPath);

			File pathToBinary = null;
			if (isWin) {
				pathToBinary = new File(firefoxFullPath.replace("\\", "\\\\"));
			} else {
				pathToBinary = new File(firefoxFullPath);
			}

			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			FirefoxOptions firefoxOptions = new FirefoxOptions().setBinary(ffBinary).setProfile(firefoxProfile);

			return new FirefoxDriver(firefoxOptions);
		}

	}

	public String initialSeparator() {
		return File.separator;
	}

	// mail sending function
	public String initialSenderName() {
		if (properties == null)
			return null;
		return properties.getProperty("senderUserName");
	}

	public String initialSenderPass() {
		if (properties == null)
			return null;
		return properties.getProperty("senderUserPass");
	}

	public String initialSenderMail() {
		if (properties == null)
			return null;
		return properties.getProperty("senderUserMail");
	}

	public String initialSenderSmtpPort() {
		if (properties == null)
			return null;
		return properties.getProperty("senderUserSmtpPort");
	}

	public String initialSenderSmtpHost() {
		if (properties == null)
			return null;
		return properties.getProperty("senderUserSmtpHost");
	}

	public String initialMailTitle() {
		if (properties == null)
			return null;
		return properties.getProperty("mailTitle");
	}

}
