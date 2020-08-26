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

  @Test
  public void should_return_white_wins_with_three_of_a_kind_of_5_when_calculate_winner_given_black_with_high_card_and_white_with_three_of_a_kind() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "5D 9H 5C 5S KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with three of a kind of 5", res);
  }

  @Test
  public void should_return_white_wins_with_straight_of_34567_when_calculate_winner_given_black_with_high_card_and_white_with_straight_of_34567() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3D 4H 5C 6S 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight of 34567", res);
  }

  @Test
  public void should_return_white_wins_with_flush_of_367TQ_with_diamonds_when_calculate_winner_given_black_with_high_card_and_white_with_flush_of_367TQ() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3D 6D 7D TD QD";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with flush of 3D6D7DTDQD", res);
  }

  @Test
  public void should_return_white_wins_with_full_house_of_33553_with_diamonds_when_calculate_winner_given_black_with_high_card_and_white_with_full_house_of_33553() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3H 3D 5S 5C 3D";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_with_diamonds_when_calculate_winner_given_black_with_high_card_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3H 3D 3S 3C KD";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

}





