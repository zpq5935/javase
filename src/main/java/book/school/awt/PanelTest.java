package book.school.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;

public class PanelTest {

	public static void main(String[] args) {
		Frame frame = new Frame("测试窗口");
		Panel panel = new Panel();
		panel.add(new Button("按钮"));
		panel.add(new TextArea("zcp你要坚强，没人能帮到你",2,50));
		frame.add(panel);
		frame.setBounds(100, 100, 500, 400);
		frame.setVisible(true);
	}
}
