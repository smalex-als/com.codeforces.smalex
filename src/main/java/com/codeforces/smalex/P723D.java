package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

public class P723D {
  // problem http://codeforces.com/contest/723723/problem/D
  InputStream is;
  PrintWriter out;
  String INPUT =    "4 3 2\n" +
                    "***\n" +
                    "***\n" +
                    "*.*\n" +
                    "**.\n";
  int lastColor = 1;

  int[] len = new int[50*50];

  void solve() {
    int y = ni();
    int x = ni();
    int k = ni();
    int[][] m = new int[y + 2][x + 2];
    for (int i = 0; i < y; i++) {
      String str = ns();
      for (int j = 0; j < x; j++) {
        int ch = str.charAt(j);
        if (ch == '*') {
          m[i + 1][j + 1] = 1;
        }
      }
    }
    dfs(m, 0, 0, true);
    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        dfs(m, i + 1, j + 1, true);
      }
    }
    List<Integer> colors = new ArrayList<>();
    for (int i = 3; i < len.length; i++) {
      if (len[i] > 0) {
        colors.add(i);
      }
    }
    if (colors.size() > 1) {
      Collections.sort(colors, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return Integer.compare(len[o2], len[o1]);
        }
      });
    }
    int nowK = colors.size();
    int res = 0;
    while (nowK > k) {
      int deleteColor = colors.remove(colors.size() - 1);
      nowK--;
      for (int i = 0; i < y; i++) {
        for (int j = 0; j < x; j++) {
          if (m[i + 1][j + 1] == deleteColor) {
            m[i + 1][j + 1] = 1;
            res++;
          }
        }
      }
    }
    
    System.out.println(res);
    for (int i = 0; i < y; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < x; j++) {
        if (m[i + 1][j + 1] == 1) {
          sb.append('*');
        } else {
          sb.append('.');
        }
      }
      System.out.println(sb.toString());
    }
  }

  public void dfs(int[][] m, int y, int x, boolean first) {
    if (y >= 0 && x >= 0 && y < m.length && x < m[0].length 
        && m[y][x] == 0) {
      if (first) {
        lastColor++;
      }
      m[y][x] = lastColor;
      len[lastColor]++;
      dfs(m, y + 1, x, false);
      dfs(m, y - 1, x, false);
      dfs(m, y, x - 1, false);
      dfs(m, y, x + 1, false);
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P723D().run();
  }
  
  void run() throws Exception {
    INPUT = "50 50 0\n";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 25; i++) {
      for (int j = 0; j < 25; j++) {
        sb.append(".*");
      }
      sb.append("\n");
      for (int j = 0; j < 25; j++) {
        sb.append("*.");
      }
      sb.append("\n");
      
    }
    INPUT += sb.toString();
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
