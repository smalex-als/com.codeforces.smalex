package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P723C {
  // problem http://codeforces.com/contest/723/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT = "20 3\n" +
    "3 2 2 3 3 3 2 3 3 3 2 748578511 149249674 844954396 321901094 3 255089924 244803836 3 943090472\n";

  void solve() {
    int n = ni();
    int m = ni();
    int opt = n / m;
    ArrayDeque<Integer> badIndexes = new ArrayDeque<>();
    ArrayDeque<Integer>[] list = new ArrayDeque[m];
    for (int i = 0; i < m; i++) {
      list[i] = new ArrayDeque<>();
    }
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      int value = ni();
      a[i] = value;
      if (value > m) {
        badIndexes.add(i);
      } else {
        list[value - 1].add(i);
      }
    }
    int changes = 0;
    for (int i = 0; i < m && !badIndexes.isEmpty(); i++) {
      while (list[i].size() < opt && !badIndexes.isEmpty()) {
        list[i].add(badIndexes.poll());
        changes++;
      }
    }
    while (true) {
      int min = getMin(list);
      if (list[min].size() >= opt) {
        break;
      }
      int max = getMax(list);
      int diff = list[max].size() - list[min].size();
      if (diff <= 1) {
        break;
      }
      changes++;
      list[min].add(list[max].poll());
    }
    out.println(opt + " " + changes);
    for (int i = 0; i < list.length; i++) {
      for (int v : list[i]) {
        a[v] = i + 1;
      }
    }
    for (int i = 0; i < n; i++) {
      out.print(a[i] + " ");
    }
  }
  
  public int getMin(ArrayDeque<Integer>[] list) {
    int min = 0;
    for (int i = 0; i < list.length; i++) {
      if (list[i].size() < list[min].size()) {
        min = i;
      }
    }
    return min;
  }

  public int getMax(ArrayDeque<Integer>[] list) {
    int max = 0;
    for (int i = 0; i < list.length; i++) {
      if (list[i].size() > list[max].size()) {
        max = i;
      }
    }
    return max;
  }

  public static void main(String[] args) throws Exception {
    new P723C().run();
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
