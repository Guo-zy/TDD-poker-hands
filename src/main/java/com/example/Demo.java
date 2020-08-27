package com.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Demo {

  private final Map<String, Integer> pokerHandsCard;
  private final Map<Integer, String> pokerHandsSituation;

  {
    pokerHandsSituation = new HashMap<>();
    pokerHandsSituation.put(14, "A");
    pokerHandsSituation.put(2, "2");
    pokerHandsSituation.put(3, "3");
    pokerHandsSituation.put(4, "4");
    pokerHandsSituation.put(5, "5");
    pokerHandsSituation.put(6, "6");
    pokerHandsSituation.put(7, "7");
    pokerHandsSituation.put(8, "8");
    pokerHandsSituation.put(9, "9");
    pokerHandsSituation.put(10, "T");
    pokerHandsSituation.put(11, "J");
    pokerHandsSituation.put(12, "Q");
    pokerHandsSituation.put(13, "K");

    pokerHandsCard = new HashMap<>();
    pokerHandsCard.put("A", 14);
    pokerHandsCard.put("2", 2);
    pokerHandsCard.put("3", 3);
    pokerHandsCard.put("4", 4);
    pokerHandsCard.put("5", 5);
    pokerHandsCard.put("6", 6);
    pokerHandsCard.put("7", 7);
    pokerHandsCard.put("8", 8);
    pokerHandsCard.put("9", 9);
    pokerHandsCard.put("T", 10);
    pokerHandsCard.put("J", 11);
    pokerHandsCard.put("Q", 12);
    pokerHandsCard.put("K", 13);
  }


  public String run() {
    return "ABC";
  }


  private void separateCardsAndSuits(String cards, PlayerCard playerCard) {

    String[] cardsNum = cards.split(" ");

    List<Integer> pokerHands = new ArrayList<>();
    List<String> pokerColors = new ArrayList<>();

    for (String s : cardsNum) {
      String tmp = playerCard.getOriginNums() + s.substring(0, 1);
      playerCard.setOriginNums(tmp);
      pokerHands.add(pokerHandsCard.get(s.substring(0, 1)));
      pokerColors.add(s.substring(1));
    }

    Collections.sort(pokerHands);

    playerCard.setPokerHands(pokerHands);
    playerCard.setPokerColors(pokerColors);

  }

  private void pairCalculate(PlayerCard playerCard) {
    int score = 2;

    List<Integer> pokerHands = playerCard.getPokerHands();

    for (int i = 0; i < pokerHands.size() - 1; i++) {
      if (pokerHands.get(i).equals(pokerHands.get(i + 1))) {
        playerCard.setScore(score);
        playerCard.setType("with pair of " + pokerHandsSituation.get(pokerHands.get(i)));
        playerCard.setParticularNums(String.valueOf(pokerHands.get(i)));
      }
    }

  }

  private void twoPairCalculate(PlayerCard playerCard) {
    int score = 3;
    StringBuilder pair = new StringBuilder();
    List<Integer> pokerHands = playerCard.getPokerHands();

    for (int i = 0; i < pokerHands.size() - 1; i++) {
      if (pokerHands.get(i).equals(pokerHands.get(i + 1))) {
        pair.append(pokerHandsSituation.get(pokerHands.get(i)));
      }
    }

    if (pair.length() == 2) {
      playerCard.setScore(score);
      playerCard.setParticularNums(pair.toString());
      playerCard.setType("with two pairs of " + pair.substring(0, 1) + " and " + pair.substring(1));
    }

  }

  private void threeOfAKindCalculate(PlayerCard playerCard) {
    int score = 4;
    List<Integer> pokerHands = playerCard.getPokerHands();

    for (int i = 0; i < pokerHands.size() - 3; i++) {
      if (pokerHands.get(i).equals(pokerHands.get(i + 1))
          && pokerHands.get(i).equals(pokerHands.get(i + 2))) {
        playerCard.setScore(score);
        playerCard.setType("with three of a kind of " + pokerHandsSituation.get(pokerHands.get(i)));
        playerCard.setParticularNums(String.valueOf(pokerHands.get(i)));
      }
    }
  }

  private void straightCalculate(PlayerCard playerCard) {
    int score = 5;
    List<Integer> pokerHands = playerCard.getPokerHands();

    int first = pokerHands.get(0);
    int second = pokerHands.get(1);
    int third = pokerHands.get(2);
    int forth = pokerHands.get(3);
    int fifth = pokerHands.get(4);

    String type = "" +
        pokerHandsSituation.get(first) +
        pokerHandsSituation.get(second) +
        pokerHandsSituation.get(third) +
        pokerHandsSituation.get(forth) +
        pokerHandsSituation.get(fifth);

    if (first + 1 == second && second + 1 == third
        && third + 1 == forth && forth + 1 == fifth) {
      playerCard.setType("with straight of " + type);
      playerCard.setScore(score);
      playerCard.setParticularNums(type);
    }

    if (first == 2 && second == 3 && third == 4 && forth == 5 &&
        fifth == 14) {
      playerCard.setType("with straight of " + type);
      playerCard.setScore(score);
      playerCard.setParticularNums(type);
    }

  }

  private void flushCalculate(PlayerCard playerCard) {
    int score = 6;
    List<String> pokerColors = playerCard.getPokerColors();
    HashSet<String> Hset = new HashSet<>();

    for (int i = 0; i < pokerColors.size() - 1; i++) {
      Hset.add(pokerColors.get(i));
    }
    if (Hset.size() == 1) {
      if (playerCard.getScore() == 5) {
        playerCard.setStraightAndFlush(true);
      }
      playerCard.setScore(score);
      playerCard.setType("with flush of " + playerCard.getOriginNums() + " with " + pokerColors.get(0));
      playerCard.setParticularNums(playerCard.getOriginNums() + pokerColors.get(0));
    }
  }

  private void fullHouseCalculate(PlayerCard playerCard) {
    int score = 7;
    List<Integer> pokerHands = playerCard.getPokerHands();

    int first = pokerHands.get(0);
    int second = pokerHands.get(1);
    int third = pokerHands.get(2);
    int forth = pokerHands.get(3);
    int fifth = pokerHands.get(4);

    if (first == third && forth == fifth) {
      playerCard.setType(
          "with full house of " + pokerHandsSituation.get(first) + " over " + pokerHandsSituation.get(forth));
      playerCard.setScore(score);
      playerCard.setParticularNums(pokerHandsSituation.get(first) + pokerHandsSituation.get(forth));
    }

    if (first == second && third == fifth) {
      playerCard.setType(
          "with full house of " + pokerHandsSituation.get(forth) + " over " + pokerHandsSituation.get(first));
      playerCard.setScore(score);
      playerCard.setParticularNums(pokerHandsSituation.get(forth) + pokerHandsSituation.get(first));
    }
  }

  private void fourOfKindCalculate(PlayerCard playerCard) {
    int score = 8;
    List<Integer> pokerHands = playerCard.getPokerHands();

    if (pokerHands.get(0).equals(pokerHands.get(3)) ||
        pokerHands.get(1).equals(pokerHands.get(4))) {
      playerCard.setScore(score);
      playerCard.setType("with four of a kind of " + pokerHandsSituation.get(pokerHands.get(2)));
      playerCard.setParticularNums(pokerHandsSituation.get(pokerHands.get(2)));
    }

  }

  private void straightAndFlushCalculate(PlayerCard playerCard) {
    int score = 9;
    if (playerCard.isStraightAndFlush()) {
      playerCard.setScore(score);
      playerCard.setType(
          "with straight flush of " + playerCard.getOriginNums() + " with " + playerCard.getPokerColors().get(0));
    }
  }

  private String calculateWinnerOfHighCard(PlayerCard blackCard, PlayerCard whiteCard) {

    List<Integer> blackPokerhands = blackCard.getPokerHands();
    List<Integer> whitePokerhands = whiteCard.getPokerHands();

    for (int i = blackPokerhands.size() - 1; i >= 0; i--) {
      int black = blackPokerhands.get(i);
      int white = whitePokerhands.get(i);
      if (black > white) {
        return "black wins with high card " + pokerHandsSituation.get(blackPokerhands.get(i));
      }
      if (white > black) {
        return "white wins with high card " + pokerHandsSituation.get(whitePokerhands.get(i));
      }
    }
    return "Tie";
  }

  private String calculateWinnerOfPair(PlayerCard blackCard, PlayerCard whiteCard) {
    List<Integer> blackPokerhands = blackCard.getPokerHands();
    List<Integer> whitePokerhands = whiteCard.getPokerHands();

    if (blackCard.getParticularNums().compareTo(whiteCard.getParticularNums()) > 0) {
      return "black wins with pair of " + blackCard.getParticularNums();
    }
    if (whiteCard.getParticularNums().compareTo(blackCard.getParticularNums()) > 0) {
      return "white wins with pair of " + whiteCard.getParticularNums();
    }

    for (int i = blackPokerhands.size() - 1; i >= 0; i--) {
      int black = blackPokerhands.get(i);
      int white = whitePokerhands.get(i);
      if (black > white) {
        return "black wins with pair of max num is " + pokerHandsSituation.get(blackPokerhands.get(i));
      }
      if (white > black) {
        return "white wins with pair of max num is " + pokerHandsSituation.get(whitePokerhands.get(i));
      }
    }
    return "Tie";

  }

  private String calculateWinnerOfTwoPairs(PlayerCard blackCard, PlayerCard whiteCard) {
    List<Integer> blackPokerhands = blackCard.getPokerHands();
    List<Integer> whitePokerhands = whiteCard.getPokerHands();
    return null;
  }


  private void calculateScore(PlayerCard playerCard) {

    // pair
    pairCalculate(playerCard);

    //two pair
    twoPairCalculate(playerCard);

    //three of a kind
    threeOfAKindCalculate(playerCard);

    //Straight
    straightCalculate(playerCard);

    //flush
    flushCalculate(playerCard);

    //Full House
    fullHouseCalculate(playerCard);

    //four of a kind
    fourOfKindCalculate(playerCard);

    //Straight and flush
    straightAndFlushCalculate(playerCard);

  }


  public String calculateWinner(String black, String white) {

    PlayerCard blackCards = new PlayerCard();
    PlayerCard whiteCards = new PlayerCard();

    separateCardsAndSuits(black, blackCards);
    separateCardsAndSuits(white, whiteCards);

    calculateScore(blackCards);
    calculateScore(whiteCards);

    if (blackCards.getScore().equals(whiteCards.getScore())) {
      if (blackCards.getScore() == 1) {
        System.out.println(calculateWinnerOfHighCard(blackCards, whiteCards));
        return calculateWinnerOfHighCard(blackCards, whiteCards);
      }
      if (blackCards.getScore() == 2) {
        return calculateWinnerOfPair(blackCards, whiteCards);
      }
      if (blackCards.getScore() == 3) {
        return calculateWinnerOfTwoPairs(blackCards, whiteCards);
      }
    }

    if (blackCards.getScore() < whiteCards.getScore()) {
      return "white wins " + whiteCards.getType();
    }

    return "black wins " + blackCards.getType();
  }


  public static void main(String[] args) {
    Demo demo = new Demo();

    demo.calculateWinner("2S 3H 5C 9D KS", "2H 6D 8S 7C AH");
  }

}
