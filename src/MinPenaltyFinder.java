import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MinPenaltyFinder {
	static int[] taskAssignments = new int[8];							//Order of task assignment from machine 1-8
	
	public static void main(String[] args) {	
		String fileName = "/Users/USER/Desktop/CPSC449Input.txt";	//Input file location and name
		ReadFile(fileName);											//Loads file into variables
	}
	
	//Reads file of expected format and saves values
	static public void ReadFile(String fileName){
		String line;
		dataArray inputData = new dataArray();
		
		try{
			FileReader fileReader = new FileReader(fileName);			//Open file for reading
			BufferedReader reader = new BufferedReader(fileReader);		//Buffer file
			
			while ((line = reader.readLine()) != null){					//Read next line and escape if end of file
				if (line.equals("forced partial assignment:")){
					while (!(line = reader.readLine()).isEmpty()){		//If next line is not empty
						inputData.machinePenalties[charToIndex(line.charAt(1))-1][charToIndex(line.charAt(3))] = 11; //Load values into array
					}
				}
				
				if (line.equals("forbidden machine:")){
					while (!(line = reader.readLine()).isEmpty()){		//If next line is not empty
						inputData.machinePenalties[charToIndex(line.charAt(1))-1][charToIndex(line.charAt(3))] = -1; //Load values into array
					}
				}
				
				if (line.equals("too-near tasks:")){
					while (!(line = reader.readLine()).isEmpty()){		//If next line is not empty
						int [] tnt = {charToIndex(line.charAt(1))-1,charToIndex(line.charAt(3))};
						inputData.tooNearTasks.add(tnt);	
					}
				}
				
				if (line.equals("machine penalties:")){
					
					for(int j=0 ; j <= 7; j++){
						line = reader.readLine();
						if(line.length() != 8){								//Check if correct number of penalties
							System.out.println("machine penalty error, length was: " + line.length());
							System.out.println("machine penalty error");
							System.exit(0);
						}
						
						for(int i=0 ; i <= 7; i++){
							inputData.machinePenalties[j][i] = charToIndex(line.charAt(i));	//Assign penalty values
						}	
					}
					
					if (!(line = reader.readLine()).isEmpty()){				//Check if more than 8 lines given
						System.out.println("machine penalty error, too many lines");
						System.out.println("machine penalty error");
						System.exit(0);
					}
				}
				
				if (line.equals("too-near penalities:")){
					while (line = reader.readLine() == null || !(line.isEmpty())){		//If next line is not empty
						int [] tnp = {charToIndex(line.charAt(1))-1,charToIndex(line.charAt(3)), charToIndex(line.charAt(5))}; //ONLY READS ONE CHAR, NEED TO FIX
						inputData.tooNearTasks.add(tnp);	
					}
				}
			}
			
			fileReader.close();											//Close input file
		}
		
		catch (IOException e){
			System.out.print("File not found");
		}	
		
		System.out.println("Condensed Content:");
		for(int j=0 ; j <= 7; j++){
			for(int i=0 ; i <= 7; i++){
				System.out.print(inputData.machinePenalties[j][i]+":");
			}
		}
		
	}	
	
	//Converts a char to the appropriate 0-index int 
	static int charToIndex (char character){
		String validAlph = "ABCDEFGH";
		String validNum = "0123456789";
		int returingValue;
		boolean characterIsNumb;
		
		returingValue = validNum.indexOf(character);							//Find index if in Num	
		if(returingValue == -1){returingValue = validAlph.indexOf(character);}	//If not found in Num, check Alph
		if(returingValue == -1){												//If value in neither, error
			System.out.println("Invalid input: " + character);
			System.exit(0);} 			
						
		return(returingValue);
	}
}
	


