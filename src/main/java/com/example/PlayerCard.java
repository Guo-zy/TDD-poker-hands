package com.example;

public class PlayerCard {

  protected Integer score;
  protected String Type;
  protected Integer maxNum;

  public Integer getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(Integer maxNum) {
    this.maxNum = maxNum;
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
}
