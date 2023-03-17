package book.fengkuang.unit12_swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class TestBorder {
	private JFrame frame = new JFrame("测试边框");
	public void init(){
		frame.setLayout(new GridLayout(2, 4));
		//BevelBorder
		Border border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.RED,Color.GREEN, Color.BLUE, Color.GRAY);
		frame.add(getPaneWithBorder(border1, "BevelBorder"));
		//LineBorder
		Border border2 = BorderFactory.createLineBorder(Color.orange,10);
		frame.add(getPaneWithBorder(border2, "LineBorder"));
		//EmptyBorder
		Border border3 = BorderFactory.createEmptyBorder(21, 5, 10, 30);
		frame.add(getPaneWithBorder(border3, "EmptyBorder"));
		//EtchedBorder
		Border border4 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.red, Color.green);
		frame.add(getPaneWithBorder(border4, "EtchedBorder"));
		// TitledBorder
		Border border5 = new TitledBorder(border2,"测试标题",TitledBorder.LEFT,TitledBorder.BOTTOM,
				new Font("StSong", Font.BOLD, 18),Color.blue);
		frame.add(getPaneWithBorder(border5, "TitledBorder"));
		// MatteBorder
		Border border6 = new MatteBorder(20,5,10,30,Color.GREEN);
		frame.add(getPaneWithBorder(border6, "MatteBorder"));
		//CompoundBorder
		CompoundBorder cb = new CompoundBorder(new LineBorder(Color.red,8),border5);
		frame.add(getPaneWithBorder(cb, "CompoundBorder"));
		frame.pack();
		frame.setVisible(true);
				
	}
	public JPanel getPaneWithBorder(Border b, String borderName){
		JPanel panel = new JPanel();
		panel.add(new JLabel(borderName));
		panel.setBorder(b);
		return panel;
	}
	public static void main(String[] args) {
		new TestBorder().init();
	}
}
