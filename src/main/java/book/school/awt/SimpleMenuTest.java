package book.school.awt;

import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleMenuTest {
	private Frame frame = new Frame();
	private MenuBar menuBar = new MenuBar();
	
	public void init(){
		//
		ActionListener menuListener = e ->{
			String cmd = e.getActionCommand();
			System.out.println(cmd);
			if(cmd.equals("退出")){
				System.exit(0);;
			}
		};
		//
		Menu fileMenu = new Menu("文件");
		Menu editMenu = new Menu("编辑");
		MenuItem exitMenu = new MenuItem("退出");
		fileMenu.add(new MenuItem("保存"));
		fileMenu.add(new MenuItem("打开"));
		fileMenu.add(new MenuItem("-"));
		fileMenu.add(exitMenu);
		editMenu.add(new CheckboxMenuItem("自动换行"));
		editMenu.add(new MenuItem("复制"));
		editMenu.add(new MenuItem("粘贴"));
		Menu format = new Menu("格式");
		format.add(new MenuItem("注释"));
		format.add(new MenuItem("取消注释"));
		editMenu.addSeparator();
		editMenu.add(format);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		frame.setMenuBar(menuBar);
		//
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		//
		exitMenu.addActionListener(menuListener);
		//
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new SimpleMenuTest().init();
	}
}
