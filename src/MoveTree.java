import java.util.*;

public class MoveTree implements Comparable<MoveTree>{
	MoveNode mNode;
	int esti = -65536;
	
	ArrayList<MoveTree> childNode;
	MoveTree(MoveNode mn){
		this.mNode = mn;
		this.childNode = new ArrayList<MoveTree>();
	}
	
	/**
	 * function to expand a current node
	 * @param side whether it is the user's or AI's turn
	 */
	void expand(boolean side){
		Board b = mNode.board; 
		childNode.removeAll(childNode);
		for (int i = 0; i < b.chesses.size(); i++){	
			if (b.chesses.get(i).side == side){
				
				b.chesses.get(i).move(b,true);
				for (int p = 0; p < b.chesses.get(i).move.size(); p ++){
					Board tmp = new Board();
					cpy(tmp, b);
					tmp.chesses.get(i).movAct(tmp, p);
					childNode.add(new MoveTree(new MoveNode(tmp)));
				}
				
				for (int p = 0; p < b.chesses.get(i).attack.size(); p ++){
					Board tmp = new Board();
					cpy(tmp, b);
					tmp.chesses.get(i).attAct(tmp, p);
					childNode.add(new MoveTree(new MoveNode(tmp)));
				}
			}
		}
	}
	
	/**
	 * the function for the alpha beta pruning
	 * @param depth the searching depth
	 * @param alpha h
	 * @param beta
	 * @param MaxizingPlayer whether it is the player to find the max value
	 * @return the estimation value
	 */
	int alphaBeta(int depth, int alpha, int beta, boolean MaxizingPlayer){
		if (depth == 0){
			this.esti = this.mNode.eval;
			return this.esti;
		}
		this.expand(MaxizingPlayer);
		if (MaxizingPlayer){
			int v = -65536;
			for (int i = 0; i < childNode.size(); i++){
				v = Math.max(v, childNode.get(i).alphaBeta(depth - 1, alpha, beta, false));
				alpha = Math.max(alpha, v);
				this.esti = alpha;
				if (beta <= alpha){
					break;
				}
			}
			return v;
		}
		else{
			int v = 65535;
			for (int i = 0; i < childNode.size(); i++){
				v = Math.min(v, childNode.get(i).alphaBeta(depth - 1, alpha, beta, true));
				beta = Math.min(beta, v);
				this.esti = beta;
				if (beta <= alpha){
					break;
				}
			}
			return v;
		}
	}
	
	/**
	 * function to deep clone a board
	 * @param tmp a temperate board
	 * @param b the current board
	 */
	void cpy(Board tmp, Board b){
		tmp.chesses.removeAll(tmp.chesses);
		for(int j = 0; j < b.chesses.size(); j++){
			Chess tmpC1,tmpC2;
			tmpC1 = b.chesses.get(j);
			tmpC2 = (Chess)tmpC1.clone();
			tmp.chesses.add(tmpC2);
		}
	}

	/**
	 * return the child with the largest estimation value
	 * @return the child
	 */
	MoveTree LargestChild(){
		MoveTree result = null;
		MoveTree tmp = null;
		int LEsti = -65536;
		for (int i = 0; i < childNode.size(); i++){
			tmp = childNode.get(i);
			if(tmp.esti > LEsti){
				LEsti = tmp.esti;
				result = tmp;
			}
		}
		return result;
	}
	
	
	@Override
	public int compareTo(MoveTree o) {
		// TODO Auto-generated method stub
		if(this.mNode.eval > o.mNode.eval)
			return -1;
		if(this.mNode.eval < o.mNode.eval)
			return 1;
		return 0;
	}
}
	