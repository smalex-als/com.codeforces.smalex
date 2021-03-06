package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P738C {
  // problem http://codeforces.com/contest/738/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT =  
    "2 2 10 18\n" +
    "10 4\n" +
    "20 6\n" +
    "5 3\n";
  
  void solve() {
    int n = ni(); // cars
    int k = ni(); // gas stations
    int s = ni(); // distance
    long t = ni(); // time
    int[][] cars = new int[n][2];
    int maxTrunk = 0;
    for (int i = 0; i < n; i++) {
      cars[i][0] = ni(); // cost
      cars[i][1] = ni(); // trunk
      maxTrunk = Math.max(cars[i][1], maxTrunk);
    }
    int[] g = new int[k + 1];
    for (int i = 0; i < k; i++) {
      g[i] = ni();
    }
    g[k] = s;

    sortRadix(g);
    int lo = 0;
    int hi = maxTrunk;
    int best = Integer.MAX_VALUE;
    while (lo <= hi) {
      int mid = lo + hi >>> 1;
      if (valid(g, mid) <= t) {
        hi = mid - 1;
        best = mid;
      } else {
        lo = mid + 1;
      }
    }
    int res = -1;
    if (best != Integer.MAX_VALUE) {
      for (int i = 0; i < cars.length; i++) {
        if (cars[i][1] >= best) {
          if (res == -1 || cars[i][0] < res) {
            res = cars[i][0];
          }
        }
      }
    }
    out.println(res);
  }

  public static int[] sortRadix(int[] f) {
    int[] to = new int[f.length];
    {
      int[] b = new int[65537];
      for(int i = 0;i < f.length;i++)b[1+(f[i]&0xffff)]++;
      for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
      for(int i = 0;i < f.length;i++)to[b[f[i]&0xffff]++] = f[i];
      int[] d = f; f = to;to = d;
    }
    {
      int[] b = new int[65537];
      for(int i = 0;i < f.length;i++)b[1+(f[i]>>>16)]++;
      for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
      for(int i = 0;i < f.length;i++)to[b[f[i]>>>16]++] = f[i];
      int[] d = f; f = to;to = d;
    }
    return f;
  }

  private long valid(int[] g, long trunk) {
    long t = 0;
    long s = 0;
    for (int i = 0; i < g.length; i++) {
      long dist = g[i] - s;
      if (dist > trunk) {
        return Integer.MAX_VALUE;
      }
      if (trunk >= dist * 2) {
        t += dist;
      } else {
        t += 3L * dist - trunk;
      }
      s = g[i];
    }
    return t;
  }

  public static void main(String[] args) throws Exception {
    new P738C().run();
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
