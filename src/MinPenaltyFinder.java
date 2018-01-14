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
			
			//Read file once to fill matrix with base penalties
			while ((line = reader.readLine()) != null){
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
			}
			
			fileReader = new FileReader(fileName);
			reader = new BufferedReader(fileReader);
			//Read file second time to replace forbidden and and forced assignments
			while ((line = reader.readLine()) != null){					//Read next line and escape if end of file
				
				//System.out.print("test");
				
				if (line.equals("forced partial assignment:")){
					while (!(line = reader.readLine()).isEmpty()){		//If next line is not empty	
						int machIndex = charToIndex(line.charAt(1))-1;
						int taskIndex = charToIndex(line.charAt(3));
						
						int forcedPAValue = inputData.machinePenalties[machIndex][taskIndex]+10;	//10-19 indicates forced assignment
						
						//Disable relevant machine and task assignments
						for (int i=0; i <= 7; i++){
							inputData.machinePenalties[machIndex][i] = -1;
						}
						for (int j=0; j <= 7; j++){
							inputData.machinePenalties[j][taskIndex] = -1;
						}
						
						inputData.machinePenalties[machIndex][taskIndex] = forcedPAValue; //Force assignment
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
				
				if (line.equals("too-near penalities:")){
					line = reader.readLine();
					while (line != null && !(line.isEmpty())){		//If next line is not empty
						int [] tnp = {charToIndex(line.charAt(1))-1, charToIndex(line.charAt(3)), charToIndex(line.charAt(5))}; //ONLY READS ONE CHAR, NEED TO FIX
						inputData.tooNearTasks.add(tnp);
						line = reader.readLine();
					}
				}
			}
			
			fileReader.close();											//Close input file
		}
		
		catch (IOException e){
			System.out.print("File not found");
		}	
		
		//For testing
		System.out.println("Condensed Content:");
		for(int j=0 ; j <= 7; j++){
			for(int i=0 ; i <= 7; i++){
				if(0 <= inputData.machinePenalties[j][i] && inputData.machinePenalties[j][i] <= 9){
					System.out.print(" " + inputData.machinePenalties[j][i]+":");
				}
				else{
					System.out.print(inputData.machinePenalties[j][i]+":");
				}
			}
			System.out.println("");
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
	


