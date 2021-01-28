package com.example;
import java.util.Arrays;

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
  //sums the total score
  private int sumScore(int[] rows, int[] columns, int[] diagonals) {
    int rSum = Arrays.stream(rows).sum();
    int cSum = Arrays.stream(columns).sum();
    int dSum = Arrays.stream(diagonals).sum();
    return rSum + cSum + dSum;
  }
  //checks if board isn't reachable
  /*
  private boolean checkUnreachableState(boolean xWin, boolean oWin, int xCount, int oCount) {
    if (xWin && oWin) {
      return true;
    }
  }
   */

  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    boolean xWin = false;
    boolean oWin = false;
    Evaluation boardState = null;
    int rowLength = (int) Math.sqrt(board.length());


    int[] rows = new int[rowLength];
    int[] columns = new int[rowLength];
    int[] diagonals = new int[2];

    int rowNum = 0;
    int xCount = 0;
    int oCount = 0;
    for (int i = 0; i < board.length(); i++) {
      char player = board.charAt(i);
      //to change what row number the loop is on
      //make sure this is updated at correct time
      if (i % rowLength == 0 && i != 0) {
        rowNum++;
      }
      System.out.println(rowNum);

      int score = 0;
      if (player == 'x') {
        score = 1;
        xCount++;
      } else if (player == 'o') {
        score = -1;
        oCount++;
        //Added this condition for random characters
      } else {
        score = -1;
      }
      //set row score
      rows[rowNum] += score;

      //set column score
      columns[i % rowLength] += score;

      //set diagonal score
      //Make sure middle square doesn't get added twice
      if (i == rowNum) {
        //adds score to left diagonal
        diagonals[0] += score;
      } else {
        //adds score to right diagonal
        //this else statement might be incorrect logic
        diagonals[1] += score;
      }
    }
    //sums all of the arrays
    int totalScore = sumScore(rows, columns, diagonals);
    if (totalScore == rowLength) {
      xWin = true;
      boardState = Evaluation.Xwins;
    } else if (totalScore == -rowLength) {
      oWin = true;
      boardState = Evaluation.Owins;
    }

    //TODO
    /*
    if(checkUnreachableState(xWin, oWin, xCount, oCount)) {
      boardState = Evaluation.UnreachableState;
    } else {
    }
     */


    return boardState;
  }
}
