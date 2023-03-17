package book.school.awt;

import java.awt.Button;
import java.awt.Frame;

public class NullLayoutTest {
	Frame f1 = new Frame("测试绝对定位");
	Button btn1 = new Button("按钮1");
	Button btn2 = new Button("按钮2");
	public void init(){
		f1.setLayout(null);
		btn1.setBounds(20,30,80,78);
		f1.add(btn1);
		btn2.setBounds(50,20,80,78);
		f1.add(btn2);
		f1.setBounds(400, 200, 500, 350);
		f1.setVisible(true);
	}
	public static void main(String[] args) {
		new NullLayoutTest().init();
	}
}
