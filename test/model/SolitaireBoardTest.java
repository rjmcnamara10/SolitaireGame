package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.boards.MyBoardImpl;
import model.boards.SolitaireBoard;
import model.cards.Card;
import model.cards.MyCardImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolitaireBoardTest {
  MyBoardImpl board;

  @Before
  public void setup() {
    board = new SolitaireBoard();
  }

  @Test
  public void testGetShuffledDeck() {
    ArrayList<Card> deck = board.getShuffledDeck();
    assertEquals(52, deck.size());
    assertTrue(board.getDiscard().isEmpty());
    assertTrue(board.getCol1().isEmpty());
    assertTrue(board.getCol2().isEmpty());
    assertTrue(board.getCol3().isEmpty());
    assertTrue(board.getCol4().isEmpty());
    assertTrue(board.getCol5().isEmpty());
    assertTrue(board.getCol6().isEmpty());
    assertTrue(board.getCol7().isEmpty());
    assertTrue(board.getSpadeDiscard().isEmpty());
    assertTrue(board.getClubDiscard().isEmpty());
    assertTrue(board.getHeartDiscard().isEmpty());
    assertTrue(board.getDiamondDiscard().isEmpty());
  }

  @Test
  public void testClearBoard() {
    // fill up board first
    board.clearBoard();
    assertTrue(board.getStock().isEmpty());
    assertTrue(board.getDiscard().isEmpty());
    assertTrue(board.getCol1().isEmpty());
    assertTrue(board.getCol2().isEmpty());
    assertTrue(board.getCol3().isEmpty());
    assertTrue(board.getCol4().isEmpty());
    assertTrue(board.getCol5().isEmpty());
    assertTrue(board.getCol6().isEmpty());
    assertTrue(board.getCol7().isEmpty());
    assertTrue(board.getSpadeDiscard().isEmpty());
    assertTrue(board.getClubDiscard().isEmpty());
    assertTrue(board.getHeartDiscard().isEmpty());
    assertTrue(board.getDiamondDiscard().isEmpty());
  }

  @Test
  public void testArrangeCards() {
    board.arrangeCards();
    ArrayList<Card> stockCopy = board.getStock();
    ArrayList<Card> col1Copy = board.getCol1();
    ArrayList<Card> col2Copy = board.getCol2();
    ArrayList<Card> col3Copy = board.getCol3();
    ArrayList<Card> col4Copy = board.getCol4();
    ArrayList<Card> col5Copy = board.getCol5();
    ArrayList<Card> col6Copy = board.getCol6();
    ArrayList<Card> col7Copy = board.getCol7();
    assertEquals(24, stockCopy.size());
    for (Card c : stockCopy) {
      assertTrue(c.getVisibility());
    }
    assertEquals(1, col1Copy.size());
    assertEquals(2, col2Copy.size());
    assertEquals(3, col3Copy.size());
    assertEquals(4, col4Copy.size());
    assertEquals(5, col5Copy.size());
    assertEquals(6, col6Copy.size());
    assertEquals(7, col7Copy.size());
    assertTrue(col1Copy.get(0).getVisibility());
    assertTrue(col2Copy.get(1).getVisibility());
    assertTrue(col3Copy.get(2).getVisibility());
    assertTrue(col4Copy.get(3).getVisibility());
    assertTrue(col5Copy.get(4).getVisibility());
    assertTrue(col6Copy.get(5).getVisibility());
    assertTrue(col7Copy.get(6).getVisibility());
    assertTrue(board.getDiscard().isEmpty());
    assertTrue(board.getSpadeDiscard().isEmpty());
    assertTrue(board.getClubDiscard().isEmpty());
    assertTrue(board.getHeartDiscard().isEmpty());
    assertTrue(board.getDiamondDiscard().isEmpty());
  }

  @Test
  public void testMoveErrors() {
    Card spadeAce = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Ace);
    Card spadeTwo = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Two);
    Card spadeThree = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Three);
    Card spadeFour = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Four);
    Card spadeFive = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Five);
    Card spadeSix = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Six);
    Card spadeSeven = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Seven);
    Card spadeEight = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Eight);
    Card spadeNine = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Nine);
    Card spadeTen = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Ten);
    Card spadeJack = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Jack);
    Card spadeQueen = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.Queen);
    Card spadeKing = new Card(MyCardImpl.Suit.Spade, MyCardImpl.Rank.King);

    Card clubAce = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Ace);
    Card clubTwo = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Two);
    Card clubThree = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Three);
    Card clubFour = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Four);
    Card clubFive = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Five);
    Card clubSix = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Six);
    Card clubSeven = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Seven);
    Card clubEight = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Eight);
    Card clubNine = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Nine);
    Card clubTen = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Ten);
    Card clubJack = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Jack);
    Card clubQueen = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.Queen);
    Card clubKing = new Card(MyCardImpl.Suit.Club, MyCardImpl.Rank.King);

    Card heartAce = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Ace);
    Card heartTwo = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Two);
    Card heartThree = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Three);
    Card heartFour = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Four);
    Card heartFive = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Five);
    Card heartSix = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Six);
    Card heartSeven = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Seven);
    Card heartEight = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Eight);
    Card heartNine = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Nine);
    Card heartTen = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Ten);
    Card heartJack = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Jack);
    Card heartQueen = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.Queen);
    Card heartKing = new Card(MyCardImpl.Suit.Heart, MyCardImpl.Rank.King);

    Card diamondAce = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Ace);
    Card diamondTwo = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Two);
    Card diamondThree = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Three);
    Card diamondFour = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Four);
    Card diamondFive = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Five);
    Card diamondSix = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Six);
    Card diamondSeven = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Seven);
    Card diamondEight = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Eight);
    Card diamondNine = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Nine);
    Card diamondTen = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Ten);
    Card diamondJack = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Jack);
    Card diamondQueen = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.Queen);
    Card diamondKing = new Card(MyCardImpl.Suit.Diamond, MyCardImpl.Rank.King);

    ArrayList<Card> stock = new ArrayList<>(Arrays.asList(spadeNine, heartTwo, heartEight,
            diamondFive, clubFive, clubFour, diamondQueen, clubQueen, diamondThree, diamondSix,
            heartSeven, spadeFour, spadeAce, clubJack, heartFive, spadeEight, spadeFive,
            diamondNine, clubKing, clubTwo, heartAce, spadeSeven, clubSix, spadeKing));
    ArrayList<Card> discard = new ArrayList<>();
    // clubEight
    ArrayList<Card> col1 = new ArrayList<>();
    ArrayList<Card> col2 = new ArrayList<>(Arrays.asList(heartNine, diamondSeven));
    ArrayList<Card> col3 = new ArrayList<>(Arrays.asList(spadeThree, heartFour, spadeTen));
    ArrayList<Card> col4 = new ArrayList<>(Arrays.asList(spadeSix, diamondKing, heartThree,
            clubTen));
    ArrayList<Card> col5 = new ArrayList<>(Arrays.asList(clubSeven, diamondEight, diamondTwo,
            heartSix, heartQueen));
    ArrayList<Card> col6 = new ArrayList<>(Arrays.asList(clubNine, spadeJack, heartTen, spadeQueen,
            heartJack, diamondAce));
    ArrayList<Card> col7 = new ArrayList<>(Arrays.asList(diamondTen, clubAce, clubThree, heartKing,
            spadeTwo, diamondFour, diamondJack));
    ArrayList<Card> spadeDiscard = new ArrayList<>();
    ArrayList<Card> clubDiscard = new ArrayList<>();
    ArrayList<Card> heartDiscard = new ArrayList<>();
    ArrayList<Card> diamondDiscard = new ArrayList<>();
    SolitaireBoard board2 = new SolitaireBoard(stock, discard, col1, col2, col3, col4, col5, col6,
            col7, spadeDiscard, clubDiscard, heartDiscard, diamondDiscard);

    // clubEight | diamondSeven | spadeTen | clubTen | heartQueen | diamondAce | diamondJack

    try {
      board2.move(0, new ArrayList<>(List.of(clubTen)), 7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Moving column must be an integer between 1 and 12.", e.getMessage());
    }
    try {
      board2.move(13, new ArrayList<>(List.of(clubTen)), 7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Moving column must be an integer between 1 and 12.", e.getMessage());
    }
    try {
      board2.move(4, new ArrayList<>(List.of(clubTen)), 0);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Destination column must be an integer between 1 and 11.",
              e.getMessage());
    }
    try {
      board2.move(4, new ArrayList<>(List.of(clubTen)), 12);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Destination column must be an integer between 1 and 11.",
              e.getMessage());
    }
    try {
      board2.move(4, new ArrayList<>(), 7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Cannot move empty list of cards.", e.getMessage());
    }
    try {
      board2.move(12, new ArrayList<>(Arrays.asList(heartThree, clubTen)), 7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("When moving from a discard pile, you may only move one card at a "
              + "time.", e.getMessage());
    }
    try {
      board2.move(10, new ArrayList<>(Arrays.asList(heartThree, clubTen)), 7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("When moving from a discard pile, you may only move one card at a "
              + "time.", e.getMessage());
    }
    try {
      board2.move(4, new ArrayList<>(Arrays.asList(diamondEight, heartThree, clubTen)),
              7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("The column associated with the given number must end with the given "
              + "cards to be moved.", e.getMessage());
    }
    try {
      board2.move(6, new ArrayList<>(Arrays.asList(heartThree, clubTen)),
              7);
    }
    catch (IllegalArgumentException e) {
      assertEquals("The column associated with the given number must end with the given "
              + "cards to be moved.", e.getMessage());
    }
    try {
      board2.move(2, new ArrayList<>(List.of(diamondSeven)), 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid card rank for empty pile.", e.getMessage());
    }
    try {
      board2.move(2, new ArrayList<>(List.of(diamondSeven)), 11);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid card rank for empty pile.", e.getMessage());
    }
  }
}