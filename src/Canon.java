
public class Canon extends Chess{
	Canon(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		int tmpEval[] = { 
			0,  0,  1,  0, -1,  0,  0,  1,  2,  4,
			0,  1,  0,  0,  0,  0,  3,  1,  2,  4,
			1,  2,  4,  0,  3,  0,  3,  0,  0,  0,
			3,  2,  3,  0,  0,  0,  2, -5, -4, -5,
			3,  2,  5,  0,  4,  4,  4, -4, -7, -6,
			3,  2,  3,  0,  0,  0,  2, -5, -4, -5,
			1,  2,  4,  0,  3,  0,  3,  0,  0,  0,
			0,  1,  0,  0,  0,  0,  3,  1,  2,  4,
			0,  0,  1,  0, -1,  0,  0,  1,  2,  4
		};
		this.eval = tmpEval;
	}

	
	@Override
	char getLabel() {
		// TODO Auto-generated method stub
		if (this.side){
			return 'C';
		}else{
			return 'c';
		}
	}


	@Override
	void move(Board b, boolean flag) {
		// TODO Auto-generated method stub
		move.removeAll(this.move);
		attack.removeAll(this.attack);
		Chess tmpC = null;
		int i, j;
		//searching rightward
		i = pos[0] + 1;
		while (tmpC == null && i < 9){
			int cmpPos[] = {i, pos[1]};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			i ++;
		}
		tmpC = null;
		if (i < 9){
			while (tmpC == null && i < 9){
				int cmpPos[] = {i, pos[1]};
				tmpC = b.findChess(cmpPos);
				if (tmpC != null) {
					if (tmpC.side != this.side){
						attack.add(cmpPos);
					}else{
						protect.add(cmpPos);
					}
				}
				i ++;
			}
		}
		tmpC = null;
		
		//searching leftward
		i = pos[0] - 1;
		while (tmpC == null && i > -1){
			int cmpPos[] = {i, pos[1]};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			i --;
		}
		tmpC = null;
		if (i > -1){
			while (tmpC == null && i > -1){
				int cmpPos[] = {i, pos[1]};
				tmpC = b.findChess(cmpPos);
				if (tmpC != null) {
					if (tmpC.side != this.side){
						attack.add(cmpPos);
					}else{
						protect.add(cmpPos);
					}
				}
				i --;
			}
		}
		tmpC = null;
		
		//searching upward
		j = pos[1] + 1;
		while (tmpC == null && j < 10){
			int cmpPos[] = {pos[0], j};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			j ++;
		}
		tmpC = null;
		if (j < 10){
			while (tmpC == null && j < 10){
				int cmpPos[] = {pos[0], j};
				tmpC = b.findChess(cmpPos);
				if (tmpC != null) {
					if (tmpC.side != this.side){
						attack.add(cmpPos);
					}else{
						protect.add(cmpPos);
					}
				}
				j ++;
			}
		}
		tmpC = null;
		
		//searching downward
		j = pos[1] - 1;
		while (tmpC == null && j > -1){
			int cmpPos[] = {pos[0], j};
			tmpC = b.findChess(cmpPos);
			if (tmpC == null) 
				move.add(cmpPos);
			j --;
		}
		tmpC = null;
		if (j > -1){
			while (tmpC == null && j > -1){
				int cmpPos[] = {pos[0], j};
				tmpC = b.findChess(cmpPos);
				if (tmpC != null) {
					if (tmpC.side != this.side){
						attack.add(cmpPos);
					}else{
						protect.add(cmpPos);
					}
				}
				j --;
			}
		}
		tmpC = null;
		
		if(flag){
			this.AI(b);
		}else{
			this.userInter(b);
		}
	}

}
