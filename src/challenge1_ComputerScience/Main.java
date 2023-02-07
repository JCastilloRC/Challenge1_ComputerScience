package challenge1_ComputerScience;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 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

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Please enter path of text file");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String textPath = reader.readLine();
		DanceFloor endavaParty = new DanceFloor(textPath);
		System.out.print("Longest Endavans Line Dance is: ");
		System.out.println(endavaParty.getLongestStringPath());
		System.out.print("Length of Path is: ");
		System.out.println(endavaParty.getLongestPath());
	}
	
}
