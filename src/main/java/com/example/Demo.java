package com.example;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Demo {

  private static final Integer COUNT = 5;

  private PlayerCard blackCards;
  private PlayerCard whiteCards;

  private final List<Integer> blackPokerHands = new ArrayList<>(COUNT);
  private final List<Integer> whitePokerHands = new ArrayList<>(COUNT);
  private final List<String> blackPokerColor = new ArrayList<>(COUNT);
  private final List<String> whitePokerColor = new ArrayList<>(COUNT);


  private final Map<String, Integer> pokerHandsCard;
  private final Map<Integer, String> pokerHandsSituation;

  {
    pokerHandsSituation = new HashMap<>();
    pokerHandsSituation.put(1, "A");
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
    pokerHandsCard.put("A", 1);
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


  private void separateCardsAndSuits(String black, String white) {
    blackPokerHands.clear();
    blackPokerColor.clear();
    whitePokerHands.clear();
    whitePokerColor.clear();

    String[] blacks = black.split(" ");
    String[] whites = white.split(" ");
    StringBuilder blackOriginNums = new StringBuilder();
    StringBuilder blackOriginColors = new StringBuilder();
    StringBuilder whiteOriginNums = new StringBuilder();
    StringBuilder whiteOriginColors = new StringBuilder();

    for (String s : blacks) {
      blackOriginNums.append(s, 0, 1);
      blackOriginColors.append(s.substring(1));
      blackPokerHands.add(pokerHandsCard.get(s.substring(0, 1)));
      blackPokerColor.add(s.substring(1));
    }
    for (String s : whites) {
      whiteOriginNums.append(s, 0, 1);
      whiteOriginColors.append(s.substring(1));
      whitePokerHands.add(pokerHandsCard.get(s.substring(0, 1)));
      whitePokerColor.add(s.substring(1));
    }

    whiteCards.setOriginNums(whiteOriginNums.toString());
    whiteCards.setColors(whiteOriginColors.toString());
    blackCards.setOriginNums(blackOriginNums.toString());
    blackCards.setColors(blackOriginColors.toString());

    Collections.sort(blackPokerHands);
    Collections.sort(whitePokerHands);
  }

  private void pairCalculate(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int score = 2;

    for (int i = 0; i < COUNT - 2; i++) {
      if (blackPokerHands.get(i).equals(blackPokerHands.get(i + 1))) {
        blackCards.setScore(score);
        blackCards.setType("with pair of " + pokerHandsSituation.get(blackPokerHands.get(i)));
        blackCards.setParticularNums(pokerHandsSituation.get(blackPokerHands.get(i)));
      }
      if (whitePokerHands.get(i).equals(whitePokerHands.get(i + 1))) {
        whiteCards.setScore(score);
        whiteCards.setType("with pair of " + pokerHandsSituation.get(whitePokerHands.get(i)));
        whiteCards.setParticularNums(pokerHandsSituation.get(whitePokerHands.get(i)));
      }
    }
  }

  private void twoPairCalculate(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int score = 3;
    StringBuilder blackPair = new StringBuilder();
    StringBuilder whitePair = new StringBuilder();

    for (int i = 0; i < COUNT - 2; i++) {
      if (blackPokerHands.get(i).equals(blackPokerHands.get(i + 1))) {
        blackPair.append(pokerHandsSituation.get(blackPokerHands.get(i)));
      }
      if (whitePokerHands.get(i).equals(whitePokerHands.get(i + 1))) {
        whitePair.append(pokerHandsSituation.get(whitePokerHands.get(i)));
      }
    }

    if (blackPair.length() == 2) {
      blackCards.setScore(score);
      blackCards.setParticularNums(blackPair.toString());
      blackCards.setType("with two pairs of " + blackPair.substring(0, 1) + " and " + blackPair.substring(1));

    }
    if (whitePair.length() == 2) {
      whiteCards.setScore(score);
      whiteCards.setParticularNums(whitePair.toString());
      whiteCards.setType("with two pairs of " + whitePair.substring(0, 1) + " and " + whitePair.substring(1));
    }

  }

  private void threeOfAKindCalculate(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int score = 4;

    for (int i = 0; i < COUNT - 3; i++) {
      if (blackPokerHands.get(i).equals(blackPokerHands.get(i + 1))
          && blackPokerHands.get(i).equals(blackPokerHands.get(i + 2))) {
        blackCards.setScore(score);
        blackCards.setType("with three of a kind of " + pokerHandsSituation.get(blackPokerHands.get(i)));
        blackCards.setParticularNums(pokerHandsSituation.get(blackPokerHands.get(i)));
      }
      if (whitePokerHands.get(i).equals(whitePokerHands.get(i + 1))
          && whitePokerHands.get(i).equals(whitePokerHands.get(i + 2))) {
        whiteCards.setScore(score);
        whiteCards.setType("with three of a kind of " + pokerHandsSituation.get(whitePokerHands.get(i)));
        whiteCards.setParticularNums(String.valueOf(pokerHandsSituation.get(blackPokerHands.get(i))));
      }
    }


  }

  private void straightCalculate(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int score = 5;

    int blackFirst = blackPokerHands.get(0), whiteFirst = whitePokerHands.get(0);
    int blackSecond = blackPokerHands.get(1), whiteSecond = whitePokerHands.get(1);
    int blackThird = blackPokerHands.get(2), whiteThird = whitePokerHands.get(2);
    int blackForth = blackPokerHands.get(3), whiteForth = whitePokerHands.get(3);
    int blackFifth = blackPokerHands.get(4), whiteFifth = whitePokerHands.get(4);

    String blackType = "" +
        pokerHandsSituation.get(blackFirst) +
        pokerHandsSituation.get(blackSecond) +
        pokerHandsSituation.get(blackThird) +
        pokerHandsSituation.get(blackForth) +
        pokerHandsSituation.get(blackFifth);

    String whiteType = "" +
        pokerHandsSituation.get(whiteFirst) +
        pokerHandsSituation.get(whiteSecond) +
        pokerHandsSituation.get(whiteThird) +
        pokerHandsSituation.get(whiteForth) +
        pokerHandsSituation.get(whiteFifth);

    if (blackFirst + 1 == blackSecond && blackSecond + 1 == blackThird
        && blackThird + 1 == blackForth && blackForth + 1 == blackFifth) {
      blackCards.setType("with straight of " + blackType);
      blackCards.setScore(score);
      blackCards.setParticularNums(blackType);
    }
    if (whiteFirst + 1 == whiteSecond && whiteSecond + 1 == whiteThird
        && whiteThird + 1 == whiteForth && whiteForth + 1 == whiteFifth) {
      whiteCards.setType("with straight of " + whiteType);
      whiteCards.setScore(score);
      whiteCards.setParticularNums(whiteType);
    }

    if (blackFirst == 1 && blackSecond == 10 && blackThird == 11 && blackForth == 12 &&
        blackFifth == 13) {
      blackCards.setType("with straight of " + blackType);
      blackCards.setScore(score);
      blackCards.setParticularNums(blackType);
    }

    if (whiteFirst == 1 && whiteSecond == 10 && whiteThird == 11 && whiteForth == 12 &&
        whiteFifth == 13) {
      whiteCards.setType("with straight of " + whiteType);
      whiteCards.setScore(score);
      whiteCards.setParticularNums(whiteType);
    }
  }

  private void flushCalculate(List<String> blackPokerColors, List<String> whitePokerColors) {
    int score = 6;

    HashSet<String> blackHset = new HashSet<>();
    HashSet<String> whiteHset = new HashSet<>();

    for (int i = 0; i < COUNT - 1; i++) {
      blackHset.add(blackPokerColors.get(i));
      whiteHset.add(whitePokerColors.get(i));
    }
    if (blackHset.size() == 1) {
      if (blackCards.getScore() == 5) {
        blackCards.setStraightAndFlush(true);
      }
      blackCards.setScore(score);
      blackCards.setType("with flush of " + blackCards.getOriginNums() + " with " + blackPokerColors.get(0));
      blackCards.setParticularNums(blackCards.getOriginNums() + blackPokerColors.get(0));
    }
    if (whiteHset.size() == 1) {
      if (whiteCards.getScore() == 5) {
        whiteCards.setStraightAndFlush(true);
      }
      whiteCards.setScore(score);
      whiteCards.setType("with flush of " + whiteCards.getOriginNums() + " with " + whitePokerColors.get(0));
      whiteCards.setParticularNums(whiteCards.getOriginNums() + whitePokerColors.get(0));
    }
  }

  private void fullHouseCalculate(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int score = 7;

    int blackFirst = blackPokerHands.get(0), whiteFirst = whitePokerHands.get(0);
    int blackSecond = blackPokerHands.get(1), whiteSecond = whitePokerHands.get(1);
    int blackThird = blackPokerHands.get(2), whiteThird = whitePokerHands.get(2);
    int blackForth = blackPokerHands.get(3), whiteForth = whitePokerHands.get(3);
    int blackFifth = blackPokerHands.get(4), whiteFifth = whitePokerHands.get(4);

    if (blackFirst == blackThird && blackForth == blackFifth) {
      blackCards.setType(
          "with full house of " + pokerHandsSituation.get(blackFirst) + " over " + pokerHandsSituation.get(blackForth));
      blackCards.setScore(score);
      blackCards.setParticularNums(pokerHandsSituation.get(blackFirst) + pokerHandsSituation.get(blackForth));
    }

    if (blackFirst == blackSecond && blackThird == blackFifth) {
      blackCards.setType(
          "with full house of " + pokerHandsSituation.get(blackForth) + " over " + pokerHandsSituation.get(blackFirst));
      blackCards.setScore(score);
      blackCards.setParticularNums(pokerHandsSituation.get(blackForth) + pokerHandsSituation.get(blackFirst));
    }

    if (whiteFirst == whiteThird && whiteForth == whiteFifth) {
      whiteCards.setType(
          "with full house of " + pokerHandsSituation.get(whiteFirst) + " over " + pokerHandsSituation.get(whiteFifth));
      whiteCards.setScore(score);
      whiteCards.setParticularNums(pokerHandsSituation.get(whiteFirst) + pokerHandsSituation.get(whiteFifth));
    }

    if (whiteFirst == whiteSecond && whiteThird == whiteFifth) {
      whiteCards.setType(
          "with full house of " + pokerHandsSituation.get(whiteThird) + " over " + pokerHandsSituation.get(whiteFirst));
      whiteCards.setScore(score);
      whiteCards.setParticularNums(pokerHandsSituation.get(whiteThird) + pokerHandsSituation.get(whiteFirst));
    }
  }

  private void fourOfKindCalculate(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int score = 8;

    if (blackPokerHands.get(0).equals(blackPokerHands.get(3)) ||
        blackPokerHands.get(1).equals(blackPokerHands.get(4))) {
      System.out.println(1);
      blackCards.setScore(score);
      blackCards.setType("with four of a kind of " + pokerHandsSituation.get(blackPokerHands.get(2)));
      blackCards.setParticularNums(pokerHandsSituation.get(blackPokerHands.get(2)));
    }

    if (whitePokerHands.get(0).equals(whitePokerHands.get(3)) ||
        whitePokerHands.get(1).equals(whitePokerHands.get(4))) {

      whiteCards.setScore(score);
      whiteCards.setType("with four of a kind of " + pokerHandsSituation.get(whitePokerHands.get(2)));
      whiteCards.setParticularNums(pokerHandsSituation.get(whitePokerHands.get(2)));
    }
  }

  private void straightAndFlushCalculate() {
    int score = 9;
    if (blackCards.isStraightAndFlush()) {
      blackCards.setScore(score);
      blackCards.setType("with straight flush of " + blackCards.getOriginNums() +" with " + blackPokerColor.get(0));
    }
    if (whiteCards.isStraightAndFlush()) {
      whiteCards.setScore(score);
      whiteCards.setType("with straight flush of " + whiteCards.getOriginNums() +" with " + whitePokerColor.get(0));
    }
  }


  private boolean isTie(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    for (int i = 0; i < blackPokerHands.size(); i++) {
      if (!blackPokerHands.get(i).equals(whitePokerHands.get(i))) {
        return false;
      }
    }
    return true;
  }

  private String calculateWinnerOfHighCard(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    if (blackPokerHands.get(0) == 1 && whitePokerHands.get(0) != 1) {
      return " black wins with high card A";
    }
    if (whitePokerHands.get(0) == 1 && blackPokerHands.get(0) != 1) {
      return "white wins with high card A";
    }

    for (int i = COUNT - 1; i >= 0; i--) {
      if (blackPokerHands.get(i) > whitePokerHands.get(i)) {
        for (String getkey : pokerHandsCard.keySet()) {
          if (pokerHandsCard.get(getkey).equals(blackPokerHands.get(i))) {
            return "black wins with high card " + getkey;
          }
        }
      }
      if (whitePokerHands.get(i) > blackPokerHands.get(i)) {
        for (String getkey : pokerHandsCard.keySet()) {
          if (pokerHandsCard.get(getkey).equals(whitePokerHands.get(i))) {
            return "white wins with high card " + getkey;
          }
        }

      }
    }
    return null;
  }

  private String calculateWinnerOfPair(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int blackPairNum = 0;
    int whitePairNum = 0;
    for (int i = 0; i < blackPokerHands.size() - 1; i++) {
      if (blackPokerHands.get(i).equals(blackPokerHands.get(i + 1))) {
        blackPairNum = blackPokerHands.get(i);
      }
      if (whitePokerHands.get(i).equals(whitePokerHands.get(i + 1))) {
        whitePairNum = whitePokerHands.get(i);
      }
    }
    if (blackPairNum == whitePairNum) {
      if (blackPairNum != 1) {
        if (blackPokerHands.get(0) == 1 && whitePokerHands.get(0) != 1) {
          return "black wins with pair of max num is A";
        }
        if (whitePokerHands.get(0) == 1 && blackPokerHands.get(0) != 1) {
          return "white wins with pair of max num is A";
        }
      }
      for (int i = COUNT - 1; i >= 0; i--) {
        if (blackPokerHands.get(i) > whitePokerHands.get(i)) {
          for (String getkey : pokerHandsCard.keySet()) {
            if (pokerHandsCard.get(getkey).equals(blackPokerHands.get(i))) {
              return "black wins with pair of max num is " + getkey;
            }
          }
        }
        if (whitePokerHands.get(i) > blackPokerHands.get(i)) {
          for (String getkey : pokerHandsCard.keySet()) {
            if (pokerHandsCard.get(getkey).equals(whitePokerHands.get(i))) {
              return "white wins with pair of max num is " + getkey;
            }
          }
        }
      }
      return null;
    } else {
      return blackPairNum > whitePairNum ? "black wins with pair of " + blackPairNum
          : "white wins with pair of " + whitePairNum;
    }
  }

  private String calculateWinnerOfTwoPairs(List<Integer> blackPokerHands, List<Integer> whitePokerHands) {
    int blackFirstCp = 0;
    int blackSecondCp = 0;
    int whiteFirstCp = 0;
    int whiteSecondCp = 0;
    return null;
  }


  private void calculateScore(List<Integer> blackPokerHands, List<String> blackPokerColors,
      List<Integer> whitePokerHands, List<String> whitePokerColors) {

    blackCards.setScore(1);
    whiteCards.setScore(1);

    // pair
    pairCalculate(blackPokerHands, whitePokerHands);

    //two pair
    twoPairCalculate(blackPokerHands, whitePokerHands);

    //three of a kind
    threeOfAKindCalculate(blackPokerHands, whitePokerHands);

    //Straight
    straightCalculate(blackPokerHands, whitePokerHands);

    //flush
    flushCalculate(blackPokerColors, whitePokerColors);

    //Full House
    fullHouseCalculate(blackPokerHands, whitePokerHands);

    //four of a kind
    fourOfKindCalculate(blackPokerHands, whitePokerHands);

    //Straight and flush
    straightAndFlushCalculate();

  }


  public String calculateWinner(String black, String white) {

    blackCards = new PlayerCard();
    whiteCards = new PlayerCard();

    separateCardsAndSuits(black, white);

    calculateScore(blackPokerHands, blackPokerColor, whitePokerHands, whitePokerColor);

    if (isTie(blackPokerHands, whitePokerHands)) {
      return "Tie";
    }

//        if (blackCards.getScore().equals(whiteCards.getScore())) {
//            if (blackCards.getScore() == 1) {
//                return calculateWinnerOfHighCard(blackPokerHands, whitePokerHands);
//            }
//            if (blackCards.getScore() == 2) {
//                return calculateWinnerOfPair(blackPokerHands, whitePokerHands);
//            }
//            if (blackCards.getScore() == 3) {
//                return calculateWinnerOfTwoPairs(blackPokerHands, whitePokerHands);
//            }
//        }

    System.out.println("black : " + blackCards.getScore());
    System.out.println("white : " + whiteCards.getScore());

    if (blackCards.getScore() < whiteCards.getScore()) {
      return "white wins " + whiteCards.getType();
    }

    return "black wins " + blackCards.getType();
  }


  public static void main(String[] args) {
    Demo demo = new Demo();

    demo.calculateWinner("4S 5S 6S 7S 9S", "3H 3D 3S 3C KH");
  }

}
