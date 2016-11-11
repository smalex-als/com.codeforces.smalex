package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P540C {
  // problem http://codeforces.com/contest/540/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT2 =  "4 6\n" +
                  "X...XX\n" +
                  "...XX.\n" +
                  ".X..X.\n" +
                  "......\n" +
                  "1 6\n" +
                  "2 2\n";

  String INPUT = 
"5 4\n" + 
".X..\n" + 
"...X\n" + 
"X.X.\n" + 
"....\n" + 
".XX.\n" + 
"5 3\n" + 
"1 1\n"; 

    int r1;
    int c1;
    int[] yx = new int[]{ -1, 0,1,0,
                         0, -1,0,1};
  
  void solve() {
    int m = ni();
    int n = ni();
    int[][] map = new int[m][n];
    for (int i = 0; i < m; i++) {
      char[] cols = ns().toCharArray();
      for (int j = 0; j < n; j++) {
        map[i][j] = cols[j] == 'X' ? 1 : 0;
      }
    }
    int r0 = ni() - 1;
    int c0 = ni() - 1;
    r1 = ni() - 1;
    c1 = ni() - 1;
    if (dfsStage1(r0, c0, map)) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }

  private boolean dfsStage1(int r0, int c0, int[][] map) {
    for (int i = 0; i < yx.length; i += 2) {
      int ny = r0 + yx[i];
      int nx = c0 + yx[i + 1];
      if (ny < 0 || ny >= map.length || nx < 0 || nx >= map[0].length) continue;
      if (ny == r1 && nx == c1 && map[ny][nx] == 1) {
        return true;
      }
      if (map[ny][nx] == 1) {
        continue;
      }
      map[ny][nx] = 1;
      if (dfsStage1(ny, nx, map)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    new P540C().run();
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
