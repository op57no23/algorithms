import java.io.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int[] allTrials;
	private int size;
	private double mean;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
		this.allTrials = new int[trials];
		this.size = n;
		for (int i = 0; i < trials; i++){
			allTrials[i] = runTrial(n);
			System.out.println(allTrials[i]);
		}
	}
	
	private int[] convertBack(int position, int size){
		int[] tuple = new int[2];
		tuple[0] = position / size + 1;
		tuple[1] = position % size + 1;
		return tuple;
	}

	private int runTrial(int n){
		Percolation perc = new Percolation(n);
		while (!perc.percolate()){
			int rand = StdRandom.uniform(0, n * n);
			int[] tuple = convertBack(rand, n);
			//System.out.println(tuple[0] + "," + tuple[1]);
			perc.open(tuple[0], tuple[1]);
		}
		return perc.numberOfOpenSites();
	}
    // sample mean of percolation threshold
    public double mean(){
			double sum = 0;
			for (int i = 0; i < allTrials.length; i++){
					sum += (double) allTrials[i] / (size * size);
			}
			mean = sum / allTrials.length;
			return sum / allTrials.length;
	}
	public static void main(String[] args){
			PercolationStats trial = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			trial.mean();
	}	
	
    // sample standard deviation of percolation threshold
    public double stddev(){

	}

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
	}

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
}
