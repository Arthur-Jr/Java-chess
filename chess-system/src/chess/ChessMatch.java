package chess;

import boardgame.Board;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		this.board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];
		for (int row = 0; row < board.getRows(); row += 1) {
			for (int col = 0; col < board.getColumns(); col += 1) {
				pieces[row][col] = (ChessPiece) board.getPiece(row, col);
			}
		}
		
		return pieces;
	}
}
