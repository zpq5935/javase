package book.school.awt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AnonymousEventHandler {
	private Frame frame = new Frame();
	public void init(){
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
					System.exit(0);
			}
		});
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new AnonymousEventHandler().init();
	}
}
