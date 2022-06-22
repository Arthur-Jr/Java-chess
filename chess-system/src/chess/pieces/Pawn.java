package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "P";
	}
	
	private void takeOpponentPiece(Position position, boolean[][] moves) {
		if (this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			moves[position.getRow()][position.getColumn()] = true;
		}
	}
	
	public final boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position positionToMove = new Position(0, 0);
		
		if (this.getColor() == Color.WHITE) {
			positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn());
			if (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
				moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
				
				if (this.getMoveCount() == 0) {
					positionToMove.setPosition(this.position.getRow() - 2, this.position.getColumn());
					moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
				}
			}
			
			positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() + 1);
			this.takeOpponentPiece(positionToMove, moves);
			
			positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn() - 1);
			this.takeOpponentPiece(positionToMove, moves);

		} else {
			positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn());
			if (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
				moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
				
				if (this.getMoveCount() == 0) {
					positionToMove.setPosition(this.position.getRow() + 2, this.position.getColumn());
					moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
				}
			}
			
			positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() + 1);
			this.takeOpponentPiece(positionToMove, moves);
			
			positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn() - 1);
			this.takeOpponentPiece(positionToMove, moves);
		}
		
		
		return moves;
	}
}
