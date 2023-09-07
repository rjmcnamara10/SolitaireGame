package model.boards;

import java.util.ArrayList;

import model.cards.Card;

/**
 * Represents an interface containing the base methods necessary for a game of solitaire.
 */
public interface GeneralSolitaireBoard {

  /**
   * Returns a deck of 52 cards.
   *
   * @return an array list of Cards representing a shuffled deck of 52 cards.
   */
  ArrayList<Card> getShuffledDeck();

  /**
   * Clears the board of all cards.
   */
  void clearBoard();

  /**
   * Arranges cards for a game of solitaire.
   */
  void arrangeCards();

  /**
   * Attempts to move a set of cards from one column to the end of another column.
   *
   * @param movingColNum a number representing the column/pile where a set of cards are being moved
   *                     from
   * @param movingCards an array list representing a set of cards that are being moved
   * @param destColNum a number representing the column/pile where a set of cards are being moved to
   * @throws IllegalArgumentException if the requested move is invalid
   */
  void move(int movingColNum, ArrayList<Card> movingCards, int destColNum)
          throws IllegalArgumentException;

  /**
   * Performs an action for when the 'stock' pile of cards is selected
   */
  void stockAction();
}
