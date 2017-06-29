package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P818B {
  // problem http://codeforces.com/contest/818/problem/B
  InputStream is;
  PrintWriter out;
  // String INPUT = "4 5 2 3 1 4 4";
  // String INPUT = "3 3 1 2 2";
  // String INPUT = "2 2 1 2 2";
  // String INPUT = "3 3 3 1 2";
  String INPUT = "6 8  2 5 4 2 5 4 2 5";
  
  void solve() {
    int n = ni();
    int m = ni();
    int[] l = new int[m];
    for (int i = 0; i < m; i++) {
      l[i] = ni();
    }
    int[] a = new int[n];
    for (int i = 0; i + 1 < m; i++) {
      int cur = l[i];
      int next = l[i+1];
      if (next <= cur) {
        next += n;
      }
      int d = next - cur;
      int pos = l[i]-1;
      if (a[pos] > 0 && a[pos] != d) {
        System.out.println(-1);
        return;
      } else {
        a[pos] = d;
      }
    }
    boolean[] used = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (a[i] > 0) {
        if (used[a[i] - 1]) {
          System.out.println(-1);
          return;
        }
        used[a[i] - 1] = true;
      }
    }
    int j = 0;
    for (int i = 0; i < n; i++) {
      if (a[i] == 0) {
        for (; j < n; j++) {
          if (!used[j]) {
            used[j] = true;
            a[i] = j + 1;
            break;
          }
        }
      }
    }

    int[] b = new int[n];
    System.arraycopy(a, 0, b, 0, n);
    Arrays.sort(b);
    boolean ok = true;
    for (int i = 0; i < n; i++) {
      if (b[i] != i+1) {
        ok = false;
        break;
      }
    }
    if (ok) {
      for (int i = 0; i < n; i++) {
        if (i > 0) {
          out.print(" ");
        }
        out.print(a[i]);
      }
      out.println();
      out.flush();
    } else {
      System.out.println(-1);
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P818B().run();
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
