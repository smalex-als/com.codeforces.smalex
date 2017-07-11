package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class P828C {
  // problem http://codeforces.com/contest/828/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT = "3\n" +
    "a 4 1 3 5 7\n" +
    "ab 2 1 5\n" +
    "ca 1 4\n";
  
  void solve() {
    int n = ni();
    String[] p = new String[n];
    int[][] a = new int[n][];

    int m = 0;
    int max = 0;
    for (int i = 0; i < n; i++) {
      p[i] = ns();
      int t = ni();
      a[i] = new int[t];
      m += t;
      for (int j = 0; j < t; j++) {
        a[i][j] = ni() - 1;
      }
    }
    int[][] b = new int[m][3];
    int last = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < a[i].length; j++) {
        b[last][0] = a[i][j];
        b[last][1] = a[i][j] + p[i].length();
        b[last][2] = i;
        max = Math.max(b[last][1], max);
        last++;
      }
    }
    Arrays.sort(b, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        int cmp = Integer.compare(o1[0], o2[0]);
        if (cmp != 0) {
          return cmp;
        }
        return Integer.compare(o2[1], o1[1]);
      }
    });
    char[] res = new char[max];
    for (int i = 0; i < res.length; i++) {
      res[i] = 'a';
    }
    for (int i = 0; i < b.length; i++) {
      String str = p[b[i][2]];
      int start = b[i][0];
      int end = b[i][1];
      if (i > 0) {
        int pstart = b[i-1][0];
        int pend = b[i-1][1];
        if (start >= pstart && end <= pend) {
          continue;
        }
      }
      for (int j = 0; j < str.length(); j++) {
        res[start + j] = str.charAt(j);
      }
    }
    System.out.println(new String(res));
  }
  
  public static void main(String[] args) throws Exception {
    new P828C().run();
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
