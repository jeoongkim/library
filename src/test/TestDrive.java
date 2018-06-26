package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDrive 
{
	public static void main(String[] args) 
	{
		DateFormat df=new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		System.out.println(date);
	}
}
