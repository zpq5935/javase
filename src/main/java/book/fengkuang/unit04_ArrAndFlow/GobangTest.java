package book.fengkuang.unit04_ArrAndFlow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GobangTest {

	private static int BOARD_SIZE = 15;
	private String[][] board;

	public void initBoard() {
		board = new String[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = "＋";
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
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
				System.out.println("机下：");
				printBoard();
				checkOver(xPos, yPos);
			}

		}

	}

	// 检查是否结束，依照5子棋规则
	public void checkOver(int xPos, int yPos) {
		// 横向判断
		int left = (yPos - 4) > 0 ? (yPos - 4) : 0;
		int right = (yPos + 4) < BOARD_SIZE ? (yPos + 4) : (BOARD_SIZE - 1);
		for (int i = left; i <= yPos; i++) {
			if (right - i >= 4) {
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
		}

		// 纵向判断
	}

	public static void main(String[] args) throws IOException {
		GobangTest gb = new GobangTest();
		gb.initBoard();
		gb.printBoard();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String bufferLine = null;
		System.out.println("输入坐标，以 x,y 的格式：");
		while ((bufferLine = bufferedReader.readLine()) != null) {
			//新增的try-catch语句块，应对不合理的输入以给出合理的提示及处理
			try {
				String[] posStrArr = bufferLine.split(",");
				int xPos = Integer.parseInt(posStrArr[0]);
				int yPos = Integer.parseInt(posStrArr[1]);
				gb.board[xPos - 1][yPos - 1] = "●";
				gb.printBoard();
				gb.checkOver(xPos - 1, yPos - 1);
				gb.addByMachine();
				System.out.println("输入坐标，以 x,y 的格式：");
			} catch (Exception e) {
				System.out.println("输入有误，请重新输入！");
				continue;
			}
		}
		
	}

}
