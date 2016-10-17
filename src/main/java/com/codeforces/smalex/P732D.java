package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P732D {
  // problem http://codeforces.com/contest/732/problem/D
  InputStream is;
  PrintWriter out;
  String INPUT = "10 3\n" +
    "0 0 1 2 3 0 2 0 1 2\n" +
    "1 1 4\n";
  int[] last;
  
  void solve() {
    int n = ni();
    int m = ni();
    int[] d = na(n);
    int[] a = na(m);
    last = new int[a.length + 1];
    int lo = 0;
    int hi = d.length - 1;

    int ans = Integer.MAX_VALUE;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (isValid(d, a, mid)) {
        ans = mid + 1;
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
  }

  private boolean isValid(int[] d, int[] a, int n) {
    Arrays.fill(last, -1);
    int cnt = 0;
    for (int i = n; i > 0; i--) {
      int t = d[i];
      if (t == 0) continue;
      if (last[t] == -1) {
        last[t] = i;
        cnt++;
        if (cnt == a.length) {
          break;
        }
      }
    }
    if (cnt != a.length) return false;
    Arrays.sort(last);
    int pos = 0;
    for (int i = 1; i < last.length; i++) {
      int day = last[i];
      int index = d[last[i]];
      if (day - pos < a[index - 1]) {
        return false;
      }
      pos += a[index - 1] + 1;
    }
    return true;
  }

  public static void main(String[] args) throws Exception {
    new P732D().run();
  }
  
  void run() throws Exception {
    // StringBuilder sb = new StringBuilder();
    // int days = 200000;
    // sb.append(days + " " + 100000);
    // sb.append("\n");
    // for (int i = 1; i <= days / 2; i++) {
    //   sb.append(" " + i + " " + i);
    // }
    // sb.append("\n");
    // for (int i = 1; i <= 100000; i++) {
    //   sb.append(" " + 1);
    // }
    // INPUT = sb.toString();
    // System.out.println(INPUT);
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
