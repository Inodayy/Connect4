public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char brd[][];
	int j=0;
	int count=0;

	
	public Board() {
		this.brd = new char[10][10];
		this.reset();
		for (int w = 0; NUM_OF_COLUMNS -1 > w; w += 1) {
            for (int h = 0; NUM_OF_ROW +2> h; h += 1) {
				brd[w][h] = ' ';
				brd[5][h]='_';
				brd[5][7]=' ';
            }
        }
		
	}
	private void copyBoard(char[][] source, char[][] dest) {
		for(int i = 0; i < NUM_OF_ROW; i++) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				dest[i][j] = source[i][j];
			}
		}
	}
	
	public void printBoard() {
		
		j=0;
		while (brd[5-j][HumanPlayer.userMove-1]!=' ' && brd[5-j][HumanPlayer.userMove-1]!='_' && HumanPlayer.symbol0!='_' && j<5) {
 			j++;
 		} 
		while (brd[5-j][HumanPlayer.userMove-1]!=' ' && brd[5-j][HumanPlayer.userMove-1]!='_') {
			 System.out.println("Please input a valid move:");
			 HumanPlayer.userMove=HumanPlayer.sc.nextInt();
		   }
		j=0;
		while (brd[5-j][HumanPlayer.userMove-1]!=' ' && brd[5-j][HumanPlayer.userMove-1]!='_' && HumanPlayer.symbol0!='_' && j<5) {
 			j++;
 		} 
		
			brd[5-j][HumanPlayer.userMove-1]=HumanPlayer.symbol0;
			for (int w = 0; NUM_OF_COLUMNS -1 > w; w += 1) {
				
	            for (int h = 0; NUM_OF_ROW +2> h; h += 1) {
	            	
	                System.out.print("|"+brd[w][h]);                
	            }
	           
	            System.out.println();
	            
	        }
	     
			System.out.println();
		}
		
	public void addChecker(int colNum, char symbol) {

		if(this.isColumnFull(colNum)) {
			return;
		}
		
		int colIndex = colNum - 1;
		int rowIndex = NUM_OF_ROW - 1;
		while(brd[rowIndex][colIndex] != ' ') {
			rowIndex -= 1;
		}
		brd[rowIndex][colIndex] = symbol;
	}
	
	public boolean isColumnFull(int colNum) {
		int colIndex = colNum - 1;
		for(int i = 0; i < NUM_OF_ROW; i++) {
			if(brd[i][colIndex] == ' ') {
				return false;
			}
		}
		return true;
	}
	
	
	public int getColNum() {
		return NUM_OF_COLUMNS;
	}

	public int winPossible(char symbol) {
		char[][] boardCopy = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		this.copyBoard(this.brd, boardCopy);
		int ans = -1;
		int colNum = 1;
		
		while (colNum <= 7) {
			this.addChecker(colNum, symbol);
			if(this.containsWin()) {
				ans = colNum;
				break;
			}
			this.copyBoard(boardCopy, this.brd);
			colNum++;
		}
		
		this.copyBoard(boardCopy, this.brd);
		return ans;
	}
	
	public int blockPossible(char symbol) {

		char other = ' ';

		for(int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if(brd[i][j]!= ' ' && brd[i][j]!= symbol ) {
					other = brd[i][j];
				}
			}
		}
		return this.winPossible(other);
	}
	
	public boolean containsWin() {
		 // horizontal 
	    for (int w = 0; w<6 ; w++ ){
	        for (int h = 0; h<8; h++){
	            if (brd[h][w] == HumanPlayer.symbol0 && brd[h][w+1] == HumanPlayer.symbol0 && brd[h][w+2] == HumanPlayer.symbol0 && brd[h][w+3] == HumanPlayer.symbol0 && HumanPlayer.symbol0!='_'){
	                return true;
	            }           
	        }
	    }
	    // vertical
	    for (int h = 0; h<6; h++){
	        for (int w = 0; w<8 ; w++ ){
	            if (brd[h][w] == HumanPlayer.symbol0 && brd[h+1][w] == HumanPlayer.symbol0 && brd[h+2][w] == HumanPlayer.symbol0 && brd[h+3][w] == HumanPlayer.symbol0 && HumanPlayer.symbol0!='_'){
	                return true;
	            }           
	        }
	    }
	   // ascending diagonal 
	    for (int h = 3; h<8; h++){
	        for (int w = 0; w<6 ; w++ ){
	            if (brd[h][w] == HumanPlayer.symbol0 && brd[h-1][w+1] == HumanPlayer.symbol0 && brd[h-2][w+2] == HumanPlayer.symbol0 && brd[h-3][w+3] == HumanPlayer.symbol0 && HumanPlayer.symbol0!='_'){
	                return true;
	            }           
	        }
	    }
	   // descending diagonal
	    for (int h = 3; h<6; h++){
	        for (int w = 3; w<8 ; w++ ){
	            if (brd[h][w] == HumanPlayer.symbol0 && brd[h-1][w-1] == HumanPlayer.symbol0 && brd[h-2][w-2] == HumanPlayer.symbol0 && brd[h-3][w-3] == HumanPlayer.symbol0 && HumanPlayer.symbol0!='_'){
	                return true;
	            }           
	        }
	    }
		return false;
		
	}
	
	public boolean isTie() {
		for (int w = 0; NUM_OF_COLUMNS -1 > w; w += 1) {
            for (int h = 0; NUM_OF_ROW +2> h; h += 1) {
				if (brd[w][h] != ' '&& h!=7 && w!=5){
				 count++;
				}
	
            }
        }
		if (count == 35){
			return true;
		}
		count =0;
		return false;
	}
	
	public void reset() {
		
		for (int w = 0; NUM_OF_COLUMNS -1 > w; w += 1) {
            for (int h = 0; NUM_OF_ROW +2> h; h += 1) {
				brd[w][h] = ' ';
				brd[5][h]='_';
				brd[5][7]=' ';
            }
       }
	}
}