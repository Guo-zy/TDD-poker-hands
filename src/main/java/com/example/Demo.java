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


    public void separateCardsAndSuits(String black, String white) {
        blackPokerHands.clear();
        blackPokerColor.clear();
        whitePokerHands.clear();
        whitePokerColor.clear();

        String[] blacks = black.split(" ");
        String[] whites = white.split(" ");
        for (String s : blacks) {
            blackPokerHands.add(pokerHandsCard.get(s.substring(0, 1)));
            blackPokerColor.add(s.substring(1));
        }
        for (String s : whites) {
            whitePokerHands.add(pokerHandsCard.get(s.substring(0, 1)));
            whitePokerColor.add(s.substring(1));
        }
        Collections.sort(blackPokerHands);
        Collections.sort(whitePokerHands);
    }

    private void pairCalculate(List<Integer> pokerHands, PlayerCard playerCard) {
        int pairNum = 1;
        int sameNum = 0;
        for (int i = 0; i < pokerHands.size(); i++) {
            int tmp = 1;
            for (int j = i + 1; j < pokerHands.size(); j++) {
                if (pokerHands.get(i).equals(pokerHands.get(j))) {
                    tmp++;
                    sameNum = pokerHands.get(i);
                }
            }
            pairNum = Math.max(tmp, pairNum);
            if (pairNum == 4) {
                break;
            }
        }
        if (pairNum == 2) {
            playerCard.setType("with pair of " + sameNum);
            playerCard.setScore(2);
        }
        if (pairNum == 3) {
            playerCard.setType("with three of a kind of " + sameNum);
            playerCard.setScore(4);
        }
        if (pairNum == 4) {
            playerCard.setType("with four of a kind of " + sameNum);
            playerCard.setScore(8);
        }
    }

    private void twoPairCalculate(List<Integer> pokerHands, PlayerCard playerCard) {
        int pairNum = 0;
        int firstCount = 0, secondCount = 0;
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
                if (3 > playerCard.getScore()) {
                    playerCard.setType("with two pairs of " + firstCount + " and " + secondCount);
                    playerCard.setScore(3);
                }
                break;
            }
        }
    }

    private void StraightAndFlushCalculate(List<Integer> pokerHands, List<String> pokerColors, PlayerCard playerCard) {
        boolean isStraight = true;
        StringBuilder straightCardType = new StringBuilder();
        for (int i = 0; i < pokerHands.size() - 1; i++) {
            straightCardType.append(pokerHands.get(i));
            if (pokerHands.get(i) + 1 != pokerHands.get(i + 1)) {
                isStraight = false;
            }
            if (i == pokerHands.size() - 2) {
                straightCardType.append(pokerHands.get(i + 1));
            }
        }
        if (pokerHands.get(0) == 1 && pokerHands.get(1) == 10 && pokerHands.get(2) == 11 && pokerHands.get(3) == 12
                && pokerHands.get(4) == 13) {
            straightCardType = new StringBuilder("TJQKA");
            isStraight = true;
        }
        if (isStraight) {
            playerCard.setType("with straight of " + straightCardType);
            playerCard.setScore(5);
        }

        boolean isFlush = true;
        StringBuilder flushCardType = new StringBuilder();
        String color = pokerColors.get(0);
        for (String pokerColor : pokerColors) {
            if (!pokerColor.equals(color)) {
                isFlush = false;
                break;
            }
        }
        if (isFlush) {
            for (int num : pokerHands) {
                for (String getkey : pokerHandsCard.keySet()) {
                    if (pokerHandsCard.get(getkey).equals(num)) {
                        flushCardType.append(getkey).append(color);

                    }
                }
            }
            playerCard.setScore(6);
            playerCard.setType("with flush of " + flushCardType);
        }

        if (isStraight && isFlush) {
            playerCard.setType("with straight flush of " + flushCardType);
            playerCard.setScore(9);

        }
    }

    private void fullHouseCalculate(List<Integer> pokerHands, PlayerCard playerCard) {
        if (pokerHands.get(0).equals(pokerHands.get(2)) && pokerHands.get(3).equals(pokerHands.get(COUNT - 1))) {
            playerCard.setType("with full house of " + pokerHands.get(0) + " over " + pokerHands.get(3));
            playerCard.setScore(7);
        }
        if (pokerHands.get(2).equals(pokerHands.get(COUNT - 1)) && pokerHands.get(0).equals(pokerHands.get(1))) {
            playerCard.setType("with full house of " + pokerHands.get(2) + " over " + pokerHands.get(0));
            playerCard.setScore(7);
        }
    }


    public PlayerCard calculateScore(List<Integer> pokerHands, List<String> pokerColors) {

        PlayerCard playerCard = new PlayerCard();
        playerCard.setScore(1);

        // pair , three of a kind , four of a kind
        pairCalculate(pokerHands, playerCard);

        // two pair
        twoPairCalculate(pokerHands, playerCard);

        //Straight, flush , Straight and flush
        StraightAndFlushCalculate(pokerHands, pokerColors, playerCard);

        //Full House
        fullHouseCalculate(pokerHands, playerCard);

        return playerCard;
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
        for (int i = 0; i< blackPokerHands.size() - 1; i++) {
            if(blackPokerHands.get(i).equals(blackPokerHands.get(i+1))){
                blackPairNum = blackPokerHands.get(i);
            }
            if (whitePokerHands.get(i).equals(whitePokerHands.get(i+1))){
                whitePairNum = whitePokerHands.get(i);
            }
        }
        if(blackPairNum == whitePairNum){
            if(blackPairNum != 1 ){
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
        }
        else
        return blackPairNum > whitePairNum ? "black wins with pair of " + blackPairNum :"white wins with pair of " + whitePairNum;
    }

    public String calculateWinner(String black, String white) {

        separateCardsAndSuits(black, white);
        PlayerCard blackCard = calculateScore(blackPokerHands, blackPokerColor);
        PlayerCard whiteCard = calculateScore(whitePokerHands, whitePokerColor);


        if (isTie(blackPokerHands, whitePokerHands)) {
            return "Tie";
        }

        if (blackCard.getScore().equals(whiteCard.getScore())) {
            if (blackCard.getScore() == 1) {
                return calculateWinnerOfHighCard(blackPokerHands, whitePokerHands);
            }
            if (blackCard.getScore() == 2) {
                return calculateWinnerOfPair(blackPokerHands, whitePokerHands);
            }
        }

        if (blackCard.getScore() < whiteCard.getScore()) {
            return "white wins " + whiteCard.getType();
        }

        return "black wins " + blackCard.getType();
    }


    public static void main(String[] args) {
        Demo demo = new Demo();

        demo.calculateWinner("2S 3H 5C 9D KS", "2H 6D 8S 7C AH");
    }

}
