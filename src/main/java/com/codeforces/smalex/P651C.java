package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class P651C {
  // problem http://codeforces.com/contest/651/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT23 = "3 1 1\n 7 5\n 1 5\n";
  String INPUT = "6\n" +
    "0 0\n" +
    "0 1\n" +
    "0 2\n" +
    "-1 1\n" +
    "0 1\n" +
    "1 1\n";
  
  void solve() {
    int n = ni();
    int[][] xy = new int[n][2];
    for (int i = 0; i < n; i++) {
      xy[i][0] = ni();
      xy[i][1] = ni();
    }
    Arrays.sort(xy, new Comparator<int[]>() {
      @Override
      public int compare(int[] p1, int[] p2) {
        int res = Integer.compare(p1[0], p2[0]);
        if (res != 0) return res;
        return Integer.compare(p1[1], p2[1]);
      }
    });
    long eq = 1;
    long result = 0;
    for (int i = 1; i < n; i++) {
      if (xy[i - 1][0] == xy[i][0] && xy[i - 1][1] == xy[i][1]) {
        eq++;
      } else {
        result -= eq * (eq - 1) / 2;
        eq = 1;
      }
    }
    result -= eq * (eq - 1) / 2;
    eq = 1;
    for (int i = 1; i < n; i++) {
      if (xy[i - 1][0] == xy[i][0]) {
        eq++;
      } else {
        result += eq * (eq - 1) / 2;
        eq = 1;
      }
    }
    result += eq * (eq - 1) / 2;
    Arrays.sort(xy, new Comparator<int[]>() {
      @Override
      public int compare(int[] p1, int[] p2) {
        int res = Integer.compare(p1[1], p2[1]);
        if (res != 0) return res;
        return Integer.compare(p1[0], p2[0]);
      }
    });
    eq = 1;
    for (int i = 1; i < n; i++) {
      if (xy[i - 1][1] == xy[i][1]) {
        eq++;
      } else {
        result += eq * (eq - 1) / 2;
        eq = 1;
      }
    }
    result += eq * (eq - 1) / 2;
    System.out.println(result);
  }

  public static void main(String[] args) throws Exception {
    new P651C().run();
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
