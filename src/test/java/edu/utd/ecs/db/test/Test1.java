package edu.utd.ecs.db.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		String values = "Pankaj||1212112||sdsds||asdasda";
		String cleanString = values.trim();
		System.out.println(cleanString);
		String[] formFields = cleanString.split("\\|\\|");
		System.out.println(formFields.length);
		System.out.println(formFields[0]);
		String name = formFields[0].trim();
		System.out.println(name);
		String ssn = formFields[1].trim();
		System.out.println(ssn);
		String address = formFields[2].trim();
		System.out.println(address);
		String phone = formFields[3].trim();
		System.out.println(phone);
		
		
		
		String str = "Welcome:dear guest"; 
		String [] arrOfStr = str.split(":"); 
		System.out.println(arrOfStr[0]);
		for(String a:arrOfStr) 
		System.out.println(a);
		
		
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, 14);
		Date currentDatePlusFourteen = c.getTime();
		System.out.println("Date: " + dateFormat.format(currentDate));
	}
}
