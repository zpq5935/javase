package book.school.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.Timer;

public class PinBall {

	private Frame frame = new Frame("弹球游戏");
	private MyCanvas table = new MyCanvas();
	private Random dRandom = new Random();
	private int racketX = dRandom.nextInt(200);// 球拍的横坐标
	private int RACKET_Y = 340;// 球拍的纵坐标
	private int TABLE_WIDTH = 300;// 桌面宽度
	private int TABLE_HEIFHT = 400;// 桌面高度
	private int RACKET_WIDTH = 60;// 球拍宽度
	private int RACKET_HEIGHT = 20;// 球拍高度
	private int BALL_SIZE = 16;// 球的大小
	private int ySpeed = 10;// 小球纵向运动速度
	private double xyRate = dRandom.nextDouble() - 0.5;// xy比率，表示横向运行
	private int xSpeed = (int) (ySpeed * xyRate * 2);
	private int ballX = dRandom.nextInt(200) + 20;
	private int ballY = dRandom.nextInt(10) + 20;
	private boolean isOver = false;
	private Timer timer;

	private Dialog dialog = new Dialog(frame, "设置", true);
	private Button restartBtn = new Button("ReStart");
	private Button overBtn = new Button("Over");

	public static void main(String[] args) {
		new PinBall().init();
	}

	public void initPara() {
		racketX = dRandom.nextInt(200);// 球拍的横坐标
		ySpeed = 10;// 小球纵向运动速度
		xSpeed = (int) (ySpeed * xyRate * 2);
		ballX = dRandom.nextInt(200) + 20;
		ballY = dRandom.nextInt(10) + 20;
		isOver = false;
	}

	public void restart() {
		dialog.setVisible(false);
		initPara();
		timer.start();
	}

	public void init() {
		restartBtn.addActionListener(e -> {
			restart();
		});
		overBtn.addActionListener(e -> {
			System.exit(0);
		});
		dialog.add(restartBtn);
		dialog.add(overBtn,BorderLayout.SOUTH);
		//
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (racketX > 0)
						racketX = (racketX - 10) > 0 ? (racketX - 10) : 0;
					table.repaint();
					System.out.println("LEFT_KEY->racketX:" + racketX);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (racketX < TABLE_WIDTH - RACKET_WIDTH)
						racketX += 10;
					table.repaint();
					System.out.println("RIGHT_KEY->racketX:" + racketX);
				}
			}
		};
		frame.addKeyListener(keyAdapter);
		table.addKeyListener(keyAdapter);
		table.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIFHT));
		frame.add(table);
		ActionListener taskListener = evt -> {
			// 如果小球碰到边框
			if (ballX <= 0 || ballX >= TABLE_WIDTH - BALL_SIZE) {
				xSpeed = -xSpeed;
			}
			// 如果小球掉下球拍，游戏结束
			if (ballY >= RACKET_Y - BALL_SIZE && (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
				timer.stop();
				isOver = true;
				table.repaint();
			}
			// 如果碰到球拍,反弹
			if (ballY <= 0 || (ballY >= RACKET_Y - BALL_SIZE && ballX > racketX && ballX <= racketX + RACKET_WIDTH)) {
				ySpeed += (ySpeed > 0) ? 5 : 0;
				ySpeed = -ySpeed;
				System.out.println("speed:" + ySpeed);
			}
			ballY += ySpeed;
			ballX += xSpeed;
			table.repaint();
		};
		timer = new Timer(100, taskListener);
		timer.start();
		frame.pack();
		frame.setVisible(true);
	}

	class MyCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (isOver) {
				g.setColor(new Color(255, 0, 0));
				g.setFont(new Font("Times", Font.BOLD, 30));
				g.drawString("游戏结束！！", 100, 100);
				dialog.pack();
				dialog.setVisible(true);
			} else {
				g.setColor(new Color(240, 240, 82));
				g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
				g.setColor(new Color(80, 80, 200));
				g.fillRect(racketX, RACKET_Y, RACKET_WIDTH, RACKET_HEIGHT);
			}

		}
	}
}
