package com.codeforces.smalex;

import org.junit.Assert;
import org.junit.Test;

import com.codeforces.smalex.P591A;

public class P591ATest {
  @Test
  public void firstTest() {
    double res = P591A.solve(100, 50, 50);
    Assert.assertEquals(50.0d, res, 0.001d);
  }

  @Test
  public void secondTest() {
    double res = P591A.solve(199, 60, 40);
    Assert.assertEquals(119.4, res, 0.001d);
  }
}
