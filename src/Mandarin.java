
public class Mandarin extends Chess{

	Mandarin(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		movePath = new int[4][2];
		movePath[0][0] = 1; movePath[0][1] = 1;
		movePath[1][0] = -1; movePath[1][1] = -1;
		movePath[2][0] = -1; movePath[2][1] = 1;
		movePath[3][0] = 1; movePath[3][1] = -1;
		
		int tmpEval[] = { 
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  3,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0
		};
			
		this.eval = tmpEval;
	}

	@Override
	void move(Board b, boolean flag) {
		// TODO Auto-generated method stub
		move.removeAll(this.move);
		attack.removeAll(this.attack);
		for (int i = 0; i < 4; i++){
			int tmpPos[] = new int[2];
			tmpPos[0] = this.pos[0] + movePath[i][0]; tmpPos[1] = this.pos[1] + movePath[i][1];
			if (tmpPos[0] >= 3 && tmpPos[0] <= 5){
				if (this.side == false && tmpPos[1] >= 0 && tmpPos[1] <= 2){
					move.add(tmpPos);
				}
				if (this.side == true && tmpPos[1] >= 7 && tmpPos[1] <= 9){
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
			return 'M';
		}else{
			return 'm';
		}
	}

}
