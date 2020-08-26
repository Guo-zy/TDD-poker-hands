package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest {

  private static Demo demo;

  @BeforeAll
  public static void setUp() throws Exception {
    demo = new Demo();
  }

  @Test
  public void test01() {
    String result = demo.run();
    assertThat(result).isEqualTo("ABC");
  }


  @Test
  public void should_return_tie_when_calculate_winner_given_same_hands_num() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "2D 3H 5C 9S KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("Tie", res);
  }

  @Test
  public void should_return_white_wins_with_pair_of_5_when_calculate_winner_given_black_with_high_card_and_white_with_pair_5() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "5D 3H 5C 9S KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with pair of 5", res);
  }

  @Test
  public void should_return_white_wins_with_two_pairs_when_calculate_winner_given_black_with_high_card_and_white_with_two_pairs() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "5D 9H 5C 9S KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with two pairs of 5 and 9", res);
  }

}





