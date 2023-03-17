package book.school.awt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gobang2 {

	private BufferedImage table;
	private final int TABLE_WIDTH = 500;
	private final int TABLE_HEIGHT = 501;
	private final int RATE = TABLE_WIDTH / BOARD_SIZE;
	private final int X_OFFSET = 5;
	private final int Y_OFFSET = 6;
	private JFrame frame = new JFrame("五子棋");
	ChessBoard chessBoard = new ChessBoard();
	private int selectedX = -1;
	private int selectedY = -1;
	private int X_P = 0;
	private int Y_P = 0;
	//
	private static int BOARD_SIZE = 15;
	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

	public void initBoard() throws IOException {
		table = ImageIO.read(new File("img/board.jpg"));
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = "＋";
			}
		}
		chessBoard.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		chessBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int xPos = (int) ((e.getX() - X_OFFSET) / RATE);
				int yPos = (int) ((e.getY() - Y_OFFSET) / RATE);
				X_P = e.getX();
				Y_P = e.getY();
				if (!board[xPos][yPos].equals("＋"))
					return;
				System.out.println("人:" + xPos + " " + yPos);
				board[xPos][yPos] = "●";
				chessBoard.repaint();
				checkOver(xPos, yPos);
				addByMachine();
				chessBoard.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				selectedX = -1;
				selectedY = -1;
				chessBoard.repaint();
			}
		});
		chessBoard.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				selectedX = (e.getX() - X_OFFSET) / RATE;
				selectedY = (e.getY() - Y_OFFSET) / RATE;
				chessBoard.repaint();
			}
		});
		frame.add(chessBoard);
		frame.pack();
		frame.setVisible(true);
	}

	// 机器随机下棋
	public void addByMachine() {
		boolean flag = true;
		while (flag) {
			int xPos = (int) (Math.random() * 15);
			int yPos = (int) (Math.random() * 15);
			if (board[xPos][yPos].equals("＋")) {
				board[xPos][yPos] = "○";
				flag = false;
				System.out.println("机下：" + xPos + " " + yPos);
				// printBoard();
				checkOver(xPos, yPos);
			}

		}

	}

	// 检查是否结束，依照5子棋规则
	public void checkOver(int xPos, int yPos) {
		// 纵向判断
		int left = (yPos - 4) > 0 ? (yPos - 4) : 0;
		int right = (yPos + 4) < BOARD_SIZE ? (yPos + 4) : (BOARD_SIZE - 1);
		for (int i = left; i <= yPos; i++) {
			if (right - i >= 4)
				if (board[xPos][i].equals(board[xPos][i + 1]))
					if (board[xPos][i].equals(board[xPos][i + 2]))
						if (board[xPos][i].equals(board[xPos][i + 3]))
							if (board[xPos][i].equals(board[xPos][i + 4])) {
								System.out.println("***********Over***********");
								System.out.println("起点-->" + "【x:" + xPos + ",y:" + i + "】");
								System.out.println("终点-->" + "【x:" + xPos + ",y:" + (i + 4) + "】");
								System.exit(0);
							}
		}
		// 向判断
	}

	public static void main(String[] args) throws IOException {
		new Gobang2().initBoard();
	}

	@SuppressWarnings("serial")
	class ChessBoard extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.drawImage(table, 0, 0, null);
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (board[i][j].equals("●")) {
						g.setColor(new Color(255, 0, 0));
						int xPos = i * RATE + X_OFFSET;
						int yPos = j * RATE + Y_OFFSET;
						// g.fillOval(xPos, yPos, 5, 5);
						g.fillOval(xPos, yPos, RATE, RATE);
					}
					if (board[i][j].equals("○")) {
						g.setColor(new Color(0, 255, 0));
						// g.fillOval(i * RATE + X_OFFSET, j * RATE + Y_OFFSET,
						// 5, 5);
						g.fillOval(i * RATE + X_OFFSET, j * RATE + Y_OFFSET, RATE, RATE);
					}
				}
			}
		}
	}
}
