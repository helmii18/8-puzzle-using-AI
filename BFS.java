package pkg8puzzle;
import java.util.*;

public class BFS {
   private puzzle initial;
    Queue <puzzle> q=new LinkedList<>();
    HashMap<Integer,puzzle>visited=new HashMap<Integer,puzzle>();
    
    public BFS(puzzle initial) {
        this.initial = initial;
    }
    
    public boolean solve(int [][] goal)
    {
        long starttime =System.nanoTime();
        puzzle node=initial;
        q.add(node);
        
        while(!(q.isEmpty())) {
            node=q.remove();
            visited.put(node.hashCode(),node);
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
                }
                System.out.println(endtime-starttime+"nanosecs");
                    return true;
            }
            
			List<puzzle> list = node.neighborsBFS(node);
			for(puzzle temp: list) {
				boolean ans = visited.containsKey(temp.hashCode());
				if(ans==false){
					if(!(q.contains(temp))) {
					q.add(temp);
					
					}
				}
                        }
        }return false;
    }
}
