package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class P616C {
  InputStream is;
  PrintWriter out;
  String INPUT = "4 5\n" +
      "**..*\n" +
      "..***\n" +
      ".*.*.\n" +
      "*.*.*";
  static int[][] xy = new int[][]{{1,0}, {0,1}, {-1, 0}, {0, -1}};
  
  void solve() {
    int n = ni();
    int m = ni();
    int[][] map = new int[n][m];
    for (int i = 0; i < n; i++) {
      String str = ns();
      for (int j = 0; j < str.length(); j++) {
        map[i][j] = str.charAt(j) == '*' ? -1 : 0;
      }
    }
    List<Integer> colors = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 0) {
          colors.add(dfs(map, i, j, colors.size() + 1));
        }
      }
    }
    Set<Integer> used = new HashSet<Integer>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == -1) {
          used.clear();
          int res = 1;
          for (int k = 0; k < 4; k++) {
            int ni = i + xy[k][0];
            int nj = j + xy[k][1];
            if (ni >= 0 && nj >= 0 && ni < map.length && nj < map[0].length 
                && map[ni][nj] > 0 && !used.contains(map[ni][nj])) {
              res += colors.get(map[ni][nj] - 1);
              used.add(map[ni][nj]);
            }
          }
          sb.append(res % 10);
        } else {
          sb.append('.');
        }
      }
      sb.append("\n");
    }
    out.print(sb.toString());
  }

  private int dfs(int[][] map, int i, int j, int color) {
    map[i][j] = color;
    int res = 1;
    for (int k = 0; k < 4; k++) {
      int ni = i + xy[k][0];
      int nj = j + xy[k][1];
      if (ni >= 0 && nj >= 0 && ni < map.length && nj < map[0].length 
          && map[ni][nj] == 0) {
        res += dfs(map, ni, nj, color);
      }
    }
    return res;
  }

  public static void main(String[] args) throws Exception {
    new P616C().run();
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
