package book.school.awt;

import java.awt.Button;
import java.awt.Frame;

import javax.swing.BoxLayout;

public class BoxLayoutTest {
	Frame f1 = new Frame("测试BoxLayout布局管理器");
	
	public void init(){
		f1.setLayout(new BoxLayout(f1, BoxLayout.Y_AXIS));
		f1.add(new Button("按钮一"));
		f1.add(new Button("我是按钮一"));
		f1.setVisible(true);
	}
	public static void main(String[] args) {
		new BoxLayoutTest().init();
	}
}
