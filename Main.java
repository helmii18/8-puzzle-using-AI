package pkg8puzzle;

import java.util.*;

public class Main {
    public static Scanner input=new Scanner(System.in);
    
    static int getInvCount(int[] arr)
{
    int inv_count = 0;
    for (int i = 0; i < 9; i++)
        for (int j = i + 1; j < 9; j++)  
            if (arr[i] > 0 && arr[j] > 0 && arr[i] > arr[j])
                inv_count++;
           return inv_count;
}
 static boolean isSolvable(int[][] puzzle)
{
    int linearPuzzle[];
    linearPuzzle = new int[9];
    int k = 0;
    for(int i=0; i<3; i++)
        for(int j=0; j<3; j++)
            linearPuzzle[k++] = puzzle[i][j];
    int invCount = getInvCount(linearPuzzle);
    return (invCount % 2 == 0);
}
    public static void printpuzzle(int[][] ar){
        System.out.println(" _________________");
        System.out.println("|     |     |     |");
        System.out.println("|  "+ar[0][0]+"  |  "+ar[0][1]+"  |  "+ar[0][2]+"  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.println("|  "+ar[1][0]+"  |  "+ar[1][1]+"  |  "+ar[1][2]+"  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.println("|  "+ar[2][0]+"  |  "+ar[2][1]+"  |  "+ar[2][2]+"  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("");
        System.out.println("");
        
    }
    
    public static boolean contains (int [][] arr,int num){
        
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
            if(arr[i][j]==num)
                return true;
            }
        }return false;
    
    
    }
    public static int [][] inputgoal(){
        
        System.out.println("now enter your goal 8 puzzle");
        int[][] p=new int[3][3];
        int num=0;
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                printpuzzle(p);
                System.out.println("enter in["+i+"]["+j+"] a unique number between 0-8"  );
                
                boolean bol=false;
                while(bol==false){
                int in=input.nextInt();
                
                if(num!=99){
                if (in==0){
                    p[i][j]=0;
                    num=99;
                    break;
                }
                }
                if(in >=0 && in <=8){
                    if(contains(p,in)==false){
                        p[i][j]=in;
                        bol=true;
                    }
                    else
                        System.out.println("please enter in["+i+"]["+j+"] a unique number between 0-8");
                
                }else{
                    System.out.println("please enter in["+i+"]["+j+"] a unique number between 0-8 ");
                }
                }
                
            }
        }printpuzzle(p);
        return p; 
        }
    
    public static int [][] inputpuz(){
        
        System.out.println("Please enter your 8 puzzle");
        int[][] p=new int[3][3];
        int num=0;
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                printpuzzle(p);
                System.out.println("enter in["+i+"]["+j+"] a unique number between 0-8"  );
                
                boolean bol=false;
                while(bol==false){
                int in=input.nextInt();
                
                if(num!=99){
                if (in==0){
                    p[i][j]=0;
                    num=99;
                    break;
                }
                }
                if(in >=0 && in <=8){
                    if(contains(p,in)==false){
                        p[i][j]=in;
                        bol=true;
                    }
                    else
                        System.out.println("please enter in["+i+"]["+j+"] a unique number between 0-8");
                
                }else{
                    System.out.println("please enter in["+i+"]["+j+"] a unique number between 0-8 ");
                }
                }
                
            }
        }printpuzzle(p);
        return p;
        }
    
    public static boolean equals(int [][]x,int [][]y){
         for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                if(x[i][j]!= y[i][j])
                return false;
            }
         }return true;
    
    
    
    }
    
    public static void main(String[] args) {
        int [][] g1={{0,1,2},{3,4,5},{6,7,8}};
        int [][] g2={{1,2,3},{4,5,6},{7,8,0}};
        System.out.println("Welcome to 8 puzzle");
        System.out.println("********");
        int[][] initial=inputpuz();
        puzzle initialpuzzle=new puzzle(initial);
        int[][] goal=inputgoal();
        boolean boll=false;
        if(equals(goal,g1) || equals(goal,g2)){
            if(!isSolvable(initial)){
                System.out.println(" unsolvable puzzle");
                boll=true;
            }
        
        }
        
        if(equals(initial,goal))
            System.out.println("initial puzzle = goal puzzle");
        else if ( boll==false ){
            boolean bol=false;
            while(bol==false){
            System.out.println("Please choose an Algorithm:");
            System.out.println("1. BFS");
            System.out.println("2. DFS");
            System.out.println("3. A*");
            System.out.println("4.exit");
            int in =input.nextInt();
                
            switch(in){
                    case 1:
                        BFS b=new BFS(initialpuzzle);
                        if(b.solve(goal)==false)
                            System.out.println("unreachable");
                        break;
                    case 2:
                        DFS bf=new DFS(initialpuzzle);
                        if(bf.solve(goal)==false)
                            System.out.println("unreachable");
                        break;
                    case 3:
                        System.out.println("1. using eclidean h");
                        System.out.println("2. using manhhattan h");
                        int nn=input.nextInt();
                        Astar a=new Astar(initialpuzzle,nn,goal);
                        if(a.solve()==false)
                            System.out.println("unreachable");
                        
                        break;
                    case 4:
                        System.out.println("Thank U!!!");
                        bol=true;
                        break;
                    default:
                        System.out.println("incorrect input ");
            }
        }
        }    
    }
}
