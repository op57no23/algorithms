import java.io.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int arraysize;
	private boolean[] openSites;
	private int size;
	private int countOpen;
	public WeightedQuickUnionUF grid;

    public Percolation(int n){
		if (n <= 0) {throw new IllegalArgumentException();}
		grid =	new WeightedQuickUnionUF(n*n + 2);
		arraysize = n*n + 2; 
		openSites = new boolean[n*n];
		countOpen = 0;
		size = n;
		for (int i = 1; i <= size; i++){
			grid.union(0,i);
			grid.union(arraysize - 1, arraysize - 1 - i);
		}
	}
	
	private int convert(int row, int col){
		return (row - 1) * size + col - 1;	
	}

	private void checkrange(int row, int col){
		if (row < 1 || row > size || col < 1 || col > size) {throw new IllegalArgumentException();}
	}

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
		checkrange(row, col);
		if (isFull(row, col)){
				int position = convert(row, col) + 1;
				try{
					if (isOpen(row, col - 1)) {grid.union(position, position - 1);}}
				catch(Exception e){;}
				try{
					if (isOpen(row, col + 1)) {grid.union(position, position + 1);}}
				catch(Exception e){;} 
				try{
					if (isOpen(row - 1, col)) {grid.union(position, position - size);}}
				catch(Exception e){;} 
				try{
					if (isOpen(row + 1, col)) {grid.union(position, position + size);}}
				catch(Exception e){;} 
				countOpen++;
				openSites[position - 1] = true;
		}
	}

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
		checkrange(row, col);
		return openSites[convert(row, col)];
	}

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
		checkrange(row, col);
		return !isOpen(row, col);
	}

    // returns the number of open sites
    public int numberOfOpenSites(){
		return countOpen;
	}
	
	public boolean percolate(){
		return grid.connected(0, arraysize - 1);
	}
	public static void main(String[] args){
		Percolation perc = new Percolation(5);
	    perc.open(1,1);
		perc.open(2,1);
		perc.open(3,1);
		perc.open(4,1);
		perc.open(5,1);
		System.out.println(perc.isOpen(6,43));
		System.out.println(perc.percolate());
		System.out.println(perc.numberOfOpenSites());
	}
}
