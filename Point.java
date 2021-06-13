package Monte-Carlo-Simulation;

import java.util.Random;

/*				~~~~~~~~Point~~~~~~~
Point class, used to save x and y coordinate.
Point contains: 
1.x,y - integers. 
*/
public class Point {
	private int x, y;
	Random r = new Random();

	/*
	 * The constructor set x and y.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * getX() - returns x.
	 */
	public int getX() {
		return x;
	}

	/*
	 * getY() - returns y.
	 */
	public int getY() {
		return y;
	}

	/*
	 * toString() - returns string in this format: (x,y).
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/*
	 * randGaussianPoint(int SD) - returns random point when the SD is the given SD
	 * and the average is the current point (as asked in the assignment).
	 */
	public Point randGaussianPoint(int SD) {
		return new Point((int) (x + SD * r.nextGaussian()), (int) (y + SD * r.nextGaussian()));
	}

	/*
	 * public boolean equals(Object other) - returns true if x1=x2 and y1=y2.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Point))
			return false;
		Point o = (Point) other;
		return getX() == o.getX() && getY() == o.getY();
	}

}
