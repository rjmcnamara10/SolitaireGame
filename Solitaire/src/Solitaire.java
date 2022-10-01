import tester.Tester;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javalib.worldimages.*;
import javalib.impworld.*;

interface ISuit {
  
  // returns the appropriate color based on the suit
  Color accessColor();
  
  // draws the image for a specific suit
  WorldImage drawSuit();
  
  // draws the image for a specific suit given a color
  WorldImage drawSuit(Color color);
}

class Spade implements ISuit {
  Spade() {}

  //returns the appropriate color for a spade
  public Color accessColor() {
    return Color.black;
  }

  // draws the image for a spade
  public WorldImage drawSuit() {
    WorldImage im1 = new RotateImage(new Utils().heartImage(Color.black), 180.0);
    WorldImage im2 = new OverlayOffsetImage(im1, 0.0, 12.0, SolitaireWorld.STEM);
    return im2;
  }
  
  // draws the image for a spade using the given color
  public WorldImage drawSuit(Color color) {
    WorldImage im1 = new RotateImage(new Utils().heartImage(color), 180.0);
    WorldImage im2 = new OverlayOffsetImage(im1, 0.0, 12.0, SolitaireWorld.GRAY_STEM);
    return im2;
  }
}

class Club implements ISuit {
  Club() {}
  
  //returns the appropriate color for a club
  public Color accessColor() {
    return Color.black;
  }
  
  // draws the image for a club
  public WorldImage drawSuit() {
    WorldImage cir = new CircleImage(6, OutlineMode.SOLID, Color.black);
    WorldImage im1 = new OverlayOffsetImage(cir, 9.5, 0.0, cir);
    WorldImage im2 = new OverlayOffsetImage(cir, 0.0, 8.0, im1);
    WorldImage im3 = new OverlayOffsetImage(im2, 0.0, 11.0, SolitaireWorld.STEM);
    return im3;
  }
  
  // draws the image for a club using the given color
  public WorldImage drawSuit(Color color) {
    WorldImage cir = new CircleImage(6, OutlineMode.SOLID, color);
    WorldImage im1 = new OverlayOffsetImage(cir, 9.5, 0.0, cir);
    WorldImage im2 = new OverlayOffsetImage(cir, 0.0, 8.0, im1);
    WorldImage im3 = new OverlayOffsetImage(im2, 0.0, 11.0, SolitaireWorld.GRAY_STEM);
    return im3;
  }
}

class Heart implements ISuit {
  Heart() {}
  
  //returns the appropriate color for a heart
  public Color accessColor() {
    return Color.red;
  }
  
  // draws the image for a heart
  public WorldImage drawSuit() {
    return new Utils().heartImage(Color.red);
  }
  
  // draws the image for a heart using the given color
  public WorldImage drawSuit(Color color) {
    return new Utils().heartImage(color);
  }
}

class Diamond implements ISuit {
  Diamond() {}
  
  //returns the appropriate color for a diamond
  public Color accessColor() {
    return Color.red;
  }
  
  // draws the image for a diamond
  public WorldImage drawSuit() {
    return new AboveImage(new TriangleImage(new Posn(0, 0), new Posn(8, 14), new Posn(-8, 14),
        OutlineMode.SOLID, Color.red), 
        new TriangleImage(new Posn(0, 0), new Posn(8, -14), new Posn(-8, -14),
            OutlineMode.SOLID, Color.red));
  }
  
  // draws the image for a diamond using the given color
  public WorldImage drawSuit(Color color) {
    return new AboveImage(new TriangleImage(new Posn(0, 0), new Posn(8, 14), new Posn(-8, 14),
        OutlineMode.SOLID, color), 
        new TriangleImage(new Posn(0, 0), new Posn(8, -14), new Posn(-8, -14),
            OutlineMode.SOLID, color));
  }
}

interface ICard {
  
  // draws a card
  WorldImage drawCard();
}

class CardBack implements ICard {
  CardBack() {}
  
  public WorldImage drawCard() {
    WorldImage blank = SolitaireWorld.BLANK_CARD;
    WorldImage line1 = new LineImage(new Posn(100, 100), Color.black);
    WorldImage line2 = new LineImage(new Posn(-100, 100), Color.black);
    for (int i = 0; i < 6; i = i + 1) {
      blank = new OverlayOffsetImage(line1, 0.0, (i * 10) - 25, blank);
    }
    for (int i = 0; i < 6; i = i + 1) {
      blank = new OverlayOffsetImage(line2, 0.0, (i * 10) - 25, blank);
    }
    return blank;
  }
}

class Card implements ICard {
  ISuit suit;
  Color color;
  String rank;
  boolean revealed;
  
  Card(ISuit suit, String rank, boolean revealed) {
    this.suit = suit;
    this.color = this.suit.accessColor();
    this.rank = rank;
    this.revealed = revealed;
  }
  
  public WorldImage drawCard() {
    String rankShort = this.rank;
    if (this.rank.length() == 2) {
      
      // do nothing
    }
    else {
      rankShort = this.rank.substring(0, 1);
    }
    if (this.revealed) {
      WorldImage im1 = new OverlayOffsetImage(this.suit.drawSuit(), -35.0, 55.0, 
          SolitaireWorld.BLANK_CARD);
      WorldImage im2 = new OverlayOffsetImage(new TextImage(rankShort, 25, 
          this.color), 35.0, 55.0, im1);
      WorldImage clear1 = new OverlayOffsetImage(this.suit.drawSuit(), -35.0, 55.0, 
          new RectangleImage(100, 150, OutlineMode.OUTLINE, Color.black));
      WorldImage clear2 = new OverlayOffsetImage(new TextImage(rankShort, 25, 
          this.color), 35.0, 55.0, clear1);
      WorldImage im3 = new OverlayImage(clear2, new RotateImage(im2, 180.0));
      WorldImage fullCard = new OverlayImage(new ScaleImage(this.suit.drawSuit(), 1.5), im3);
      return fullCard;
    }
    else {
      return new CardBack().drawCard();
    }
  }
}

class Utils {
  Utils() {}
  
  // provides an image of a blank card
  WorldImage blankCardImage() {
    return new OverlayImage(new RectangleImage(100, 150, OutlineMode.OUTLINE, Color.black),
        new RectangleImage(100, 150, OutlineMode.SOLID, Color.white));
  }
  
  // provides an image of a heart with the given color
  WorldImage heartImage(Color color) {
    WorldImage cir = new EllipseImage(12, 14, OutlineMode.SOLID, color);
    WorldImage im1 = new OverlayOffsetImage(cir, 9.5, 0.0, cir);
    WorldImage tri = new TriangleImage(new Posn(0, 0), new Posn(9, -10), new Posn(-9, -10),
        OutlineMode.SOLID, color);
    WorldImage im2 = new OverlayOffsetImage(im1, 0.0, 9.5, tri);
    return im2;
  }

  // provides an image of a stem
  public WorldImage stemImage(Color color) {
    return new OverlayOffsetImage(
        new EquilateralTriangleImage(8, OutlineMode.SOLID, color),
        0.0, -4.0,
        new RectangleImage(2, 4, OutlineMode.SOLID, color));
  }
  
  // returns a list representing an unshuffled deck of 52 cards
  ArrayList<Card> fullDeck() {
    ArrayList<Card> fullDeck = new ArrayList<Card>();
    ArrayList<ISuit> fourSuits = new ArrayList<ISuit>(Arrays.asList(
        new Spade(), new Club(), new Heart(), new Diamond()));
    for (ISuit s : fourSuits) {
      for (int i = 2; i < 11; i = i + 1) {
        fullDeck.add(new Card(s, Integer.toString(i), false));
      }
      fullDeck.add(new Card(s, "Ace", false));
      fullDeck.add(new Card(s, "Jack", false));
      fullDeck.add(new Card(s, "Queen", false));
      fullDeck.add(new Card(s, "King", false));
    }
    return fullDeck;
  }
  
  // shuffles the deck of 52 cards
  ArrayList<Card> deckShuffle() {
    ArrayList<Card> fullDeck = SolitaireWorld.FULL_DECK;
    Collections.shuffle(fullDeck);
    return fullDeck;
  }
  
  // Gets the image for the reset button
  WorldImage resetImage() {
    WorldImage outline = new CircleImage(60, 
        OutlineMode.SOLID, Color.gray);
    WorldImage center = new CircleImage(40, 
        OutlineMode.SOLID, Color.white);
    WorldImage cutoutBase = new CircleImage(60, 
        OutlineMode.SOLID, Color.white);
    WorldImage cutout = new CropImage(60, 
        60, 120, 
        120, cutoutBase);
    WorldImage circleBase = new OverlayImage(center, outline);
    WorldImage circle = new OverlayOffsetImage(cutout, -62,
        -62, circleBase);
    WorldImage arrowHead = new TriangleImage(new Posn(0, 0), 
        new Posn(60, 0), 
        new Posn(30, 30),
        OutlineMode.SOLID, Color.gray);
    WorldImage button = new OverlayOffsetImage(arrowHead, -20,
        20, circle);
    WorldImage buttonRotated = new RotateImage(button, 45);
    buttonRotated = new CropImage(24, 0, 
        122, 144,
        buttonRotated);
    return buttonRotated;
  }

  // returns an image of the reset card
  WorldImage resetCardImage() {
    WorldImage resetScaled = new ScaleImage(SolitaireWorld.RESET_BUTTON, 0.5);
    return new OverlayImage(resetScaled, SolitaireWorld.BLANK_CARD);
  }
  
  // returns an image of a card with the given suit for the discard piles
  WorldImage graySuitImage(ISuit suit) {
    return new OverlayImage(new ScaleImage(suit.drawSuit(Color.gray), 1.5), 
        SolitaireWorld.BLANK_CARD);
  }
}

class SolitaireWorld extends World {
  ArrayList<Card> deck1;
  ArrayList<Card> deck2;
  ArrayList<Card> col1;
  ArrayList<Card> col2;
  ArrayList<Card> col3;
  ArrayList<Card> col4;
  ArrayList<Card> col5;
  ArrayList<Card> col6;
  ArrayList<Card> col7;
  ArrayList<Card> spadeDiscard;
  ArrayList<Card> clubDiscard;
  ArrayList<Card> heartDiscard;
  ArrayList<Card> diamondDiscard;
  
  SolitaireWorld() {
    this.deck1 = new Utils().deckShuffle();
    this.deck2 = new ArrayList<Card>();
    this.col1 = new ArrayList<Card>();
    this.col2 = new ArrayList<Card>();
    this.col3 = new ArrayList<Card>();
    this.col4 = new ArrayList<Card>();
    this.col5 = new ArrayList<Card>();
    this.col6 = new ArrayList<Card>();
    this.col7 = new ArrayList<Card>();
    this.spadeDiscard = new ArrayList<Card>();
    this.clubDiscard = new ArrayList<Card>();
    this.heartDiscard = new ArrayList<Card>();
    this.diamondDiscard = new ArrayList<Card>();
    this.arrangeCards();
  }
  
  // provides a list representing an unshuffled deck of 52 cards
  static ArrayList<Card> FULL_DECK = new Utils().fullDeck();
  
  // provides an image of a blank card
  static WorldImage BLANK_CARD = new Utils().blankCardImage();

  // provides an image of a stem
  static WorldImage STEM = new Utils().stemImage(Color.black);
  
  // provides an image of a gray stem
  static WorldImage GRAY_STEM = new Utils().stemImage(Color.gray);
  
  // provides an image of a reset button
  static WorldImage RESET_BUTTON = new Utils().resetImage();

  // makes the scene for the game of solitaire
  public WorldScene makeScene() {
    WorldScene world = new WorldScene(0, 0);
    WorldImage deck1Image = new CardBack().drawCard();
    WorldImage deck2Image = new EmptyImage();
    WorldImage col1Cards = new EmptyImage();
    WorldImage col2Cards = new EmptyImage();
    WorldImage col3Cards = new EmptyImage();
    WorldImage col4Cards = new EmptyImage();
    WorldImage col5Cards = new EmptyImage();
    WorldImage col6Cards = new EmptyImage();
    WorldImage col7Cards = new EmptyImage();
    WorldImage blankSpace = new RectangleImage(10, 1, OutlineMode.SOLID, Color.white);
    WorldImage spadePile = new Utils().graySuitImage(new Spade());
    WorldImage clubPile = new Utils().graySuitImage(new Club());
    WorldImage heartPile = new Utils().graySuitImage(new Heart());
    WorldImage diamondPile = new Utils().graySuitImage(new Diamond());
    if (this.deck1.isEmpty()) {
      deck1Image = new Utils().resetCardImage();
    }
    else { 
      // do nothing
    }
    if (this.deck2.isEmpty()) {
      // do nothing
    }
    else {
      int numDisplay = 3;
      if (this.deck2.size() < 3) {
        numDisplay = this.deck2.size();
      }
      else {
        // do nothing
      }
      for (int i = 0; i < numDisplay; i = i + 1) {
        deck2Image = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            this.deck2.get(this.deck2.size() - 1 - i).drawCard(), 0.0, -30.0, deck2Image);
      }
    }
    world.placeImageXY(deck1Image, 75, 225);
    world.placeImageXY(deck2Image, 75, 425);
    for (Card c : this.col1) {
      col1Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col1Cards);
    }
    for (Card c : this.col2) {
      col2Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col2Cards);
    }
    for (Card c : this.col3) {
      col3Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col3Cards);
    }
    for (Card c : this.col4) {
      col4Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col4Cards);
    }
    for (Card c : this.col5) {
      col5Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col5Cards);
    }
    for (Card c : this.col6) {
      col6Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col6Cards);
    }
    for (Card c : this.col7) {
      col7Cards = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          c.drawCard(), 0.0, -30.0, col7Cards);
    }
    WorldImage gameSpace = new BesideAlignImage(AlignModeY.TOP, col1Cards, blankSpace, 
        col2Cards, blankSpace, col3Cards, blankSpace, col4Cards, blankSpace, 
        col5Cards, blankSpace, col6Cards, blankSpace, col7Cards, blankSpace);
    world.placeImageXY(gameSpace, 550, 250);
    if (this.spadeDiscard.isEmpty()) {
      // do nothing
    }
    else {
      spadePile = this.spadeDiscard.get(this.spadeDiscard.size() - 1).drawCard();
    }
    if (this.clubDiscard.isEmpty()) {
      // do nothing
    }
    else {
      clubPile = this.clubDiscard.get(this.clubDiscard.size() - 1).drawCard();
    }
    if (this.heartDiscard.isEmpty()) {
      // do nothing
    }
    else {
      heartPile = this.heartDiscard.get(this.heartDiscard.size() - 1).drawCard();
    }
    if (this.diamondDiscard.isEmpty()) {
      // do nothing
    }
    else {
      diamondPile = this.diamondDiscard.get(this.diamondDiscard.size() - 1).drawCard();
    }
    world.placeImageXY(spadePile, 320, 700);
    world.placeImageXY(clubPile, 440, 700);
    world.placeImageXY(heartPile, 560, 700);
    world.placeImageXY(diamondPile, 680, 700);
    return world;
  }
  
  // EFFECT: alters the state of the game based on the key pressed
  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      Collections.shuffle(this.deck1);
    }
  }
  
  // EFFECT: alters the state of the game based on the click
  public void onMousePressed(Posn p) {
    if ((p.x > 25) && (p.x < 125) && (p.y > 150) && (p.y < 300)) {
      if (this.deck1.isEmpty()) {
        deck1 = deck2;
        deck2 = new ArrayList<Card>();
      }
      else {
        int numMoved = 3;
        if (this.deck1.size() < 3) {
          numMoved = this.deck1.size();
        }
        else {
          // do nothing
        }
        for (int i = 0; i < numMoved; i = i + 1) {
          this.deck2.add(this.deck1.remove(0));
        }
      }
    }
    else {
      // do nothing
    }
  }
  
  // EFFECT: arranges the cards from a shuffled deck for a game of solitaire
  void arrangeCards() {
    this.col1.add(this.deck1.remove(0));
    this.col1.get(0).revealed = true;
    this.col2.add(this.deck1.remove(0));
    this.col2.add(this.deck1.remove(0));
    this.col2.get(1).revealed = true;
    this.col3.add(this.deck1.remove(0));
    this.col3.add(this.deck1.remove(0));
    this.col3.add(this.deck1.remove(0));
    this.col3.get(2).revealed = true;
    for (int i = 0; i < 4; i = i + 1) {
      this.col4.add(this.deck1.remove(0));
    }
    this.col4.get(3).revealed = true;
    for (int i = 0; i < 5; i = i + 1) {
      this.col5.add(this.deck1.remove(0));
    }
    this.col5.get(4).revealed = true;
    for (int i = 0; i < 6; i = i + 1) {
      this.col6.add(this.deck1.remove(0));
    }
    this.col6.get(5).revealed = true;
    for (int i = 0; i < 7; i = i + 1) {
      this.col7.add(this.deck1.remove(0));
    }
    this.col7.get(6).revealed = true;
    for (Card c : this.deck1) {
      c.revealed = true;
    }
  }
}

class ExamplesSolitaire {
  ExamplesSolitaire() {}
  
  ISuit spade = new Spade();
  ISuit club = new Club();
  ISuit heart = new Heart();
  ISuit diamond = new Diamond();
  Card twoD = new Card(diamond, "2", true);
  Card kingD = new Card(diamond, "King", true);
  Card jackH = new Card(heart, "Jack", true);
  Card tenS = new Card(spade, "10", true);
  Card fourCBack = new Card(club, "4", false);
  ICard back = new CardBack();
  
  void testAccessColor(Tester t) {
    t.checkExpect(spade.accessColor(), Color.black);
    t.checkExpect(club.accessColor(), Color.black);
    t.checkExpect(heart.accessColor(), Color.red);
    t.checkExpect(diamond.accessColor(), Color.red);
  }
  
  void testDeckShuffle(Tester t) {
    ArrayList<Card> fullDeck = new Utils().deckShuffle();
    t.checkExpect(fullDeck.size(), 52);
  }
  
  void testSolitaireWorld(Tester t) {
    SolitaireWorld world = new SolitaireWorld();
    world.bigBang(1000, 800, 1 / 18.0);
  }
}



