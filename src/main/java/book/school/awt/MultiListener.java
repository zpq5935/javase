package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiListener {
	private Frame frame = new Frame("多监听器多注册");
	private TextArea tArea = new TextArea(6,40);
	private Button btn1 = new Button("按钮一");
	private Button btn2 = new Button("按钮二");
	public void init(){
		FirstListener firstListener = new FirstListener();
		btn1.addActionListener(firstListener);
		btn1.addActionListener(new SecondListener());
		btn2.addActionListener(firstListener);
		frame.add(tArea);
		Panel panel = new Panel();
		panel.add(btn1);
		panel.add(btn2);
		frame.add(panel,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	class FirstListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("FirstListener"+e.getActionCommand());
			tArea.append("FirstListener"+e.getActionCommand()+"\n");
		}
	}
	class SecondListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("SecondListener"+e.getActionCommand());
			tArea.append("SecondListener"+e.getActionCommand()+"\n");
		}
	}
	public static void main(String[] args) {
		new MultiListener().init();
	}
}
