# Percolation

## Project Specification 

**Percolation**. Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

**The model**. We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

![percolates](https://github.com/op57no23/algorithms/blob/master/percolation/images/percolates-yes.png?raw=true) 
![doesn't percolate](https://github.com/op57no23/algorithms/blob/master/percolation/images/percolates-no.png?raw=true)

**The problem**. In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. When n is sufficiently large, there is a threshold value p\* such that when p < p\* a random n-by-n grid almost never percolates, and when p > p\*, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p\* has yet been derived. Your task is to write a computer program to estimate p\*.

## My code

The Percolation class has the constructor for the grid and provides methods for opening squares and checking if the grid Percolates. It relies on the provided [WeightedUnionQuickFind class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html) to implement a union-find data structure. The PercolationStats class performs the Monte Carlo simulation by initializing a grid with all blocked squares and then randomly opening blocked squares until the sytem percolates. It records the fraction of sites that are open at that point and then computes the sample mean, sample standard deviation, and confidence interval for the percolation threshold.

## How to run

1. Download the Perc.jar file 
2. Run jar -xf Perc.jar
3. Run java PercolationStats n T 
	- performs T independent computational experiments (discussed below) on an n-by-n grid, and prints the sample mean, sample standard deviation, and the 95% confidence interval for the percolation threshold.


