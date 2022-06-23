package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {
	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "Q";
	}
	
	private void takeOpponentPiece(Position position, boolean[][] moves) {
		if(this.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			moves[position.getRow()][position.getColumn()] = true;
		}
	}
	
	private void bishopMove(Position positionToMove, boolean[][] moves) {
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
	}
	
	private void rockMove(Position positionToMove, boolean[][] moves) {
		positionToMove.setPosition(this.position.getRow() - 1, this.position.getColumn());
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setRow(positionToMove.getRow() - 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow(), this.position.getColumn() - 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setColumn(positionToMove.getColumn() - 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
		
		positionToMove.setPosition(this.position.getRow(), this.position.getColumn() + 1);
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove)) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setColumn(positionToMove.getColumn() + 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
	
		positionToMove.setPosition(this.position.getRow() + 1, this.position.getColumn());
		while (this.getBoard().positionExists(positionToMove) && !this.getBoard().thereIsAPiece(positionToMove) ) {
			moves[positionToMove.getRow()][positionToMove.getColumn()] = true;
			positionToMove.setRow(positionToMove.getRow() + 1);
		}
		this.takeOpponentPiece(positionToMove, moves);
	}
		
	public final boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position positionToMove = new Position(0, 0);
		
		this.bishopMove(positionToMove, moves);
		this.rockMove(positionToMove, moves);
		
		return moves;
	}
}
