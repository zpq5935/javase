package book.school.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class FlowLayoutTest {

	public static void main(String[] args) {
		Frame frame = new Frame("测试流式布局管理器");
		frame.setLayout(new FlowLayout(FlowLayout.LEFT,20,5));
		for(int i=0; i<20; i++){
			frame.add(new Button("安妞"+i));
		}
		frame.pack();
		frame.setVisible(true);
	}

}
