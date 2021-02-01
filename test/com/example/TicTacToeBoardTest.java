package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TicTacToeBoardTest {

  //invalid board and no winner on valid board
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBoard() {
    TicTacToeBoard board = new TicTacToeBoard(".9");
  }
  @Test
  public void testValidBoardNoWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O...X.X..");
    assertEquals(Evaluation.NoWinner, board.evaluate());
  }

  //unreachable states on valid boards
  @Test
  public void testUnreachableStateTwoWinners() {
    TicTacToeBoard board = new TicTacToeBoard(".ox.oxxox");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }
  @Test
  public void testUnreachableStateNoWinner() {
    TicTacToeBoard board = new TicTacToeBoard("x.x..x.o ");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }
  @Test
  public void testUnreachableStateMoveAfterWinner() {
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
    TicTacToeBoard board = new TicTacToeBoard("OX-OXXo  ");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testValidBoardLDiagonalWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O-..OXXXO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testValidBoardRDiagonalWinner() {
    TicTacToeBoard board = new TicTacToeBoard("abxpxox8o");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  //4x4 board tests
  @Test
  public void testFourByFourWinnerRDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("OOOXXXxOoXOXX ..");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }
  @Test
  public void testFourByFourWinnerLDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("o...xoxx..ox...o");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testFourByFourWinnerHorizontal() {
    TicTacToeBoard board = new TicTacToeBoard("....ooooxxx...x.");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
  @Test
  public void testFourByFourUnreachableState() {
    TicTacToeBoard board = new TicTacToeBoard("o...ooooxxx...x.");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }
}