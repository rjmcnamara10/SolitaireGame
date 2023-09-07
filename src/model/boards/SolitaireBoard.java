package model.boards;

import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;

public class SolitaireBoard implements MyBoardImpl {
  private final ArrayList<Card> stock;
  private final ArrayList<Card> discard;
  private final ArrayList<Card> col1;
  private final ArrayList<Card> col2;
  private final ArrayList<Card> col3;
  private final ArrayList<Card> col4;
  private final ArrayList<Card> col5;
  private final ArrayList<Card> col6;
  private final ArrayList<Card> col7;
  private final ArrayList<Card> spadeDiscard;
  private final ArrayList<Card> clubDiscard;
  private final ArrayList<Card> heartDiscard;
  private final ArrayList<Card> diamondDiscard;

  /**
   * Default constructor for a SolitaireBoard, sets each field to an empty array list of Cards.
   */
  public SolitaireBoard() {
    this.stock = new ArrayList<>();
    this.discard = new ArrayList<>();
    this.col1 = new ArrayList<>();
    this.col2 = new ArrayList<>();
    this.col3 = new ArrayList<>();
    this.col4 = new ArrayList<>();
    this.col5 = new ArrayList<>();
    this.col6 = new ArrayList<>();
    this.col7 = new ArrayList<>();
    this.spadeDiscard = new ArrayList<>();
    this.clubDiscard = new ArrayList<>();
    this.heartDiscard = new ArrayList<>();
    this.diamondDiscard = new ArrayList<>();
  }

  /**
   * Constructor for a SolitaireBoard that accepts 13 array lists of Cards as arguments and sets
   * the fields of the SolitaireBoard to the given Card groupings, utilized during testing.
   */
  public SolitaireBoard(ArrayList<Card> stock, ArrayList<Card> discard, ArrayList<Card> col1,
                        ArrayList<Card> col2, ArrayList<Card> col3, ArrayList<Card> col4,
                        ArrayList<Card> col5, ArrayList<Card> col6, ArrayList<Card> col7,
                        ArrayList<Card> spadeDiscard, ArrayList<Card> clubDiscard,
                        ArrayList<Card> heartDiscard, ArrayList<Card> diamondDiscard) {
    this.stock = stock;
    this.discard = discard;
    this.col1 = col1;
    this.col2 = col2;
    this.col3 = col3;
    this.col4 = col4;
    this.col5 = col5;
    this.col6 = col6;
    this.col7 = col7;
    this.spadeDiscard = spadeDiscard;
    this.clubDiscard = clubDiscard;
    this.heartDiscard = heartDiscard;
    this.diamondDiscard = diamondDiscard;
  }

  /**
   * Returns a deck of 52 cards.
   *
   * @return an array list of Cards representing a shuffled deck of 52 cards.
   */
  public ArrayList<Card> getShuffledDeck() {
    ArrayList<Card> deck = new ArrayList<>();
    for (Card.Suit suit : Card.Suit.values()) {
      for (Card.Rank rank : Card.Rank.values()) {
        deck.add(new Card(suit, rank));
      }
    }
    Collections.shuffle(deck);
    return deck;
  }

  /**
   * Clears the board by emptying each list of cards.
   */
  public void clearBoard() {
    this.stock.clear();
    this.discard.clear();
    this.col1.clear();
    this.col2.clear();
    this.col3.clear();
    this.col4.clear();
    this.col5.clear();
    this.col6.clear();
    this.col7.clear();
    this.spadeDiscard.clear();
    this.clubDiscard.clear();
    this.heartDiscard.clear();
    this.diamondDiscard.clear();
  }

  /**
   * Arranges cards for a game of solitaire. Outcome is functionally equal to the method outlined
   * at the following link: <a href="https://www.wikihow.com/Set-Up-Solitaire">...</a>.
   */
  public void arrangeCards() {
    this.clearBoard();
    this.stock.addAll(this.getShuffledDeck());
    this.col1.add(this.stock.remove(0));
    for (int i = 0; i < 7; i++) {
      if (col1.size() < 1) {
        this.col1.add(this.stock.remove(0));
      }
      if (col2.size() < 2) {
        this.col2.add(this.stock.remove(0));
      }
      if (col3.size() < 3) {
        this.col3.add(this.stock.remove(0));
      }
      if (col4.size() < 4) {
        this.col4.add(this.stock.remove(0));
      }
      if (col5.size() < 5) {
        this.col5.add(this.stock.remove(0));
      }
      if (col6.size() < 6) {
        this.col6.add(this.stock.remove(0));
      }
      this.col7.add(this.stock.remove(0));
    }
    this.col1.get(0).flipCard();
    this.col2.get(1).flipCard();
    this.col3.get(2).flipCard();
    this.col4.get(3).flipCard();
    this.col5.get(4).flipCard();
    this.col6.get(5).flipCard();
    this.col7.get(6).flipCard();
    for (Card c : this.stock) {
      c.flipCard();
    }
  }

  /**
   * Attempts to move a set of cards from one column to the end of another column.
   * Column numbers:
   * 1 --> col1
   * 2 --> col2
   * 3 --> col3
   * 4 --> col4
   * 5 --> col5
   * 6 --> col6
   * 7 --> col7
   * 8 --> spadeDiscard
   * 9 --> clubDiscard
   * 10 --> heartDiscard
   * 11 --> diamondDiscard
   * 12 --> discard
   *
   * @param movingColNum a number representing the column where a set of cards are being moved from
   * @param movingCards  an array list representing a set of cards that are being moved
   * @param destColNum   a number representing the column where a set of cards are being moved to
   * @throws IllegalArgumentException if movingColNum is not an integer between 1 and 12
   * @throws IllegalArgumentException if destColNum is not an integer between 1 and 11
   * @throws IllegalArgumentException if movingCards is empty
   * @throws IllegalArgumentException if attempting to move multiple Cards from a discard pile
   * @throws IllegalArgumentException if the column associated with the given number doesn't end
   *                                  with the given cards to be moved
   * @throws IllegalArgumentException if the destination column is a column 1-7, it is empty and
   *                                  the suit of the first card being moved is not a 'King'
   * @throws IllegalArgumentException if the destination column is an empty suit discard pile and
   *                                  the suit of the first card being moved is not an 'Ace'
   * @throws IllegalArgumentException moving to a column 1-7: if the last card in the destination
   *                                  column and the first card in the movingCards list are the
   *                                  same color
   * @throws IllegalArgumentException moving to a column 1-7: if the rank pairing of the last card
   *                                  in the destination column and the first card in the
   *                                  movingCards list is invalid based on the rules of Solitaire
   * @throws IllegalArgumentException moving to a suit discard: if the size of the movingCards list
   *                                  is greater than 1
   * @throws IllegalArgumentException moving to a suit discard: if the suit of the card being moved
   *                                  is different from the suit of the discard pile that it is
   *                                  being moved to
   * @throws IllegalArgumentException moving to a suit discard: if the rank pairing of the last
   *                                  card in the destination column and the first card in the
   *                                  movingCards list is invalid based on the rules of Solitaire
   */
  public void move(int movingColNum, ArrayList<Card> movingCards, int destColNum)
          throws IllegalArgumentException {
    if (movingColNum < 1 || movingColNum > 12) {
      throw new IllegalArgumentException("Moving column must be an integer between 1 and 12.");
    }
    if (destColNum < 1 || destColNum > 11) {
      throw new IllegalArgumentException("Destination column must be an integer between 1 and 11.");
    }
    this.validMoveList(movingColNum, movingCards);
    ArrayList<Card> destColList = this.getCol(destColNum);
    Card movingCard = movingCards.get(0);
    if (destColList.isEmpty()) {
      // If destination column is empty, the card being moved must be a king if it's being moved
      // to columns 1-7, or must be an ace if being moved to a suit discard pile
      Card.Rank emptyPileRank = this.getEmptyPileRank(destColNum);
      if (movingCard.getRank() != emptyPileRank) {
        throw new IllegalArgumentException("Invalid card rank for empty pile.");
      }
    } else {
      Card destCard = destColList.get(destColList.size() - 1);
      if (destColNum <= 7) {
        // Moving to columns 1-7
        this.moveToRegularColumn(movingCard, destCard);
      } else {
        // Moving to a suit discard pile
        this.moveToSuitDiscard(movingCards, destColNum, destCard);
      }
    }
    // If no errors thrown, move is valid
    this.removeCards(movingColNum, movingCards);
    this.addCards(movingCards, destColNum);
  }

  /**
   * A helper method for move() - Gets a copy of the list of cards for a column
   *
   * @param colNum the number representing a column, number-column pairs given in the move() Javadoc
   * @return an array list of Cards representing the desired column
   * @throws IllegalArgumentException if colNum is not an integer between 1 and 12
   */
  private ArrayList<Card> getCol(int colNum) throws IllegalArgumentException {
    switch (colNum) {
      case 1:
        return getCol1();
      case 2:
        return getCol2();
      case 3:
        return getCol3();
      case 4:
        return getCol4();
      case 5:
        return getCol5();
      case 6:
        return getCol6();
      case 7:
        return getCol7();
      case 8:
        return getSpadeDiscard();
      case 9:
        return getClubDiscard();
      case 10:
        return getHeartDiscard();
      case 11:
        return getDiamondDiscard();
      case 12:
        return getDiscard();
      default:
        throw new IllegalArgumentException("Column number must be an integer between 1 and 12");
    }
  }

  /**
   * A helper method for move() - Ensures that the array list of Cards given to be moved is valid.
   *
   * @param movingColNum a number representing the column where a set of cards are being moved from
   * @param movingCards  an array list representing a set of cards that are being moved
   * @throws IllegalArgumentException if movingCards is empty
   * @throws IllegalArgumentException if attempting to move multiple Cards from a discard pile
   * @throws IllegalArgumentException if the column associated with the given number doesn't end
   *                                  with the given cards to be moved
   */
  private void validMoveList(int movingColNum, ArrayList<Card> movingCards)
          throws IllegalArgumentException {
    if (movingCards.isEmpty()) {
      throw new IllegalArgumentException("Cannot move empty list of cards.");
    }
    if ((movingColNum >= 8 && movingColNum <= 12) && (movingCards.size() != 1)) {
      throw new IllegalArgumentException("When moving from a discard pile, you may only move one "
              + "card at a time.");
    }
    boolean listsMatch = true;
    ArrayList<Card> movingColList = this.getCol(movingColNum);
    if (movingColList.size() < movingCards.size()) {
      listsMatch = false;
    } else {
      while (movingColList.size() > movingCards.size()) {
        movingColList.remove(0);
      }
      listsMatch = (movingColList.equals(movingCards));
    }
    if (!listsMatch) {
      throw new IllegalArgumentException("The column associated with the given number must end "
              + "with the given cards to be moved.");
    }
  }

  /**
   * A helper method for move() - Gets the rank needed to fill an empty pile based on the
   * destination column number, which is a King for columns 1-7 and an Ace for any suit
   * discard pile.
   *
   * @param destColNum the number of the column that a move is being attempted to
   * @return the rank that can fill the empty column given
   * @throws IllegalArgumentException if destColNum is not an integer between 1 and 11
   */
  private Card.Rank getEmptyPileRank(int destColNum) throws IllegalArgumentException {
    if (destColNum >= 1 && destColNum <= 7) {
      return Card.Rank.King;
    } else if (destColNum >= 8 && destColNum <= 11) {
      return Card.Rank.Ace;
    } else {
      throw new IllegalArgumentException("For the getEmptyPileRank() method, the destination column"
              + "number must be an integer between 1 and 11");
    }
  }

  /**
   * A helper method for move() - Checks to see if a move to a column 1-7 is valid.
   *
   * @param movingCard the first card in the set that is being moved
   * @param destCard   the last card in the column where the movingCard is being moved to
   * @throws IllegalArgumentException if the last card in the destination column and the first card
   *                                  in the movingCards list are the same color
   * @throws IllegalArgumentException if the rank pairing of the last card in the destination column
   *                                  and the first card in the movingCards list is invalid based on the rules of Solitaire
   */
  private void moveToRegularColumn(Card movingCard, Card destCard) throws IllegalArgumentException {
    // First check to make sure the cards are different colors
    if (movingCard.getColor() == destCard.getColor()) {
      throw new IllegalArgumentException("Cards cannot be the same color.");
    }
    // Then check that the ranks are valid
    if (!(this.checkRanks(destCard.getRank(), movingCard.getRank()))) {
      throw new IllegalArgumentException("Ranks of cards in a column must be in order from top "
              + "to bottom, starting with 'King' and ending with 'Ace'.");
    }
  }

  /**
   * A helper method for move() - Checks to see if a move to a suit discard pile is valid.
   *
   * @param movingCards an array list representing the set of cards that are being moved
   * @param destColNum  a number representing the column where a set of cards are being moved to
   * @param destCard    the last card in the column where movingCards are being moved to
   * @throws IllegalArgumentException if the size of the movingCards list is greater than 1
   * @throws IllegalArgumentException if the suit of the card being moved is different from the suit
   *                                  of the discard pile that it is being moved to
   * @throws IllegalArgumentException if the rank pairing of the last card in the destination column
   *                                  and the first card in the movingCards list is invalid based on the rules of Solitaire
   */
  private void moveToSuitDiscard(ArrayList<Card> movingCards, int destColNum, Card destCard)
          throws IllegalArgumentException {
    // First make sure that only a single card is being moved
    if (movingCards.size() < 1) {
      throw new IllegalArgumentException("You may only move one card to a discard pile at a "
              + "time");
    }
    Card movingCard = movingCards.get(0);
    // Next check that the card being moved is of the correct suit
    boolean correctSuit = this.correctSuit(destColNum, movingCard.getSuit());
    if (!correctSuit) {
      throw new IllegalArgumentException("Invalid suit for desired pile.");
    }
    // Then check that the ranks are valid
    if (!(this.checkRanks(movingCard.getRank(), destCard.getRank()))) {
      throw new IllegalArgumentException("Ranks of cards in a suit discard pile must be in order"
              + "starting from 'Ace' at the bottom and ending with 'King' on top.");
    }
  }

  /**
   * A helper method for move() - Returns true if the card being moved is the correct suit based on
   * the suit discard pile it is being moved to.
   *
   * @param destColNum     the number representing the suit discard pile that the card is being
   *                       moved to
   * @param movingCardSuit the suit of the card that is being moved
   * @return true if the suit of the card matches the desired suit discard pile
   * @throws IllegalArgumentException if destColNum is not an integer between 8 and 11
   */
  private boolean correctSuit(int destColNum, Card.Suit movingCardSuit)
          throws IllegalArgumentException {
    switch (destColNum) {
      case 8:
        return (movingCardSuit == Card.Suit.Spade);
      case 9:
        return (movingCardSuit == Card.Suit.Club);
      case 10:
        return (movingCardSuit == Card.Suit.Heart);
      case 11:
        return (movingCardSuit == Card.Suit.Diamond);
      default:
        throw new IllegalArgumentException("For the correctSuit() method, the destination column "
                + "number must be an integer between 8 and 11");
    }
  }

  /**
   * A helper method for move() - Returns true if the 'placed' card rank is legal to place on
   * the 'base' card rank according to the rules of Solitaire.
   *
   * @param base   the rank of the card that the other card is being placed on top of
   * @param placed the rank of the card being moved
   * @return if the move is valid based on the ranks of the two cards
   */
  private boolean checkRanks(Card.Rank base, Card.Rank placed) {
    switch (base) {
      case King:
        return (placed == Card.Rank.Queen);
      case Queen:
        return (placed == Card.Rank.Jack);
      case Jack:
        return (placed == Card.Rank.Ten);
      case Ten:
        return (placed == Card.Rank.Nine);
      case Nine:
        return (placed == Card.Rank.Eight);
      case Eight:
        return (placed == Card.Rank.Seven);
      case Seven:
        return (placed == Card.Rank.Six);
      case Six:
        return (placed == Card.Rank.Five);
      case Five:
        return (placed == Card.Rank.Four);
      case Four:
        return (placed == Card.Rank.Three);
      case Three:
        return (placed == Card.Rank.Two);
      case Two:
        return (placed == Card.Rank.Ace);
      default:
        return false;
    }
  }

  /**
   * A helper method for move() - Removes the set of cards to be moved from the appropriate column
   * and flips over the new last card in the column if there is one remaining.
   *
   * @param movingColNum a number representing the column where a set of cards are being moved from
   * @param movingCards  an array list representing the set of cards that are being moved
   * @throws IllegalArgumentException if movingColNum is not an integer between 1 and 12
   */
  private void removeCards(int movingColNum, ArrayList<Card> movingCards)
          throws IllegalArgumentException {
    switch (movingColNum) {
      case 12:
        this.discard.removeAll(movingCards);
        break;
      case 1:
        this.col1.removeAll(movingCards);
        if (!(this.col1.isEmpty())) {
          this.col1.get(this.col1.size() - 1).flipCard();
        }
        break;
      case 2:
        this.col2.removeAll(movingCards);
        if (!(this.col2.isEmpty())) {
          this.col2.get(this.col2.size() - 1).flipCard();
        }
        break;
      case 3:
        this.col3.removeAll(movingCards);
        if (!(this.col3.isEmpty())) {
          this.col3.get(this.col3.size() - 1).flipCard();
        }
        break;
      case 4:
        this.col4.removeAll(movingCards);
        if (!(this.col4.isEmpty())) {
          this.col4.get(this.col4.size() - 1).flipCard();
        }
        break;
      case 5:
        this.col5.removeAll(movingCards);
        if (!(this.col5.isEmpty())) {
          this.col5.get(this.col5.size() - 1).flipCard();
        }
        break;
      case 6:
        this.col6.removeAll(movingCards);
        if (!(this.col6.isEmpty())) {
          this.col6.get(this.col6.size() - 1).flipCard();
        }
        break;
      case 7:
        this.col7.removeAll(movingCards);
        if (!(this.col7.isEmpty())) {
          this.col7.get(this.col7.size() - 1).flipCard();
        }
        break;
      case 8:
        this.spadeDiscard.removeAll(movingCards);
        break;
      case 9:
        this.clubDiscard.removeAll(movingCards);
        break;
      case 10:
        this.heartDiscard.removeAll(movingCards);
        break;
      case 11:
        this.diamondDiscard.removeAll(movingCards);
        break;
      default:
        throw new IllegalArgumentException("The moving column number must be an integer between 1 "
                + "and 12");
    }
  }

  /**
   * A helper method for move() - Adds the set of cards to be moved to the end of the desired column
   * or on top of the desired suit discard pile.
   *
   * @param movingCards an array list representing the set of cards that are being moved
   * @param destColNum  a number representing the column where the set of cards are being moved to
   * @throws IllegalArgumentException if destColNum is not an integer between 1 and 11
   */
  private void addCards(ArrayList<Card> movingCards, int destColNum) throws IllegalArgumentException {
    switch (destColNum) {
      case 1:
        this.col1.addAll(movingCards);
        break;
      case 2:
        this.col2.addAll(movingCards);
        break;
      case 3:
        this.col3.addAll(movingCards);
        break;
      case 4:
        this.col4.addAll(movingCards);
        break;
      case 5:
        this.col5.addAll(movingCards);
        break;
      case 6:
        this.col6.addAll(movingCards);
        break;
      case 7:
        this.col7.addAll(movingCards);
        break;
      case 8:
        this.spadeDiscard.addAll(movingCards);
        break;
      case 9:
        this.clubDiscard.addAll(movingCards);
        break;
      case 10:
        this.heartDiscard.addAll(movingCards);
        break;
      case 11:
        this.diamondDiscard.addAll(movingCards);
        break;
      default:
        throw new IllegalArgumentException("The destination column number must be an integer "
                + "between 1 and 11");
    }
  }

  /**
   * Moves three cards at a time from the 'stock' pile to the 'discard' pile. If the 'stock' pile
   * is empty, it moves any and all cards in the 'discard pile' back to the 'stock' pile.
   */
  public void stockAction() {
    if (this.stock.isEmpty() && !this.discard.isEmpty()) {
      this.stock.addAll(this.discard);
      this.discard.clear();
    }
    if (!this.stock.isEmpty()) {
      int numMoved = 3;
      if (this.stock.size() < 3) {
        numMoved = this.stock.size();
      }
      // if issues, change to numMoved minus 1
      for (int i = 0; i < numMoved; i++) {
        this.discard.add(this.stock.remove(0));
      }
    }
  }

  public ArrayList<Card> getStock() {
    return new ArrayList<>(this.stock);
  }

  public ArrayList<Card> getDiscard() {
    return new ArrayList<>(this.discard);
  }

  public ArrayList<Card> getCol1() {
    return new ArrayList<>(this.col1);
  }

  public ArrayList<Card> getCol2() {
    return new ArrayList<>(this.col2);
  }

  public ArrayList<Card> getCol3() {
    return new ArrayList<>(this.col3);
  }

  public ArrayList<Card> getCol4() {
    return new ArrayList<>(this.col4);
  }

  public ArrayList<Card> getCol5() {
    return new ArrayList<>(this.col5);
  }

  public ArrayList<Card> getCol6() {
    return new ArrayList<>(this.col6);
  }

  public ArrayList<Card> getCol7() {
    return new ArrayList<>(this.col7);
  }

  public ArrayList<Card> getSpadeDiscard() {
    return new ArrayList<>(this.spadeDiscard);
  }

  public ArrayList<Card> getClubDiscard() {
    return new ArrayList<>(this.clubDiscard);
  }

  public ArrayList<Card> getHeartDiscard() {
    return new ArrayList<>(this.heartDiscard);
  }

  public ArrayList<Card> getDiamondDiscard() {
    return new ArrayList<>(this.diamondDiscard);
  }
}