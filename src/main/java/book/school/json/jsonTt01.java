package book.school.json;

import org.junit.Test;

public class jsonTt01 {
	@Test
	public void tt1() {
		String jsonStr = "{\"name\":\"JSON\",\"address\":\"北京市西城区\",\"age\":\"25\"}";
		System.out.println(jsonStr);
		String[] strings = jsonStr.split("\"");
		int index = 2;
		for (String s : strings) {
			if (!(s.equals("{") || s.equals("}") || s.equals(":") || s.equals(","))) {
				System.out.print(s);
				System.out.print((index % 2 == 0) ? ":" : "\n");
//				System.out.print(index);
				index++;

			}
		}

	}
}
