package com.example;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {
  private final String board;
  private final int rowLength;

  private boolean xWin;
  private boolean oWin;
  private int xCount;
  private int oCount;
  /**
   * This method should load a string into your TicTacToeBoard class.
   * @param setBoard The string representing the board
   */
  public TicTacToeBoard(String setBoard) {
    if (setBoard == null || setBoard.length() == 0) {
      throw new IllegalArgumentException();
    }
    //cite source
    int setRowLength = (int) Math.sqrt(setBoard.length());
    if (setRowLength * setRowLength != setBoard.length()) {
      throw new IllegalArgumentException();
    } else {
      board = setBoard.toLowerCase();
      rowLength = setRowLength;
    }
  }
  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    Evaluation boardState = null;

    int[] scores = tallyScore();
    for (int score : scores) {
      if (score == rowLength) {
        xWin = true;
        boardState = Evaluation.Xwins;
      } else if (score == -rowLength) {
        oWin = true;
        boardState = Evaluation.Owins;
      }
    }

    if (isUnreachableState()) {
      boardState = Evaluation.UnreachableState;
    } else if (!xWin && !oWin) {
      boardState = Evaluation.NoWinner;
    }

    return boardState;
  }
  private boolean isUnreachableState() {
    if (!(xCount == oCount || (oCount + 1 == xCount))) {
      return true;
      //checks for a move after a player has won
      //This is also works as a check for two winners
    } else if ((xWin && (oCount == xCount)) || (oWin && (xCount != oCount))) {
      return true;
    }
    return false;
  }

  private int[] tallyScore() {
    //{row0, row1, row2, col1, col2, col3, Ldiag, Rdiag}
    int[] rowsColumnsDiagonals = new int[2*rowLength + 2];

    int rowNum = 0;
    for (int boardIndex = 0; boardIndex < board.length(); boardIndex++) {
      char player = board.charAt(boardIndex);

      if (boardIndex % rowLength == 0 && boardIndex != 0) {
        rowNum++;
      }
      int columnNum = (boardIndex % rowLength);

      int score = 0;
      if (player == 'x') {
        score = 1;
        xCount++;
      } else if (player == 'o') {
        score = -1;
        oCount++;
      }
      //add row score
      rowsColumnsDiagonals[rowNum] += score;
      //add column score
      rowsColumnsDiagonals[columnNum + rowLength] += score;
      //add diagonal score
      if (columnNum == rowNum) {
        rowsColumnsDiagonals[2 * rowLength] += score;
        if (((rowLength - 1) / 2.0) == rowNum) {
          rowsColumnsDiagonals[2 * rowLength + 1] += score;
        }
      } else if ((boardIndex % (rowLength - 1) == 0)) {
        rowsColumnsDiagonals[2 * rowLength + 1] += score;
      }
    }
    return rowsColumnsDiagonals;
  }
}
