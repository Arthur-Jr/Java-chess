package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return this.moveCount;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPostion(this.position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece piece = (ChessPiece)this.getBoard().getPiece(position);
		return piece != null && piece.getColor() != this.color;
	}
	
	protected void increaseMoveCount() {
		this.moveCount += 1;
	}
	
	protected void decreaseMoveCount() {
		this.moveCount -= 1;
	}
}
