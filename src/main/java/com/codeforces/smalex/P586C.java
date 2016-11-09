package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class P586C {
  // problem http://codeforces.com/contest/586/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT = 
    "5\n" +
    "  4 2 2\n" +
    "  4 1 2\n" +
    "  5 2 4\n" +
    "  3 3 5\n" +
    "  5 1 2\n";

  void solve() {
    int n = ni();
    int[][] d = new int[n][3];
    for (int i = 0; i < n; i++) {
      d[i][0] = ni();
      d[i][1] = ni();
      d[i][2] = ni();
    }
    boolean[] gone = new boolean[n];
    int[] res = new int[n];
    int len = 0;
    for (int i = 0; i < n; i++) {
      int[] cur = d[i];
      if (gone[i]) continue;
      gone[i] = true;
      LinkedList<Integer> q = new LinkedList<>();
      q.add(i);
      while (!q.isEmpty()) {
        int k = q.poll();
        if (k == i) {
          for (int j = k + 1; j < n; j++) {
            if (gone[j]) continue;
            if (cur[0] == 0) break;
            d[j][2] -= cur[0];
            cur[0]--;
            if (d[j][2] < 0) {
              q.push(j);
              gone[j] = true;
            }
          }
        } else {
          for (int j = k + 1; j < n; j++) {
            if (gone[j]) continue;
            d[j][2] -= d[k][1];
            if (d[j][2] < 0) {
              q.push(j);
              gone[j] = true;
            }
          }
        }
      }
      res[len++] = i + 1;
    }
    out.println(len);
    for (int i = 0; i < len; i++) {
      out.print(res[i] + " ");
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P586C().run();
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
