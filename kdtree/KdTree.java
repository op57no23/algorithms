import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

public class KdTree {
		private Node root;
		private int size;

		private static class Node {
				private Node leftChild;
				private Node rightChild;
				private Point2D p;
				private RectHV rect;

				public Node(Point2D p, RectHV rect) {
						this.p = p;
						this.rect = rect;
				}
		}
		// construct an empty set of points 
		public         KdTree(){
				size = 0;
		}
		// is the set empty? 
		public           boolean isEmpty(){
				return root == null;
		}                      

		// number of points in the set 
		public               int size(){
				return size;	
		}                         

		// add the point to the set (if it is not already in the set). True if comparing x coordinates, false if comparing y. 
		public              void insert(Point2D p){
				if (p == null) throw new IllegalArgumentException("calls insert() with a null key");
				if (contains(p)) return;
				if (size() == 0) {
					root = new Node(p, new RectHV(0,0,1,1));
					size += 1;
					return;
				}
				Node parent = null;
				Node current = root;
				boolean bool = true;
				while (current != null) {
					if (bool == true) {
						int cmp = Point2D.X_ORDER.compare(p, current.p);
						if      (cmp < 0) {
							parent = current;
							current = current.leftChild;
						}
						else { 
							parent = current;
							current = current.rightChild;
						}
					}
					else {
						int cmp = Point2D.Y_ORDER.compare(p, current.p);
						if (cmp < 0) {
							parent = current;
							current = current.leftChild;
						}
						else {
							parent = current;
							current = current.rightChild;
						}
					}
					bool = !bool;
				}
				if (bool) {
					int cmp = Point2D.Y_ORDER.compare(p, parent.p);
					if (cmp < 0) {
						RectHV new_rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y()); 
						parent.leftChild = new Node(p, new_rect);
					}
					else {
						RectHV new_rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax()); 
						parent.rightChild = new Node(p, new_rect);
					}
				}
				else {
					int cmp = Point2D.X_ORDER.compare(p, parent.p);
					if (cmp < 0) {
						RectHV new_rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
						parent.leftChild = new Node (p, new_rect);
					}
					else {
						RectHV new_rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
						parent.rightChild = new Node(p, new_rect);
					}
				}
				size += 1;
		}

		// does the set contain point p? 
		public           boolean contains(Point2D p){
				Node current = root;
				boolean bool = true;
				if (p == null) throw new IllegalArgumentException("calls contains() with a null key");
				while (current != null) {
						if (bool) {
								int cmp = Point2D.X_ORDER.compare(p, current.p);
								if      (cmp < 0) {
										current = current.leftChild;
										bool = !bool;
								}
								else if (cmp > 0) {
										current = current.rightChild;
										bool = !bool;
								}
								else if (cmp == 0) {
										if (current.p.compareTo(p) == 0) return true;
										else {
												current = current.rightChild;
												bool = !bool;
										}
								}
						}
						else {
								int cmp = Point2D.Y_ORDER.compare(p, current.p);
								if      (cmp < 0) {
										current = current.leftChild;
										bool = !bool;
								}
								else if (cmp > 0) {
										current = current.rightChild;
										bool = !bool;
								}
								else if (cmp == 0) {
										if (current.p.compareTo(p) == 0) return true;
										else {
												current = current.rightChild;
												bool = !bool;
										}
								}

						}

				}
				return false;
		}

		public              void draw(){
				draw(root, true);
		}                        

		private void draw(Node current,	boolean bool) {
				if (current == null) return;
				StdDraw.setPenRadius(0.001);
				if (bool) {
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.line(current.p.x(), current.rect.ymin(), current.p.x(), current.rect.ymax());
				}
				else {
						StdDraw.setPenColor(StdDraw.BLUE);
						StdDraw.line(current.rect.xmin(), current.p.y(), current.rect.xmax(), current.p.y());
				}
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.point(current.p.x(), current.p.y());
				draw(current.leftChild, !bool);
				draw(current.rightChild, !bool);
		}
		// all points that are inside the rectangle (or on the boundary) 
		public Iterable<Point2D> range(RectHV rect){
			    if (isEmpty()) return null;
				if (rect == null) throw new IllegalArgumentException("argmuent to range is null");
				ArrayList<Point2D> pointsRect = new ArrayList<Point2D>();
				pointsRect.addAll(range_helper(root, rect));
				return pointsRect;
		} 

		private ArrayList<Point2D> range_helper(Node current, RectHV rect) {
				ArrayList<Point2D> pointsRect = new ArrayList<Point2D>();
				if (current.rect.intersects(rect)) {
						if (rect.contains(current.p)) {
								pointsRect.add(current.p);
						}
						if (current.leftChild != null) pointsRect.addAll(range_helper(current.leftChild, rect));

						if (current.rightChild != null) pointsRect.addAll(range_helper(current.rightChild, rect));
				}
				return pointsRect;
		}

		// a nearest neighbor in the set to point p; null if the set is empty 
		public           Point2D nearest(Point2D p) {
				if (p == null) throw new IllegalArgumentException("argmuent to nearest is null");
				if (isEmpty()) return null;
				Point2D closest = nearestHelper(root, root.p, p);
				return closest;
		}   

		private Point2D nearestHelper(Node current, Point2D closest, Point2D p) {
				double distance = closest.distanceSquaredTo(p);
				boolean a = (current.leftChild == null);
				boolean b = (current.rightChild == null);
				
				if (a && b) return closest;
				else if (a && !b) {
						if (current.rightChild.rect.distanceSquaredTo(p) < distance) {
								if (current.rightChild.p.distanceSquaredTo(p) < distance) {
										closest = current.rightChild.p;
								}
								closest = nearestHelper(current.rightChild, closest, p);
								distance = closest.distanceSquaredTo(p);
						}
				}
				else if (b && !a) {
						if (current.leftChild.rect.distanceSquaredTo(p) < distance) {
								if (current.leftChild.p.distanceSquaredTo(p) < distance) {
										closest = current.leftChild.p;
								}
								closest = nearestHelper(current.leftChild, closest, p);
								distance = closest.distanceSquaredTo(p);
						}
				}
				else {
					double c = current.leftChild.rect.distanceSquaredTo(p);
					double d = current.rightChild.rect.distanceSquaredTo(p);
						if (c < d && c < distance) {
								if (current.leftChild.p.distanceSquaredTo(p) < distance) {
										closest = current.leftChild.p;
								}
								closest = nearestHelper(current.leftChild, closest, p);
								distance = closest.distanceSquaredTo(p);
								if (d < distance) {
										if (current.rightChild.p.distanceSquaredTo(p) < distance) {
												closest = current.rightChild.p;
										}
										closest = nearestHelper(current.rightChild, closest, p);
										distance = closest.distanceSquaredTo(p);
								}
						}
						else if (d <= c && d < distance) {
								if (current.rightChild.p.distanceSquaredTo(p) < distance) {
										closest = current.rightChild.p;
								}
								closest = nearestHelper(current.rightChild, closest, p);
								distance = closest.distanceSquaredTo(p);
								if (c < distance) {
										if (current.leftChild.p.distanceSquaredTo(p) < distance) {
												closest = current.leftChild.p;
										}
										closest = nearestHelper(current.leftChild, closest, p);
										distance = closest.distanceSquaredTo(p);
								}
						}
				}
				return closest;
		}

		// unit testing of the methods (optional) 
		public static void main(String[] args){
				KdTree test = new KdTree();
				In in = new In(args[0]);
				while (!in.isEmpty()) {
						double d1 = in.readDouble();
						double d2 = in.readDouble();
						Point2D point2D = new Point2D(d1, d2);
						test.insert(point2D);
				} 
				test.draw();
		}                  

}
