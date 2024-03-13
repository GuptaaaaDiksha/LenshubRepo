package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public abstract class GenericMethods {
	public static int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000000000);
	}

	public static int randomIntNumber() {
		return new Random().nextInt(99);
	}

	public static String getProjectRootDirectory() {
		String currentDirectory = System.getProperty("user.dir");
		return currentDirectory;
	}

	public static String getCurrentLocalDate() {
		return LocalDate.now().toString();
	}

	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return formatter.format(date);
	}

	public static String getEndDate(int difference, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, difference);
		String output = sdf.format(c.getTime());
		return output;
	}

	public static int randomSingleIntNumber() {
		return new Random().nextInt(9);
	}

	public static String getSubtractedLocalDate(String date, int days) {
		LocalDate dt = LocalDate.parse(date);
		return dt.minusDays(days).toString();
	}
	
	public static String getPerivousDate(String date, int days) {
		LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return date = d.minusDays(days).toString();
	}

	
	public static String changeRequiredDateFormat(String date, String oldDateFormat,String newDateFormat) {
		String dateWithNewFormat = "";
		SimpleDateFormat sdf = new SimpleDateFormat(oldDateFormat);
		SimpleDateFormat sdfnew = new SimpleDateFormat(newDateFormat);
		try {
			dateWithNewFormat= sdfnew.format(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateWithNewFormat;
	}
	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public static String getRandomElementFromList(List<String> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	public static String changeDateFormat(String dateToBeChanged) throws ParseException{
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = dt.parse(dateToBeChanged); 
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		return dt1.format(date).toString();		
	}
	
	
	public static String reverseDateFormat(String dateToBeChanged) throws ParseException{
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = dt.parse(dateToBeChanged); 
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		return dt1.format(date).toString();		
	}
	
	//Added By Ujjwal
	
	public static String[] getCurrentWeekStartDateAndEndDate() {
		Calendar c = GregorianCalendar.getInstance();
		String[] strArr= {"",""};
		System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

		// Set the calendar to monday of the current week
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		System.out.println("Current week = " + Calendar.DAY_OF_WEEK);
		// Print dates of the current week starting on Monday
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String startDate = "", endDate = "";
		startDate = df.format(c.getTime());
		c.add(Calendar.DATE, 6);
		endDate = df.format(c.getTime());
		System.out.println("Start Date = " + startDate);
		System.out.println("End Date = " + endDate);
		strArr[0]=startDate;
		strArr[1]=endDate;
		
		return strArr;
	}
	
	
	
	public static String getLastDateOfCurrentMonth()
	{
		Date today = new Date();  

        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  

        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);  

        Date lastDayOfMonth = calendar.getTime();  

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
        System.out.println("Today            : " + sdf.format(today));  
        System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth));  
        return sdf.format(lastDayOfMonth);
	}
	
	
	public static String getFirstDateOfCurrentMonth()
	{
		LocalDate now = LocalDate.now();  
	    LocalDate firstDayOfMonth = now.withDayOfMonth(1);
	    return firstDayOfMonth.toString();
		
	}
	
	
	public static String[] getFirstAndLastDateOfPreviousMonth() {
		Calendar aCalendar = Calendar.getInstance();
		 aCalendar.add(Calendar.MONTH, -1);
		 aCalendar.set(Calendar.DATE, 1);
		 Date firstDateOfPreviousMonth = aCalendar.getTime();
		 System.out.println(firstDateOfPreviousMonth);
	     DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
	     aCalendar.set(Calendar.DATE,     aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	
		 Date lastDateOfPreviousMonth = aCalendar.getTime();
		  
	     String dt[] = {sdf.format(firstDateOfPreviousMonth).toString(),sdf.format(lastDateOfPreviousMonth).toString()};
	     
	     return dt;
	 
	}
}