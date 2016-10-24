package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class P730B {
  // problem http://codeforces.com/contest/730/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT = "2 5 < > = = < > 3 > >";
  // String INPUT = "2 2 > 3 = =";
  
  void solve() {
    int T = ni();
    for (int i = 0; i < T; i++) {
      int n = ni();
      List<Integer> a = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        a.add(j);
      }
      if (n == 1) {
        out.println("! 1 1");
        out.flush();
        continue;
      }
      List<Integer> min = new ArrayList<>();
      List<Integer> max = new ArrayList<>();

      for (int j = 0; j + 1 < n; j += 2) {
        if (less(a, j, j + 1)) {
          min.add(a.get(j));
          max.add(a.get(j + 1));
        } else {
          min.add(a.get(j + 1));
          max.add(a.get(j));
        }
      }
      if (a.size() % 2 == 1) {
        a.set(0, min.get(0));
        if (less(a, 0, n - 1)) {
          max.add(a.get(n - 1));
        } else {
          min.set(0, a.get(n - 1));
        }
      }
      while (min.size() > 1) {
        List<Integer> newArr = new ArrayList<>();
        for (int j = 0; j + 1 < min.size(); j += 2) {
          if (less(min, j, j + 1)) {
            newArr.add(min.get(j));
          } else {
            newArr.add(min.get(j + 1));
          }
        }
        if (min.size() % 2 == 1) {
          newArr.add(min.get(min.size() - 1));
        }
        min = newArr;
      }
      while (max.size() > 1) {
        List<Integer> newArr = new ArrayList<>();
        for (int j = 0; j + 1 < max.size(); j += 2) {
          if (great(max, j, j + 1)) {
            newArr.add(max.get(j));
          } else {
            newArr.add(max.get(j + 1));
          }
        }
        if (max.size() % 2 == 1) {
          newArr.add(max.get(max.size() - 1));
        }
        max = newArr;
      }
      out.println("! " + (min.get(0) + 1) + " " + (max.get(0) + 1));
      out.flush();
    }
  }

  public boolean less(List<Integer> a, int i, int j) {
    out.println("? " + (a.get(i) + 1) + " " + (a.get(j) + 1));
    out.flush();
    String resp = ns();
    return resp.equals("=") || resp.equals("<");
  }

  public boolean great(List<Integer> a, int i, int j) {
    out.println("? " + (a.get(i) + 1) + " " + (a.get(j) + 1));
    out.flush();
    String resp = ns();
    return resp.equals("=") || resp.equals(">");
  }

  public static void main(String[] args) throws Exception {
    new P730B().run();
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
