package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public final class King extends ChessPiece {
	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece pieceOnPosition = (ChessPiece)this.getBoard().getPiece(position);
		return pieceOnPosition == null || pieceOnPosition.getColor() != this.getColor();
	}
	
	private void move(Position position, boolean[][] moves) {
		if (this.getBoard().positionExists(position) && this.canMove(position)) {
			moves[position.getRow()][position.getColumn()] = true;			
		}
	}
	
	@Override
	public final boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position positionToMove = new Position(0, 0);
		
		// white piece POV: up
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn());
		this.move(positionToMove, moves);
	
		// white piece POV: left
		positionToMove.setPosition(this.position.getRow(), this.position.getColumn() - 1);
		this.move(positionToMove, moves);
		
		// white piece POV: right
		positionToMove.setPosition(this.position.getRow(), this.position.getColumn() + 1);
		this.move(positionToMove, moves);
		
		// white piece POV: down
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn());
		this.move(positionToMove, moves);
		
		// white piece POV: nw
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() - 1);
		this.move(positionToMove, moves);
		
		// white piece POV: ne
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() + 1);
		this.move(positionToMove, moves);
		
		// white piece POV: sw
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() - 1);
		this.move(positionToMove, moves);
		
		// white piece POV: se
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() + 1);
		this.move(positionToMove, moves);
		
		return moves;
	}
}
