import java.util.ArrayList;

public class Pawn extends Chess{

	Pawn(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		movePath = new int[4][2];
		movePath[0][0] = 0; movePath[0][1] = 1;
		movePath[1][0] = 0; movePath[1][1] = -1;
		movePath[2][0] = -1; movePath[2][1] = 0;
		movePath[3][0] = 1; movePath[3][1] = 0;
		
		int tmpEval[] = {
			0,  0,  0, -2,  3, 10, 20, 20, 20,  0,
			0,  0,  0,  0,  0, 18, 27, 30, 30,  0,
			0,  0,  0, -2,  4, 22, 30, 45, 50,  0,
			0,  0,  0,  0,  0, 35, 40, 55, 65,  2,
			0,  0,  0,  6,  7, 40, 42, 55, 70,  4,
			0,  0,  0,  0,  0, 35, 40, 55, 65,  2,
			0,  0,  0, -2,  4, 22, 30, 45, 50,  0,
			0,  0,  0,  0,  0, 18, 27, 30, 30,  0,
			0,  0,  0, -2,  3, 10, 20, 20, 20,  0
		};
		this.eval = tmpEval;
	}

	void move(Board b, boolean flag) {
		// TODO Auto-generated method stub
		move.removeAll(this.move);
		attack.removeAll(this.attack);
		if(side == false){
			int tmpPos[];
			if(pos[1] <= 4){
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[0][0];	tmpPos[1] = pos[1] + movePath[0][1];
				move.add(tmpPos);
			}else{
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[0][0];	tmpPos[1] = pos[1] + movePath[0][1];
				move.add(tmpPos);
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[2][0];	tmpPos[1] = pos[1] + movePath[2][1];
				move.add(tmpPos);
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[3][0];	tmpPos[1] = pos[1] + movePath[3][1];
				move.add(tmpPos);			
			}
		}else{
			int tmpPos[];
			if(pos[1] >= 5){
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[1][0];	tmpPos[1] = pos[1] + movePath[1][1];
				move.add(tmpPos);
			}else{
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[1][0];	tmpPos[1] = pos[1] + movePath[1][1];
				move.add(tmpPos);
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[2][0];	tmpPos[1] = pos[1] + movePath[2][1];
				move.add(tmpPos);
				tmpPos = new int[2];
				tmpPos[0] = pos[0] + movePath[3][0];	tmpPos[1] = pos[1] + movePath[3][1];
				move.add(tmpPos);
			}
		}
		for (int i = 0; i < move.size(); i++){
			int tmpMov[] = move.get(i);
			
			if(tmpMov[0] < 0 || tmpMov[1] < 0 || tmpMov[0] > 8 || tmpMov [1] > 9){
				move.remove(i);
				i--;
			}
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
			return 'P';
		}else{
			return 'p';
		}
	}

}
