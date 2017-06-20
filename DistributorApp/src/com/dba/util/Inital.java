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

public class Inital {
	
	LOG log = new LOG();
	Result resultLog = new Result();
	
	
	private static Properties properties = new Properties();

	public Inital() {
		Result.info("Couldn't find mail box information in following web sites!");

		try {
			FileInputStream inputStream = new FileInputStream(new File("config.properties"));
			properties.load(inputStream);
			LOG.info(properties.getProperty("countries"));
		} catch (IOException e) {
			LOG.error(e.getClass().toString(), e.getMessage());
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

		// system properties setting
		System.setProperty("webdriver.gecko.driver", ".\\src\\com\\dba\\requireFile\\geckodriver.exe");

		String explorerType = properties.getProperty("explorerType").toLowerCase();

		if (explorerType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\com\\dba\\requireFile\\chromedriver.exe");
			return new ChromeDriver();
		} else {
			String firefoxPath = properties.getProperty("FirefoxInstallPath");
			String firefoxFullPath = (firefoxPath.endsWith("\\")) ? firefoxPath : (firefoxPath + "\\");
			LOG.debug(firefoxPath);
			LOG.debug(firefoxFullPath);
			LOG.debug(firefoxFullPath.replace("\\", "\\\\") + "firefox.exe");
			File pathToBinary = new File(firefoxFullPath.replace("\\", "\\\\") + "firefox.exe");

			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			FirefoxOptions firefoxOptions = new FirefoxOptions().setBinary(ffBinary).setProfile(firefoxProfile);

			return new FirefoxDriver(firefoxOptions);
		}

	}

}
