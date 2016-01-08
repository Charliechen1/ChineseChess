import java.util.ArrayList;

public class General extends Chess{

	General(int[] pos, boolean side) {
		super(pos, side);
		// TODO Auto-generated constructor stub
		movePath = new int[4][2];
		movePath[0][0] = 0; movePath[0][1] = 1;
		movePath[1][0] = 1; movePath[1][1] = 0;
		movePath[2][0] = 0; movePath[2][1] = -1;
		movePath[3][0] = -1; movePath[3][1] = 0;
		
		int tmpEval[] = { 
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			1, -8, -9,  0,  0,  0,  0,  0,  0,  0,
			5, -8, -9,  0,  0,  0,  0,  0,  0,  0,
			1, -8, -9,  0,  0,  0,  0,  0,  0,  0,
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
			tmpPos[0] = pos[0] + movePath[i][0];		tmpPos[1] = pos[1] + movePath[i][1];
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
				}
				move.remove(i);
				i--;
			}
		}

		//eliminate the cases facing the opponent's general
		for (int i = 0; i < move.size(); i++){
			int tmpMov[] = move.get(i);
			if(this.faceGeneral(tmpMov, b)){
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
			return 'G';
		}else{
			return 'g';
		}
	}

	boolean faceGeneral(int [] curPos, Board b){
		for (int j = curPos[1]; j < 10; j ++){
			int compPos[] = {curPos[0], j};
			Chess tmpC = b.findChess(compPos);
			
			if(tmpC != null){
				char tmpL = tmpC.getLabel();
				if ((tmpL == 'G' || tmpL == 'g') && tmpL != this.getLabel()){
					return true;
				}
				else
					return false;
			}
		}
		for (int j = curPos[1]; j >= 0; j --){
			int compPos[] = {curPos[0], j};
			Chess tmpC = b.findChess(compPos);
			
			if(tmpC != null){
				char tmpL = tmpC.getLabel();
				if ((tmpL == 'G' || tmpL == 'g') && tmpL != this.getLabel()){
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}
}
