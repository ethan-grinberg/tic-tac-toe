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
  //horizontal winner
  @Test
  public void testValidBoardWinnerX() {
    TicTacToeBoard board = new TicTacToeBoard("OOXXXXOXO");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }
  //vertical winner
  @Test
  public void testValidBoardWinnerO() {
    TicTacToeBoard board = new TicTacToeBoard("OX-OXXOOX");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testValidBoardDiagonalWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O-.XOXXXO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testValidBoardUnreachableState() {
    TicTacToeBoard board = new TicTacToeBoard("X--XX-XOO");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }
  //How do we test an invalid board entry? assertThrows?
}
