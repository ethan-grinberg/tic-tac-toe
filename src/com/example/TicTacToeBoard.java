package com.example;
import java.util.Arrays;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {
  //variable that stores the board string
  private String board;
  private int rowLength;
  private int numSquares;
  /**
   * This method should load a string into your TicTacToeBoard class.
   * @param setBoard The string representing the board
   */
  public TicTacToeBoard(String setBoard) {
    if (setBoard == null || setBoard.length() == 0) {
      throw new IllegalArgumentException();
    }
    int setNumSquares = setBoard.length();
    //cite source
    int setRowLength = (int) Math.sqrt(setNumSquares);
    if (setRowLength * setRowLength != setNumSquares) {
      throw new IllegalArgumentException();
    } else {
      board = setBoard.toLowerCase();
      rowLength = setRowLength;
      numSquares = setNumSquares;
    }
  }
  private boolean isUnreachableState(boolean xWin, boolean oWin, int xCount, int oCount) {
    if (xWin && oWin) {
      return true;
    } else if (!(xCount == oCount || (oCount + 1 == xCount))) {
      return true;
    }
    return false;
  }

  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    boolean xWin = false;
    boolean oWin = false;
    Evaluation boardState = null;

    //{row0, row1, row2, col1, col2, col3, Ldiag, Rdiag}
    int[] rowsColumnsDiagonals = new int[2*rowLength + 2];
    int xCount = 0;
    int oCount = 0;

    int rowNum = 0;
    for (int boardIndex = 0; boardIndex < numSquares; boardIndex++) {
      char player = board.charAt(boardIndex);
      //to change what row number the loop is on
      //make sure this is updated at correct time
      if (boardIndex % rowLength == 0 && boardIndex != 0) {
        rowNum++;
      }
      System.out.println(rowNum);
      //the column number of the current character
      int columnNum = (boardIndex % rowLength);

      int score = 0;
      if (player == 'x') {
        score = 1;
        xCount++;
      } else if (player == 'o') {
        score = -1;
        oCount++;
      }
      //set row score
      rowsColumnsDiagonals[rowNum] += score;

      //set column score
      rowsColumnsDiagonals[columnNum + rowLength] += score;

      //TODO
      //I think the diagonal is creating a bug
      //set diagonal score
      //I need to count the middle for both diagonals
      if (columnNum == rowNum) {
        //adds score to left diagonal
        rowsColumnsDiagonals[2 * rowLength] += score;
        if (((rowLength - 1) / 2) == rowNum) {
          rowsColumnsDiagonals[2 * rowLength + 1] += score;
        }
        //i != 0 might be redundant because of first conditional
      } else if (boardIndex != 0 && (boardIndex % (rowLength - 1) == 0)) {
        //checks if character is on right diagonal and adds score there
        //I believe that the i != rowNum makes sure middle and 0,0 isn't counted
        rowsColumnsDiagonals[2 * rowLength + 1] += score;
      }
    }

    System.out.println("R" + Arrays.toString(rowsColumnsDiagonals));
    for (int i = 0; i < rowsColumnsDiagonals.length; i++) {
      if (rowsColumnsDiagonals[i] == rowLength) {
        xWin = true;
        boardState = Evaluation.Xwins;
      } else if (rowsColumnsDiagonals[i] == -rowLength) {
        oWin = true;
        boardState = Evaluation.Owins;
      }
    }

    if(isUnreachableState(xWin, oWin, xCount, oCount)) {
      boardState = Evaluation.UnreachableState;
    } else if (!xWin && !oWin) {
      boardState = Evaluation.NoWinner;
    }

    return boardState;
  }
}
