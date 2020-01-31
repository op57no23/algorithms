# KD-Trees

## The problem: 

Write a data type to represent a set of points in the unit square (all points have x- and y-coordinates between 0 and 1) using a 2d-tree to support efficient range search (find all of the points contained in a query rectangle) and nearest-neighbor search (find a closest point to a query point).

## Givens:

[Point2d](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Point2D.html)

[RectHV](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/RectHV.html)

Java's tree set for BST implementation in PointSet.

## My code

### PointSET

The brute force solution to the nearest-neighbor and range search problems. Uses java.util.TreeSet as underlying data structure. Iterates through all points to find nearest and to determine if they are contained in the rectangle.

### KdTree

Implementation of a 2d [kd-tree](https://en.wikipedia.org/wiki/K-d_tree) data type, which has the same API as PointSET and efficiently solves the problem of nearest-neighbor and range search. Assuming reasonable balance, nearest-neighbor is solved in log(n).

## Test clients of KdTree

1. Download jar files and sample inputs from inputPoints folder
2. Run:

- java -jar kd.jar inputFile.txt
	- Draws the kd tree of the input file.
- java -jar nearest.jar inputFile.txt
	- Highlights the nearest-neighbor to the mouse cursor.
- java -jar range.jar inputFile.txt
	- Highlights the points contained in the box dragged by the mouse

