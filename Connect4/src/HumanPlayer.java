import java.util.Scanner;

public class HumanPlayer extends Player{
	static int userMove=1;
	static char symbol0='_';
	
	static Scanner sc = new Scanner(System.in);
	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	
	}
	
	public void makeMove(Board board) {
		System.out.println(name+" please input your move (numbers 1-7):");
		userMove = sc.nextInt();
		symbol0 = symbol;
	}

}