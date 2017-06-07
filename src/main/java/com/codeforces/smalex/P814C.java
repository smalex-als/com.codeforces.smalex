package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class P814C {
  // problem http://codeforces.com/contest/814/problem/C
  InputStream is;
  PrintWriter out;
  private Map<String, Integer> memo = new HashMap<>();
  private char[] a;
  String INPUT = "15\n" + 
                "yamatonadeshiko\n" + 
                "10\n" + 
                "1 a\n" + 
                "2 a\n" + 
                "3 a\n" + 
                "4 a\n" + 
                "5 a\n" + 
                "1 b\n" + 
                "2 b\n" + 
                "3 b\n" + 
                "4 b\n" + 
                "5 b\n";

  void solve() {
    int n = ni();
    a = ns().toCharArray();
    int t = ni();
    for (int i = 0; i < t; i++) {
      out.println(solveDetail());
    }
    // System.out.println(String.format("memo = %s ", memo));
  }

  int solveDetail() {
    int m = ni ();
    char c = ns().toCharArray()[0];
    String key = m + "|" + c;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    int start = 0;
    int end = 0;
    for (end = 0; end < a.length && m > 0; end++) {
      if (a[end] != c) {
        m--;
      }
    }
    int ans = end - start;
    for (; end < a.length; end++) {
      if (a[end] != c) {
        while (a[start] == c) {
          start++;
        }
        start++;
      }
      ans = Math.max(ans, end - start + 1);
    }
    memo.put(key, ans);
    return ans;
  }
  
  public static void main(String[] args) throws Exception {
    new P814C().run();
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
