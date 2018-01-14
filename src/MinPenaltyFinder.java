import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MinPenaltyFinder {
	static int[] taskAssignments = new int[8];							//Order of task assignment from machine 1-8
	
	public static void main(String[] args) {	
		dataArray inputData = new dataArray();
		
		String fileName = "/Users/USER/Desktop/CPSC449Input.txt";	//Input file location and name
		FileParser.ReadFile(fileName, inputData);
		//testArray(inputData); 										//Loads file into variables
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static void testArray(dataArray inputData){
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
}
	


