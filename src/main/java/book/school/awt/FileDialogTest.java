package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;

public class FileDialogTest {
	Frame frame = new Frame("测试FileDialog");
	FileDialog loadfileDialog = new FileDialog(frame,"请选择要打开的文件",FileDialog.LOAD);
	FileDialog savefileDialog = new FileDialog(frame,"请选择要保存的文件",FileDialog.SAVE);
	Button loadBtn = new Button("打开文件");
	Button saveBtn = new Button("保存文件");
	public void init(){
		saveBtn.addActionListener(e ->{
			savefileDialog.setVisible(true);
			System.out.println(savefileDialog.getDirectory()+savefileDialog.getFile());
		});
		loadBtn.addActionListener(e ->{
			loadfileDialog.setVisible(true);
			System.out.println(loadfileDialog.getDirectory()+loadfileDialog.getFile());
		});
		frame.add(loadBtn);
		frame.add(saveBtn,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new FileDialogTest().init();
	}
}
