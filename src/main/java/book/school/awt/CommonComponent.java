package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;

public class CommonComponent {

	Frame f = new Frame();
	Button okBtn = new Button("确认");
	CheckboxGroup cbp = new CheckboxGroup();
	Checkbox maleCheckb = new Checkbox("男", cbp, true);
	Checkbox femaleCheckb = new Checkbox("女", cbp, false);
	Checkbox married = new Checkbox("是否已婚？", false);
	Choice colorChooser = new Choice();
	List colorList = new List();
	TextArea tArea = new TextArea(5,20);
	TextField tField = new TextField(50);

	public void init() {
		colorChooser.add("红色");
		colorChooser.add("绿色");
		colorChooser.add("蓝色");
		colorList.add("红色");
		colorList.add("绿色");
		colorList.add("蓝色");
		Panel bottomPanel = new Panel();
		bottomPanel.add(tField);
		bottomPanel.add(okBtn);
		f.add(bottomPanel,BorderLayout.SOUTH);
		Panel topLeftPanel = new Panel();
		topLeftPanel.add(colorChooser);
		topLeftPanel.add(maleCheckb);
		topLeftPanel.add(femaleCheckb);
		topLeftPanel.add(married);
		Box topLeftBox = Box.createVerticalBox();
		topLeftBox.add(tArea);
		topLeftBox.add(topLeftPanel);
		Box topBox = Box.createHorizontalBox();
		topBox.add(topLeftBox);
		topBox.add(colorList);
		f.add(topBox);
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
			new CommonComponent().init();
	}
}
