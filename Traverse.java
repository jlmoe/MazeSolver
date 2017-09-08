package project1;
/*
 * Traverse is the main recursive method called in the MazeSolver program
 * The traverse method finds a way through a 2D array maze
 * Requirements
 * Input must be a 15*15 array.
 * The initial point must be (0,0).
 * The end must be (14,14).
 * "1" is a path not traversed yet
 * "3" is marked path that has been traversed
 * "7" is the marked path for the answer
 * Each attempted move calls a function
 * If there are no moves that work, we backtrack.
 * The position can be moved into a square <-> it is "1".
 * To move into a square there are 3 requirements:
 * -must be in bounds
 * -must not have been traversed before
 * -must not be a wall ("0")
 * Once in a new square, the square must be marked with a "3" to 
 * show it has been visited.
 */

/***************************
 * BASIC CONTROL STRUCTURE *
 ***************************/

/*
 * ->method call
 * ->boolean END
 * ->if row,column=(14,14)
 * 		->True-> set square to 7, return true
 * 		->False-> if !=visited,!=outofbounds,!=0
 * 				->set the position as marked
 * 				->check down, right,up,left
 * 					** This order is specified as the general direction the maze
 * 					** is traversed is down and right. This provides a good chance 
 * 					** the maze will be solved more quickly
 * 					
 * 					** Checking each direct calls the travel method recursively, therefore, the only way it will backtrack
 * 					** is if a the method highest on the stack returns false, causing the position to backtrack to the previous
 * 					** position, in which it will either continue checking or backtrack further.
 * 					** Returning true will also cause the stack to backtrack, however, because the only way to get true for any result
 * 					** is by reaching the base case, the stack will collapse backwards on the correct path of the maze, simultaneously
 * 					** marking the correct path
 * 				 -> if true -> set as correct path, return true
 * 					->false -> go to next direct, if there is no next direct, return false
 */
public class Traverse {
	
	public boolean Solver(int [][] maze, int row, int column){
		
		boolean result=false;
		if(row==14&&column==14){	//check to see if it is the end
			maze[row][column]=7;
			result=true;
		}
		
		else if(ValidSquare(maze,row,column)){//check if maze is a valid square
			maze[row][column]=3;
			
			//This chain of if and else if statements check each direction, if it finds a direction that is true,
			//the function it calls will set the maze location to 7 and return back here.
			//If the function did return true, we know we have found the base case, so we make the result true so we can backtrack
			//to the correct square and confirm they are also true, causing the stack to collapse to main.
			if(Down(maze,row,column)){	//move down if possible
										//when this is true, we break out of the if/else chain
				result=true;	//base case found! return true
			}
			else if(Right(maze,row,column)){	//move right if possible
				result=true;	//base case found! return true

			}
			else if(Up(maze,row,column)){	//move up if possible
				result=true;	//base case found! return true

			}
			else if(Left(maze,row,column)){	//move left if possible
				result=true;	//base case found! return true

			}
			else{	//if no move is possible, backtrack to previous square because there is a dead end
				result=false;	//dead end
			}
		}
		else{
			result = false;	//backtrack to previous square because this square is invalid
		}
		return result;	//backtrack to previous call
	}
	
	
	//these functions make this type of recursion indirect
	private boolean Down(int [][] maze, int row, int column){	//try to move down
		
		boolean result=false;
		
		if(Solver(maze,row+1,column)==true){	//if this is the correct path
			maze[row+1][column]=7;	//mark as correct path
			result=true;	//base case found! return true
		}
		return result;
	}
	private boolean Right(int [][] maze, int row, int column){	//try to move right
		
		boolean result=false;
		if(Solver(maze,row,column+1)==true){	//if this is the correct path
			maze[row][column+1]=7;//mark as correct path
			result=true;//base case found! return true
		}
		return result;
	}
	private boolean Up(int [][] maze, int row, int column){	//try to move up
		
		boolean result=false;
		if(Solver(maze,row-1,column)==true){	//if this is the correct path
			maze[row-1][column]=7;//mark as correct path
			result=true;//base case found! return true
		}
		return result;
	}
	
	private boolean Left(int [][] maze, int row, int column){	//try to move left
		boolean result=false;
		if(Solver(maze,row,column-1)==true){
			maze[row][column-1]=7;//mark as correct path
			result=true;//base case found! return true
		}
		return result;
	}
	
	private boolean ValidSquare(int [][] maze, int row, int column){
		boolean result=false;
		if(row<15 && column<15 && row>-1 && column>-1){//check if maze is in bounds
			if(maze[row][column]!=0 && maze[row][column] !=3){	//if it is a free square
				result=true;
			}
			else{	//if it is not a valid square
				result=false;
			}
		}
		return result;
	}
}
