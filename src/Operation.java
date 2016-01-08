import java.util.ArrayList;
import java.util.Scanner;

public class Operation {
	static Board b;
	static MoveTree mt;
	static String command;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		Scanner scan=new Scanner(System.in); 
		System.out.println("\nPress \"e\" to exit, any other to countinue:");
		command = scan.nextLine();
		while(!command.equals("e")){
			userTurn();
			AITurn();
			if(b.findChess('G') != null && b.findChess('g') != null){
				System.out.println("\nPress \"e\" to exit, any other to countinue:");
				command = scan.nextLine();
			}
		}
	}
	
	static void init(){
		b = new Board();
		b.print();
	}
	
	static void userTurn(){
		Chess choChess = chooseChess();
		choChess.move(b, false);
		b.print();
		
	}
	
	static void AITurn(){
		System.out.println("Now AI's turn!");
		
		mt = new MoveTree(new MoveNode(b));
		
		if (b.findChess('g') == null){
			System.out.println("=====================================");
			System.out.println("         COMPUTER WINS!!!           ");
			System.out.println("=====================================");
			command = "e";
			return;
		}
		if (b.findChess('G') == null){
			System.out.println("=====================================");
			System.out.println("               YOU WINS!!!              ");
			System.out.println("=====================================");
			command = "e";
			return;
		}
		
		mt.alphaBeta(3, -65536, 65535, true);
		b = mt.LargestChild().mNode.board;
		b.print();
		
		if (b.findChess('g') == null){
			System.out.println("=====================================");
			System.out.println("         COMPUTER WINS!!!           ");
			System.out.println("=====================================");
			command = "e";
			return;
		}
		if (b.findChess('G') == null){
			System.out.println("=====================================");
			System.out.println("               YOU WINS!!!              ");
			System.out.println("=====================================");
			command = "e";
			return;
		}
	}
	
	static Chess chooseChess(){
		Scanner scan=new Scanner(System.in); 
		System.out.println("Please choose the position of the chess you want to move (input type: x y,   e.g. 0 1 ):");
		int x = scan.nextInt();
		int y = scan.nextInt();
		int choPos[] = {x, y};
		Chess choChess = b.findChess(choPos);
		while(choChess == null || choChess.side == true){
			if(choChess == null){
				System.out.println("This position is empty (input type: x y,   e.g. 0 1 ):");
			}else{
				System.out.println("This chess is not under your control (input type: x y,   e.g. 0 1 ):");
			}
			x = scan.nextInt();
			y = scan.nextInt();
			choPos[0] = x; choPos[1] = y;
			choChess = b.findChess(choPos);
		}
		
		return choChess;
	}
}
