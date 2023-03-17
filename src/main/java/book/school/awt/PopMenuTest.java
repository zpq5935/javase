package book.school.awt;

import java.awt.CheckboxMenuItem;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopMenuTest {

	private Frame frame = new Frame();
	private PopupMenu popupMenu = new PopupMenu();
	
	public void init(){
		popupMenu.add(new CheckboxMenuItem("自动换行"));
		popupMenu.add(new MenuItem("复制"));
		popupMenu.add(new MenuItem("粘贴"));
		popupMenu.add(new MenuItem("-"));
		Menu format = new Menu("格式");
		format.add(new MenuItem("注释"));
		format.add(new MenuItem("去注释"));
		popupMenu.add(format);
		final Panel p1 =new Panel();
		p1.setPreferredSize(new Dimension(300, 160));
		p1.add(popupMenu);
		p1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if(e.isPopupTrigger()){
					popupMenu.show(p1, e.getX(), e.getY());
				}
			}
		});
		//
		frame.add(p1);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		//
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new PopMenuTest().init();
	}
}
