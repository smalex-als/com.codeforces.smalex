package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class P725D {
  // problem http://codeforces.com/contest/725/problem/D
  InputStream is;
  PrintWriter out;
  String INPUT = "8\n" +
    "20 1000\n" +
    "32 37\n" +
    "40 1000\n" +
    "45 50\n" +
    "16 16\n" +
    "16 16\n" +
    "14 1000\n" +
    "2 1000\n";


  private static class Pair {
    int index;
    long balls;
    long weight;

    public Pair(int index, long balls, long weight) {
      this.index = index;
      this.balls = balls;
      this.weight = weight;
    }

    public String toString() {
      return "Pair{i=" + index + ",b=" + balls + ",w=" + weight + "}";
    }
  }
  
  void solve() {
    int n = ni();
    Pair p = new Pair(0, nl(), nl());

    PriorityQueue<Pair> q = new PriorityQueue<Pair>(new Comparator<Pair>() {

      @Override
      public int compare(Pair o1, Pair o2) {
        return Long.compare(o2.balls, o1.balls);
      }
    });
    PriorityQueue<Pair> localQ = new PriorityQueue<Pair>(new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        int x = Long.compare(o1.weight - o1.balls, o2.weight - o2.balls);
        if (x == 0) {
          x = Long.compare(o2.index, o1.index);
        }
        return x;
      }
    });
    for (int i = 0; i < n - 1; i++) {
      q.add(new Pair(i + 1, nl(), nl()));
    }
    while (!q.isEmpty() && q.peek().balls > p.balls) {
      localQ.add(q.poll());
    }
    int best = localQ.size();
    int cur = best;
    while (!localQ.isEmpty()) {
      Pair c = localQ.poll();
      if (c.weight - c.balls < p.balls) {
        p.balls -= c.weight - c.balls + 1;
        cur--;
        while (!q.isEmpty() && q.peek().balls > p.balls) {
          localQ.add(q.poll());
          cur++;
        }
        best = Math.min(cur, best);
      }
    }
    System.out.println(best + 1);
  }
  
  public static void main(String[] args) throws Exception {
    new P725D().run();
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
