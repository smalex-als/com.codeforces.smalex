package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class P624C {
  InputStream is;
  PrintWriter out;
  String INPUT = "4 4 1 2 1 3 1 4 3 4";
  // String INPUT = "3 2 1 2 1 3";
  // String INPUT = "4 3 1 2 1 3 1 4";
  boolean[][] map; 
  char[] result;
  List<String> all = new ArrayList<String>();

  void solve() {
    int n = ni();
    int m = ni();

    map = new boolean[n][n];
    result = new char[n];
    int start = 1;
    int max = 0;
    int[] cnt = new int[n];
    for (int i = 0; i < m; i++) {
      int x = ni() - 1;
      int y = ni() - 1;
      map[x][y] = true;
      map[y][x] = true;
      cnt[x]++;
      if (cnt[x] > max) {
        start = x;
      }
    }
    for (char c = 'a'; c <= 'c'; c++) {
      dfs(start, c, 1);
      if (all.size() > 0) {
        System.out.println("Yes");
        System.out.println(all.get(0));
        return;
      }
    }
    System.out.println("No");
  }

  private void dfs(int start, char startc, int len) {
    Arrays.fill(result, (char) 0);
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(start * 1000 + startc);
    Set<Integer> visited = new HashSet<Integer>();
    visited.add(start * 1000 + startc);
    while (!queue.isEmpty()) {
      int point = queue.removeFirst();
      int pos = point / 1000;
      char ch = (char) (point % 1000);
      result[pos] = ch;
      int cnt = 0;
      for (int i = 0; i < result.length; i++) {
        if (result[i] != 0) {
          cnt++;
        }
      }
      if (cnt == result.length) {
        boolean valid = true;
        for (int j = 0; j < result.length; j++) {
          for (int i = j + 1; i < result.length; i++) {
            if (map[j][i]) {
              if (!(result[j] == result[i] 
                || result[j] + 1 == result[i] 
                || result[j] - 1 == result[i])) {
                valid = false;
                break;
              }
            }
          }
        }
        if (valid) {
          all.add(new String(result));
          return;
        } else {
          System.out.println(new String(result));
        }
      }
      for (int i = 0; i < map[pos].length; i++) {
        if (map[pos][i]) {
          for (int j = -1; j <= 1; j++) {
            char newc = (char) (ch + j);
            if ((newc >= 'a' && newc <= 'c')) {
              Integer code = i * 1000 + newc;
              if (!visited.contains(code)) {
                queue.add(code);
                visited.add(code);
              }
            }
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    new P624C().run();
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
