package com.dba.util;

public class Close {
	
	public static void closeApp(){
		LOG.close();
		Result.close();
	}
}
