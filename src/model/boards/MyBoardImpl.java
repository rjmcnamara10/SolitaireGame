package model.boards;

import java.util.ArrayList;

import model.cards.Card;

/**
 * An interface containing the methods necessary to access the fields specific to my implementation
 * of a solitaire board.
 */
public interface MyBoardImpl extends GeneralSolitaireBoard {

  /**
   * Gets a copy of the array list of Cards representing the 'stock' pile.
   *
   * @return an array list which is a copy of the 'stock' field
   */
  ArrayList<Card> getStock();

  /**
   * Gets a copy of the array list of Cards representing the 'discard' pile.
   *
   * @return an array list which is a copy of the 'discard' field
   */
  ArrayList<Card> getDiscard();

  /**
   * Gets a copy of the array list of Cards representing the 'col1' pile.
   *
   * @return an array list which is a copy of the 'col1' field
   */
  ArrayList<Card> getCol1();

  /**
   * Gets a copy of the array list of Cards representing the 'col2' pile.
   *
   * @return an array list which is a copy of the 'col2' field
   */
  ArrayList<Card> getCol2();

  /**
   * Gets a copy of the array list of Cards representing the 'col3' pile.
   *
   * @return an array list which is a copy of the 'col3' field
   */
  ArrayList<Card> getCol3();

  /**
   * Gets a copy of the array list of Cards representing the 'col4' pile.
   *
   * @return an array list which is a copy of the 'col4' field
   */
  ArrayList<Card> getCol4();

  /**
   * Gets a copy of the array list of Cards representing the 'col5' pile.
   *
   * @return an array list which is a copy of the 'col5' field
   */
  ArrayList<Card> getCol5();

  /**
   * Gets a copy of the array list of Cards representing the 'col6' pile.
   *
   * @return an array list which is a copy of the 'col6' field
   */
  ArrayList<Card> getCol6();

  /**
   * Gets a copy of the array list of Cards representing the 'col7' pile.
   *
   * @return an array list which is a copy of the 'col7' field
   */
  ArrayList<Card> getCol7();

  /**
   * Gets a copy of the array list of Cards representing the 'spadeDiscard' pile.
   *
   * @return an array list which is a copy of the 'spadeDiscard' field
   */
  ArrayList<Card> getSpadeDiscard();

  /**
   * Gets a copy of the array list of Cards representing the 'clubDiscard' pile.
   *
   * @return an array list which is a copy of the 'clubDiscard' field
   */
  ArrayList<Card> getClubDiscard();

  /**
   * Gets a copy of the array list of Cards representing the 'heartDiscard' pile.
   *
   * @return an array list which is a copy of the 'heartDiscard' field
   */
  ArrayList<Card> getHeartDiscard();

  /**
   * Gets a copy of the array list of Cards representing the 'diamondDiscard' pile.
   *
   * @return an array list which is a copy of the 'diamondDiscard' field
   */
  ArrayList<Card> getDiamondDiscard();
}