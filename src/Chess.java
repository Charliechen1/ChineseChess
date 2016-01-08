import java.util.ArrayList;
import java.util.Scanner;

abstract public class Chess implements Cloneable{
	int pos[];
	int movePath[][] = new int[34][2];
	boolean side;
	int eval[];
	
	/**
	 * the array to store all the possible move 
	 */
	ArrayList <int[]> move = new ArrayList <int[]>();
	/**
	 * the array to store all the possible attack
	 */
	ArrayList <int[]> attack = new ArrayList <int[]>();
	/**
	 * the array to store all the possible protect
	 */
	ArrayList <int[]> protect = new ArrayList <int[]>();
	abstract char getLabel();
	
	Chess(int[] pos,boolean side){
		this.pos = new int[2];
		this.pos[0] = pos[0];
		this.pos[1] = pos[1];
		this.side = side;
	}
	
	void AI(Board b){
		
	}
	
	void userInter(Board b){
		System.out.println("User Turn");
		System.out.printf("Now moving Chess \"%c\" at (%d, %d)\n", this.getLabel(), pos[0], pos[1]);
		if (this.move.size() > 0){
			System.out.println("There are the valid moves:");
			for(int i = 0; i < this.move.size(); i++){
				int currMove[] = this.move.get(i);
				System.out.printf("(%d, %d)\n", currMove[0], currMove[1]);
			}	
		}
		
		if (this.attack.size() > 0){
			System.out.println("There are the valid attacks:");
			for(int i = 0; i < this.attack.size(); i++){
				int currAttack[] = this.attack.get(i);
				System.out.printf("(%d, %d)\n", currAttack[0], currAttack[1]);
			}
		}
		Scanner scan=new Scanner(System.in); 
		
		if (this.move.size() == 0 && this.attack.size() == 0){
			System.out.println("No actions can be done on this chess");
			return;
		}
		
		if (this.move.size() != 0 && this.attack.size() == 0){
			moveCMD(b);
		}
		
		if (this.move.size() == 0 && this.attack.size() != 0){
			attCMD(b);
		}
		
		if (this.move.size() != 0 && this.attack.size() != 0){
			System.out.println("Input the command to [a]ttack or [m]ove:");
			String cmd = scan.nextLine();
			while(!cmd.equals("a") && !cmd.equals("m")){
				System.out.println("Input the command to [a]ttack or [m]ove:");
				cmd = scan.nextLine();
			}
			if (cmd.equals("a")){
				attCMD(b);
			}
			else if (cmd.equals("m")){
				moveCMD(b);
			}
		}
	}
	
	
	/**
	 * the function to generate the move and attack array and
	 * interact with the user if currently it is the user
	 * @param b the current board
	 * @param flag the flag to know whether it's user's turn or AI's turn
	 */
	abstract void move(Board b, boolean flag);
	
	boolean checkPos(ArrayList <int[]> al, int x, int y){
		for (int i = 0; i < al.size(); i ++){
			int tmpPos[] = al.get(i);
			if (tmpPos[0] == x && tmpPos[1] == y)
				return true;
		}
		return false;
	}
	
	/**
	 * function to interact with user when move
	 * @param b the current board
	 */
	void moveCMD(Board b){
		Scanner scan=new Scanner(System.in); 
		System.out.println("Please input the position to move (input type: x y,   e.g. 0 1 ): ");
		int x,y;
		x = scan.nextInt();
		y = scan.nextInt();
		while(!checkPos(this.move,x,y)){
			System.out.println("Your input is not valid, please input again: ");
			x = scan.nextInt();
			y = scan.nextInt();
		}
		int index = b.chessIndex(this.pos);
		this.pos[0] = x;
		this.pos[1] = y;
		b.chesses.set(index, this);
		return;
	}
	
	/**
	 * function to interact with the user when attack
	 * @param b the current board
	 */
	void attCMD(Board b){
		Scanner scan=new Scanner(System.in);
		System.out.println("Please input the position to attack (input type: x y,   e.g. 0 1 ): ");
		int x,y;
		x = scan.nextInt();
		y = scan.nextInt();
		while(!checkPos(this.attack,x,y)){
			System.out.println("Your input is not valid, please input again: ");
			x = scan.nextInt();
			y = scan.nextInt();
		}
		int attPos[] = {x, y};
		int index = b.chessIndex(attPos);
		b.chesses.remove(index);
		
		index = b.chessIndex(this.pos);
		this.pos[0] = x;
		this.pos[1] = y;
		b.chesses.set(index, this);
		return;
	}
	
	/**
	 * the move action to take when it's AI's turn
	 * @param b the current board
	 * @param movID the ID for the move to take
	 * @return the board after the movement
	 */
	Board movAct(Board b, int movID){
		Board tmp = (Board)b.clone();
		int mov[] = this.move.get(movID);
		int index = tmp.chessIndex(this.pos);
		if(index >= 0){
			this.pos[0] = mov[0];
			this.pos[1] = mov[1];
			tmp.chesses.set(index, this);
		}
		return tmp;
	}
	
	/**
	 * the attack action to take when it's AI's turn
	 * @param b
	 * @param attID the ID of the attack to take
	 * @return the board after the attack
	 */
	Board attAct(Board b, int attID){
		Board tmp = b;
		int att[] = this.attack.get(attID);
		int index = tmp.chessIndex(att);
		if(index >= 0){
			tmp.chesses.remove(index);
		}
		index = b.chessIndex(this.pos);
		
		if(index >= 0){
			this.pos[0] = att[0];
			this.pos[1] = att[1];
			tmp.chesses.set(index, this);
		}
		return tmp;
	}
	
	@Override  
    public Object clone() {  
        Chess che = null;  
        try{  
            che = (Chess)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        che.pos = (int[])pos.clone();
        che.movePath = movePath.clone();
        che.side = side;
        che.eval = eval.clone();
    	che.move = (ArrayList <int[]>)move.clone();
    	che.attack = (ArrayList <int[]>)attack.clone();
    	che.protect = (ArrayList <int[]>)protect.clone();
    	return che;  
    } 
}
