import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Game {
	public static void printBoard(char[] arr) {
		System.out.println(arr[0]+"|"+arr[1]+"|"+arr[2]);
		System.out.println("----------");
		System.out.println(arr[3]+"|"+arr[4]+"|"+arr[5]);
		System.out.println("----------");
		System.out.println(arr[6]+"|"+arr[7]+"|"+arr[8]);
	}
	
	public static boolean chkWin(HashSet<Integer> hs) {
		ArrayList<HashSet<Integer>> winScenarios = new ArrayList<HashSet<Integer>>();
		winScenarios.add(new HashSet<Integer>(Arrays.asList(0,1,2)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(3,4,5)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(6,7,8)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(0,3,6)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(1,4,7)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(2,5,8)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(0,4,8)));
		winScenarios.add(new HashSet<Integer>(Arrays.asList(2,4,6)));
		
		for(HashSet<Integer> h : winScenarios) {
			if(hs.containsAll(h)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		char[] board = {'0','1','2','3','4','5','6','7','8'};
		HashSet<Integer> p1 = new HashSet<>();
		HashSet<Integer> p2 = new HashSet<>();
		boolean gameRunning = true;
		while(gameRunning) {
			boolean isItPlayerOneTurn = true;
			Scanner scan = new Scanner(System.in);
			System.out.println("Welcome! You are playing Tic-Tac-Toe!");
			System.out.println("Player 1 Marker = X, Player 2 Marker = O");
				while(true) {
					if(isItPlayerOneTurn) {
						System.out.println("It is Player One's Turn!");
						isItPlayerOneTurn=false;
					}
					else {
						System.out.println("It is Player Two's Turn!");
						isItPlayerOneTurn=true;
					}
					
					printBoard(board);
					
					while(true) {
						System.out.println("Where would you like to place ur marker on the board? \n[Enter the number vaue of the location on the board where you would like to place your marker]: ");
						try {
							int choice = Integer.valueOf(scan.nextLine());
							if(choice<0 || choice>8) {
								System.out.println("Invalid Choice. Try Again!");
							}
							else if(board[choice] == 'X' || board[choice] == 'O') {
								System.out.println("That spot is already taken! Try Again!");
							}
							else {
								if(isItPlayerOneTurn==false){//playerOne turn
									board[choice] = 'X';
									p1.add(choice);
									break;
								}
								else {//playertwo turn
									board[choice] = 'O';
									p2.add(choice);
									break;
								}
								
							}
						}catch(Exception e) {
							System.out.println("Invalid Choice. Try Again!");
						}
					}
					
					
					if(chkWin(p1)) {
						printBoard(board);
						System.out.println("Player 1 has won!");
						break;
					}
					
					if(chkWin(p2)) {
						printBoard(board);
						System.out.println("Player 2 has won!");
						break;
					}
					
					if(p2.size()+p1.size()==9) {
						printBoard(board);
						System.out.println("It is a Tie!");
						break;
					}

				}
				
				

				while(true) {
					System.out.println("Would you like to play again? \n1. Yes\n2. No");
					try {
						int newGame = Integer.valueOf(scan.nextLine());
						if(newGame==1) {
							board[0] = '0';
							board[1] = '1';
							board[2] = '2';
							board[3] = '3';
							board[4] = '4';
							board[5] = '5';
							board[6] = '6';
							board[7] = '7';
							board[8] = '8';
							p1.clear();
							p2.clear();
							break;
						}
						else if(newGame==2) {
							System.out.println("Thank You For Playing! Goodbye!");
							gameRunning = false;
							break;
						}
						else {
							System.out.println("Invalid Choice. Try Again!");
						}
					}catch(Exception e) {
						System.out.println("Invalid Choice. Try Again!");
					}
				}
		}
	}
	
}
