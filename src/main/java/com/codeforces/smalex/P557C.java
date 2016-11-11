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
import java.util.TreeMap;

public class P557C {
  // problem http://codeforces.com/contest/557/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT2 =
"6\n" +
"2 2 1 1 3 3\n" +
"4 3 5 5 2 1\n";

  String INPUT3 = "3\n2 4 4\n1 1 1";

  String INPUT = "10\n" +
  "20 1 15 17 11 2 15 3 16 3\n" +
  "129 114 183 94 169 16 18 104 49 146\n";
  
  void solve() {
    int n = ni();
    int[] l = new int[n];
    int[] d = new int[n];
    List<Integer>[] a = new List[202];
    TreeMap<Integer, Integer> t = new TreeMap<>();
    for (int i = 0; i < n; i++) {
      l[i] = ni();
    }
    for (int i = 0; i < n; i++) {
      d[i] = ni();
    }
    for (int i = 0; i < n; i++) {
      if (a[d[i]] == null) {
        a[d[i]] = new ArrayList<Integer>();
      }
      a[d[i]].add(l[i]);
      Integer cnt = t.get(l[i]);
      if (cnt == null) {
        t.put(l[i], 1);
      } else {
        t.put(l[i], cnt + 1);
      }
    }
    for (int i = 0; i < a.length; i++) {
      if (a[i] != null) {
        Collections.sort(a[i]);
      }
    }
    int total = 0;
    Integer len = t.lastKey();
    while (len != null) {
      total += t.get(len);
      len = t.lowerKey(len);
    }
    len = t.lastKey();
    int totalcost = 0;
    int best = Integer.MAX_VALUE;
    while (len != null) {
      int currentcost = 0;
      int cnt = t.get(len);
      total -= cnt;
      for (int i = 0; i < a.length; i++) {
        List<Integer> items = a[i];
        if (items != null) {
          while (!items.isEmpty() && items.get(items.size() - 1).equals(len)) {
            items.remove(items.size() - 1);
            currentcost += i;
          }
        }
      }

      if (cnt <= total) {
        int cost = totalcost;
        int removed = 0;
        for (int i = 0; i < a.length; i++) {
          List<Integer> items = a[i];
          if (items != null && !items.isEmpty()) {
            int tmp = Math.min(total - cnt + 1 - removed, items.size());
            cost += tmp * i;
            removed += tmp;
            if (cnt > total - removed) {
              best = Math.min(best, cost);
              break;
            }
          }
        }
      } else {
        best = Math.min(best, totalcost);
      }
      totalcost += currentcost;
      len = t.lowerKey(len);
    }
    System.out.println(best);
  }

  public static void main(String[] args) throws Exception {
    new P557C().run();
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
