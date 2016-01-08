import java.util.ArrayList;

public class Board implements Cloneable{
	char[][] board;
	ArrayList <Chess> chesses;
	
	Board(){
		this.init();
	}
	
	Board(Board b){
		this.board = new char[9][10];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 10; j++){
				this.board[i][j] = b.board[i][j];
			}
		}
		this.chesses = new ArrayList <Chess>();
		for (int i = 0; i < b.chesses.size(); i++){
			Chess tmpC = b.chesses.get(i);
			this.chesses.add((Chess)tmpC.clone());
		}
	}
	
	/**
	 * function to print the content of the current board
	 */
	void print(){
		boardPaint();
		System.out.println();
		for (int j = 9; j > 4; j--){
			for (int i = 0; i < 9; i++){
				System.out.printf("%-4c",this.board[i][j]);
			}
			System.out.println(j);
		}
		System.out.println("        CHU HE      HAN JIE      ");
		for (int j = 4; j > -1; j--){
			for (int i = 0; i < 9; i++){
				System.out.printf("%-4c",this.board[i][j]);
			}
			System.out.println(j);
		}
		for (int i = 0; i < 9; i++){
			System.out.printf("%-4d",i);
		}
		System.out.println();
		System.out.println();
	}
	
	/**
	 * initialize the board's content array
	 */
	void boardInit(){
		this.board = new char[9][10];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 10; j++){
				this.board[i][j] = '+';
			}
		}
	}
	
	/**
	 * paint the current chesses onto the board
	 */
	void boardPaint(){
		boardInit();
		for(int i = 0; i < chesses.size(); i++){
			int tmppos[] = chesses.get(i).pos;
			Chess tmp = chesses.get(i);
			this.board[tmppos[0]][tmppos[1]] = tmp.getLabel();
		}
	}
	
	/**
	 * initialize the board with the initial positions of the chesses
	 */
	void init(){
		chesses = new ArrayList<Chess>();
		boolean side = false;
		int pos[] = new int[2];
		//adding chesses for side 0;
		pos[0] = 0; 
		pos[1] = 0;
		chesses.add(new Rook(pos,side));
		pos[0] = 8;
		chesses.add(new Rook(pos,side));
		pos[0] = 1;
		chesses.add(new Knight(pos,side));
		pos[0] = 7;
		chesses.add(new Knight(pos,side));
		pos[0] = 2;
		chesses.add(new Bishop(pos,side));
		pos[0] = 6;
		chesses.add(new Bishop(pos,side));
		pos[0] = 3;
		chesses.add(new Mandarin(pos,side));
		pos[1] = 0;
		pos[0] = 5;
		chesses.add(new Mandarin(pos,side));
		pos[0] = 4;
		chesses.add(new General(pos,side));
		pos[0] = 1; 
		pos[1] = 2;
		chesses.add(new Canon(pos,side));
		pos[0] = 7;
		chesses.add(new Canon(pos,side));
		pos[1] = 3;
		for(int i = 0; i < 5; i++){
			pos[0] = 2*i;
			chesses.add(new Pawn(pos,side));
		}
		
		side = true;
		//adding chesses for side 1;
		pos[1] = 9;
		pos[0] = 0; 
		chesses.add(new Rook(pos,side));
		pos[0] = 8;
		chesses.add(new Rook(pos,side));
		pos[0] = 1;
		chesses.add(new Knight(pos,side));
		pos[0] = 7;
		chesses.add(new Knight(pos,side));
		pos[0] = 2;
		chesses.add(new Bishop(pos,side));
		pos[0] = 6;
		chesses.add(new Bishop(pos,side));
		pos[0] = 3;
		chesses.add(new Mandarin(pos,side));
		pos[0] = 5;
		chesses.add(new Mandarin(pos,side));
		pos[0] = 4;
		chesses.add(new General(pos,side));
		pos[0] = 1; 
		pos[1] = 7;
		chesses.add(new Canon(pos,side));
		pos[0] = 7;
		chesses.add(new Canon(pos,side));
		pos[1] = 6;
		for(int i = 0; i < 5; i++){
			pos[0] = 2*i;
			chesses.add(new Pawn(pos,side));
		}
		
		boardPaint();
	}
	
	
	/**
	 * find a chess with the given position
	 * @param inPos given position
	 * @return the chess found
	 */
	public Chess findChess(int inPos[]){
		for (int i = 0; i < chesses.size(); i++){
			Chess tmpC = chesses.get(i);
			if(tmpC.pos[0] == inPos[0] && tmpC.pos[1] == inPos[1]){
				return tmpC;
			}
		}
		return null;
	}
	
	/**
	 * return the chess's index with the given position
	 * @param inPos the given position
	 * @return the index
	 */
	public int chessIndex(int inPos[]){
		for (int i = 0; i < chesses.size(); i++){
			Chess tmpC = chesses.get(i);
			if(tmpC.pos[0] == inPos[0] && tmpC.pos[1] == inPos[1]){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * find the chess with the given label
	 * @param label the given label
	 * @return the chess found
	 */
	public Chess findChess(char label){
		for (int i = 0; i < chesses.size(); i++){
			Chess tmpC = chesses.get(i);
			if(tmpC.getLabel() == label){
				return tmpC;
			}
		}
		return null;
	}
	
	public Object clone() {  
        Board o = null;  
        try {  
            o = (Board) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
}
