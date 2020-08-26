package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Demo {

  private static final Integer COUNT = 5;


  private List<Integer> blackPokerHands = new ArrayList<>(COUNT);
  private List<Integer> whitePokerHands = new ArrayList<>(COUNT);
  private List<String> blackPokerColor = new ArrayList<>(COUNT);
  private List<String> whitePokerColor = new ArrayList<>(COUNT);


  private Map<String, Integer> pokerHandsCard;
  private Map<String, Integer> pokerHandsSituation;

  {
    pokerHandsSituation = new HashMap<>();
    pokerHandsSituation.put("High Card", 1);
    pokerHandsSituation.put("Pair", 2);
    pokerHandsSituation.put("Two Pairs", 3);
    pokerHandsSituation.put("Three of a Kind", 4);
    pokerHandsSituation.put("Straight", 5);
    pokerHandsSituation.put("Flush", 6);
    pokerHandsSituation.put("Full House", 7);
    pokerHandsSituation.put("Four of a Kind", 8);
    pokerHandsSituation.put("Straight flush", 9);

    pokerHandsCard = new HashMap<>();
    pokerHandsSituation.put("A", 1);
    pokerHandsSituation.put("2", 2);
    pokerHandsSituation.put("3", 3);
    pokerHandsSituation.put("4", 4);
    pokerHandsSituation.put("5", 5);
    pokerHandsSituation.put("6", 6);
    pokerHandsSituation.put("7", 7);
    pokerHandsSituation.put("8", 8);
    pokerHandsSituation.put("9", 9);
    pokerHandsSituation.put("T", 10);
    pokerHandsSituation.put("J", 11);
    pokerHandsSituation.put("Q", 12);
    pokerHandsSituation.put("K", 13);
  }


  public String run() {
    return "ABC";
  }


  public void separateCardsAndSuits(String black, String white) {
    blackPokerHands.clear();
    blackPokerColor.clear();
    whitePokerHands.clear();
    whitePokerColor.clear();

    String[] blacks = black.split(" ");
    String[] whites = white.split(" ");
    for (String s : blacks) {
      blackPokerHands.add(pokerHandsSituation.get(s.substring(0, 1)));
      blackPokerColor.add(s.substring(1));
    }
    for (String s : whites) {
      whitePokerHands.add(pokerHandsSituation.get(s.substring(0, 1)));
      whitePokerColor.add(s.substring(1));
    }

    Collections.sort(blackPokerHands);
    Collections.sort(whitePokerHands);
  }


  public PlayerCard calculateScore(List<Integer> pokerHands, List<String> pokerColors) {

    PlayerCard playerCard = new PlayerCard();

    Integer score = 1;

    // pair
    for (int i = 0; i < pokerHands.size(); i++) {
      for (int j = i + 1; j < pokerHands.size(); j++) {
        if (pokerHands.get(i).equals(pokerHands.get(j))) {
          playerCard.setType("with pair of " + String.valueOf(pokerHands.get(i)));
          score = 2;
        }
      }
      if (score == 2) {
        break;
      }
    }

    // two pair
    int pairNum = 0;
    int firstCount = 0 , secondCount = 0;
    for (int i = 0; i < pokerHands.size(); i++) {
      for (int j = i + 1; j < pokerHands.size(); j++) {
        if (pairNum == 1 && pokerHands.get(i).equals(pokerHands.get(j))) {
          secondCount = pokerHands.get(i);
          pairNum++;
        }
        if (pairNum == 0 && pokerHands.get(i).equals(pokerHands.get(j))) {
          firstCount = pokerHands.get(i);
          pairNum++;
        }
      }
      if (pairNum == 2) {
        playerCard.setType("with two pairs of " + firstCount + " and " + secondCount);
        score = 3;
        break;
      }
    }

    // Three of a Kind
    for (int i = 0; i < pokerHands.size(); i++) {
      int num = 1;
      for (int j = i + 1; j < pokerHands.size(); j++) {
        if (pokerHands.get(i).equals(pokerHands.get(j))) {
          num++;
        }
      }
      if (num == 3) {
        playerCard.setType("with three of a kind of " + String.valueOf(pokerHands.get(i)));
        score = 4;
        break;
      }
    }

    //Straight
    boolean isStraight = true;
    String straightCardType = "";
    for (int i = 0; i < pokerHands.size() - 1; i++) {
      straightCardType += String.valueOf(pokerHands.get(i)) + " ";
      if (!pokerHands.get(i).equals(pokerHands.get(i + 1))) {
        isStraight = false;
      }
    }
    if (pokerHands.get(0) == 1 && pokerHands.get(1) == 10 && pokerHands.get(2) == 11 && pokerHands.get(3) == 12
        && pokerHands.get(4) == 13) {
      straightCardType = "1 10 11 12 13";
      isStraight = true;
    }
    if (isStraight) {
      playerCard.setType("with Straight of :" + straightCardType);
      score = 5;
    }

    //Flush
    boolean isFlush = false;
    int front = 0, end = COUNT - 1;
    String flushCardType = "";
    String color = pokerColors.get(front);
    while (front <= end) {
      if (front == end && pokerColors.get(front).equals(pokerColors.get(0))) {
        playerCard.setType("with Flush of:" + flushCardType);
        isFlush = true;
        score = 6;
      } else {
        if (pokerColors.get(front).equals(pokerColors.get(end))) {
          flushCardType += String.valueOf((pokerColors.get(front))) + color;
          front++;
          end--;
        } else {
          break;
        }
      }
    }

    //Full House
    if (pokerHands.get(0).equals(pokerHands.get(2)) && pokerHands.get(3).equals(pokerHands.get(COUNT - 1))) {
      playerCard.setType("with Full House of :" + pokerHands.get(0) + " over " + pokerHands.get(3));
      score = 7;
    }
    if (pokerHands.get(2).equals(pokerHands.get(COUNT - 1)) && pokerHands.get(0).equals(pokerHands.get(1))) {
      playerCard.setType("with Full House of :" + pokerHands.get(2) + " over " + pokerHands.get(0));
      score = 7;
    }

    // Four of a Kind
    for (int i = 0; i < pokerHands.size(); i++) {
      int num = 1;
      for (int j = i + 1; j < pokerHands.size(); j++) {
        if (pokerHands.get(i).equals(pokerHands.get(j))) {
          num++;
        }
      }
      if (num == 4) {
        playerCard.setType("with Four of a Kind of : " + pokerHands.get(i));
        score = 8;
        break;
      }
    }

    //Straight flush
    if (isStraight && isFlush) {
      playerCard.setType("with Straight flush of : " + flushCardType);
      score = 9;
    }
    playerCard.setScore(score);
    playerCard.setMaxNum(pokerHands.get(COUNT - 1));


    return playerCard;
  }


  public boolean isPokerHandsEquals() {
    for (int i = 0; i < COUNT - 1; i++) {
      if (!blackPokerHands.get(i).equals(whitePokerHands.get(i))) {
        return false;
      }
    }
    return true;
  }

  public String calculateWinner(String black, String white) {

    separateCardsAndSuits(black, white);
    PlayerCard blackCard = calculateScore(blackPokerHands, blackPokerColor);
    PlayerCard whiteCard = calculateScore(whitePokerHands, whitePokerColor);

    System.out.println( whiteCard.getType());

    if (blackCard.getScore().equals(whiteCard.getScore())) {
      if (blackCard.getScore() == 1) {
        if(blackCard.getMaxNum().equals(whiteCard.getMaxNum())){
          return "Tie";
        }
      }
    }

    if (blackCard.getScore() < whiteCard.getScore()) {
        return "white wins " + whiteCard.getType() ;
    }

    return null;
  }


  public static void main(String[] args) {
    Demo demo = new Demo();

    demo.calculateWinner("2D 3H 5C 9S KH", "5D 3H 5C 5S KH");
  }

}
