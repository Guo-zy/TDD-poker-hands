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
  public void should_return_tie_when_comparison_size_given_same_hands_num() {
    //given
    String black = "2H 3D 5S 9C KD";
    String white = "2D 3H 5C 9S KH";

    //when
    String res = demo. calculate_winner(black, white);

    //then
//        assertThat(res).isEqualTo("Tie");
    Assertions.assertEquals("Tie", res);
  }
}