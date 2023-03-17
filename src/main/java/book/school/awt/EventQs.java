package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventQs {

	private Frame  frame = new Frame();
	private Button okButton = new Button("单击出文本");
	private TextField tField =new TextField(20);
	public void init(){
		okButton.addActionListener(new OkListener());
		frame.add(tField);
		frame.add(okButton,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	class OkListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("单击OK按钮");
			tField.setText("Hello World章朝佩");
		}
	}
	public static void main(String[] args) {
		new EventQs().init();
	}
}
