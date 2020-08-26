package com.example;

import java.util.HashMap;
import java.util.Map;

public class Demo {

  private int black_score = 0;
  private int white_score = 0;

  private Map<String, Integer> poker_hands_situation;

  {
    poker_hands_situation = new HashMap<>();
    poker_hands_situation.put("High Card", 1);
    poker_hands_situation.put("Pair", 2);
    poker_hands_situation.put("Two Pairs", 3);
    poker_hands_situation.put("Three of a Kind", 4);
    poker_hands_situation.put("Straight", 5);
    poker_hands_situation.put("Flush", 6);
    poker_hands_situation.put("Full House", 7);
    poker_hands_situation.put("Four of a Kind", 8);
    poker_hands_situation.put("Straight flush", 9);
  }


  public String run() {
    return "ABC";
  }

  public String calculate_winner(String black, String white) {

      return null;
  }

}
