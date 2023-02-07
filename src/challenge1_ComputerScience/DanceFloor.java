package challenge1_ComputerScience;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 


public class DanceFloor {
	
	private int[][] danceFloorPeople;
	
	private int danceFloorLength;
	
	public DanceFloor(String filePath){
     
		setDanceFloorPeople(filePath);
		
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
		    		 String[] fileIntegers = fileData.split("	");
		    		 for( int columns = 0; columns < danceFloorLength; columns++) {
		    			 danceFloorPeople[rows][columns] = Integer.parseInt(fileIntegers[columns]);
		    	      }
		    	}
		    	rows++;
		      }
		      myReader.close(); 
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }		
	}


}
