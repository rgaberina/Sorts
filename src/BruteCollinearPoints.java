import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 */

/**
 * @author berina
 *
 */
public class BruteCollinearPoints {
	
	private ArrayList<LineSegment> lines;
	
	/* finds all line segments containing 4 points */
	public BruteCollinearPoints(Point[] points) {
		checkPoints(points);
		Point[] points2 = copyPointArray(points);
		Arrays.sort(points2);
		lines = new ArrayList<>();
		for (int i = 0; i < points2.length; i++) {
			for (int j = i+1; j < points2.length; j++) {
				for (int k = j+1; k < points2.length; k++) {
					for (int l = k+1; l < points2.length; l++) {
						if (sameLine(points2[i], points2[j], points2[k], points2[l]))
						{
							LineSegment line = new LineSegment(points2[i], points2[l]);
							lines.add(line);
						}
					}
				}
			}
		}
	}
	
	private Point[] copyPointArray(Point[] points) {
		Point[] newPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			newPoints[i] = points[i];
		return newPoints;
	}
	
	private void checkPoints(Point[] points) {
		if (points == null)
			throw new IllegalArgumentException();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new IllegalArgumentException();
			for (int j = i+1; j < points.length; j++) {
				 if (points[j] == null)
					throw new IllegalArgumentException();
				if (points[i].compareTo(points[j]) == 0)
					throw new IllegalArgumentException();
			}
		}
	}
	
	private boolean sameLine(Point a, Point b, Point c, Point d) {
		double slope1 = a.slopeTo(b);
		double slope2 = a.slopeTo(c);
		double slope3 = a.slopeTo(d);
		if (slope1 == slope2 && slope2 == slope3) 
			return true;
		return false;
	}

	/* the number of line segments */
	public int numberOfSegments() {
		return lines.size();
	}

	/* the line segments */
	public LineSegment[] segments() {
		LineSegment[] temp = new LineSegment[lines.size()];
		return lines.toArray(temp);
	}
	
	public static void main(String[] args) {
		// read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
