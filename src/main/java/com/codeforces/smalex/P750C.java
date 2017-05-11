package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P750C {
  // problem http://codeforces.com/contest/750/problem/C
  InputStream is;
  PrintWriter out;
  // String INPUT =    "3\n"+
  //                   "-7 1\n"+
  //                   "5 2\n"+
  //                   "8 2\n";
  String INPUT =    "3\n"+
                    "-1000 2\n"+
                    "-1000 2\n"+
                    "-1000 2\n";
  
  void solve() {
    int n = ni();
    int[] c = new int[n];
    int[] d = new int[n];
    for (int i = 0; i < n; i++) {
      c[i] = ni();
      d[i] = ni();
    }
    int lo = Integer.MIN_VALUE / 2;
    int hi = Integer.MAX_VALUE / 2;
    int[] res = new int[]{0};
    int best = Integer.MIN_VALUE / 2;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = solve(mid, res, c, d);
      if (cmp == 0) {
        best = Math.max(best, res[0]);
        lo = mid + 1;
      } else if (cmp == 1) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    if (hi == Integer.MAX_VALUE / 2) {
      System.out.println("Infinity");
    } else if (best == Integer.MIN_VALUE / 2) {
      System.out.println("Impossible");
    } else {
      System.out.println(best);
    }
  }

  private int solve(int r, int[] res, int[] c, int[] d) {
    for (int i = 0; i < d.length; i++) {
      if (d[i] == 1) {
        if (r < 1900) {
          return 1;
        }
      } else {
        if (r > 1899) {
          return -1;
        }
      }
      r += c[i];
    }
    res[0] = r;
    return 0;
  }

  public static void main(String[] args) throws Exception {
    new P750C().run();
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
