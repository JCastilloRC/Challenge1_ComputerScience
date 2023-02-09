package challenge1_ComputerScience;

public class Main {
	public static void main(String[] args) {
		
		boolean isOptimized = true;
		String textPath = "DanceFloor03.txt";
		
		long initialTime = System.nanoTime();
		DanceFloor endavaParty = new DanceFloor(textPath, isOptimized);
		long finalTime = System.nanoTime();
		System.out.print("Longest Endavans Line Dance is: ");
		System.out.println(endavaParty.getLongestStringPath());
		System.out.print("Length of Path is: ");
		System.out.println(endavaParty.getLongestPath());
		System.out.print("Elapsed Time is: ");
		System.out.println(finalTime - initialTime);
	}
}
