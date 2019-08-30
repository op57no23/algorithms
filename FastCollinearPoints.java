import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {

		private ArrayList<LineSegment> lineSegments;
		private LineSegment[] segments;

		public FastCollinearPoints(Point[] points) {
				lineSegments = new ArrayList<LineSegment>();
				for (int j = 0; j < points.length; j++) {
						Point[] copyPoints = Arrays.copyOf(points, points.length);
						Arrays.sort(copyPoints, points[j].slopeOrder());
						int count = 2;
						for (int i = 2; i < copyPoints.length; i++) {
								boolean stillSegment = (copyPoints[i].slopeTo(copyPoints[0]) == copyPoints[i - 1].slopeTo(copyPoints[0]));
								if (stillSegment) { 
										count++;
								}
								if (count > 3 && !stillSegment) {
										Point[] segment = new Point[count];
										segment[0] = points[j];
										while (count > 1){
												segment[segment.length - count + 1] = copyPoints[i - count + 1];
												count--;
										}
										count = 2;
										lineSegments.add(createSegment(segment));
								}
								}
						segments = new LineSegment[0];
						segments = lineSegments.toArray(segments);
				}
		}

		public int numberOfSegments() {
				return segments.length;
		}

		public LineSegment[] segments() {
				return segments;
		}

		private LineSegment createSegment(Point[] points){
				Point min = points[0];
				Point max = points[0];
				for (int i = 1; i < points.length; i++){
						if (points[i].compareTo(min) < 0) min = points[i];
						if (points[i].compareTo(max) > 0) max = points[i];
				}
				LineSegment output = new LineSegment(min, max);
				return output;
		}

		public static void main(String[] args) {
				In in = new In(args[0]);
				int n = in.readInt();
				Point[] testpoints = new Point[n];
				for (int i = 0; i < n; i++) {
						int x = in.readInt();
						int y = in.readInt();
						testpoints[i] = new Point(x,y);
				}
				FastCollinearPoints test = new FastCollinearPoints(testpoints);
				for (LineSegment x : test.segments()) {
						System.out.println(x);
				}
		}
}
