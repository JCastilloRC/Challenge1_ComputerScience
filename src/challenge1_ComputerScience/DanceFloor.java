package challenge1_ComputerScience;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class DanceFloor {
	
	private int[][] danceFloorPeople;
	private int danceFloorLength;
	private int longestPath = 0;
	private String longestStringPath;
	
	public DanceFloor(String filePath){
		setDanceFloorPeople(filePath);	
		setLongestPath();
    }
	
	private void setDanceFloorPeople(String filePath) {
		int rows = -1;
		try {
		      File textFile = new File(filePath);
		      Scanner reader = new Scanner(textFile);
		      while (reader.hasNextLine()) {
		    	if(rows == -1) {
		    		danceFloorLength = Integer.parseInt(reader.nextLine());
		    		danceFloorPeople = new int[danceFloorLength][danceFloorLength];
		    	}
		    	else {
		    		 String fileData = reader.nextLine();
		    		 String[] fileIntegers = fileData.split(" ");
		    		 for(int columns = 0; columns < danceFloorLength; columns++) {
		    			 danceFloorPeople[rows][columns] = Integer.parseInt(fileIntegers[columns]);
		    	      }
		    	}
		    	rows++;
		      }
		      reader.close(); 
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");		      
		    }		
	}

	private void setLongestPath() {
		int [][]lengthPaths = new int[danceFloorLength][danceFloorLength];
		String[][]stringPaths = new String[danceFloorLength][danceFloorLength];
		for (int rows = danceFloorLength - 1; rows >= 0; rows--) {
			for (int columns = danceFloorLength - 1; columns >= 0; columns--) {				
				int currentPath = 0;
				int rightPath = 0;
				int downPath = 0;
				lengthPaths[rows][columns] = 1;
				try {
					if(danceFloorPeople[rows][columns] == danceFloorPeople[rows][columns + 1] + 1 || danceFloorPeople[rows][columns] == danceFloorPeople[rows][columns + 1] - 1 ) {
						rightPath = lengthPaths[rows][columns + 1];
					}
				}catch(ArrayIndexOutOfBoundsException e){
					rightPath = 0;
				}
				try {
					if(danceFloorPeople[rows][columns] == danceFloorPeople[rows + 1][columns] + 1 || danceFloorPeople[rows][columns] == danceFloorPeople[rows + 1][columns] - 1 ) {
						downPath = lengthPaths[rows + 1][columns];
					}
				}catch(ArrayIndexOutOfBoundsException e){
					downPath = 0;
				}
				if(rightPath > downPath) {
					lengthPaths[rows][columns] += lengthPaths[rows][columns + 1];
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]) + " - " + stringPaths[rows][columns + 1];
				}
				else if(downPath > rightPath) {
					lengthPaths[rows][columns] += lengthPaths[rows + 1][columns];
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]) + " - " + stringPaths[rows + 1][columns];
				}
				else if(downPath == rightPath && downPath + rightPath != 0){
					lengthPaths[rows][columns] += lengthPaths[rows][columns + 1];
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]) + " - " + stringPaths[rows][columns + 1];
				}
				else {
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]);
				}
			    currentPath = lengthPaths[rows][columns];
				if(currentPath > longestPath) {
					longestPath = currentPath;
					longestStringPath = stringPaths[rows][columns];
				}
			}
		}
	}

	public int getLongestPath() {
		return longestPath;
	}
	
	public String getLongestStringPath() {
		return longestStringPath;
	}
}
