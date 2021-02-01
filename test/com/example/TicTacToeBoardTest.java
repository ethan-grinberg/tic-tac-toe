package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TicTacToeBoardTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInValidBoard() {
    TicTacToeBoard board = new TicTacToeBoard(".9");
  }

  @Test
  public void testValidBoardNoWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O...X.X..");
    assertEquals(Evaluation.NoWinner, board.evaluate());
  }
  @Test
  public void testValidBoardUnreachableState() {
    TicTacToeBoard board = new TicTacToeBoard("X--XX-XOO");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }
  @Test
  public void testMoveAfterWinnerUnreachable() {
    TicTacToeBoard board = new TicTacToeBoard("--X-XOXOO");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  //3X3 board winners
  @Test
  public void testValidBoardWinnerXHorizontal() {
    TicTacToeBoard board = new TicTacToeBoard("OOXxXXOXo");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }
  @Test
  public void testValidBoardWinnerOVertical() {
    TicTacToeBoard board = new TicTacToeBoard("OX-OXXOO ");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testValidBoardLDiagonalWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O-.XOXXXO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  //4x4 board winner
  @Test
  public void testFourByFourWinnerRDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("OOOXXXxOOXOXX...");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }
}