package pkg8puzzle;
import java.util.*;
public class Astar {
    PriorityQueue<puzzle> pQueue = new PriorityQueue<>();
    HashMap<Integer,puzzle>visited=new HashMap<Integer,puzzle>();
    private puzzle initial;
    private int i;
    private int [][]goal=new int[3][3]; 
	
	public Astar(puzzle node, int i,int[][] goal ) {
		this.initial = node; 
		this.i = i;
                for(int l=0; l<=2; l++) {
			for(int j=0; j<=2; j++){
                        this.goal[l][j]=goal[l][j];
                        
                        }
                        }
                
	}
	
	private class f1Comparator implements Comparator<puzzle>{  
		
		Heuristics h = new Heuristics();
		
		public int compare(puzzle a, puzzle b) {
			return (a.getCount()+ h.eclidean(a,goal)) - (b.getCount()+h.eclidean(b,goal));
		}
	}
	

	private class f2Comparator implements Comparator<puzzle>{			
		
		Heuristics h = new Heuristics();
		
                
		public int compare(puzzle a, puzzle b) {
                    int x=(a.getCount() + h.manhattan(a,goal)) - (b.getCount()+h.manhattan(b,goal));
			return x ;
		}
	} 
     
    
     
        public boolean solve() {
            long starttime =System.nanoTime();
            if(this.i==1) {
                    
                     pQueue = new PriorityQueue<>(new f1Comparator());
            }
            else {
                     pQueue = new PriorityQueue<>(new f2Comparator());
                   
            }
             
            puzzle node = initial;
            pQueue.add(node);

            while(!(pQueue.isEmpty())) {
                   node = pQueue.poll();
                   visited.put(node.hashCode(), node);
                   if(node.isgoal(goal))
                   {
                       long endtime =System.nanoTime();  
                       node.printPath(initial);
                Scanner input=new Scanner(System.in );
                System.out.println("0. without visited nodes ");
                System.out.println("1. with visited nodes ");
                int x=input.nextInt();
                if(x==1){
                    int count=0;
                     for(puzzle val : visited.values()){
                        val.printpuzzle();
                        System.out.println(" ");
                    count++;
                    }System.out.println("number of vistited nodes is "+count);
                }System.out.println(endtime-starttime+"nanosecs");
                    return true;
                    }
                    List<puzzle> list = node.neighborsBFS(node);

                    for(puzzle temp: list) {
                        boolean ans = visited.containsKey(temp.hashCode());
                        if(ans==false){
                            if(!(pQueue.contains(temp))) {
                                    pQueue.add(temp);

                                    }
                            }   
        }
        }
            return false;
    }
}
