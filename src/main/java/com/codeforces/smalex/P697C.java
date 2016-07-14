package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

public class P697C {
  InputStream is;
  PrintWriter out;
  String INPUT = 
"10\n" + 
"1 1 63669439577744021 396980128\n" + 
"1 2582240553355225 63669439577744021 997926286\n" + 
"1 2582240553355225 1 619026011\n" + 
"1 1 4 231881718\n" + 
"2 63669439577744021 3886074192977\n" + 
"2 4 63669439577744021\n" + 
"2 124354374175272 10328962213420903\n" + 
"1 10328962213420903 3886074192977 188186816\n" + 
"1 124354374175272 31088593543820 705639304\n" + 
"2 2582240553355225 254677758310976084\n";
  HashMap<String, Long> costMap = new HashMap<>();
  
  void solve() {
    int q = ni();

    for (int i = 0; i < q; i++) {
      if (ni() == 1) {
        long u = nl();
        long v = nl();
        long w = nl();
        updateCost(u, v, w);
      } else {
        long sum = 0;
        long u = nl();
        long v = nl();
        int levelU = level(u);
        int levelV = level(v);
        while (levelU > levelV) {
          sum += getCostPoint(u/2, u);
          levelU--;
          u /= 2;
        }
        while (levelV > levelU) {
          sum += getCostPoint(v/2, v);
          levelV--;
          v /= 2;
        }
        while (u != v) {
          levelV--;
          levelU--;
          sum += getCostPoint(u/2, u);
          sum += getCostPoint(v/2, v);
          v /= 2;
          u /= 2;
        }
        System.out.println(sum);
      }
    }
  }

  private void updateCost(long u, long v, long w) {
    if (u == v) return;
    trace(u, v, w);
  }

  private int level(long u) {
    int level = 0;
    while (u > 0) {
      u >>= 1;
      level++;
    }
    return level;
  }

  private void trace(long u, long v, long w) {
    int levelU = level(u);
    int levelV = level(v);
    while (levelU > levelV) {
      addCost(u / 2, u, w);
      levelU--;
      u /= 2;
    }
    while (levelV > levelU) {
      addCost(v / 2, v, w);
      levelV--;
      v /= 2;
    }
    while (u != v) {
      levelV--;
      levelU--;
      addCost(u / 2, u, w);
      addCost(v / 2, v, w);
      v /= 2;
      u /= 2;
    }
  }

  private long getCostPoint(long v, long u) {
    String key = v + " to " + u;
    Long value = costMap.get(key);
    if (value != null) {
      return value;
    }
    return 0;
  }

  private void addCost(long v, long u, long w) {
    String key = v + " to " + u;
    Long prevw = costMap.get(key);
    if (prevw != null) {
      costMap.put(key, prevw + w);
    } else {
      costMap.put(key, w);
    }
  }

  public static void main(String[] args) throws Exception {
    new P697C().run();
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
