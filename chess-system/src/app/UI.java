package app;

import chess.ChessPiece;

public class UI {
	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println();

		for (int row = 0; row < pieces.length; row += 1) {
			System.out.print(8 - row + " ");
			
			for (int col = 0; col < pieces.length; col += 1) {
				printPiece(pieces[row][col]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
