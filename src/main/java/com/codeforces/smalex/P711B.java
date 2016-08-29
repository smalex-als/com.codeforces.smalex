package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P711B {
  // problem http://codeforces.com/contest/711/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT = "3 3 8 1 2 4 6 7 0 5";
  String INPUT2 = "3\n" 
    + "4 9 2\n" 
    + "3 5 7\n" 
    + "8 1 0\n" ;
  String INPUT3 = "5\n" +
  "0 2 1 3 1\n" +
  "2 3 1 1 1\n" +
  "1 1 2 1 3\n" +
  "1 1 3 1 2\n" +
  "3 1 1 2 1\n" ;
  
  void solve() {
    int n = ni();
    long[] rows = new long[n];
    long[] cols = new long[n];
    int x = -1;
    int y = -1;
    long[][] a = new long[n][n];
    long dig0sum = 0;
    long dig1sum = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        long num = nl();
        a[i][j] = num;
        if (num != 0) {
          rows[i] += num;
          cols[j] += num;
          if (i == j) {
            dig0sum += num;
          }
          if (i + j == n - 1) {
            dig1sum += num;
          }
        } else {
          x = j;
          y = i;
        }
      }
    }
    if (n == 1) {
      System.out.println(1);
      return;
    }
    long sum = -1;
    for (int i = 0; i < n; i++) {
      if (i != y) {
        if (sum == -1) {
          sum = rows[i];
        } else if (sum != rows[i]) {
          sum = -1;
          break;
        }
      }
      if (i != x) {
        if (sum == -1) {
          sum = cols[i];
        } else if (sum != cols[i]) {
          sum = -1;
          break;
        }
      }
    }
    if (sum == -1 || cols[x] != rows[y]) {
      out.println(-1);
      return;
    }
    // System.out.println(String.format("dig0sum = %s dig1sum = %s sum = %s ", dig0sum, dig1sum, sum));
    long ans0 = sum - rows[y];
    long ans1 = -1;
    if (x == y) {
      ans1 = sum - dig0sum;
    } else {
      if (dig0sum == sum) {
        ans1 = ans0;
      }
    }
    long ans2 = -1;
    if (x + y == n - 1) {
      ans2 = sum - dig1sum;
    } else {
      if (dig1sum == sum) {
        ans2 = ans0;
      }
    }

    if (ans0 > 0 && ans0 == ans1 && ans1 == ans2) {
      out.println(ans0);
    } else {
      out.println(-1);
    }
  }

  public static void main(String[] args) throws Exception {
    new P711B().run();
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
