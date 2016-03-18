package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.TreeSet;

public class P645C {
  InputStream is;
  PrintWriter out;
  String INPUT = "9 3\n" +
      "010001000\n";

  void solve() {
    int n = ni();
    int k = ni() + 1;
    char[] s = ns().toCharArray();
    TreeSet<Integer> set = new TreeSet<>();
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      if (s[i] == '0') {
        set.add(i);
        if (set.size() == k) {
          System.out.println("set = " + set);
          int b = set.first();
          int e = set.last();
          System.out.println("b = " + b);
          System.out.println("e = " + e);
          int mid = (b + e) / 2;
          System.out.println("mid = " + mid);
          int c1 = set.floor(mid);
          int c2 = set.ceiling(mid);
          System.out.println("c1 = " + c1);
          System.out.println("c2 = " + c2);
          System.out.println("mid = " + mid);
          ans = Math.min(ans, Math.min(Math.max(e - c1, c1 - b), Math.max(e - c2, c2 - b)));
          set.pollFirst();
        }
      }
    }
    out.println(ans);
  }

  public static void main(String[] args) throws Exception {
    new P645C().run();
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
