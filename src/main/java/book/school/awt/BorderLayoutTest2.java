package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

public class BorderLayoutTest2 {

	public static void main(String[] args) {
		Frame frame = new Frame("测试BorderLayout");
		frame.setLayout(new BorderLayout(20, 5));
		frame.add(new Button("安妞北"), BorderLayout.NORTH);
		frame.add(new Button("安妞南"), BorderLayout.SOUTH);
		Panel panel = new Panel();
		panel.add(new TextField(20));
		panel.add(new Button("我是panel里的按钮"),BorderLayout.CENTER);
		frame.add(panel);
		frame.add(new Button("我是frame右边--》"),BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
	}
}
