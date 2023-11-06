import java.util.Random;

public class AIPlayer extends Player{
	
	private int AIMoveCount;
	private int randomCol;
	private Random random;
	
	public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
        AIMoveCount = 0;
        randomCol = 0;
        random = new Random();
    }
	
	@Override
	public void makeMove(Board board) {
		int canWin;
		
		if(AIMoveCount < 2) {
			
			randomCol = random.nextInt(board.getColNum());
			board.addChecker(randomCol + 1, symbol);
			
			AIMoveCount++;

			return;
		} 
		
		canWin = board.winPossible(symbol);
		if(canWin != -1) {
			board.addChecker(canWin, symbol);	
			return;
		}

		canWin = board.blockPossible(symbol);
		if (canWin != -1) {
			board.addChecker(canWin, symbol);
			return;
		}

		randomCol = random.nextInt(board.getColNum());

		while(this.board.isColumnFull(randomCol + 1)) {
			randomCol = random.nextInt(board.getColNum());
		}
		board.addChecker(randomCol + 1, symbol);
    }
}