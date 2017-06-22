package com.dba.constants;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.dba.util.Inital;

public class CONSTAINTS {
	public static String searchContent;
	public static String[] countries;
	public static String searchEngine;
	public static int page;
	public static WebDriver driver;
	public static String Separator = File.separator;
	
	public static void inital() {
		Inital inital = new Inital();		
		searchContent = inital.initialSearchContent();
		countries = inital.initialCountries();
		page =inital.initialpage();
		searchEngine = inital.initialEngine();
		driver = inital.initalExplorerDriver();
	}

	public static void end() {
		// TODO Auto-generated method stub
		
	}

}
