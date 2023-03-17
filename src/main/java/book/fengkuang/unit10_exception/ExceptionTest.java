package book.fengkuang.unit10_exception;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class ExceptionTest {
	@Test
	public void t1() {
		Integer i1 = new Integer(12);
		System.out.println(i1 instanceof Integer);
		System.out.println(i1 instanceof Number);
		System.out.println(i1 instanceof Object);
		System.out.println(i1.getClass().getName());
		// System.out.println(i1 instanceof String);
	}

	/**
	 * Java7提供的捕获多异常
	 */

	@Test
	public void t2() {
		try {
			int a = 10, b = 0;
			String string = null;
			string.length();
		} catch (NullPointerException | NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * finally语句块 java的垃圾回收机制，并不能回收物理资源（如数据库连接，磁盘连接等），只能回收堆内存中对象所占的内存。
	 * return语句不会影响finally语句块的执行，但是System.exit(1)会
	 */
	@Test
	public void t3() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("adad.dad");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
			// return;
			// System.exit(0);
			// exception.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e) {
					System.out.println("关闭FileInputSteam出错");
				}
			}
			System.out.println("finally");
		}

	}

	/**
	 * 
	 * @Description:自定义异常类
	 * @Author:zpq
	 * @Date:2019年7月8日
	 * @MehtodName:t4
	 * @param:
	 * @return:void
	 * @throws MyException
	 */
	@Test
	public void t4() throws MyException {
		try {
			throw new MyException("这是一个自定义异常类");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new MyException("这是一个自定义异常类");
		}
	}

	/*******
	 * 异常跟踪栈
	 * 
	 * @throws MyException
	 ***************/
	public void first() throws MyException {
		second();
	}

	public void second() throws MyException {
		third();
	}

	public void third() throws MyException {
		throw new MyException("这是最里面的异常");
	}

	@Test
	public void tt() {
		try{
			new ExceptionTest().first();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**************************************************/
}
