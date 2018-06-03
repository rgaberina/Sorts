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
public class FastCollinearPoints {

	private ArrayList<LineSegment> lines;

	/** finds all line segments containing 4 or more points */
	public FastCollinearPoints(Point[] points) {
		checkPoints(points);
		Point[] copy = copyPointArray(points);
		Arrays.sort(copy);
		lines = new ArrayList<>();
		Point[] tempPoints = copyPointArray(copy);
		ArrayList<Double> slopes = new ArrayList<>();
		ArrayList<String> linePoints = new ArrayList<>();
		ArrayList<ArrayList<Point>> pointsOfLines = new ArrayList<>();
		for(int i = 0; i < copy.length; i++) {
			Point refPoint = copy[i];
			Arrays.sort(tempPoints, refPoint.slopeOrder());
			double prevSlope = Double.NEGATIVE_INFINITY;
			int count = 0;
			ArrayList<Point> temp = new ArrayList<>();
			double slope = 0.0;
			for (int j = 0; j < tempPoints.length; j++) {
				slope = refPoint.slopeTo(tempPoints[j]);
				if (slope == prevSlope) {
					count++;
					temp.add(tempPoints[j]);
				} else {
					if (count >= 3) {
						temp.sort(null);
						String lineP = temp.get(0) + "," + temp.get(temp.size()-1);
						if(!linePoints.contains(lineP)) {
							pointsOfLines.add(temp);
							slopes.add(slope);
							linePoints.add(lineP);
						}
					}
					count = 1;
					prevSlope = slope;
					temp = new ArrayList<>();
					temp.add(tempPoints[j]);
					temp.add(refPoint);
				}
			}
			if (count >= 3) {
				temp.sort(null);
				String lineP = temp.get(0) + "," + temp.get(temp.size()-1);
				if(!linePoints.contains(lineP)) {
					pointsOfLines.add(temp);
					slopes.add(slope);
					linePoints.add(lineP);
				}
			}
		}
		for (ArrayList<Point> arrayList : pointsOfLines) {
			LineSegment line = new LineSegment(arrayList.get(0), arrayList.get(arrayList.size()-1));
			lines.add(line);
		}
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

	private Point[] copyPointArray(Point[] points) {
		Point[] newPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			newPoints[i] = points[i];
		return newPoints;
	}

	/** the number of line segments */
	public int numberOfSegments() {
		return lines.size();
	}

	/** the line segments */
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
