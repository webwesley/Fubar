
import java.util.Random;
import java.util.Scanner;

public class TicTacTo {

	static String[] position;

	public static void main(String[] args) {
		startUp();
	}

	private static void startUp() {
		String playAgain = "y";
		while (playAgain.equals("y")) {
			initialize();
			play();
			Scanner in = new Scanner(System.in);
			System.out.println("Play Again? [Y/N]:");
			playAgain = in.nextLine().toLowerCase();
			in.close();
		}

	}

	private static void initialize() {
		position = new String[9];
		for (int i = 0; i <= 8; i++) {
			position[i] = Integer.toString(i + 1);
		}

		System.out.println(
				"Thank you for playing Tic Tack Toe. The board setup is below. You are X's, and the computer is O's. \n");
		drawBoard();
		for (int i = 0; i <= 8; i++) {
			position[i] = " ";
		}

	}

	private static void drawBoard() { // sets up board
		System.out.println("_" + position[0] + "_|_" + position[1] + "_|_" + position[2] + "_" + "\n_" + position[3]
				+ "_|_" + position[4] + "_|_" + position[5] + "_" + "\n_" + position[6] + "_|_" + position[7] + "_|_"
				+ position[8] + "_" + "\n");

	}

	private static void play() {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			if (i == 4) {
				getMove(in);
				drawBoard();
				if (checkForWin() == 0) {
					System.out.println("It's a tie");
					return;
				} else if (checkForWin() == 1) {
					System.out.println("X has won");// x win
					return;
				} else if (checkForWin() == 2) {
					System.out.println("O has won");// o win
					return;
				}
				return;
			} else {
				getMove(in);
			}
			if (checkForWin() == 1) {
				System.out.println("X has won");// x win
				return;
			} else if (checkForWin() == 2) {
				System.out.println("O has won");// o win
				return;
			}
			compMove();
			drawBoard();
			if (checkForWin() == 0) {
				continue;
			} else if (checkForWin() == 1) {
				System.out.println("X has won");// x win
				return;
			} else if (checkForWin() == 2) {
				System.out.println("O has won");// o win
				return;
			}
		}
		in.close();
	}

	private static int checkForWin() { // sets up and checks for win conditions
										// using a 2-d array
		String[][] tmp = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tmp[i][j] = position[3 * i + j];
			}
		}

		if (checkRows(tmp) == 1 || checkCol(tmp) == 1 || checkDiag(tmp) == 1) {
			return 1;
		} else if (checkRows(tmp) == 2 || checkCol(tmp) == 2 || checkDiag(tmp) == 2) {
			return 2;
		}
		return 0;

	}

	private static int checkRows(String[][] tmp) {
		int xCount, yCount;
		for (int i = 0; i < 3; i++) {
			xCount = 0;
			yCount = 0;
			for (String s : tmp[i]) {
				if (s.equals("O")) {
					yCount++;
				} else if (s.equals("X")) {
					xCount++;
				}
			}
			if (xCount == 3) {
				return 1; // need to change checkWin etc to return 0,1,2 to
							// determin if no win, x win or y win.
			} else if (yCount == 3) {
				return 2;
			}
		}
		return 0;

	}

	private static int checkCol(String[][] tmp) {
		int xCount, yCount;
		for (int i = 0; i < 3; i++) {
			xCount = 0;
			yCount = 0;
			for (int j = 0; j < 3; j++) {
				if (tmp[j][i].equals("O")) {
					yCount++;
				} else if (tmp[j][i].equals("X")) {
					xCount++;
				}
			}
			if (xCount == 3) {
				return 1; // need to change checkWin etc to return 0,1,2 to
							// determin if no win, x win or y win.
			} else if (yCount == 3) {
				return 2;
			}

		}
		return 0;
	}

	private static int checkDiag(String[][] tmp) {
		int xCount = 0, yCount = 0;
		
		for (int i = 0; i < 3; i++) {
			if (tmp[i][i].equals("O")) {
				yCount++;
			} else if (tmp[i][i].equals("X")) {
				xCount++;
			}
			if (xCount == 3) {
				return 1; // need to change checkWin etc to return 0,1,2 to
							// determin if no win, x win or y win.
			} else if (yCount == 3) {
				return 2;
			}
		}
		xCount = 0;
		yCount = 0;
		
		for (int i = 0; i < 3; i++) {
			if (tmp[i][2 - i].equals("O")) {
				yCount++;
			} else if (tmp[i][2- i].equals("X")) {
				xCount++;
			}
			if (xCount == 3) {
				return 1; // need to change checkWin etc to return 0,1,2 to
							// determin if no win, x win or y win.
			} else if (yCount == 3) {
				return 2;
			}
		}

		return 0;
	}

	private static void getMove(Scanner in) { // gets the move, and inputs an
												// "x" into the selected
												// position array

		try {
			System.out.print("Please input position for move:");
			String move = in.next();
			int moveInt = Integer.parseInt(move);
			if (1 > moveInt && moveInt > 9 || position[moveInt - 1].equals("O") || position[moveInt - 1].equals("X")) {
				throw new NumberFormatException();
			}
			position[moveInt - 1] = "X";
			System.out.println("\n");
			return;
		} catch (NumberFormatException e) {
			System.out.println("Please input a valid integer between 1 and 9, inclusive \n");
			getMove(in);
		}
	}

	private static void compMove() {
		Random random = new Random();
		int move = random.nextInt(8);
		while (position[move].equals("X") || position[move].equals("O")) {
			move = random.nextInt(8);
		}
		position[move] = "O";
		return;
	}
}
