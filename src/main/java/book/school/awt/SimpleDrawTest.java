package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Random;

public class SimpleDrawTest {

	private final String RECT_SHAPE = "rect";
	private final String OVAL_SHAPE = "oval";
	private Frame frame = new Frame("简单绘图");
	private Button rectBtn = new Button("矩形");
	private Button ovalBtn = new Button("圆形");
	private MyCanvas drawCanvas = new MyCanvas();
	private String shape = "";

	class MyCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Random random = new Random();
			if (shape.equals(OVAL_SHAPE)) {
				g.setColor(new Color(220, 100, 80));
				g.fillOval(random.nextInt(200), random.nextInt(120), 50, 40);
			}
			if (shape.equals(RECT_SHAPE)) {
				g.setColor(new Color(80, 100, 200));
				g.drawRect(random.nextInt(100), random.nextInt(180), 40, 60);
			}
		}
	}

	public void init() {
		Panel panel = new Panel();
		rectBtn.addActionListener(e ->{
			shape = RECT_SHAPE;
			drawCanvas.repaint();
		});
		ovalBtn.addActionListener(e ->{
			shape = OVAL_SHAPE;
			drawCanvas.repaint();
		});
		panel.add(rectBtn);
		panel.add(ovalBtn);
		drawCanvas.setPreferredSize(new Dimension(250, 180));
		frame.add(drawCanvas);
		frame.add(panel,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new SimpleDrawTest().init();
	}
}
