package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P738B {
  // problem http://codeforces.com/contest/738/problem/B
  InputStream is;
  PrintWriter out;
//  String INPUT = "2 4\n" +
//                  "0 1 0 0\n" +
//                  "1 0 1 0\n";
  String INPUT = "4 4\n" +
    "0 0 0 0\n" +
    "1 0 0 1\n" +
    "0 1 1 0\n" +
    "0 1 0 0\n";
  int[][] yx = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  
  void solve() {
    int n = ni();
    int m = ni();
    boolean[][] map = new boolean[n][m];
    int[][][] vis = new int[4][n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        map[i][j] = ni() == 1;
      }
    }
    int res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!map[i][j]) {
          for (int k = 0; k < 4; k++) {
            if (dfs(i, j, vis, map, k)) {
              res++;
            }
          }
        }
      }
    }
    System.out.println(res);
  }

  private boolean dfs(int y, int x, int[][][] vis, boolean[][] map, int k) {
    if (x < 0 || y < 0 || x == map[0].length || y == map.length) {
      return false;
    }
    if (vis[k][y][x] != 0) {
      return vis[k][y][x] == 2;
    }
    if (map[y][x]) {
      vis[k][y][x] = 2;
    } else {
      vis[k][y][x] = dfs(y + yx[k][0], x + yx[k][1], vis, map, k) ? 2 : 1;
    }
    return vis[k][y][x] == 2;
  }

  public static void main(String[] args) throws Exception {
    new P738B().run();
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
