package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chess = new ChessMatch();
		List<ChessPiece> capturedPieces = new ArrayList<>();
		
		while(!chess.getCheckMateStatus()) {
			try {
				UI.clearScreen();
				UI.printMatch(chess, capturedPieces);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.ReadNextPlay(sc);
				
				boolean[][] possibleMoves = chess.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chess.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.ReadNextPlay(sc);
				
				ChessPiece capturedPiece = chess.performMove(source, target);			
				if (capturedPiece != null) {
					capturedPieces.add(capturedPiece);
				}

			} catch (ChessException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println("Press Enter");
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println("Press Enter");
				sc.nextLine();
			}
		}
		
		UI.clearScreen();
		UI.printMatch(chess, capturedPieces);
	}

}
