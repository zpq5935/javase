package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionListener;

public class CardLayoutTest {
	Frame frame = new Frame("测试CardLayout布局管理器");
	String[] names = {"第1张","第2张","第3张","第4张","第5张",};
	Panel p1 = new Panel();
	
	public void init(){
		final CardLayout cardLt = new CardLayout();
		p1.setLayout(cardLt);
		for(int i=0; i<names.length; i++){
			p1.add(names[i], new Button(names[i]));
		}
		Panel p2 = new Panel();
		//
		ActionListener listener = e->{
			switch(e.getActionCommand()){
			case "上一张":
				cardLt.previous(p1);
				break;
			case "下一张":
				cardLt.next(p1);
				break;
			case "第一张":
				cardLt.first(p1);
				break;
			case "最后一张":
				cardLt.last(p1);
				break;
			case "第三张":
				cardLt.show(p1,"第3张");
				break;
			}
		};
		//
		Button btn1 = new Button("上一张");
		btn1.addActionListener(listener);
		Button btn2 = new Button("下一张");
		btn2.addActionListener(listener);
		Button btn3 = new Button("第一张");
		btn3.addActionListener(listener);
		Button btn4 = new Button("最后一张");
		btn4.addActionListener(listener);
		Button btn5 = new Button("第三张");
		btn5.addActionListener(listener);
		//
		p2.add(btn1);p2.add(btn2);p2.add(btn3);p2.add(btn4);p2.add(btn5);
		frame.add(p1);
		frame.add(p2,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		new CardLayoutTest().init();
	}

}
