package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;

public class P732E {
  // problem http://codeforces.com/contest/732/problem/E
  InputStream is;
  PrintWriter out;
  String INPUT =  "2 2\n" +
      "607578 103023\n" + // power
      "185480405 843960081\n"; // socket
  
  void solve() {
    int n = ni();
    int m = ni();
    Map<Integer, LinkedList<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int x = ni();
      if (!map.containsKey(x)) {
        map.put(x, new LinkedList<>());
      }
      map.get(x).add(i);
    }
    long[] s = new long[m];
    for (int i = 0; i < m; i++) {
      s[i] = nl() * m + i;
    }
    Arrays.sort(s);
    int[] b = new int[n];
    Arrays.fill(b, -1);
    int[] tr = new int[m];
    int c = 0;
    for (int i = 0; i < m; i++) {
      int power = (int) (s[i] / m);
      int index = (int) (s[i] % m);
      int v = power;
      int cnt = 0;
      while (v > 1 && !map.containsKey(v)) {
        v = (v + 1) / 2;
        cnt++;
      }
      if (map.containsKey(v)) {
        c++;
        b[map.get(v).removeFirst()] = index;
        tr[index] = cnt;
        if (map.get(v).isEmpty()) {
          map.remove(v);
        }
      }
    }
    int u = 0;
    for (int i = 0; i < tr.length; i++) {
      u += tr[i];
    }
    out.println(c + " " + u);

    for (int i = 0; i < tr.length; i++) {
      if (i > 0) out.print(" ");
      out.print(tr[i]);
    }
    out.println();
    for (int i = 0; i < b.length; i++) {
      if (i > 0) out.print(" ");
      out.print(b[i] + 1);
    }
    out.println();
  }
  
  public static void main(String[] args) throws Exception {
    new P732E().run();
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
