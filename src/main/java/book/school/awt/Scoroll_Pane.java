package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.TextField;

public class Scoroll_Pane {
	public static void main(String[] args) {
		Frame frame = new Frame();
		ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		scrollPane.add(new Button("我是安妞"), BorderLayout.NORTH);
		scrollPane.add(new TextField(20), BorderLayout.SOUTH);
		frame.add(scrollPane);
		frame.setBounds(100, 200, 100, 200);
		frame.setVisible(true);
	}
}
