package com.codeforces.smalex;

import org.junit.Assert;
import org.junit.Test;

import com.codeforces.smalex.P591A;

public class P591ATest {
  @Test
  public void firstTest() {
    double s = P591A.solve(100, 50, 2);
    Assert.assertEquals(96.153846d, s, 0.001d);
  }

  @Test
  public void secondTest() {
    double s = P591A.solve(50, 50, 5);
    Assert.assertEquals(45.45454545d, s, 0.001d);
  }
}
