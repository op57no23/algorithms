# Collinear Points

**The problem**. Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.

## My Code

The Point class has a method for computing the slope to another point and a Comparator which compares the argument points by the slope they make with the invoking point. 

BruteCollinear first generates all combinations of 4 points and then iterates through the groups, checking if they all have the same slope relative to one point in the group. Its runtime is n^4 because of the combination generation. 

FastCollinear iterates through each point but for each point sorts the other points using the slopeOrder comparator. It then iterates through the ordered points and creates segments from the adjacent sorted points that have equal slopes. It runs in n^2 log n time. It uses the built-in sort.

## Running

1. Download the jar files and inputExamples
2. java -jar fast.jar inputFile.txt

brute.jar will fail for large input files because of its quartic runtime. 



