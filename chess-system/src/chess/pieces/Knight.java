package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {
	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
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
	
	public final boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position positionToMove = new Position(0, 0);
		
		positionToMove.setPosition(this.position.getRow() - 2, this.position.getColumn() - 1);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() - 2, this.position.getColumn() + 1);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() + 2, this.position.getColumn() - 1);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() + 2, this.position.getColumn() + 1);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() + 2);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() + 2);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() - 2);
		this.move(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() - 2);
		this.move(positionToMove, moves);
		
		return moves;
	}
}
