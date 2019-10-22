import java.io.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math.*;

public class PercolationStats {

	private double[] allTrials;
	private int size;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
		this.allTrials = new double[trials];
		this.size = n;
		for (int i = 0; i < trials; i++){
			allTrials[i] = runTrial(n);
		}
	}
	
	private int[] convertBack(int position, int size){
		int[] tuple = new int[2];
		tuple[0] = position / size + 1;
		tuple[1] = position % size + 1;
		return tuple;
	}

	private double runTrial(int n){
		Percolation perc = new Percolation(n);
		while (!perc.percolate()){
			int rand = StdRandom.uniform(0, n * n);
			int[] tuple = convertBack(rand, n);
			perc.open(tuple[0], tuple[1]);
		}
		return (double) perc.numberOfOpenSites() / (size * size);
	}
    // sample mean of percolation threshold
    public double mean(){
			return StdStats.mean(allTrials);
	}
	public static void main(String[] args){
			PercolationStats trial = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			System.out.format("%1$-25s = %2$f%n", "mean", trial.mean());
			System.out.format("%1$-25s = %2$f%n", "stddev", trial.stddev());
			System.out.format("%1$-25s = [%2$f , %3$f]%n","95% confidence interval",trial.confidenceLo(), trial.confidenceHi());
	}	
	
    // sample standard deviation of percolation threshold
    public double stddev(){
			return StdStats.stddev(allTrials);
	}

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
			return mean() - (1.96 * stddev()) / Math.sqrt(allTrials.length);
	}

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
			return mean() + (1.96 * stddev()) / Math.sqrt(allTrials.length);

	}
}
