package model.cards;

import java.awt.Color;

/**
 * An interface containing the methods necessary to access the fields specific to my implementation
 * of a playing card. Also defines the Suit and Rank enumerations.
 */
public interface MyCardImpl extends PlayingCard {
  enum Suit {Club, Spade, Diamond, Heart}
  enum Rank {Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King}

  /**
   * Gets the suit of this card.
   *
   * @return the suit of this card
   */
  Suit getSuit();

  /**
   * Gets the rank of this card.
   *
   * @return the rank of this card
   */
  Rank getRank();

  /**
   * Gets the color of this card.
   *
   * @return the color of this card
   */
  Color getColor();

  /**
   * Gets the state of this card (whether it is flipped over or not).
   *
   * @return true if this card is visible, false if it is flipped over
   */
  boolean getVisibility();
}
