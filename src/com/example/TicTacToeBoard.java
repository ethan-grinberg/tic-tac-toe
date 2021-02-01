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
   * This constructor loads a board into the class and checks for invalid board
   * @param setBoard The string representing a nxn board
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
   * Checks the state of any nxn board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    Evaluation boardState = null;

    int[] sectionScores = sumSectionScores();
    for (int score : sectionScores) {
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

  //returns an array with the sum of the scores in each section of the board
  // one X being a score of 1, and one O being a score of -1
  private int[] sumSectionScores() {
    int numSections = 2 * rowLength + 2;
    //Stored in the format:
    // {row0, row1, row2, col0, col2, col3, Ldiag, Rdiag}
    int[] sectionScores = new int[numSections];

    int rowNum = 0;
    for (int boardIndex = 0; boardIndex < board.length(); boardIndex++) {

      int columnNum = (boardIndex % rowLength);
      if (columnNum == 0 && boardIndex != 0) {
        rowNum++;
      }

      char player = board.charAt(boardIndex);
      int playerScore = 0;
      if (player == 'x') {
        playerScore = 1;
        xCount++;
      } else if (player == 'o') {
        playerScore = -1;
        oCount++;
      }
      //add player score to corresponding row section score
      sectionScores[rowNum] += playerScore;

      //add player score to corresponding column section score
      sectionScores[columnNum + rowLength] += playerScore;

      //add player score to corresponding diagonal section score if appropriate
      if (columnNum == rowNum) {
        sectionScores[numSections - 2] += playerScore;
        //This checks if the character is in the middle square
        if (((rowLength - 1) / 2.0) == rowNum) {
          sectionScores[numSections - 1] += playerScore;
        }
      } else if ((boardIndex % (rowLength - 1) == 0)) {
        sectionScores[numSections - 1] += playerScore;
      }
    }

    return sectionScores;
  }

  //returns true if board state is unreachable, false otherwise
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
}
