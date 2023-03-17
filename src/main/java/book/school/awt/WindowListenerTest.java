package book.school.awt;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowListenerTest {
	private Frame f1 = new Frame("测试WindowListener监听器");
	private TextArea tArea = new TextArea(6,20);
	public void init(){
		f1.addWindowListener(new MyWindowListener());
		f1.add(tArea);
		f1.pack();
		f1.setVisible(true);
	}
	public static void main(String[] args) {
		new WindowListenerTest().init();
	}
	class MyWindowListener implements WindowListener{
		@Override
		public void windowClosed(WindowEvent e) {
			tArea.append("窗口已关闭\n");
		}

		@Override
		public void windowOpened(WindowEvent e) {
			tArea.append("窗口已打开\n");
		}

		@Override
		public void windowClosing(WindowEvent e) {
			tArea.append("窗口正在关闭\n");
			System.exit(0);
		}

		@Override
		public void windowIconified(WindowEvent e) {
			tArea.append("窗口最小化\n");
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			tArea.append("窗口被恢复\n");
		}

		@Override
		public void windowActivated(WindowEvent e) {
			tArea.append("窗口被激活\n");
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			tArea.append("窗口失去焦点\n");
		}
	}
}
