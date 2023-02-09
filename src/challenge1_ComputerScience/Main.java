package challenge1_ComputerScience;

public class Main {
	public static void main(String[] args) {
		
		boolean isOptimized = false;
		String textPath = "DanceFloor01.txt";
		DanceFloor endavaParty = new DanceFloor(textPath, isOptimized);
		System.out.print("Longest Endavans Line Dance is: ");
		System.out.println(endavaParty.getLongestStringPath());
		System.out.print("Length of Path is: ");
		System.out.println(endavaParty.getLongestPath());
	}
}
