
public class MoveNode implements Comparable<MoveNode>{
	Board board;
	int eval;
	
	MoveNode(Board bb){
		this.board = bb;
		this.calEval();
	}
	
	void calEval(){
		int myEval = 0;
		int oppEval = 0;
		for (int i = 0; i < this.board.chesses.size(); i++) {
			Chess tmpC = this.board.chesses.get(i);
			if (tmpC.side == true){
				myEval += tmpC.eval[10 * (8 - tmpC.pos[0]) + (9 - tmpC.pos[1])];
				if(tmpC.getLabel() == 'P'){
					myEval += 80;
					myEval += (tmpC.move.size() + tmpC.attack.size()) * 15;
				}
				if(tmpC.getLabel() == 'C'){
					myEval += 300;
					myEval += (tmpC.move.size() + tmpC.attack.size()) * 6;
				}
				if(tmpC.getLabel() == 'R'){
					myEval += 500;
					myEval += (tmpC.move.size() + tmpC.attack.size()) * 6;
				}
				if(tmpC.getLabel() == 'K'){
					myEval += 300;
					myEval += (tmpC.move.size() + tmpC.attack.size()) * 12;
				}
				if(tmpC.getLabel() == 'B'){
					if (this.board.chesses.size() > 26)
						myEval += 50;
					else
						myEval += 250;
					myEval += (tmpC.move.size() + tmpC.attack.size()) * 1;
				}
				if(tmpC.getLabel() == 'M'){
					if (this.board.chesses.size() > 26)
						myEval += 50;
					else
						myEval += 250;
					myEval += (tmpC.move.size() + tmpC.attack.size()) * 1;
				}
				if(tmpC.getLabel() == 'G'){
					myEval += 50000;
				}
				myEval += tmpC.protect.size() * 50;
			}else{
				oppEval += tmpC.eval[10 * tmpC.pos[0] + tmpC.pos[1]];
				if(tmpC.getLabel() == 'p'){
					oppEval += 80;
					oppEval += (tmpC.move.size() + tmpC.attack.size()) * 15;
				}
				if(tmpC.getLabel() == 'c'){
					oppEval += 300;
					oppEval += (tmpC.move.size() + tmpC.attack.size()) * 6;
				}
				if(tmpC.getLabel() == 'r'){
					oppEval += 500;
					oppEval += (tmpC.move.size() + tmpC.attack.size()) * 6;
				}
				if(tmpC.getLabel() == 'k'){
					oppEval += 300;
					oppEval += (tmpC.move.size() + tmpC.attack.size()) * 12;
				}
				if(tmpC.getLabel() == 'b'){
					if(this.board.chesses.size() > 26)	
						oppEval += 50;
					else
						oppEval += 250;
					oppEval += (tmpC.move.size() + tmpC.attack.size()) * 1;
				}
				if(tmpC.getLabel() == 'm'){
					if(this.board.chesses.size() > 26)	
						oppEval += 50;
					else
						oppEval += 250;
					oppEval += (tmpC.move.size() + tmpC.attack.size()) * 1;
				}
				if(tmpC.getLabel() == 'g'){
					oppEval += 10000;
				}
				oppEval += tmpC.protect.size() * 50;
			}
		}
		
		this.eval = myEval - oppEval;
	}

	@Override
	public int compareTo(MoveNode o) {
		// TODO Auto-generated method stub
		if (this.eval > o.eval)
			return 1;
		if (this.eval < o.eval)
			return -1;  
		return 0;
	}	
}
