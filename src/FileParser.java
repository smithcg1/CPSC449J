import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
		//Reads file of expected format and saves values
		static public void ReadFile(String fileName, dataArray inputData)
		{
			String line;
			
			for(int i=0; i<7; i++)
			{
				for(int j=0; j<7; j++)
				{
					inputData.machinePenalties[i][j] = -2;					//Set all error values to unassigned marker (-2)
				}
			}
			
			
			try
			{
				FileReader fileReader = new FileReader(fileName);			//Open file for reading
				BufferedReader reader = new BufferedReader(fileReader);		//Buffer file
			
				//Check name area
				line = reader.readLine();									
				if(!line.equals("Name:")){pError();}						//Line 1 must be "Name:"
				line = reader.readLine();
				if(line.equals("")){pError();}								//If name line is blank, error
				line = reader.readLine();
				if(!line.equals("")){pError();}								//If anything is below the name, error
				line = reader.readLine();
				
				//Check forced assignment area
				if(!line.equals("forced partial assignment:")){pError();}	//Line 1 must be "forced partial assignment:"
				line = reader.readLine();
				while(!line.equals(""))
				{
					if(line.charAt(0) != "(".charAt(0)){pError();}
					if(line.charAt(0) != "(".charAt(0)){pError();}
					if(line.charAt(0) != ",".charAt(0)){pError();}
					if(line.charAt(0) != "(".charAt(0)){pError();}
					if(line.charAt(0) != ")".charAt(0)){pError();}
				}
				line = reader.readLine();
				
				//Check forbidden area
				
			}
				
			catch (IOException e)
			{
				System.out.print("File not found");
			}	
		}
		
		//Converts an int to the appropriate 0-index int with error checking
		static int intToIndex (int integer){
			String validNum = "0123456789";
			
			if(1 > integer || integer < 8){pError();}
		
			return(integer);
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
		
		
		//All expected errors
		//If two forced pairs assign the same task or to the same machine
		static void paError()								//Partial assignment error
		{
			System.out.println("partial assignment error");
			System.exit(0);
		}
		
		//If more or less than 8 lines or if any line has too few (but not too many) numbers
		static void mpError()								//Machine penalty error
		{
			System.out.println("machine penalty error");
			System.exit(0);
		}
		
		//If any of the triples has a task not in {A,B,C,D,E,F,G,H}
		static void tnpError()								//Too-near penalty error
		{
			System.out.println("invalid task");
			System.exit(0);
		}
		
		//If input should be a natural number but it is not (soft constraints only)
		static void nnError()								//Natural number error
		{
			System.out.println("invalid penalty");
			System.exit(0);
		}
		
		//If a key word is missing, an invalid value is assigned, or file contains unspecified content
		static void pError()								//Parsing error
			{
				System.out.println("Error while parsing input file");
				System.exit(0);
			}
		
		
		
		
		/*
		while ((line = reader.readLine()) != null)
		{
			if (line.equals("machine penalties:"))
			{
				
				for(int j=0 ; j <= 7; j++)
				{
					line = reader.readLine();
					if(line.length() != 8)
					{								//Check if correct number of penalties
						System.out.println("machine penalty error, length was: " + line.length());
						System.out.println("machine penalty error");
						System.exit(0);
					}
					
					for(int i=0 ; i <= 7; i++)
					{
						inputData.machinePenalties[j][i] = charToIndex(line.charAt(i));	//Assign penalty values
					}	
				}
				
				if (!(line = reader.readLine()).isEmpty())
				{				//Check if more than 8 lines given
					System.out.println("machine penalty error, too many lines");
					System.out.println("machine penalty error");
					System.exit(0);
				}
			}
		}

		//Read file second time to replace forbidden and and forced assignments
		while ((line = reader.readLine()) != null){					//Read next line and escape if end of file
			
			if (line.equals("forced partial assignment:")){
				line = reader.readLine();
				while (line != null && !(line.isEmpty())){		//If next line is not empty	
					int [] fpa = {charToIndex(line.charAt(1))-1, charToIndex(line.charAt(3))};
					inputData.forcedPairs.add(fpa);
					
					//***May be able to delete from here***
                    int machIndex = charToIndex(line.charAt(1))-1;
                    int taskIndex = charToIndex(line.charAt(3));
                    
                    int forcedPAValue = inputData.machinePenalties[machIndex][taskIndex];    //10-19 indicates forced assignment
                    
                    //Disable relevant machine and task assignments
                    for (int i=0; i <= 7; i++){
                        inputData.machinePenalties[machIndex][i] = -1;
                    }
                    for (int j=0; j <= 7; j++){
                        inputData.machinePenalties[j][taskIndex] = -1;
                    }
                    
                    inputData.machinePenalties[machIndex][taskIndex] = forcedPAValue; //Force assignment
                    //***To here***
                    
					line = reader.readLine();
				}
			}
			
			
			if (line.equals("forbidden machine:")){
				line = reader.readLine();
				while (line != null && !(line.isEmpty())){		//If next line is not empty
					inputData.machinePenalties[charToIndex(line.charAt(1))-1][charToIndex(line.charAt(3))] = -1; //Load values into array
					line = reader.readLine();
				}
			}
			
			if (line.equals("too-near tasks:")){
				line = reader.readLine();
				while (line != null && !(line.isEmpty())){		//If next line is not empty
					int [] tnt = {charToIndex(line.charAt(1))-1,charToIndex(line.charAt(3))};
					inputData.tooNearTasks.add(tnt);	
					line = reader.readLine();
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
	*/
}
