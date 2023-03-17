package book.school.awt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowAdapterTest {
	Frame frame = new Frame("测试WindowAdapter");
	public void init(){
		frame.addWindowListener(new MyWindowListener());
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new WindowAdapterTest().init();
	}
	class MyWindowListener extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			System.exit(0);
		}
	}
}
