package com.tutorialsninja.qa.Utilities;

import java.util.Date;


public class Util {
	
	public static String emailWithDateTimeStamp() {
		
		Date date = new Date(); //import java.util not java.sql
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + timeStamp + "@gmail.com";
	}
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 20;
	public static final int SCRIPT_TIME = 1000;

}
