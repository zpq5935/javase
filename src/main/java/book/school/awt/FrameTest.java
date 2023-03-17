package book.school.awt;

import java.awt.Frame;

public class FrameTest {

	public static void main(String[] args) {
		Frame frame = new Frame("测试");
		frame.setBounds(1000, 800, 600, 400);
		frame.setVisible(true);
	}
}
