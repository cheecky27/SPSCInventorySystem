package app.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CurrentDate {
	public String showDateToday(){
		String currentDate;
		Calendar cal=new GregorianCalendar();
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONDAY)+1;
		currentDate = year+""+month+""+day;

		return currentDate;
	}
	public String showCompleteDateToday(){
		String timeStamp=(new SimpleDateFormat("EEEE, MMMM d, y ").format(new Date()));
		return timeStamp;
	}
	public String showTime(){
		String time;
		time = new SimpleDateFormat("hh:mm:ss a").format(new java.util.Date());
		return time;
	}
	public String intoTheDBDate(){
		String forDBdate;
		Calendar cal=new GregorianCalendar();
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONDAY);
		String time;
		int superMonth = month+1;
		time = new SimpleDateFormat("hh:mm:ss").format(new java.util.Date());
		
		forDBdate =year+"-"+superMonth+"-"+(day)+" "+time;
	
		return forDBdate;
	}
}
