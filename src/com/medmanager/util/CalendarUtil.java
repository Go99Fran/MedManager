package com.medmanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
	
	public static Calendar ParseString(String fecha) {
		if(fecha != null) {
			Calendar cal  = Calendar.getInstance();
			try {
				cal.setTime(sdf.parse(fecha));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return cal;
		}else {
			return null;
		}	
	}
	
	public static Calendar ParseStringReves(String fecha) {
		if(fecha != null) {
			Calendar cal  = Calendar.getInstance();
			try {
				cal.setTime(sdf2.parse(fecha));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return cal;
		}else {
			return null;
		}
		
	}
	
	public static String ParseCalendar(Calendar calendar) {
		return sdf2.format(calendar.getTime());		
	}
	
	public static Calendar sacarHora(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
}
