package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

  private static final Integer COUNT = 5;

  private Integer blackScore = 0;
  private Integer whiteScore = 0;

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

  public String calculate_winner(String black, String white) {
    String[] blacks = black.split(" ");
    String[] whites = white.split(" ");
    for (String s : blacks) {
      blackPokerHands.add(pokerHandsSituation.get(s.substring(0,1)));
      blackPokerColor.add(s.substring(1));
    }
    for (String s : whites) {
      whitePokerHands.add(pokerHandsSituation.get(s.substring(0,1)));
      whitePokerColor.add(s.substring(1));
    }

    for(int i = 0; i < COUNT; i ++){
      if(!blackPokerHands.get(i).equals(whitePokerHands.get(i))) return  null;
    }

    return "Tie";
  }






  public static void main(String[] args) {
    Demo demo = new Demo();

    demo.calculate_winner("2H 3D 5S 9C KD", "2D 3H 5C 9S KH");
  }

}
