package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;

public class DialogTest {
	Frame frame = new Frame("测试对话框");
	Dialog dialog1 = new Dialog(frame,"模态对话框",true);
	Dialog dialog2 = new Dialog(frame,"非模态对话框",false);
	Button btn1 = new Button("打开模态对话框");
	Button btn2 = new Button("打开非模态对话框");
	public void init(){
		dialog1.setBounds(100,200,300,400);
		dialog1.setBounds(200,100,400,200);
		btn1.addActionListener(e -> dialog1.setVisible(true));
		btn2.addActionListener(e -> dialog2.setVisible(true));
		frame.add(btn1);
		frame.add(btn2,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new DialogTest().init();
		
	}
}
