

import java.util.ArrayList;

public class board {
	static int player1Symbol;
	final static char blank = 9;

	static int player2Symbol;
	final static int Player_1_Wins=1;
	final static int Player_2_Wins=2;
	int[][] boardarr;
	private final static int XDir[] = {0,0,1,1,1,-1,-1,-1};
	private final static int YDir[] = {1,-1,0,1,-1,0,-1,1};
	public final static int DRAW = 3;
	public final static int INCOMPLETE = 4;

	public board(int symbol1,int symbol2){
		player1Symbol = symbol1;
		player2Symbol = symbol2;
		boardarr = new int[8][8];
		for(int i = 0 ; i < 8;i++){
			for(int j = 0; j<8;j++){
				boardarr[i][j] = blank;
			}
		}
		boardarr[3][3] = player1Symbol;
		boardarr[3][4] = player2Symbol;
		boardarr[4][3] = player2Symbol;
		boardarr[4][4] = player1Symbol;

	}

	public boolean makeMove(int s, int x, int y){

		boolean makeMove =false;

		for(int i = 0 ; i< XDir.length; i++){
			int count = 0 ;
			int xStep = XDir[i];
			int yStep = YDir[i];
			int currentX = x + xStep ;
			int currentY = y + yStep ;
			while(currentX>=0 && currentX<8 && currentY>=0 && currentY<8){

				if(boardarr[currentX][currentY]==blank){
					break ;
				}
				else if(boardarr[currentX][currentY]!= s ){
					count++;
					currentX += xStep ;
					currentY += yStep ;
				}
				else{
					if(count>0){
						makeMove = true;
						boardarr[x][y] = s;
						currentX -= xStep ;
						currentY -= yStep ;
						while(count!=0){
							boardarr[currentX][currentY]= s;
							currentX -= xStep ;
							currentY -= yStep ;
							count--;
						}
					}
					break;
				}
			}

		}
		return makeMove;
	}
	public boolean Move(int s, int x, int y){
		boolean makeMove =false;

		for(int i = 0 ; i< XDir.length; i++){
			int count = 0 ;
			int xStep = XDir[i];
			int yStep = YDir[i];
			int currentX = x + xStep ;
			int currentY = y + yStep ;
			while(currentX>=0 && currentX<8 && currentY>=0 && currentY<8){

				if(boardarr[currentX][currentY]==blank||boardarr[currentX][currentY]==0){
					break ;
				}
				else if(boardarr[currentX][currentY]!= s ){
					count++;
					currentX += xStep ;
					currentY += yStep ;
				}
				else{
					if(count>0){
						makeMove = true;
						boardarr[x][y] = 0;

					}
					return makeMove;
				}
			}
		}


		return makeMove;
	}


	public int gameStatus(){
		int count1=0, count2=0;
		for(int j =0;j< 8; j++){
			for(int k = 0 ; k <8 ; k++){
				if(boardarr[j][k]==player1Symbol)
					count1++;	

				else if(boardarr[j][k]==player2Symbol)
					count2++;
			}
		}

		if(count1+count2==64){
			if(count1>count2)
				return Player_1_Wins;
			else if(count2>count1)
				return Player_2_Wins;
			else
				return DRAW;
		}
		return INCOMPLETE;
	}

	public ArrayList<returntype> showpossiblemoves(int symbol){
		ArrayList<returntype> arr = new ArrayList<>();	
		for(int j =0;j< 8; j++){
			for(int k = 0 ; k <8 ; k++){
				if(Move(symbol,j,k)){
					returntype r = new returntype();
					r.x =j;
					r.y = k;
					arr.add(r);

				}
			}
		}

		return arr;
	}

	public void printBoard() {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.print(" | "+ boardarr[i][j] +" | ");
			}
			System.out.println();
		}
	}

	public void revert(ArrayList<returntype> arr,int x,int y) {
		int i =0 ;
		while(i<arr.size()){
			if(arr.get(i).x==x && arr.get(i).y==y){
							i++;
			}
			else{
				boardarr[arr.get(i).x][arr.get(i).y]=9;
	
				i++;
			}


		}	

	}


}


