package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public final class Rock extends ChessPiece {
	public Rock(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public final boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position positionToMove = new Position(0, 0);
		
		// white piece POV: up
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn());
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setRow(positionToMove.getRow() - 1);
		}
		
		if(this.getBoard().positionExists(positionToMove) && this.isThereOpponentPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
		}
		
		// white piece POV: left
		positionToMove.setPosition(this.position.getRow(), this.position.getColumn() - 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setColumn(positionToMove.getColumn() - 1);
		}
		
		if(this.getBoard().positionExists(positionToMove) && this.isThereOpponentPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
		}
		
		// white piece POV: right
		positionToMove.setPosition(this.position.getRow(), this.position.getColumn() + 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setColumn(positionToMove.getColumn() + 1);
		}
		
		if(this.getBoard().positionExists(positionToMove) && this.isThereOpponentPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
		}
	
		// white piece POV: down
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn());
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove) ) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setRow(positionToMove.getRow() + 1);
		}
		
		if(this.getBoard().positionExists(positionToMove) && this.isThereOpponentPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
		}
		
		return moves;
	}
}
