package challenge1_ComputerScience;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.Arrays;

public class DanceFloor {
	
	private int[][] danceFloorPeople;
	private int danceFloorLength;
	private int longestPath = 0;
	private String longestStringPath;
	
	public DanceFloor(String filePath, boolean isOptimized){
		setDanceFloorPeople(filePath);	
		if(isOptimized) {
			setLongestPath();
		}
		else {
			setLongestPathNotOptimal();
		}
    }
	
	private void setDanceFloorPeople(String filePath) {
		int rows = -1;
		try {
		      File textFile = new File(filePath);
		      Scanner reader = new Scanner(textFile);
		      while (reader.hasNextLine()) {
		    	if(rows == -1) {
		    		danceFloorLength = Integer.parseInt(reader.nextLine().replaceAll("\\s",""));
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
	
	private void setLongestPathNotOptimal() {
		for (int rows = 0 ; rows < danceFloorLength; rows++) {
			for (int columns = 0; columns < danceFloorLength; columns++) {				
				int[] currentPath = getOnePath(rows, columns);	
				if(currentPath.length > longestPath) {
					longestPath = currentPath.length;
					longestStringPath = Arrays.toString(currentPath);
				}
			}
		}
		longestStringPath = longestStringPath.replaceAll("\\[|\\]|,", "").replaceAll("\\s", " - ");
	}
	
	private int[] getOnePath(int rows, int columns) {
		int[] rightPath = {};
		int[] downPath = {};
		if (rows == danceFloorLength - 1 && columns == danceFloorLength - 1) {
			return new int[]{danceFloorPeople[rows][columns]};
		}
		else {	
			try {
				if(danceFloorPeople[rows][columns] == danceFloorPeople[rows][columns + 1] + 1 || danceFloorPeople[rows][columns] == danceFloorPeople[rows][columns + 1] - 1 ) {
					rightPath = getOnePath(rows, columns + 1);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				rightPath = new int[0];
			}
			try {
				if(danceFloorPeople[rows][columns] == danceFloorPeople[rows + 1][columns] + 1 || danceFloorPeople[rows][columns] == danceFloorPeople[rows + 1][columns] - 1 ) {
					downPath = getOnePath(rows + 1, columns);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				downPath = new int[0];
			}
			if(rightPath.length > downPath.length) {
				int[] finalPath = new int[1 + rightPath.length];
				System.arraycopy(new int[]{danceFloorPeople[rows][columns]}, 0, finalPath, 0, 1);  
				System.arraycopy(rightPath, 0, finalPath, 1, rightPath.length);
				return finalPath;	
			}
			else if(downPath.length > rightPath.length) {
				int[] finalPath = new int[1 + downPath.length];
				System.arraycopy(new int[]{danceFloorPeople[rows][columns]}, 0, finalPath, 0, 1);  
				System.arraycopy(downPath, 0, finalPath, 1, downPath.length);
				return finalPath;
			}
			else if(downPath.length == rightPath.length && downPath.length + rightPath.length != 0){
				int[] finalPath = new int[1 + rightPath.length];
				System.arraycopy(new int[]{danceFloorPeople[rows][columns]}, 0, finalPath, 0, 1);  
				System.arraycopy(rightPath, 0, finalPath, 1, rightPath.length);
				return finalPath;
			}
			else {
				return new int[]{danceFloorPeople[rows][columns]};	
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
