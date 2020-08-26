package com.example;

public class PlayerCard {

  protected Integer score;
  protected String Type;
  protected String particularNums;
  protected String originNums;
  protected String colors;
  protected boolean isStraightAndFlush;

  public boolean isStraightAndFlush() {
    return isStraightAndFlush;
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
