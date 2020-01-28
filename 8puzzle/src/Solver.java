import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {
	private Node solution;

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null) throw new IllegalArgumentException();
		MinPQ<Node> priorityQueue;
		MinPQ<Node> priorityQueue1;
		boolean solved;
		priorityQueue = new MinPQ<Node>(2);
		priorityQueue1 = new MinPQ<Node>(2);
		Node initialNode = new Node(initial, 0, null);
		Node initialNode1 = new Node(initial.twin(), 0, null);
		priorityQueue.insert(initialNode);
		priorityQueue1.insert(initialNode1);
		solved = false;
		if (initialNode.board.isGoal()) {
			solved = true;
			solution = initialNode;
		}
		else if (initialNode1.board.isGoal()) {
			solved = true;
			solution = null;
		}
		while (!solved) {
			Node min = priorityQueue.delMin();
			Node min1 = priorityQueue1.delMin();
			if (min.board.isGoal())  {
				solved = true;
				solution = min;
			}
			else if (min1.board.isGoal()) {
				solution = null;
				break;
			}
			for (Node n : min.createChildren()) {
				priorityQueue.insert(n);
			}
			for (Node n : min1.createChildren()) {
				priorityQueue1.insert(n);
			}
		}
	}
	public boolean isSolvable() {
		return (solution != null);
	}

	public int moves() {
		if (!isSolvable()) return -1;
		int count = 0;
		Iterable<Board> solution = solution();
		for (Board b : solution) {
			count += 1;
		}
		return count - 1;
	}

	// sequence of boards in a shortest solution
	public Iterable<Board> solution() {
		if (!isSolvable()) return null;
		Node current = solution;
		ArrayList<Board> solutionList = new ArrayList<Board>();
		solutionList.add(current.board);
		while (current.previous != null) {
			solutionList.add(current.previous.board);
			current = current.previous;
		}
		Collections.reverse(solutionList);
		return solutionList;
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
		Board testBoard = new Board(tiles);
		Solver test = new Solver(testBoard);
		if (test.isSolvable()) {
			System.out.println(test.moves());
			for (Board b : test.solution()) {
				System.out.println(b);	
			}
		}
		else {
			System.out.println(test.moves());
			System.out.println(test.solution());
			System.out.println("no solution possible");
		}

	}



	private static class Node implements Comparable<Node> {
		private Board board;
		private int previousMoves;
		private Node previous;
		private int manhattan;

		public Node(Board b, int previousMoves, Node previous) {
			board = b;
			this.previousMoves = previousMoves;
			this.previous = previous;
			this.manhattan = b.manhattan();
		}

		public ArrayList<Node> createChildren() {
			Iterable<Board> neighbors = board.neighbors();
			ArrayList<Node> children = new ArrayList<Node>();
			for (Board b : neighbors) {
				try {
					if (b.equals(this.previous.board)) continue;
					Node n = new Node(b, this.previousMoves + 1, this);
					children.add(n);
				}
				catch (NullPointerException e) {
					Node n = new Node(b, this.previousMoves + 1, this);
					children.add(n);
				}
			}
			return children;
		}

		public int compareTo(Node n) {
			if (this.manhattan + this.previousMoves > n.manhattan + n.previousMoves) return 1;
			else if (this.manhattan + this.previousMoves < n.manhattan+ n.previousMoves) return -1;
			else return 0;
		}



	}
}
