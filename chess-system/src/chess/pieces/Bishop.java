package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	private void takeOpponentPiece(Position position, boolean[][] moves) {
		if(this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			moves[position.getRow()][position.getColumn()] = true;
		}
	}
	
	public final boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position positionToMove = new Position(0, 0);
		
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() - 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setPosition(positionToMove.getRow() - 1, positionToMove.getColumn() - 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() + 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setPosition(positionToMove.getRow() - 1, positionToMove.getColumn() + 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() + 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setPosition(positionToMove.getRow() + 1, positionToMove.getColumn() + 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() - 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setPosition(positionToMove.getRow() + 1, positionToMove.getColumn() - 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
		
		return moves;
	}
}
