package com.example;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {
  //variable that stores the board string
  private String board;
  /**
   * This method should load a string into your TicTacToeBoard class.
   * @param setBoard The string representing the board
   */
  public TicTacToeBoard(String setBoard) {
    int numSquares = setBoard.length();
    //cite source
    int rowLength = (int) Math.sqrt(numSquares);
    if (rowLength * rowLength != numSquares) {
      throw new IllegalArgumentException();
    } else {
      board = setBoard;
      board = board.toLowerCase();
    }
  }

  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    Evaluation boardState = null;
    return boardState;
  }
}
