package com.example;

import java.util.ArrayList;
import java.util.List;

public class PlayerCard {

  private static final Integer COUNT = 5;
  protected Integer score;
  protected String Type;
  protected String particularNums;
  protected String originNums;
  protected String colors;
  protected List<Integer> pokerHands;
  protected List<String> pokerColors;
  protected boolean isStraightAndFlush;

  public boolean isStraightAndFlush() {
    return isStraightAndFlush;
  }


  public PlayerCard() {
    score = 1;
    pokerHands = new ArrayList<>(COUNT);
    pokerColors = new ArrayList<>(COUNT);
    isStraightAndFlush = false;
    originNums="";
    colors="";

  }

  public List<Integer> getPokerHands() {
    return pokerHands;
  }

  public void setPokerHands(List<Integer> pokerHands) {
    this.pokerHands = pokerHands;
  }

  public List<String> getPokerColors() {
    return pokerColors;
  }

  public void setPokerColors(List<String> pokerColors) {
    this.pokerColors = pokerColors;
  }

  public void setStraightAndFlush(boolean straightAndFlush) {
    isStraightAndFlush = straightAndFlush;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
  }


  public String getParticularNums() {
    return particularNums;
  }

  public void setParticularNums(String particularNums) {
    this.particularNums = particularNums;
  }

  public String getOriginNums() {
    return originNums;
  }

  public void setOriginNums(String originNums) {
    this.originNums = originNums;
  }

  public String getColors() {
    return colors;
  }

  public void setColors(String colors) {
    this.colors = colors;
  }
}
