package book.school.string;

import org.junit.Test;

public class StringTest {
	@Test
	public void t1() {
		String string1 = "18351205850";
		String string2 = "362321199701065935";
		String phone = string1.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
		String certNo = string2.replaceAll("(\\w{3})\\w*(\\w{3})", "$1************$2");
		System.out.println(phone+"\n"+certNo);
	}
}
