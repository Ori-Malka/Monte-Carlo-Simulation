package Monte-Carlo-Simulation;

import java.util.ArrayList;
import java.util.List;

/*				~~~~~~~~CAMP~~~~~~~
 Camp class, is the terrorists camp we are attacking. 
 Camp contains: 
 1.Name - to help us distinguish between different camps (for future use).
 2.Center - a center point of the camp as described in the assignment.
 3.Buildings list - list of buildings (Inner class). 
 */
public class Camp {
	private String name;
	private Point center;
	private List<Building> l;

	/*
	 * ~~~~~~~~Building~~~~~~~ 
	 * Building class, is a building inside a camp. 
	 * Building contains: 
	 * 1.Name - to help us distinguish between different buildings (used in equals). 
	 * 2.Center - a center point of a building as described in the assignment (used in equals).
	 * **Two buildings are the same if they have the same name and center.
	 * 3.length, width - to define the size, as described in the assignment. 
	 * 4.Ymin, Ymax, Xmin, Xmax - used to determine if a point is within the building (these are the boundaries).
	 */
	private class Building {
		private Point center;
		private String name;
		private int length, width;
		private int Ymin, Ymax, Xmin, Xmax;

		/*
		 * The constructor set the name, center, width and length. also calculate the
		 * boundaries.
		 */
		public Building(String name, Point center, int width, int length) {
			this.name = name;
			this.center = center;
			this.length = length;
			this.width = width;

			// calculate the boundaries
			Xmin = center.getX() - width / 2;
			Xmax = center.getX() + width / 2;
			Ymin = center.getY() - length / 2;
			Ymax = center.getY() + length / 2;

		}

		/*
		 * getCenter() - returns the building center(Point).
		 */
		public Point getCenter() {
			return center;
		}

		/*
		 * getName() - returns the building name.
		 */
		public String getName() {
			return name;
		}

		//can use in the future.
		@SuppressWarnings("unused")
		public int getLength() {
			return length;
		}

		@SuppressWarnings("unused")
		public int getWidth() {
			return width;
		}

		/*
		 * isInside(Point p) - returns if a Point p is inside the building.
		 */
		public boolean isInside(Point p) {
			int X = p.getX(), Y = p.getY();
			if ((Ymin <= Y && Ymax >= Y) && (Xmin <= X && Xmax >= X))
				return true;

			return false;
		}

		/*
		 * equals(Object other) - help us determine if a to buildings are equal (same
		 * name and center).
		 */
		@Override
		public boolean equals(Object other) {
			if (!(other instanceof Building))
				return false;
			Building o = (Building) other;
			return getName().equals(o.getName()) && getCenter().equals(o.getCenter());
		}

		/*
		 * toString() - returns a building string in this format: [B.name(Center x,
		 * Center y)]
		 */
		public String toString() {
			return ("[" + name + getCenter().toString() + "]");
		}

	}

	/*
	 * The constructor set the name and center of the camp and creates a new list.
	 */
	public Camp(String name, Point center) {
		this.name = name;
		this.center = center;
		l = new ArrayList<>();
	}

	/*
	 * getName() - returns the camp name;
	 */
	public String getName() {
		return name;
	}

	/*
	 * getCenter() - returns the camp center (Point);
	 */
	public Point getCenter() {
		return center;
	}

	/*
	 * addBuilding() - adds a new building to the camp if its not already exists.
	 */
	public void addBuilding(String name, Point center, int width, int length) {
		Building b = new Building(name, center, width, length);
		if (!l.contains(b))
			l.add(b);
		else
			System.out.println("Building:" + b.toString() + " already exists!\n");
	}

	/*
	 * removeBuilding() - removes an existed building from the camp.
	 */
	public void removeBuilding(String name, Point center) {
		Building b = new Building(name, center, 0, 0);
		if (!l.contains(b))
			System.out.println("Building:" + b.toString() + " doesnt exists!\n");
		else
			l.remove(b);
	}

	/*
	 * checkIfHitsAll(Point[] hits) - gets an array of Points. returns true if in
	 * this array there is at least one point inside each one of the buildings
	 * of the camp.
	 * 
	 * Algorithm:
	 * 1.creates a copy of the buildings list. 
	 * 2.check if the current point is inside one (or more) of the camp buildings (saves the buildings that match in the "remove" list).
	 * **(one point can be in more then one building when buildings share the same wall - my assumption).
	 * 3.remove the buildings in the "remove" list from the copy of the buildings list (created in 1).
	 * 4.check if the buildings list is empty.
	 * 	 if empty return true. 
	 * 5.go back to 2 while hits is not empty. 
	 * 6.return false
	 * 
	 */

	public boolean checkIfHitsAll(Point[] hits) {
		List<Building> buildings = new ArrayList<>(l);
		for (Point hit : hits) {
			List<Building> remove = new ArrayList<>();
			for (Building b : buildings) {
				if (b.isInside(hit))
					remove.add(b);
			}
			buildings.removeAll(remove);
			if (buildings.isEmpty())
				return true;
		}
		return false;

	}
}
