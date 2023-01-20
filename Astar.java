package pkg8puzzle;

public class Heuristics {
	public int manhattan(puzzle node,int [][] goal) {   
		int result = 0;
		int [][]puz = node.getEpuzzle();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				int value = puz[i][j];
				result += Math.abs(i - node.getRow(value,goal)) + Math.abs(j - node.getCol(value,goal)); 
			}
		}
		return result;
	}
        
	public int eclidean(puzzle node,int [][] goal) {   
		int result = 0;
		int [][]puz = node.getEpuzzle();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				int value = puz[i][j];
				result += Math.sqrt((Math.pow(i - node.getRow(value,goal), 2)) +(Math.pow(j - node.getCol(value,goal), 2))) ; 
			}
		}
		return result;
	}
	
}
