package model.cards;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents a class for a card, contains a suit, rank, color, and visibility field.
 * The suit of a card is either Club, Spade, Diamond, or Heart.
 * The rank of a card is either Ace, Jack, Queen, King, or a number between 2 and 10 inclusive.
 * The color is black if the suit is Club or Spade and red if the suit is Diamond or Heart.
 * The visibility of a card is represented by a boolean value. True means the card is shown and
 * false means that it is flipped over.
 */
public class Card implements MyCardImpl {
  private final Suit suit;
  private final Rank rank;
  private final Color color;
  private boolean visible;

  /**
   * Provides a constructor for a Card given a suit and rank. Assigns the color appropriately and
   * sets the visibility field to false as default (flipped over).
   *
   * @param suit the suit of the card, options defined above in the Suit enumeration
   * @param rank the rank of the card, options defined above in the Rank enumeration
   */
  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
    if (suit == Suit.Club || suit == Suit.Spade) {
      this.color = Color.black;
    } else {
      this.color = Color.red;
    }
    this.visible = false;
  }

  public void flipCard() {
    this.visible = !this.visible; // change to setVisibility, better to know what visibility is changing to
  }

  public Suit getSuit() {
    return this.suit;
  }

  public Rank getRank() {
    return this.rank;
  }

  public Color getColor() {
    return this.color;
  }

  public boolean getVisibility() {
    return this.visible;
  }

  /**
   * Overrides the equals method to create extensional equality for Cards.
   * @param o the other object being compared
   * @return whether these objects are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Card)) {
      return false;
    } else {
      Card other = (Card) o;
      return ((this.suit == other.suit) && (this.rank == other.rank) && (this.color == other.color)
              && (this.visible == other.visible));
    }
  }

  /**
   * Overrides hashCode to be equals compliant.
   * @return the objects hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.suit, this.rank, this.color, this.visible);
  }
}