package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;


public class P816C {
  // problem http://codeforces.com/contest/816/problem/C
  InputStream is;
  PrintWriter out;
  private int[][] a;
  private int total = 0;
  private int n;
  private int m;
  private List<String> res = new ArrayList<>();
  String INPUT =  "3 5\n" +
                  "2 2 2 3 2\n" +
                  "0 0 0 1 0\n" +
                  "1 1 1 2 1\n";

  void solve() {
    n = ni();
    m = ni();
    a = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        a[i][j] = ni();
        total += a[i][j];
      }
    }
    if (n < m) {
      removeRow();
      removeCol();
    } else {
      removeCol();
      removeRow();
    }
    if (total == 0) {
      out.println(res.size());
      for (String cmd : res) {
        out.println(cmd);
      }
    } else {
      System.out.println(-1);
    }
  }
  
  void removeRow() {
    for (int i = 0; i < n; i++) {
      int max = 100000;
      for (int j = 0; j < m; j++) {
        max = Math.min(max, a[i][j]);
      }
      if (max > 0) {
        for (int j = 0; j < m; j++) {
          a[i][j] -= max;
        }
        while (max > 0) {
          res.add("row " + (i + 1));
          max--;
          total -= m;
        }
      }
    }
  }

  void removeCol() {
    for (int i = 0; i < m; i++) {
      int max = 100000;
      for (int j = 0; j < n; j++) {
        max = Math.min(max, a[j][i]);
      }
      if (max > 0) {
        for (int j = 0; j < n; j++) {
          a[j][i] -= max;
        }
        while (max > 0) {
          res.add("col " + (i + 1));
          max--;
          total -= n;
        }
      }
    } 
  }

  public static void main(String[] args) throws Exception {
    new P816C().run();
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
