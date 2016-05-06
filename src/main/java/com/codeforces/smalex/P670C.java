package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class P670C {
  InputStream is;
  PrintWriter out;
  // String INPUT = "3 2 3 2 2 3 2 2 3";
  String INPUT = "6\n" + 
    "6 3 1 1 3 7\n" + 
    "5\n" + 
    "1 2 3 4 5\n" +
    "2 3 1 5 1";
  Map<Integer, Integer> newIndex = new HashMap<Integer, Integer>();

  private static class Res implements Comparable<Res> {
    int index;
    int b;
    int s;

    /**
     * @param index
     * @param b
     * @param s
     */
    public Res(int index, int b, int s) {
      this.index = index;
      this.b = b;
      this.s = s;
    }

    @Override
    public int compareTo(Res o) {
      int res = Integer.compare(b, o.b);
      if (res == 0) {
        res = Integer.compare(s, o.s);
      }
      return res;
    }
  }
  
  void solve() {
    int n = ni();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = reindexLang(ni());
    }
    int m = ni();
    int[] b = new int[m];
    int[] s = new int[m];
    for (int i = 0; i < m; i++) {
      b[i] = reindexLang(ni());
    }
    for (int i = 0; i < m; i++) {
      s[i] = reindexLang(ni());
    }
    int[] acnt = new int[newIndex.size() + 1];
    for (int i = 0; i < n; i++) {
      acnt[a[i]]++;
    }
    int maxb = 0;
    int maxs = 0;
    int result = 0;
    List<Res> list = new ArrayList<Res>();
    for (int i = 0; i < m; i++) {
      int vb = acnt[b[i]];
      int vs = acnt[s[i]];
      list.add(new Res(i + 1, vb, vs));
    }
    Collections.sort(list);
    System.out.println(list.get(list.size() - 1).index);
  }

  private int reindexLang(int l) {
    if (!newIndex.containsKey(l)) {
      int id = newIndex.size() + 1;
      newIndex.put(l, id);
    }
    return newIndex.get(l);
  }

  public static void main(String[] args) throws Exception {
    new P670C().run();
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
