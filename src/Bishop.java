import java.util.ArrayList;

public class Bishop extends Chess{
	
	Bishop(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		movePath = new int[4][2];
		movePath[0][0] = 2; movePath[0][1] = 2;
		movePath[1][0] = 2; movePath[1][1] = -2;
		movePath[2][0] = -2; movePath[2][1] = 2;
		movePath[3][0] = -2; movePath[3][1] = -2;
		int tmpEval[] = { 
				0,  0, -2,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  3,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0, -2,  0,  0,  0,  0,  0,  0,  0
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
			int bieJiao[] = new int[2];		int biePos[] = new int[2];
			bieJiao[0] = movePath[i][0] / 2;	bieJiao[1] = movePath[i][1] / 2;
			biePos[0] = this.pos[0] + bieJiao[0];	biePos[1] = this.pos[1] + bieJiao[1];
			if (tmpPos[0] >= 0 && tmpPos[0] <= 8){
				if (this.side == false && tmpPos[1] >= 0 && tmpPos[1] <= 4){
					Chess bieChess = b.findChess(biePos);
					if (bieChess == null)
						move.add(tmpPos);
				}
				if (this.side == true && tmpPos[1] >= 5 && tmpPos[1] <= 9){
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
			return 'B';
		}else{
			return 'b';
		}
	}
	
}
