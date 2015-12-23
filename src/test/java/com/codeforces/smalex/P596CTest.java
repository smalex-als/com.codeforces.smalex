package com.codeforces.smalex;

import org.junit.Assert;
import org.junit.Test;
import com.codeforces.smalex.P596C.Point;

public class P596CTest {
  @Test
  public void testFirst() {
    int[] r = new P596C().solve(
        new Point[]{new Point(2, 0), new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1)}, 
        new int[]{0, -1, -2, 1, 0});
      int[] ex = new int[]{1, 2, 0, 4, 3};
    Assert.assertArrayEquals(ex, r);
  }
}

