package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		String format = dd.format(date);
		System.out.println(transFormat);
		System.out.println(dd.format(new Date()));
		System.out.println(format);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 14);
		System.out.println(cal.getTime());
		
	}
}
