# 8 puzzle

In this project I used the A\* search algorithm to solve the [8 puzzle](https://en.wikipedia.org/wiki/15_puzzle) and its n x n generalization. The Board class implements the Board and provides methods for scoring boards and iterating through neighboring boards. Solver uses the provided [MinPQ](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MinPQ.html) class to implement the game tree and A\* search algorithm. It also detects unsolvable boards and all operations run in quadratic time. Boards are scored according to total vertical and horizontal distances from the tiles to their goal positions.


# Running the Solver

- java -jar Solver.jar inputFile.txt

The input file contains the board size n, followed by the n-by-n grid of tiles, using 0 to designate the blank square. Sample boards can be found in the TestBoards folder.

The output will look like:

|  |  |
| -- | -- |
| 	4 |  (the minimum number of moves to solve the puzzle)  |
| 3 |  (the size of the board -- n)  |
| 0 1 3 | (Board \#1) | 
| 4 2 5 | |    
| 7 8 6 | |   
| 3  | (the size of the board) 
| 1 0 3 |  (Board \#2)  |
| 4 2 5 | |  
| 7 8 6 | |  
| 3 | (etc) |
| 1 2 3 | (etc) |  
| 4 0 5 | |  
| 7 8 6 | |  
| 3 | 
| 1 2 3 | |  
| 4 5 0 | |  
| 7 8 6 | |  
| 3 | 
| 1 2 3 | (the solution) |  
| 4 5 6 | |  
| 7 8 0 | |  
