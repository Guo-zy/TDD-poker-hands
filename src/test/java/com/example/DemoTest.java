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
    String white = "5D 9H 5C 5H KH";

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
  public void should_return_white_wins_with_full_house_of_33553_when_calculate_winner_given_black_with_high_card_and_white_with_full_house_of_33553() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3H 3D 5H 5C 3H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_high_card_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_high_card_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_two_pairs_when_calculate_winner_given_black_with_pair_and_white_with_two_pairs() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "5D 9H 5C 9S KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with two pairs of 5 and 9", res);
  }

  @Test
  public void should_return_white_wins_with_three_of_a_kind_of_5_when_calculate_winner_given_black_with_pair_and_white_with_three_of_a_kind() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "5D 9H 5C 5H KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with three of a kind of 5", res);
  }

  @Test
  public void should_return_white_wins_with_straight_of_34567_when_calculate_winner_given_black_with_pair_and_white_with_straight_of_34567() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "3D 4H 5C 6S 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight of 34567", res);
  }

  @Test
  public void should_return_white_wins_with_flush_of_367TQ_with_diamonds_when_calculate_winner_given_black_with_pair_and_white_with_flush_of_367TQ() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "3D 6D 7D TD QD";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with flush of 3D6D7DTDQD", res);
  }

  @Test
  public void should_return_white_wins_with_full_house_of_33553_when_calculate_winner_given_black_with_pair_and_white_with_full_house_of_33553() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "3H 3D 5H 5C 3H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_pair_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_pair_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "2H 2D 5S 9C KD";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_three_of_a_kind_of_5_when_calculate_winner_given_black_with_two_pairs_and_white_with_three_of_a_kind() {
    //given
    String black = "2H 2D 9S 9C KD";
    String white = "5D 9H 5C 5H KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with three of a kind of 5", res);
  }

  @Test
  public void should_return_white_wins_with_straight_of_34567_when_calculate_winner_given_black_with_two_pairs_and_white_with_straight_of_34567() {
    //given
    String black = "2H 2D 9S 9C KD";
    String white = "3D 4H 5C 6S 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight of 34567", res);
  }

  @Test
  public void should_return_white_wins_with_flush_of_367TQ_with_diamonds_when_calculate_winner_given_black_with_two_pairs_and_white_with_flush_of_367TQ() {
    //given
    String black = "2H 2D 9S 9C KD";
    String white = "3D 6D 7D TD QD";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with flush of 3D6D7DTDQD", res);
  }

  @Test
  public void should_return_white_wins_with_full_house_of_33553_when_calculate_winner_given_black_with_two_pairs_and_white_with_full_house_of_33553() {
    //given
    String black = "2H 2D 9S 9C KD";
    String white = "3H 3D 5H 5C 3H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_two_pairs_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "2H 2D 9S 9C KD";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_two_pairs_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "2H 2D 9S 9C KD";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_straight_of_34567_when_calculate_winner_given_black_with_three_of_a_kind_and_white_with_straight_of_34567() {
    //given
    String black = "2H 2D 2S 9C KD";
    String white = "3D 4H 5C 6S 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight of 34567", res);
  }

  @Test
  public void should_return_white_wins_with_flush_of_367TQ_with_diamonds_when_calculate_winner_given_black_with_three_of_a_kind_and_white_with_flush_of_367TQ() {
    //given
    String black = "2H 2D 2S 9C KD";
    String white = "3D 6D 7D TD QD";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with flush of 3D6D7DTDQD", res);
  }

  @Test
  public void should_return_white_wins_with_full_house_of_33553_when_calculate_winner_given_black_with_three_of_a_kind_and_white_with_full_house_of_33553() {
    //given
    String black = "2H 2D 2S 9C KD";
    String white = "3H 3D 5H 5C 3H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }


  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_three_of_a_kind_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "2H 2D 2S 9C KD";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_three_of_a_kind_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "2H 2D 2S 9C KD";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_flush_of_367TQ_with_diamonds_when_calculate_winner_given_black_with_straight_and_white_with_flush_of_367TQ() {
    //given
    String black = "3H 4D 5S 6C 7H";
    String white = "3D 6D 7D TD QD";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with flush of 3D6D7DTDQD", res);
  }

  @Test
  public void should_return_white_wins_with_full_house_of_33553_when_calculate_winner_given_black_with_straight_and_white_with_full_house_of_33553() {
    //given
    String black = "3S 4D 5S 6C 7D";
    String white = "3H 3D 5H 5C 3C";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_straight_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "4H 5D 6S 7C 8D";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_straight_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "4C 5D 6S 7C 8D";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_full_house_of_33553_when_calculate_winner_given_black_with_flush_and_white_with_full_house_of_33553() {
    //given
    String black = "3S 4S 5S 6S 8S";
    String white = "3H 3D 5H 5C 3C";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with full house of 3 over 5", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_flush_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "4S 5S 6S 7S 9S";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_flush_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "4S 5S 6S 7S 9S";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_4_of_a_kind_of_3_when_calculate_winner_given_black_with_full_house_and_white_with_4_of_a_kind_of_3() {
    //given
    String black = "4S 4D 4H 9C 9S";
    String white = "3H 3D 3S 3C KH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with four of a kind of 3", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_full_house_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "8S 8H 8C 9H 9S";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }

  @Test
  public void should_return_white_wins_with_straight_flush_of_34567_with_diamonds_when_calculate_winner_given_black_with_4_of_kind_of_8_and_white_with_straight_flush_of_34567_with_diamonds() {
    //given
    String black = "8S 8H 8C 8D 9S";
    String white = "3H 4H 5H 6H 7H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with straight flush of 3H4H5H6H7H", res);
  }


  @Test
  public void should_return_white_wins_with_high_card_A_when_calculate_winner_given_black_with_high_card_2359K_and_white_with_high_card_2687A() {
    //given
    String black = "2S 3H 5C 9D KS";
    String white = "2H 6D 8S 7C AH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with high card A", res);
  }

  @Test
  public void should_return_white_wins_with_high_card_T_when_calculate_winner_given_black_with_high_card_24678_and_white_with_high_card_3579T() {
    //given
    String black = "2S 4H 6C 7D 8S";
    String white = "3H 5D 7S 9C TH";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with high card T", res);
  }

  @Test
  public void should_return_white_wins_with_pair_of_5_when_calculate_winner_given_black_with_33678_and_white_with_55678() {
    //given
    String black = "3S 3H 6C 7D 8S";
    String white = "5H 5D 7S 6H 8H";

    //when
    String res = demo.calculateWinner(black, white);

    //then
    Assertions.assertEquals("white wins with pair of 5", res);
  }

}





