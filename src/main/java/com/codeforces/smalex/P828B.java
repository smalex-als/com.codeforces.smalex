package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P828B {
  // problem http://codeforces.com/contest/828/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT = "3 2\n" +
                  "BB\n"+
                  "BW\n"+
                  "WW\n";
  

  void solve() {
    int n = ni();
    int m = ni();
    int[][] mm = new int[n][m];
    boolean foundBlack = false;
    for (int i = 0; i < n; i++) {
      String row = ns();
      for (int j = 0; j < row.length(); j++) {
        int color = row.charAt(j) == 'B' ? 1 : 2;
        if (color == 1) {
          foundBlack = true;
        }
        mm[i][j] = color;
      }
    }
    if (!foundBlack) {
      System.out.println(1);
      return;
    }
    int[][] box = new int[][]{{100000, -100000}, {100000, -100000}};
    int len = 0;
    for (int i = 0; i < mm.length; i++) {
      for (int j = 0; j < mm[i].length; j++) {
        if (mm[i][j] == 1) {
          len++;
          box[0][0] = Math.min(box[0][0], i);
          box[0][1] = Math.max(box[0][1], i);
          box[1][0] = Math.min(box[1][0], j);
          box[1][1] = Math.max(box[1][1], j);
        }
      }
    }
    int width = 1 + Math.max(box[0][1] - box[0][0], box[1][1] - box[1][0]);
    if (width <= n && width <= m) {
      System.out.println(width * width - len);
    } else {
      System.out.println(-1);
    }
  }

  public int dfs(int[][] mm, int y, int x, int color, int[][] box) {
    mm[y][x] = color;
    box[0][0] = Math.min(box[0][0], y);
    box[0][1] = Math.max(box[0][1], y);
    box[1][0] = Math.min(box[1][0], x);
    box[1][1] = Math.max(box[1][1], x);
    int len = 1;
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if (i != 0 || j != 0) {
          int ny = y + i;
          int nx = x + j;
          if (mm[ny][nx] == 1) {
            len += dfs(mm, ny, nx, color, box);
          }
        }
      }
    }
    return len;
  }
  
  public static void main(String[] args) throws Exception {
    new P828B().run();
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
