package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;

public class GridLayoutTest {

	public static void main(String[] args) {

		Frame frame= new Frame("测试GridLayout布局管理器");
		frame.add(new TextField(30), BorderLayout.NORTH);
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(3,5,4,4));
		String[] btns = {"0","1","2","3","4",
						"5","6","7","8","9",
						"+","-","*","/","="};
		for(int i=0; i<btns.length; i++){
			panel.add(new Button(btns[i]));
		}
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

}
