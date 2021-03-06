package app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void printMatch(ChessMatch match, List<ChessPiece> capturedPieces) {
		printBoard(match.getPieces());
		System.out.println();
		
		PrintCapturedPieces(capturedPieces);
		System.out.println();
		
		if(!match.getCheckMateStatus()) {
			System.out.println("Turn: " + match.getTurn());
			System.out.println("Waiting player: " + match.getCurrentPlayer());
			
			if (match.getCheckStatus()) {
				System.out.println("CHECK!!");
			}			
		} else {
			System.out.println("CHECKMATE!!");
			System.out.println("Winner: " + match.getCurrentPlayer());
		}
		
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println();

		for (int row = 0; row < pieces.length; row += 1) {
			System.out.print(ANSI_BLUE + (8 - row) + " " + ANSI_RESET);
			
			for (int col = 0; col < pieces.length; col += 1) {
				printPiece(pieces[row][col], false);
			}
			System.out.println();
		}
		System.out.println(ANSI_BLUE + "  a b c d e f g h" + ANSI_RESET);
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.println();
		
		for (int row = 0; row < pieces.length; row += 1) {
			System.out.print(ANSI_BLUE + (8 - row) + " " + ANSI_RESET);

			for (int col = 0; col < pieces.length; col += 1) {
				printPiece(pieces[row][col], possibleMoves[row][col]);
			}
			System.out.println();
		}
		System.out.println(ANSI_BLUE + "  a b c d e f g h" + ANSI_RESET);
	}
	
	private static void printPiece(ChessPiece piece, boolean backgroundCondition) {
		if (backgroundCondition) {
			System.out.print(ANSI_CYAN_BACKGROUND);
		}
		
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
		}
		System.out.print(" ");
	}
	
	public static ChessPosition ReadNextPlay(Scanner sc) {
		try {
			String move = sc.nextLine();
			char col = move.charAt(0);
			int row = Integer.parseInt(move.substring(1));
			
			return new ChessPosition(col, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}
	
	private static void PrintCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured pieces:");
		System.out.print("White");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Black");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
