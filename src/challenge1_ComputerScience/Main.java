package challenge1_ComputerScience;

public class Main {
	public static void main(String[] args) {
		
		boolean isOptimized = true;
		String textPath = "DanceFloor03.txt";
		DanceFloor endavaParty = new DanceFloor(textPath, isOptimized);
		System.out.print("Longest Endavans Line Dance is: ");
		System.out.println(endavaParty.getLongestStringPath());
		System.out.print("Length of Path is: ");
		System.out.println(endavaParty.getLongestPath());
	}
}
