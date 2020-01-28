import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

public class Board {

		private final int[][] board;
		private final int dimension;
		private int hamming;
		private int manhattan;

		public Board(int[][] tiles) {
				dimension = tiles.length;
				board = new int[dimension][dimension];
				for (int i = 0; i < dimension; i++) {
						for (int j = 0; j < dimension; j++) {
								board[i][j] = tiles[i][j];
						}
				}
				setHamming();
				setManhattan();

		}

		private Board(Board a) {
				this(a.board);
		}

		public String toString() {
				StringBuilder output = new StringBuilder();
				output.append(dimension);
				for (int i = 0; i < dimension; i++) {
						output.append('\n');
						for (int j = 0; j < dimension; j++) {
								output.append(board[i][j]);
								output.append(' ');
						}
				}
				return output.toString();
		}

		public int dimension() {
				return dimension;
		}

		public int hamming() {
				return hamming;
		}

		public int manhattan() {
				return manhattan;
		}
		private void setHamming() {
				int count = 0;
				for (int i = 0; i < dimension; i++) {
						for (int j = 0; j < dimension; j++) {
								if (board[i][j] != findPosition(i,j))	count++;
						}
				}
				hamming = count - 1;
		}

		private void setManhattan() {
				int total = 0;
				for (int i = 0; i < dimension; i++) {
						for (int j = 0; j < dimension; j++) {
								if (board[i][j] != findPosition(i, j)) {
										int current = board[i][j];
										if (current == 0) continue;
										int[] position = findCell(current);
										total += Math.abs(position[0] - i) + Math.abs(position[1] - j);
								}
						}
				}
				manhattan = total;

		}

		public boolean isGoal() {
				return hamming == 0;
		}

		public boolean equals(Object y) {
				if (y == null || getClass() != y.getClass()) return false;
				Board copy = (Board) y;
				if (dimension != copy.dimension) return false;
				for (int i = 0; i < dimension; i++) {
						for (int j = 0; j < dimension; j++) {
								if (copy.board[i][j] != board[i][j]) return false;
						}
				}
				return true;
		}


		public Board twin() {
				Board twin = new Board(board);
				if (twin.board[0][0] == 0) {
						int temp = twin.board[0][1];
						twin.board[0][1] = twin.board[1][1];
						twin.board[1][1] = temp;
				}
				else if (twin.board[0][1] == 0) {
						int temp = twin.board[0][0];
						twin.board[0][0] = twin.board[1][0];
						twin.board[1][0] = temp;

				}
				else {
						int temp = twin.board[0][0];
						twin.board[0][0] = twin.board[0][1];
						twin.board[0][1] = temp;
				}
				twin.setHamming();
				twin.setManhattan();
				return twin;
		}

		public Iterable<Board> neighbors()	{
				ArrayList<Board> neighbors = new ArrayList<Board>();
				int[] zeroPosition = new int[2];
				for (int i = 0; i < dimension; i++) {
						for (int j = 0; j < dimension; j++) {
								if (board[i][j] == 0) {
										zeroPosition[0] = i;
										zeroPosition[1] = j;
										break;
								}
						}
				}
				try {
						Board boardCopy = new Board(board);
						boardCopy.board[zeroPosition[0]][zeroPosition[1]] = boardCopy.board[zeroPosition[0] + 1][zeroPosition[1]];
						boardCopy.board[zeroPosition[0] + 1][zeroPosition[1]] = 0;
						boardCopy.setManhattan();
						boardCopy.setHamming();
						neighbors.add(boardCopy);
				}
				catch (IndexOutOfBoundsException e) {}
				try {
						Board boardCopy = new Board(board);
						boardCopy.board[zeroPosition[0]][zeroPosition[1]] = boardCopy.board[zeroPosition[0] - 1][zeroPosition[1]];
						boardCopy.board[zeroPosition[0] - 1][zeroPosition[1]] = 0;
						boardCopy.setManhattan();
						boardCopy.setHamming();
						neighbors.add(boardCopy);
				}
				catch (IndexOutOfBoundsException e) {}
				try {
						Board boardCopy = new Board(board);
						boardCopy.board[zeroPosition[0]][zeroPosition[1]] = boardCopy.board[zeroPosition[0]][zeroPosition[1] + 1];
						boardCopy.board[zeroPosition[0]][zeroPosition[1] + 1] = 0;
						boardCopy.setManhattan();
						boardCopy.setHamming();
						neighbors.add(boardCopy);
				}
				catch (IndexOutOfBoundsException e) {}
				try {
						Board boardCopy = new Board(board);
						boardCopy.board[zeroPosition[0]][zeroPosition[1]] = boardCopy.board[zeroPosition[0]][zeroPosition[1] - 1];
						boardCopy.board[zeroPosition[0]][zeroPosition[1] - 1] = 0;
						boardCopy.setManhattan();
						boardCopy.setHamming();
						neighbors.add(boardCopy);
				}
				catch (IndexOutOfBoundsException e) {}
				return neighbors;
		}

		public static void main(String[] args) {
				In in = new In(args[0]);
				int n = in.readInt();
				int[][] tiles = new int[n][n];
				for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								tiles[i][j] = in.readInt();
						}				
				}
				Board test = new Board(tiles);
				System.out.println(test.toString());
				System.out.println(test.isGoal());
				System.out.println(test.hamming());
				System.out.println(test.manhattan());
				System.out.println(test.twin().toString());
				System.out.println(test.twin().hamming());
				for (Board x : test.neighbors()) {
						System.out.println(x.toString());
						System.out.println(x.manhattan());
						System.out.println(x.hamming());
				}
		}

		private int findPosition(int row, int col) {
				return row * dimension + col + 1;
		}

		private int[] findCell(int num) {
				int[] position = new int[2];
				position[0] = (num - 1) / dimension;
				position[1] = (num + dimension - 1) % dimension;
				return position;
		}
}
