package challenge1_ComputerScience;

public class Main {

	public static void main(String[] args) {
		
		DanceFloor endavaParty = new DanceFloor("DanceFloor01.txt");
		System.out.print("Longest Endavans Line Dance is: ");
		System.out.println(endavaParty.getLongestStringPath());
		System.out.print("Length of Path is: ");
		System.out.println(endavaParty.getLongestPath());
		
	}
}
