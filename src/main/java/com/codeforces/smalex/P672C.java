package com.codeforces.smalex;

import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class P672C {
  InputStream is;
  PrintWriter out;
  String INPUT = "3 1 1 2 0 0\n"
    + "3\n"
    + "1 1\n"
    + "2 1\n"
    + "2 3";

  private static class ValueIndex implements Comparable<ValueIndex> {
    int index;
    double value;

    /**
     * @param index
     * @param value
     */
    public ValueIndex(int index, double value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public int compareTo(ValueIndex o) {
      return Double.compare(value, o.value);
    }

    @Override
    public String toString() {
      return index + "=" + value;
    }
  }
  
  void solve() {
    long ax = nl();
    long ay = nl();
    long bx = nl();
    long by = nl();
    long tx = nl();
    long ty = nl();
    int n = ni();
    List<ValueIndex> valuesA = new ArrayList<ValueIndex>();
    List<ValueIndex> valuesB = new ArrayList<ValueIndex>();
    double res = 0;
    for (int i = 0; i < n; i++) {
      long x = ni();
      long y = ni();
      double da = getDist(ax, ay, x, y) - getDist(tx, ty, x, y);
      double db = getDist(bx, by, x, y) - getDist(tx, ty, x, y);
      res += 2 * getDist(tx, ty, x, y);

      valuesA.add(new ValueIndex(i, da));
      valuesB.add(new ValueIndex(i, db));
    }
    Collections.sort(valuesA);
    Collections.sort(valuesB);
    ValueIndex bestA0 = valuesA.get(0);
    ValueIndex bestB0 = valuesB.get(0);
		double ret = res + Math.min(bestA0.value, bestB0.value);
		if (bestA0.index != bestB0.index) {
			out.printf("%.14f\n", Math.min(ret, res + bestA0.value + bestB0.value));
      return;
		}
    if (n >= 2) {
      ValueIndex bestA1 = valuesA.get(1);
      ValueIndex bestB1 = valuesB.get(1);
      ret = Math.min(ret, res + bestA0.value + bestB1.value);
      ret = Math.min(ret, res + bestA1.value + bestB0.value);
    }
    out.printf("%.14f\n", ret);
  }

  private double getDist(long ax, long ay, long x, long y) {
    return Point2D.distance(ax, ay, x, y);
  }

  public static void main(String[] args) throws Exception {
    new P672C().run();
  }
  
  void run() throws Exception {
    is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
    out = new PrintWriter(System.out);
  
    long s = System.currentTimeMillis();
    solve();
    out.flush();
    tr(System.currentTimeMillis() - s + "ms");
  }
  
  private byte[] inbuf = new byte[1024];
  private int lenbuf = 0, ptrbuf = 0;
  
  private int readByte() {
    if (lenbuf == -1)
      throw new InputMismatchException();
    if (ptrbuf >= lenbuf) {
      ptrbuf = 0;
      try {
        lenbuf = is.read(inbuf);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (lenbuf <= 0)
        return -1;
    }
    return inbuf[ptrbuf++];
  }
  
  private boolean isSpaceChar(int c) {
    return !(c >= 33 && c <= 126);
  }
  
  private int skip() {
    int b;
    while ((b = readByte()) != -1 && isSpaceChar(b))
      ;
    return b;
  }
  
  private double nd() {
    return Double.parseDouble(ns());
  }
  
  private char nc() {
    return (char) skip();
  }
  
  private String ns() {
    int b = skip();
    StringBuilder sb = new StringBuilder();
    while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
      sb.appendCodePoint(b);
      b = readByte();
    }
    return sb.toString();
  }
  
  private char[] ns(int n) {
    char[] buf = new char[n];
    int b = skip(), p = 0;
    while (p < n && !(isSpaceChar(b))) {
      buf[p++] = (char) b;
      b = readByte();
    }
    return n == p ? buf : Arrays.copyOf(buf, p);
  }
  
  private char[][] nm(int n, int m) {
    char[][] map = new char[n][];
    for (int i = 0; i < n; i++)
      map[i] = ns(m);
    return map;
  }
  
  private int[] na(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = ni();
    return a;
  }
  
  private int ni() {
    int num = 0, b;
    boolean minus = false;
    while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readByte();
    }
  
    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readByte();
    }
  }
  
  private long nl() {
    long num = 0;
    int b;
    boolean minus = false;
    while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readByte();
    }
  
    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readByte();
    }
  }
  
  private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
  
  private void tr(Object... o) {
    if (!oj)
      System.out.println(Arrays.deepToString(o));
  }
}
