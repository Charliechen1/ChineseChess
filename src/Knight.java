
public class Knight extends Chess{

	Knight(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		movePath = new int[8][2];
		movePath[0][0] = 1; movePath[0][1] = 2;
		movePath[1][0] = 2; movePath[1][1] = 1;
		movePath[2][0] = -1; movePath[2][1] = 2;
		movePath[3][0] = -2; movePath[3][1] = 1;
		movePath[4][0] = 1; movePath[4][1] = -2;
		movePath[5][0] = 2; movePath[5][1] = -1;
		movePath[6][0] = -1; movePath[6][1] = -2;
		movePath[7][0] = -2; movePath[7][1] = -1;
		
		int tmpEval[] = { 
			0, -3,  5,  4,  2,  2,  5,  4,  2,  2,
			-3,  2,  4,  6, 10, 12, 20, 10,  8,  2,
			2,  4,  6, 10, 13, 11, 12, 11, 15,  2,
			0,  5,  7,  7, 14, 15, 19, 15,  9,  8,
			2,-10,  4, 10, 15, 16, 12, 11,  6,  2,
			0,  5,  7,  7, 14, 15, 19, 15,  9,  8,
			2,  4,  6, 10, 13, 11, 12, 11, 15,  2,
			-3,  2,  4,  6, 10, 12, 20, 10,  8,  2,
			0, -3,  5,  4,  2,  2,  5,  4,  2,  2
		};
		
		this.eval = tmpEval;
	}

	@Override
	void move(Board b, boolean flag) {
		// TODO Auto-generated method stub
		move.removeAll(this.move);
		attack.removeAll(this.attack);
		for (int i = 0; i < 8; i++){
			int tmpPos[] = new int[2];
			tmpPos[0] = this.pos[0] + movePath[i][0]; tmpPos[1] = this.pos[1] + movePath[i][1];
			int bieJiao[] = new int[2];		int biePos[] = new int[2];
			bieJiao[0] = movePath[i][0] / 2;	bieJiao[1] = movePath[i][1] / 2;
			biePos[0] = this.pos[0] + bieJiao[0];	biePos[1] = this.pos[1] + bieJiao[1];
			if (tmpPos[0] >= 0 && tmpPos[0] <= 8){
				if (tmpPos[1] >= 0 && tmpPos[1] <= 9){
					Chess bieChess = b.findChess(biePos);
					if (bieChess == null)
						move.add(tmpPos);
				}
			}
		}
		
		for (int i = 0; i < move.size(); i++){
			int tmpMov[] = move.get(i);
			Chess tmpC = b.findChess(tmpMov);
			if (tmpC != null){
				if(tmpC.side != this.side){
					attack.add(tmpMov);
				}else{
					protect.add(tmpMov);
				}
				move.remove(i);
				i--;
			}
		}
		
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
			return 'K';
		}else{
			return 'k';
		}
	}

}
