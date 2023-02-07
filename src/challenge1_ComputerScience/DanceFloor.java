package challenge1_ComputerScience;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class DanceFloor {
	
	private int[][] danceFloorPeople;
	private int[][] lengthPaths;
	private String[][] stringPaths;
	private int danceFloorLength;
	private int longestPath;
	private String longestStringPath;
	
	public DanceFloor(String filePath){
		setDanceFloorPeople(filePath);	
		setAgePaths();
		setMaxValues();	
    }
	
	private void setDanceFloorPeople(String filePath) {
		int rows = -1;
		try {
		      File textFile = new File(filePath);
		      Scanner myReader = new Scanner(textFile);
		      while (myReader.hasNextLine()) {
		    	if(rows == -1) {
		    		danceFloorLength = Integer.parseInt(myReader.nextLine());
		    		danceFloorPeople = new int[danceFloorLength][danceFloorLength];
		    	}
		    	else {
		    		 String fileData = myReader.nextLine();
		    		 String[] fileIntegers = fileData.split(" ");
		    		 for(int columns = 0; columns < danceFloorLength; columns++) {
		    			 danceFloorPeople[rows][columns] = Integer.parseInt(fileIntegers[columns]);
		    	      }
		    	}
		    	rows++;
		      }
		      myReader.close(); 
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");		      
		    }		
	}

	private void setAgePaths() {
		lengthPaths = new int[danceFloorLength][danceFloorLength];
		stringPaths = new String[danceFloorLength][danceFloorLength];
		for (int rows = danceFloorLength - 1; rows >= 0; rows--) {
			for (int columns = danceFloorLength - 1; columns >= 0; columns--) {				
				lengthPaths[rows][columns] = 1;
				int right = 0;
				int down = 0;
				try {
					if(danceFloorPeople[rows][columns] == danceFloorPeople[rows][columns + 1] + 1 || danceFloorPeople[rows][columns] == danceFloorPeople[rows][columns + 1] - 1 ) {
						right = lengthPaths[rows][columns + 1];
					}
				}catch(ArrayIndexOutOfBoundsException e){
					right = 0;
				}
				try {
					if(danceFloorPeople[rows][columns] == danceFloorPeople[rows + 1][columns] + 1 || danceFloorPeople[rows][columns] == danceFloorPeople[rows + 1][columns] - 1 ) {
						down = lengthPaths[rows + 1][columns];
					}
				}catch(ArrayIndexOutOfBoundsException e){
					down = 0;
				}
				if(right > down) {
					lengthPaths[rows][columns] += lengthPaths[rows][columns + 1];
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]) + " - " + stringPaths[rows][columns + 1];
				}
				else if(down > right) {
					lengthPaths[rows][columns] += lengthPaths[rows + 1][columns];
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]) + " - " + stringPaths[rows + 1][columns];
				}
				else if(down == right && down + right != 0){
					lengthPaths[rows][columns] += lengthPaths[rows][columns + 1];
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]) + " - " + stringPaths[rows][columns + 1];
				}
				else {
					stringPaths[rows][columns] = Integer.toString(danceFloorPeople[rows][columns]);
				}		
			}
		}
	}

	private void setMaxValues() {
		longestPath = 1;
		for(int rows = 0; rows < danceFloorLength - 1; rows++) {
			for(int columns = 0; columns < danceFloorLength - 1; columns++) {
				int currentPath = lengthPaths[rows][columns];
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
