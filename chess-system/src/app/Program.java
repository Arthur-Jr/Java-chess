package app;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chess = new ChessMatch();
		
		while(true) {
			UI.printBoard(chess.getPieces());
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.ReadNextPlay(sc);
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.ReadNextPlay(sc);
			
			ChessPiece capturedPiece = chess.performMove(source, target);
		}
	}

}
