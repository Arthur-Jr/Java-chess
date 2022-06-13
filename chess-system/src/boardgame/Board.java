package boardgame;

public class Board {
	private int rows, columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece getPiece(int row, int column) throws BoardException {
		if (!this.checkPostions(row, column)) {
			throw new BoardException("Invalid Position");
		}
		
		return this.pieces[row][column];
	}
	
	public Piece getPiece(Position position) throws BoardException {
		if (this.positionExists(position)) {
			throw new BoardException("Invalid Position");
		}
		
		return this.pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) throws BoardException {
		if (this.positionExists(position)) {
			throw new BoardException("Invalid Position");
		}
		
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	private boolean checkPostions(int row, int col) {
		 return row >= 0 && row < this.rows && col >= 0 && col < this.columns;
	}
	
	public boolean positionExists(Position position) {
		return this.checkPostions(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) throws BoardException {
		if (this.positionExists(position)) {
			throw new BoardException("Invalid Position");
		}
		
		return this.getPiece(position) != null;
	}
}
