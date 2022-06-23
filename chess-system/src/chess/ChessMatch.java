package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rock;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check, checkMate;
	private List<Piece> piecesOnBoard, capturedPieces;

	public ChessMatch() {
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		this.piecesOnBoard = new ArrayList<>();
		this.capturedPieces = new ArrayList<>();
		this.initialSetup();
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public boolean getCheckStatus() {
		return this.check;
	}
	
	public boolean getCheckMateStatus() {
		return this.checkMate;
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
	
	private void placePiece(char col, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(col, row).toPosition());
		this.piecesOnBoard.add(piece);
	}
	
	public ChessPiece performMove(ChessPosition sourcePostion, ChessPosition targetPostion) {
		Position source = sourcePostion.toPosition();
		Position target = targetPostion.toPosition();
		
		this.validateSourcePosition(source);
		this.validateTargetPosition(source, target);
		ChessPiece capturedPiece = (ChessPiece)this.makeMove(source, target);
		
		if (this.testCheck(currentPlayer)) {
			this.undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		this.check = this.testCheck(this.opponent(currentPlayer));
		
		if (this.testCheckMate(this.opponent(currentPlayer))) {
			this.checkMate = true; 
		} else {
			this.nextTurn();			
		}
		
		return capturedPiece;
	}
	
	private Piece makeMove(Position sourcePosition, Position targetPostion) {
		ChessPiece pieceToMove = (ChessPiece)this.board.removePiece(sourcePosition);
		pieceToMove.increaseMoveCount();
		Piece capturedPiece = this.board.removePiece(targetPostion);
		this.board.placePiece(pieceToMove, targetPostion);
		
		if (capturedPiece != null) {
			this.piecesOnBoard.remove(capturedPiece);
			this.capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece piece = (ChessPiece)this.board.removePiece(target);
		piece.decreaseMoveCount();
		this.board.placePiece(piece, source);
		
		if (capturedPiece != null) {
			this.board.placePiece(capturedPiece, target);
			this.capturedPieces.remove(capturedPiece);
			this.piecesOnBoard.add(capturedPiece);
		}
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		this.validateSourcePosition(position);
		
		return this.board.getPiece(position).possibleMoves();
	}
	
	private void nextTurn() {
		this.turn += 1;
		this.currentPlayer = this.currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return color == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece getKing(Color color) {
		List<Piece> pieces = this.piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece piece : pieces) {
			if (piece instanceof King) {
				return (ChessPiece)piece;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = this.getKing(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = this.piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == this.opponent(color)).collect(Collectors.toList());
		for (Piece piece : opponentPieces) {
			boolean[][] pieceMoves = piece.possibleMoves();
			if (pieceMoves[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!this.testCheck(color)) {
			return false;
		}
		
		List<Piece> playerPieces = this.piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece piece : playerPieces) {
			boolean[][] pieceMoves = piece.possibleMoves();
			for (int row = 0; row < this.board.getRows(); row += 1) {
				for (int col = 0; col < this.board.getColumns(); col += 1) {
					if (pieceMoves[row][col]) {
						Position source = ((ChessPiece)piece).getChessPosition().toPosition();
						Position target = new Position(row, col);
						Piece capturedPiece = this.makeMove(source, target);
						boolean checkTest = this.testCheck(color);
						this.undoMove(source, target, capturedPiece);
						
						if (!checkTest) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void initialSetup() {
		placePiece('a', 1, new Rock(board, Color.WHITE));
		placePiece('b', 1, new Knight(board, Color.WHITE));
		placePiece('c', 1, new Bishop(board, Color.WHITE));
		placePiece('d', 1, new Queen(board, Color.WHITE));
		placePiece('e', 1, new King(board, Color.WHITE));
		placePiece('f', 1, new Bishop(board, Color.WHITE));
		placePiece('g', 1, new Knight(board, Color.WHITE));
		placePiece('h', 1, new Rock(board, Color.WHITE));
		for (int i = 0; i < this.getPieces().length; i += 1) {
			placePiece((char)('a' + i), 2, new Pawn(board, Color.WHITE));
		}
		
		placePiece('a', 8, new Rock(board, Color.BLACK));
		placePiece('b', 8, new Knight(board, Color.BLACK));
		placePiece('c', 8, new Bishop(board, Color.BLACK));
		placePiece('d', 8, new Queen(board, Color.BLACK));
		placePiece('e', 8, new King(board, Color.BLACK));
		placePiece('f', 8, new Bishop(board, Color.BLACK));
		placePiece('g', 8, new Knight(board, Color.BLACK));
		placePiece('h', 8, new Rock(board, Color.BLACK));
		for (int i = 0; i < this.getPieces().length; i += 1) {
			placePiece((char)('a' + i), 7, new Pawn(board, Color.BLACK));
		}
	}
	
	private void validateSourcePosition(Position position) {
		if (!this.board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		
		if (!this.board.getPiece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for this piece");
		}
		
		if (((ChessPiece)this.board.getPiece(position)).getColor() != this.currentPlayer) {
			throw new ChessException("This piece is not yours");
		}
	}
	
	private void validateTargetPosition(Position sourceP, Position targetP) {
		if (!this.board.getPiece(sourceP).possibleMove(targetP)) {
			throw new ChessException("This piece can't move to target postiion");
		}
		
	}
}
