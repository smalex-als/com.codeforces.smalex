package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P686C {
  InputStream is;
  PrintWriter out;
  String INPUT = "16808 7";
  boolean[] z = new boolean[7];
  int cntN;
  int cntM;
  char[] p;
  int n;
  int m;
  int res = 0;
  
  void solve() {
    n = ni();
    m = ni();
    cntN = digits(n);
    cntM = digits(m);
    p = new char[cntN + cntM];
    bt(0);
    out.println(res);
  }

  private void bt(int i) {
    if (i == cntN + cntM) {
      String s1 = new String(p, 0, cntN);
      String s2 = new String(p, cntN, cntM);
      if (Integer.parseInt(s1, 7) < n 
          && Integer.parseInt(s2, 7) < m) {
        res++;
      }
      return;
    }
    for (int j = 0; j < 7; j++) {
      if (!z[j]) {
        p[i] = (char) ('0' + j);
        z[j] = true;
        bt(i + 1);
        z[j] = false;
      }
    }
  }

  private int digits(int n) {
    return Integer.toString(n - 1, 7).length();
  }

  public static void main(String[] args) throws Exception {
    new P686C().run();
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
