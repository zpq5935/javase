package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class SimpleClipBoard {

	private Frame frame = new Frame("简单剪贴板");
	private Panel panel = new Panel();
	private Box box = new Box(BoxLayout.X_AXIS);
	private TextArea taCopy = new TextArea(5, 20);
	private TextArea taPaste = new TextArea(5, 20);
	private Button copyBtn = new Button("Copy");
	private Button pasteBtn = new Button("Paste");
	private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	public void init() {
		copyBtn.addActionListener(e -> {
			StringSelection selection = new StringSelection(taCopy.getText());
			clipboard.setContents(selection, null);
		});
		pasteBtn.addActionListener(e -> {
			if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
				String centents;
				try {
					centents = (String) clipboard.getData(DataFlavor.stringFlavor);
					taPaste.append(centents+"\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//
		panel.add(copyBtn);
		panel.add(pasteBtn);
		box.add(taCopy);
		box.add(taPaste);
		frame.add(box);
		frame.add(panel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new SimpleClipBoard().init();
	}
}
