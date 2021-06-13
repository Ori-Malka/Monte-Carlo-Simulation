package Monte-Carlo-Simulation;

public class Simulation {
	/*				~~~~~~~~Simulation~~~~~~~
	 Simulation class, used to create new Monte-Carlo terrorists camp attack simulation.
	 Simulation contains: 
	 1.C - the camp need to attack.
	 2.aimSD - the standard division accuracy when aimed to the center of the base (initial).
	 3.shellSD - the standard division accuracy of a shell.
	 4.shellQuantity - amount of shells need to be fired.
	 */
	private Camp C;
	private int aimSD, shellSD, shellQuantity;
	
	/*
	 * The constructor set the camp, shell quantity, aim SD and the shell SD.
	 */
	public Simulation(Camp C, int shellQuantity, int aimSD, int shellSD) {
		this.C = C;
		this.shellQuantity = shellQuantity;
		this.aimSD = aimSD;
		this.shellSD = shellSD;
	}
	
	/*
	 * changeCamp() - change the attacked camp.
	 */
	public void changeCamp(Camp C) {
		this.C = C;
	}
	
	/*
	 * changeAimSD() - change the aimSD.
	 */
	public void changeAimSD(int aimSD) {
		this.aimSD = aimSD;
	}
	
	/*
	 * changeShellSD() - change the shellSD.
	 */
	public void changeShellSD(int shellSD) {
		this.shellSD = shellSD;
	}
	
	/*
	 * changeShellQuantity() - change the shell quantity.
	 */
	public void changeShellQuantity(int shellQuantity) {
		this.shellQuantity = shellQuantity;
	}
	
	/*
	 * newMonteCarlo(int scenarios) - creates a new Monte-Carlo run using the simulation details. 
	 * Successe rate calculated = counter/scenarios.
	 */

	public void newMonteCarlo(int scenarios) {
		int counter = 0;
		double ratio;

		for (int i = 0; i < scenarios; i++) {
			if (CampAttack())
				counter++;
		}
		ratio = (double) counter / scenarios * 100;
		System.out.println(toString());
		System.out.printf("# of Scenarios: %d\nResult: %.2f%%\n", scenarios, ratio);
	}
	
	/*
	 * CampAttack() - creates new Monte-Carlo scenario as described in the assignment:
	 * 1.Generate random CPI (Common Point of Impact) - once (SD = aimSD).
	 * 2.Generate shell hits around the CPI - shellQuantity times (SD = shellSD).
	 * 3.Check which building were attacked (inside "checkIfHitsAll) and returns true if all the buildings were attacked at least one time.
	 */
	private boolean CampAttack() {
		Point[] hits = new Point[shellQuantity];
		Point CPI = C.getCenter().randGaussianPoint(aimSD);
	
		for (int i = 0; i < shellQuantity; i++) 
			hits[i] = CPI.randGaussianPoint(shellSD);
			
		return C.checkIfHitsAll(hits);

	}
	/*
	 * toString() - returns the simulation detail in a string in this format: 
	 * Simulation details:
		# of Shells: X
		aimSD: Y
		shellSD: Z
	 */
	public String toString() {
		return "\nSimulation details:\n# of Shells: "+shellQuantity+"\naimSD: "+aimSD+""
				+ "\nshellSD: "+shellSD;
	}
	
	/*~~~~~Demo~~~~~*/
	public static void main(String[] args) {
		int scenarios = 1000, shellQuantity = 500, aimSD = 100, shellSD = 50;
		System.out.println("~~~~Intro example to show the functionallity of this program~~~~\n");
		System.out.println("Lets create the Camp from the assigment!");
		System.out.println("The camp name will be 'Base' and the center is (0,0) as given.\n");
		Point center = new Point(0, 0);
		Camp C = new Camp("Base", center);
		System.out.println("The camp has 3 buildings:");
		C.addBuilding("One", new Point(-50, 40), 20, 30); // #1
		C.addBuilding("Two", new Point(0, -30), 20, 30); // #2
		C.addBuilding("Three", new Point(50, 0), 20, 30); // #3
		System.out.println("Building #1 - [One,Center(-50,40)]");
		System.out.println("Building #2 - [Two,Center(0,-30)]");
		System.out.println("Building #3 - [Three,Center(50,0)]\n");
		System.out.println("the aim accuracy SD & shell accuracy SD set to be 100 and 50 as given. the shell quantity is set to 500.");
		System.out.println("Now we can create a new camp attack Monte-Carlo simulation!\n");
		Simulation S= new Simulation(C,shellQuantity, aimSD, shellSD);
		System.out.println("This the simulation toString: "+S.toString());
		System.out.println("Lets calculate the success ratio of running the simulation 1000 times using Simulation.newMonteCarlo(1000)!");
		S.newMonteCarlo(scenarios);
		System.out.println("\nNow, Lets see what happens what happen if we reduce the aimSD using Simulation.changeAimSD(50):");
		S.changeAimSD(50);
		S.newMonteCarlo(scenarios);
		System.out.println("\nAs we can see the success ration changed dramatically!");
		System.out.println("Hope you liked the demo, more details in the word file.");

	}
}
