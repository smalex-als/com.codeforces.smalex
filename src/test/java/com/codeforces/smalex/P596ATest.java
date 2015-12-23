package com.codeforces.smalex;

import org.junit.Assert;
import org.junit.Test;

import P596A;

public class P596ATest {
  @Test
  public void testFirst() {
    Assert.assertEquals(-1, new P596A().solve(new int[][]{{1, 1}}));
  }

  @Test
  public void testSecond() {
    Assert.assertEquals(1, new P596A().solve(new int[][]{{0, 0}, {1, 1}}));
  }

  @Test
  public void testThird() {
    Assert.assertEquals(-1, new P596A().solve(new int[][]{{0, 1}, {1, 1}}));
  }
}
