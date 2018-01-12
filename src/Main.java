import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	ArrayList<Pair> forcedAssignments = new ArrayList<>();
	ArrayList<Pair> forbiddenAssignments = new ArrayList<>();
	ArrayList<Pair> tooNearTasks = new ArrayList<>();
	
	int[][] basePenalties = new int[8][8];
	ArrayList<WeightedPair> tooNearPenalties = new ArrayList<>();
	
	public static void main(String[] args) {
	}

	//Reads file of expected format and saves values
	public void ReadFile(){
		try{
			String fileName = "/Users/USER/Desktop/CPSC449Input.txt";
			
			FileReader fileReader = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			
			String line;
			
			while ((line = reader.readLine()) != null){
				
			}
			fileReader.close();	
		}
		
		catch (IOException e){
			System.out.print("File not found");
		}
		
	}

	public void TwoTuple (int val1, int val2){
		
	}
	
	public void ThreeTuple (int val1, int val2, int val3){
		
	}
	
	
}


