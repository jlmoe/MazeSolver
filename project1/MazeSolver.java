package project1;
import java.util.*;
import java.io.*;
public class MazeSolver {

	public static void main(String[] args) {
		Scanner keyboard=new Scanner(System.in);
		boolean goodFileName=false;
		String fileName;
		int[][]theMaze=new int[15][15];		

		System.out.println("Please enter a file containing only a 15x15 matrix: ");	//user prompt
		fileName=keyboard.nextLine();

		while(!goodFileName){	//runs as long as the file is incorrect
			try{
				FileReader in = new FileReader(fileName);
				Scanner dataFile=new Scanner(in);
				try{
					theMaze = MazeOperations.fillMaze(theMaze, dataFile);
					goodFileName=true;//this allows us to exit the while loop
					//we also verify the array is the correct size
				}
				catch(RuntimeException r){
					System.out.println("This array is not the right size!");
					System.out.print("Please enter a file containing only a 15x15 matrix: ");	
				}	
				//by this point we know the file exists
				//now we check to see if the array is the required size
			}
			catch(IOException e){
				System.out.println("This file does not exist, please try again");
				System.out.print("Please enter a file containing only a 15x15 matrix: ");
				fileName=keyboard.nextLine();
			}
		}//end while
		
		//now we have loaded the file, lets try and solve the maze
		MazeOperations.traverseMaze(theMaze,0,0);
		MazeOperations.printMaze(theMaze);
	}
}




