

import java.util.ArrayList;
import java.util.Scanner;

public class play {

	public static void main(String args[]){
	
startgame();
		
	
	}

	private static void startgame() {
		int num = 1;
		Scanner s = new Scanner(System.in);
		player Player1 = takePlayerInput(num++);
		player Player2 = takePlayerInput(num);
		if(Player2.symbol==Player1.symbol){
			System.out.println("Sorry your character has already been taken");
			Player2 = takePlayerInput(num);
		}
		board b = new board(Player1.symbol, Player2.symbol);
		boolean Player1Turn = true;
		boolean done;
		while(b.gameStatus() == board.INCOMPLETE){
			done=false;
			if(Player1Turn){
				while(!done){

					System.out.println("Player 1 turn !!");
					ArrayList<returntype> arr= b.showpossiblemoves(Player1.symbol);
					b.printBoard();
					System.out.println("Enter x coordinate ");
					int x = s.nextInt();
					System.out.println("Enter y coordinate ");
					int y = s.nextInt();

					if(ispresent(arr,x,y)){
						b.makeMove(Player1.symbol, x, y);
						done =true;
						b.revert(arr,x,y);
					}
					else{
						System.out.println("Enter again from the highlighted values");
					}
				}
				Player1Turn = false;
			}
			else{
				while(!done){

					System.out.println("Player 2 turn !!");
					ArrayList<returntype> arr= b.showpossiblemoves(Player2.symbol);
					b.printBoard();

					System.out.println("Enter x coordinate ");
					int x = s.nextInt();
					System.out.println("Enter y coordinate ");
					int y = s.nextInt();
					if(ispresent(arr,x,y)){
						b.makeMove(Player2.symbol, x, y);
						done =true;
						b.revert(arr,x,y);
					}
					else{
						System.out.println("Enter again from the highlighted values");
					}
				}
				Player1Turn = true;
			}
			b.printBoard();
		}

		if(b.gameStatus()== board.Player_1_Wins){
			System.out.println("Player 1 Wins");
		}
		else if(b.gameStatus()== board.Player_2_Wins){
			System.out.println("Player 2 Wins !!");
		}
		else{
			System.out.println("It's a DRAW !!");
		}


	}






	private static boolean ispresent(ArrayList<returntype> arr, int x, int y) {
		for(returntype r : arr ){
			if(r.x==x&&r.y==y)
				return true;
		}
		return false;
	}

	private static player takePlayerInput(int num) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player "+num+" name :");
		String name = s.nextLine();
		System.out.println("Enter player "+num+" symbol :");
		int symbol = s.nextInt();
		player Player = new player(name, symbol);
		return Player;
	}
}
