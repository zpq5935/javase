package book.fengkuang.unit07_basicClassLib;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class test_datetime_etc {
	/*
	 * 不推荐使用
	 * java.util.Date;GMT 1971-01-01为起始时间 
	 * 
	 */
	@Test
	public void tt1() {
		Date date = new Date(100);
		System.out.println(date);
		Date now = new Date();
		System.out.println(now.getTime());
	}
	/**
	 * 还ok
	 * java.util.Calendar;
	 * Gregorian 日历-算是世界的公历
	 * 
	 * void add(int field,int account)
	 * int get(int field)
	 * int getActualMaximum(int field)
	 * int getActualMinimum(int field)
	 * void roll(int field,int amount)
	 * -------------------------------------
	 * Calendar 有两种模式，分别是容错模式lenient与非容错non-lenient
	 * 容错运行字段值超出，自动改变其他字段
	 * ------------------------------------------
	 * set方法具有延迟修改的特性，再下一次调用get。。。等方法时才会进行相应的
	 * 
	 */
	@Test
	public void tt2() {
		Date now = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(now);
		System.out.println(calendar.get(Calendar.YEAR)+" "+Calendar.MONTH);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd ").format(calendar.getTime()));
		//测试set延迟
		System.out.println("---------------------");
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2019, 6, 31);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd ").format(calendar2.getTime()));
		calendar2.set(Calendar.MONTH, 1);
		calendar2.getTime();//当注释这条信息时，结果会不一一样
		calendar2.set(Calendar.DATE, 5);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd ").format(calendar2.getTime()));
		
	}
	
	
	/***
	 * Java8新增的日期、时间包
	 * 
	 */
}
