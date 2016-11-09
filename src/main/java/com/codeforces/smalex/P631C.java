package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class P631C {
  InputStream is;
  PrintWriter out;
  String INPUT = "4 2\n" +
    "1 2 4 3\n" +
    "2 3\n" +
    "1 2\n" +
    "1 1\n" +
    "1 2\n" +
    "2 2\n" +
    "2 1\n" +
    "1 3\n" +
    "1 1\n" +
    "2 2";
  
  void solve() {
    int n = ni();
    int m = ni();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = ni();
    }

    boolean[] dir = new boolean[m];
    int[] range = new int[m];
    for (int i = 0; i < m; i++) {
      dir[i] = ni() == 1;
      range[i] = ni();
    }
    List<Integer> sortedPos = new ArrayList<>();
    List<Boolean> sortedDir = new ArrayList<>();
    for (int i = m - 1; i >= 0; i--) {
      int pos = range[i];
      boolean d = dir[i];
      if (sortedPos.isEmpty() || sortedPos.get(sortedPos.size() - 1) < pos) {
        sortedPos.add(pos);
        sortedDir.add(d);
      }
    }
    Collections.reverse(sortedPos);
    Collections.reverse(sortedDir);
    int maxIndex = sortedPos.get(0);
    int[] arr = Arrays.copyOf(a, maxIndex);
    Arrays.sort(arr);
    int hi = arr.length - 1;
    int lo = 0;
    int dst = arr.length - 1;
    for (int i = 0; i < sortedPos.size(); i++) {
      int from = sortedPos.get(i);
      int to = i + 1 < sortedPos.size() ? sortedPos.get(i + 1) : 0;
      int cnt = from - to;
      if (!sortedDir.get(i)) {
        for (int j = 0; j < cnt; j++, lo++, dst--) {
          a[dst] = arr[lo];
        }
      } else {
        for (int j = 0; j < cnt; j++, hi--, dst--) {
          a[dst] = arr[hi];
        }
      }
    }
    for (int b : a) {
      out.print(b + " ");
    }
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

  public static void main(String[] args) throws Exception {
    new P631C().run();
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
