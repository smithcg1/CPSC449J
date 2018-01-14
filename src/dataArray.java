import java.util.ArrayList;

public class dataArray {
	ArrayList<int[]> tooNearTasks = new ArrayList<int[]>();		//Contains pairs of prohibited pairs
	ArrayList<int[]> tooNearPenalties = new ArrayList<int[]>(); //Contains pairs of penalty pairs
	int[][] machinePenalties = new int[8][8];					//All penalties and forced/forbidden pairs, [mach][task]
}
