import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.ArrayList;

public class PointSET
{
		private TreeSet<Point2D> points;

		public PointSET() {
				points = new TreeSet<Point2D>();
		}

		public boolean isEmpty() { return this.points.isEmpty(); }

		public int size() { return this.points.size(); }

		public void insert(Point2D paramPoint2D) {
				if (paramPoint2D == null) throw new IllegalArgumentException(); 
				this.points.add(paramPoint2D);
		}

		public boolean contains(Point2D paramPoint2D) {
				if (paramPoint2D == null) throw new IllegalArgumentException(); 
				return this.points.contains(paramPoint2D);
		}

		public void draw() {
				Iterator<Point2D> iterator = this.points.iterator();
				while (iterator.hasNext()) {
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.setPenRadius(0.01);
						Point2D point2D = iterator.next();
						point2D.draw();
				} 
		}
		
		// all points that are inside the rectangle (or on the boundary) 
		public Iterable<Point2D> range(RectHV rect) {
			if (rect == null) throw new IllegalArgumentException();
			ArrayList<Point2D> points_rect = new ArrayList<Point2D>();
			Iterator<Point2D> iterator = this.points.iterator();
			while (iterator.hasNext()) {
				Point2D p = iterator.next();
				if (rect.contains(p)) points_rect.add(p);
			}
			return points_rect;
			
		}
		
		public Point2D nearest(Point2D p) {
			if (p == null) throw new IllegalArgumentException();
			if (isEmpty()) return null;
			else {
				Iterator<Point2D> iterator = this.points.iterator();
				Point2D closest = iterator.next();
				double distance = closest.distanceSquaredTo(p);
				while (iterator.hasNext()) {
					Point2D current = iterator.next();
					if (current.distanceSquaredTo(p) < distance) {
						closest = current;
						distance = current.distanceSquaredTo(p);
					}
				}
			return closest;
			}
		}

		public static void main(String[] paramArrayOfString) {
				PointSET pointSET = new PointSET();
				In in = new In(paramArrayOfString[0]);
				while (!in.isEmpty()) {
						double d1 = in.readDouble();
						double d2 = in.readDouble();
						Point2D point2D = new Point2D(d1, d2);
						pointSET.insert(point2D);
				} 
				pointSET.draw();
		}
}
