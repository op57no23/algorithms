import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

		private ArrayList<LineSegment> combos;
		private Point[] allPoints;
		private LineSegment[] segments;

		public BruteCollinearPoints(Point[] points){
				if (points == null) throw new IllegalArgumentException();
				containsRepeatPoint(points);
				allPoints = Arrays.copyOf(points, points.length);
				combos = new ArrayList<LineSegment>();
				generateCombos(4);
				segments = new LineSegment[0];
				segments = combos.toArray(segments);
		}

		public int numberOfSegments() {
				return segments.length;
		}

		public LineSegment[] segments(){
				return Arrays.copyOf(segments, segments.length);

		}

		private boolean checkSegment(Point[] points){
				if (points[0].slopeTo(points[1]) == points[0].slopeTo(points[2]) && points[0].slopeTo(points[1]) == points[0].slopeTo(points[3])) {
						return true;
				}
				return false;
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

		private void containsRepeatPoint(Point[] points){
				if (points[0] == null) throw new IllegalArgumentException();
				if (points.length == 1) return;
				for (int i = 0; i < points.length - 1; i++) {
						for (int j = i + 1; j < points.length; j++) {
								if (points[j] == null || points[j - 1] == null) throw new IllegalArgumentException();
								if (points[j].compareTo(points[i]) == 0) {
										throw new IllegalArgumentException();
								}
						}
				}

		}

		private void generateCombos(int k){
				Point[] combo = new Point[k];
				generateCombos(k, k, allPoints,  combo);
		}

		private void generateCombos(int k, int n, Point[] a, Point[] combo) {
				if (k == 0) {
						if (checkSegment(combo)){
								combos.add(createSegment(combo));
						}
				}
				else {
						for (int i = 0; i <= a.length - k; i++) {
								Point[] temp = Arrays.copyOf(combo,combo.length);
								temp[n - k] = a[i];
								generateCombos(k - 1, n, Arrays.copyOfRange(a, i + 1, a.length), temp);
						}
				}
		}
		public static void main(String[] args) {
				Point p1 = new Point(3,4);
				Point p2 = new Point(4,5);
				Point p3 = new Point(5,6);
				Point p4 = new Point(6,7);
				Point p5 = new Point(0,0);
				Point p6 = new Point(6,8);
				Point p7 = new Point(0,0);
				Point[] points = { p1, p2, p3, p4, p5, p6, p7 };
				BruteCollinearPoints test = new BruteCollinearPoints(points);
				for (LineSegment y : test.segments()){
				}
		}
}