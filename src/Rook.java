import java.util.ArrayList;

public class Rook extends Chess{

	Rook(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		
		int tmpEval[] = {
			-6,  5, -2,  4,  8,  8,  6,  6,  6,  6,
			6,  8,  8,  9, 12, 11, 13,  8, 12,  8,
			4,  6,  4,  4, 12, 11, 13,  7,  9,  7,
		   	12, 12, 12, 12, 14, 14, 16, 14, 16, 13,
			0,  0, 12, 14, 15, 15, 16, 16, 33, 14,
		   	12, 12, 12, 12, 14, 14, 16, 14, 16, 13,
			4,  6,  4,  4, 12, 11, 13,  7,  9,  7,
			6,  8,  8,  9, 12, 11, 13,  8, 12,  8,
		   	-6,  5, -2,  4,  8,  8,  6,  6,  6,  6
		};
		this.eval = tmpEval;
	}
	
	@Override
	void move(Board b, boolean flag) {
		// TODO Auto-generated method stub
		Chess tmpC = null;
		move.removeAll(this.move);
		attack.removeAll(this.attack);
		int i, j;
		//searching rightward
		i = pos[0] + 1;
		while (tmpC == null && i < 9){
			int cmpPos[] = {i, pos[1]};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			else{
				if (tmpC.side != this.side){
					attack.add(cmpPos);
				}else{
					protect.add(cmpPos);
				}
			}
			i ++;
		}
		tmpC = null;
		
		//searching leftward
		i = pos[0] - 1;
		while (tmpC == null && i > -1){
			int cmpPos[] = {i, pos[1]};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			else{
				if (tmpC.side != this.side){
					attack.add(cmpPos);
				}else{
					protect.add(cmpPos);
				}
			}
			i --;
		}
		tmpC = null;
		
		//searching downward
		j = pos[1] + 1;
		while (tmpC == null && j < 10){
			int cmpPos[] = {pos[0], j};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			else{
				if (tmpC.side != this.side){
					attack.add(cmpPos);
				}else{
					protect.add(cmpPos);
				}
			}
			j ++;
		}
		tmpC = null;
				
		//searching upward
		j = pos[1] - 1;
		while (tmpC == null && j > -1){
			int cmpPos[] = {pos[0], j};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			else{
				if (tmpC.side != this.side){
					attack.add(cmpPos);
				}else{
					protect.add(cmpPos);
				}
			}
			j --;
		}
		tmpC = null;
	
		if(flag){
			this.AI(b);
		}else{
			this.userInter(b);
		}
	}

	@Override
	char getLabel() {
		// TODO Auto-generated method stub
		if (this.side){
			return 'R';
		}else{
			return 'r';
		}
	}

}
