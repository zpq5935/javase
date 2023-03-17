package book.fengkuang.unit12_swing;

import javax.swing.UIManager;

public class LAF {

	public static void main(String[] args) {
		for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
			System.out.println(info);
		}
	}
}
