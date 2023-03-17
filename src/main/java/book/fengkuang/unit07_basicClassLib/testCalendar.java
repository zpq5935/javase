package book.fengkuang.unit07_basicClassLib;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class testCalendar {

	@Test
	public void test01(){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 11);
		System.out.println(calendar.getTime());
	}
}
