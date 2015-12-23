package com.codeforces.smalex;

import org.junit.Assert;
import org.junit.Test;

public class P596BTest {
  @Test
  public void testFirst() {
    Assert.assertEquals(5, new P596B().solve(new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  public void testSecond() {
    Assert.assertEquals(3, new P596B().solve(new int[]{1, 2, 2, 1}));
  }
}
