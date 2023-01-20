package pkg8puzzle;

import java.util.*;

public class puzzle {
    private int[][]  epuzzle = new int [3][3];
    private ArrayList<puzzle> children;
    private puzzle parent;
    private enum directions {Left,Right,Up,Down};
    private directions dir;
    private String stringPuzzle;
    private int zrow;
    private int zcol;
    private int depth;
    private int cost;
    
    private int count;
  public puzzle(int [][] epuzzle) {
		this.epuzzle = epuzzle; 
		this.depth = 1;  
		this.count = 0;  
		this.children = new ArrayList<puzzle>(); 
		this.parent = null;
		this.cost = 0;
                this.stringPuzzle = stringBoard();
		this.dir = null;
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				if(epuzzle[i][j]==0) {
					this.zrow = i;
					this.zcol = j;
					break;
				}
			}
		}
	}

   public String stringBoard() {   
		StringBuilder sb = new StringBuilder();
		for (int i =0; i<3; i++) {
			for(int j = 0; j<3; j++ ) {
				sb.append(epuzzle[i][j]);
			}
		}
		return sb.toString();
	}
   
	@Override
	public int hashCode() {			
		int result = 17;
		result = 37 * result + (this.getStringPuzzle().hashCode());
		return result;
	}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStringPuzzle() {
        return stringPuzzle;
    }

    public void setStringPuzzle(String stringPuzzle) {
        this.stringPuzzle = stringPuzzle;
    }
    
        
    public int[][] getEpuzzle() {
        return epuzzle;
    }

    public void setEpuzzle(int[][] epuzzle) {
        this.epuzzle = epuzzle;
    }

    public int getZrow() {
        return zrow;
    }

    public void setZrow(int zrow) {
        this.zrow = zrow;
    }

    public int getZcol() {
        return zcol;
    }

    public void setZcol(int zcol) {
        this.zcol = zcol;
    }

    public ArrayList<puzzle> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<puzzle> children) {
        this.children = children;
    }

    public puzzle getParent() {
        return parent;
    }

    public void setParent(puzzle parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public directions getDir() {
        return dir;
    }

    public void setDir(directions dir) {
        this.dir = dir;
    }
    
    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

  

        public puzzle addChild(int a, int b){    
            
            int temp[][] = new int[3][3];
            for(int i=0; i<3; i++)
                      for(int j=0; j<3; j++)
                        temp[i][j]=epuzzle[i][j];
            temp[zrow][zcol] = temp[a][b];
            int cost = epuzzle[a][b]; 
            temp[a][b] = 0;
            puzzle child = new puzzle(temp);
            child.setCost(cost);	
            child.setParent(this);
            child.setDepth(this.getDepth()+1);
            this.children.add(child);
            child.setCount(this.getCount()+1);
            return child;
        }
        
        public boolean isgoal (int[][] goal){
            boolean bol=true;
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++){
                    if(goal[i][j]!=epuzzle[i][j])
                    {bol=false;
                        break;}
                }return bol;
        }
        
        public int getRow(int value,int [][]goal) {				
            int row = 0;
                for(int i=0; i<3; i++) {
                        for(int j=0; j<3; j++) {
                            if(goal[i][j]==value) {
                                row = i;
                                }
                        }
                }
                        return row;
            }

	public int getCol(int value,int [][]goal) {			
		int col = 0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(goal[i][j]==value) {
					col = j;
				}
			}
		}
			return col;
	}
        
	public List<puzzle> neighborsBFS(puzzle node) {
		
		List<puzzle> list = new ArrayList<puzzle>();
		
		int row = node.getZrow();
		int col = node.getZcol();
		
		if(row !=0) { 
			puzzle upNode = node.addChild(row-1, col);
			upNode.setDir(directions.Up);
			list.add(upNode);
                }
		
		if(row !=2)  {
			puzzle downNode = node.addChild(row+1, col);
			downNode.setDir(directions.Down);
			list.add(downNode);
		}
		
		if(col !=2) {
			puzzle rightNode = node.addChild(row, col+1);
			rightNode.setDir(directions.Right);
			list.add(rightNode);
		}
		
		
		if(col !=0) {
			puzzle leftNode = node.addChild(row, col-1); 
			leftNode.setDir(directions.Left);  
			list.add(leftNode);
		}
		
		return list;
        }
        
        public List<puzzle> neighborDFS(puzzle node) {
		
		
		List<puzzle> list = new ArrayList<puzzle>();
		
		int row = node.getZrow();
		int col = node.getZcol();
		
		
		
		if((row != 0 || row != 1 || row != 2) && (col !=0)) {
			puzzle leftNode = node.addChild(row, col-1); 
			leftNode.setDir(dir.Left);  
			list.add(leftNode);
		}
		
		
		if((row != 0 || row != 1 || row != 2) && (col !=2)) {
			puzzle rightNode = node.addChild(row, col+1);
			rightNode.setDir(dir.Right);
			list.add(rightNode);
		}
		
		
		if((col !=0 || col != 1 || col != 2) && (row !=2) ) {
			puzzle downNode = node.addChild(row+1, col);
			downNode.setDir(dir.Down );
			list.add(downNode);
		}
		
		
		
		if((col !=0 || col != 1 || col != 2) && (row !=0) ) {  
			puzzle upNode = node.addChild(row-1, col);
			upNode.setDir(dir.Up);
			list.add(upNode);

		}
		
		return list;  
		 
	}
        public void printPath(puzzle initial) { 
        
	
        	puzzle tempNode = this;
		List<puzzle> path = new ArrayList<puzzle>();
		
		while(!(tempNode.equals(initial))) {
			path.add(tempNode);
			tempNode = tempNode.getParent();
			
		}
		path.add(initial);
		  
		for(int i= path.size()-1;i>=0;i--) {
			System.out.println("Direction Moved: " + path.get(i).getDir());
			System.out.println("Depth: " + path.get(i).getDepth());
			System.out.println("count of moves: " + path.get(i).getCount());
			System.out.println("Cost: " + path.get(i).getCount());
			System.out.println();
			System.out.println("Current Node: \n");
			path.get(i).printpuzzle();
                }
		
	}

    public void printpuzzle(){
        System.out.println(" _________________");
        System.out.println("|     |     |     |");
        System.out.println("|  "+epuzzle[0][0]+"  |  "+epuzzle[0][1]+"  |  "+epuzzle[0][2]+"  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.println("|  "+epuzzle[1][0]+"  |  "+epuzzle[1][1]+"  |  "+epuzzle[1][2]+"  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.println("|  "+epuzzle[2][0]+"  |  "+epuzzle[2][1]+"  |  "+epuzzle[2][2]+"  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("");
        System.out.println("");
            
        
        
    }
}
