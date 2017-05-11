package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P746D {
  // problem http://codeforces.com/contest/746/problem/D
  InputStream is;
  PrintWriter out;
  // String INPUT = "5 1 3 2";
  // String INPUT = "7 2 2 5";
  // String INPUT = "4 3 4 0";
  String INPUT = "100000 39 24855 75145";
  // String INPUT = "80 5 60 20";

  void solve() {
    int n = ni();
    int k = ni();
    int a = ni();
    int b = ni();
    int[] res = new int[n];
    int[] color = new int[n];
    int len = 0;
    char first = 'G';
    char second = 'B';
    if (a < b) {
      int tmp = a;
      a = b;
      b = tmp;
      first = 'B';
      second = 'G';
    }
    while (a > 0 || b > 0) {
      if (a > 0) {
        int tmp = Math.min(k, a);
        a -= tmp;
        res[len] = tmp;
        color[len] = 1;
        len++;
      } else {
        a--;
        boolean ok = false;
        for (int i = 0; i < len; i++) {
          if (color[i] == 1 && res[i] > 1) {
            res[i]--;
            res[len] = 1;
            color[len] = 1;
            len++;
            ok = true;
            break;
          }
        }
        if (!ok) {
          System.out.println("NO");
          return;
        }
      } 
      if (b > 0) {
        int tmp = Math.min(k, b);
        b -= tmp;
        res[len] = tmp;
        color[len] = 2;
        len++;
      } else {
        if (a == 0) {
          break;
        }
        b--;
        boolean ok = false;
        for (int i = 0; i < len; i++) {
          if (color[i] == 2 && res[i] > 1) {
            res[i]--;
            res[len] = 1;
            color[len] = 2;
            len++;
            ok = true;
            break;
          }
        }
        if (!ok) {
          System.out.println("NO");
          return;
        }
      }
    }
    
    char[] chars = new char[n];
    int pos = 0;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < res[i]; j++, pos++) {
        chars[pos] = color[i] == 1 ? first : second;
      }
    }
    System.out.println(new String(chars));
  }
  
  public static void main(String[] args) throws Exception {
    new P746D().run();
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
