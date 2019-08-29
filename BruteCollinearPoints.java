import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

		public ArrayList<Point[]> combos;
		private Point[] allPoints;

		public BruteCollinearPoints(Point[] points){
				allPoints = points;
				combos = new ArrayList<Point[]>();
		}

		public int numberOfSegments() {
				return 1;	
		}
		/*
		   public LineSegment[] segments(){


		   }
		 */

		public void generateCombos(int k){
				Point[] combo = new Point[k];
				generateCombos(k, k, allPoints,  combo);
		}

		private void generateCombos(int k, int n, Point[] a, Point[] combo) {
				if (k == 0) {
						combos.add(combo);
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
				Point p3 = new Point(5,9);
				Point p4 = new Point(0,2);
				Point p5 = new Point(0,0);
				Point[] points = { p1, p2, p3, p4, p5 };
				BruteCollinearPoints test = new BruteCollinearPoints(points);
				test.generateCombos(4);
				for (Point[] x : test.combos){
						System.out.println("new array");
						for (Point y : x){

								System.out.println(y);
						}
				}
				Point p6 = new Point(0,4); 
		}
}
