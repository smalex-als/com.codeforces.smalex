package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P799C {
  // broken
  // problem http://codeforces.com/contest/799/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT =  "4 7 6\n" +
    "11 8 C\n" +
    "10 8 C\n" +
    "4 3 C\n" +
    "5 6 D\n";
  String INPUT2 = "2 4 5\n" +
    "2 5 C\n" + 
    "2 1 D\n" ;
  String INPUT3 =  "6 9 10\n" +
    "8 5 C\n" +
    "8 4 C\n" +
    "7 3 C\n" +
    "6 5 C\n" +
    "5 5 C\n" +
    "5 5 C\n" +
    "10 11 D\n";

  void solve() {
    int n = ni();
    int c = ni();
    int d = ni();
    int bestA = 0;
    int bestB = 0;
    int[][] bestForA = new int[c + 1][2];
    int[][] bestForB = new int[d + 1][2];

    for (int i = 0; i < n; i++) {
      int beauty = ni();
      int cost = ni();
      if (ns().equals("C")) {
        if (cost <= c && beauty > bestA) {
          bestA = beauty;
        }
        if (cost <= c) {
          for (int j = 0; j < 2; j++) {
            if (bestForA[cost][j] < beauty) {
              bestForA[cost][j] = beauty;
              break;
            }
          }
        }
      } else {
        if (cost <= d && beauty > bestB) {
          bestB = beauty;
        }
        if (cost <= d) {
          for (int j = 0; j < 2; j++) {
            if (bestForB[cost][j] < beauty) {
              bestForB[cost][j] = beauty;
              break;
            }
          }
        }
      }
    }
    int[] maxvalue = new int[4];
    Arrays.fill(maxvalue, 0);
    for (int i = 0; i < bestForA.length; i++) {
      for (int j = 0; j < 2; j++) {
        maxvalue[j] = bestForA[i][j];
      }
      Arrays.sort(maxvalue);
      for (int j = 0; j < 2; j++) {
        bestForA[i][j] = maxvalue[j + 2];
      }
    }
    Arrays.fill(maxvalue, 0);
    for (int i = 0; i < bestForB.length; i++) {
      for (int j = 0; j < 2; j++) {
        maxvalue[j] = bestForB[i][j];
      }
      Arrays.sort(maxvalue);
      for (int j = 0; j < 2; j++) {
        bestForB[i][j] = maxvalue[j + 2];
      }
    }
    int res = 0;
    if (bestForA[c][0] > 0 && bestForA[c][1] > 0) {
      res = bestForA[c][0] + bestForA[c][1];
    }
    if (bestForB[d][0] > 0 && bestForB[d][1] > 0) {
      res = bestForB[d][0] + bestForB[d][1];
    }

    int res3 = bestA > 0 && bestB > 0 ? bestA + bestB : 0;
    System.out.println(Math.max(res, res3));
  }

  public static void main(String[] args) throws Exception {
    new P799C().run();
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
