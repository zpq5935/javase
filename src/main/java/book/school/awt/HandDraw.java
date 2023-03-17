package book.school.awt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class HandDraw {
	private final int AREA_WIDTH = 500;
	private final int AREA_HEIGHT = 400;
	private int preX = -1;
	private int preY = -1;
	BufferedImage img = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_BGR);
	//
	PopupMenu popupMenu = new PopupMenu();
	MenuItem menuItem1 = new MenuItem("红色");
	MenuItem menuItem2 = new MenuItem("蓝色");
	MenuItem menuItem3 = new MenuItem("黄色");
	//
	Graphics graphics = img.getGraphics();
	private Frame frame = new Frame("简单画图程序");
	private DrawCanvas canvas = new DrawCanvas();
	private Color penClr = new Color(255, 0, 0);

	public void init() {
		ActionListener menuListener = e ->{
			if(e.getActionCommand().equals("红色")){
				penClr = new Color(255,0,0);
			}
			if(e.getActionCommand().equals("蓝色")){
				penClr = new Color(0,0,255);
			}
			if(e.getActionCommand().equals("黄色")){
				penClr = new Color(200,120,160);
			}
		};
		//
		menuItem1.addActionListener(menuListener);
		menuItem2.addActionListener(menuListener);
		menuItem3.addActionListener(menuListener);
		//
		popupMenu.add(menuItem1);
		popupMenu.add(menuItem2);
		popupMenu.add(menuItem3);
		canvas.add(popupMenu);
		graphics.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		canvas.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				if(preX>0 && preY>0){
					graphics.setColor(penClr);
					graphics.drawLine(preX, preY, e.getX(), e.getY());
				}
				preX = e.getX();
				preY = e.getY();
				canvas.repaint();
			}
		});
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if(e.isPopupTrigger()){
					popupMenu.show(canvas, e.getX(), e.getY());
				}
				preX =-1;
				preY = -1;
			}
		});
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new HandDraw().init();
	}

	@SuppressWarnings("serial")
	class DrawCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 0, 0, null);
		}
	}
}
