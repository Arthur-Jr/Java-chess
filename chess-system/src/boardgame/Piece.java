package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return this.possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] allMoves = this.possibleMoves();
		for(int row = 0; row < allMoves.length; row += 1) {
			for(int col = 0; col < allMoves.length; col += 1) {
				if(allMoves[row][col]) {
					return true;
				}
			}
		}
		
		return false;
	}
}
