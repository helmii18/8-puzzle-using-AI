package pkg8puzzle;
import java.util.*;

public class DFS {
    private puzzle initial;
    Stack <puzzle> s=new Stack<>();
    HashMap<Integer,puzzle> visited=new HashMap<Integer,puzzle>(); 
    
    public DFS(puzzle initial) {
        this.initial  = initial;
    }
    
    public boolean solve(int [][] goal)
    {
        long starttime =System.nanoTime();
        puzzle node=initial;
        s.push(node);
        
        while(!(s.isEmpty())) {
            node=s.pop();
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
                        
			List<puzzle> list = node.neighborDFS(node);
			for(puzzle temp: list) {
				boolean ans = visited.containsKey(temp.hashCode());
				if(ans==false){
					if(!(s.contains(temp))) {
					s.push(temp);
					}
				}
                        }
        }return false;
    }
}
