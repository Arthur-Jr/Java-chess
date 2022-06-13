package chess;

import boardgame.Position;

public class ChessPosition {
	private int row;
	private char column;
	
	public ChessPosition(int row, char column) {
		if (row < 'a' || row > 'h' || column < 1 || column > 8) {
			throw new ChessException("Invalid ChessPosition");
		}
		
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
	
	protected Position toPosition() {
		return new Position(8 - this.row, this.column - 'a');
	}
	
	protected static ChessPosition fromPostion(Position position) {
		return new ChessPosition(8 - position.getRow(), (char)('a' + position.getColumn()));
	}
	
	@Override
	public String toString() {
		return this.column + "-" + this.row;
	}
}
