package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class P677D {
  InputStream is;
  PrintWriter out;
  // String INPUT = "3 4 2"
  //   + " 1 1 2 1"
  //   + " 1 1 2 1"
  //   + " 1 2 2 2";
  // String INPUT = " 3 4 12 1 2 3 4 8 7 6 5 9 10 11 12";
  // String INPUT  = "3 3 9 1 3 5 8 9 7 4 6 2";
  String INPUT = "5 5 5"
   + " 4 2 1 2 3"
   + " 3 4 4 2 2"
   + " 3 4 1 2 4"
   + " 2 1 5 4 2"
   + " 4 3 1 1 2";
  
  private static class Point {
    int y;
    int x;
    int a;

    public Point(int y, int x, int a) {
      this.y = y;
      this.x = x;
      this.a = a;
    }

    @Override
    public String toString() {
      return "y=" + y + ",x=" + x + ",a=" + a;
    }
  }

  void solve() {
    int n = ni();
    int m = ni();
    int p = ni();
    List<Point> points = new ArrayList<Point>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int a = ni();
        points.add(new Point(i, j, a));
      }
    }
    Collections.sort(points, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.compare(o1.a, o2.a);
      }
    });
    long[][] best = new long[n][m];
    int pos = 0;
    LinkedList<Integer> q = new LinkedList<Integer>();
    long res = Long.MAX_VALUE;
    int[] next = new int[n * m + 1];
    for (long[] row : best) {
      Arrays.fill(row, -1);
    }
    for (int i = points.size() - 1; i >= 0; i--) {
      Point point = points.get(i);
      if (point.a == 1) {
        q.add(i);
        best[point.y][point.x] = point.y + point.x;
      }
      next[point.a] = Math.max(next[point.a], i);
    }

    while (!q.isEmpty()) {
      int index = q.pollFirst();
      Point point = points.get(index);
      long b = best[point.y][point.x];
      if (b == -1) {
        System.out.println("fail");
        break;
      }
      int nexta = point.a + 1;
      if (nexta == p + 1) {
        res = Math.min(res, best[point.y][point.x]);
        continue;
      }
      int nextindex = next[point.a] + 1;
      for (int i = nextindex; i < points.size(); i++) {
        Point nextpoint = points.get(i);
        if (nextpoint.a == nexta) {
          int d = dist(nextpoint, point);
          if (best[nextpoint.y][nextpoint.x] == -1) {
            q.add(i);
          }
          if (best[nextpoint.y][nextpoint.x] == -1 
              || b + d < best[nextpoint.y][nextpoint.x]) {
            best[nextpoint.y][nextpoint.x] = b + d;
          }
        } else {
          break;
        }
      }
    }
    System.out.println(res);
  }

  private int dist(Point p, Point p2) {
    return Math.abs(p.x - p2.x) 
      + Math.abs(p.y - p2.y);
  }

  public static void main(String[] args) throws Exception {
    new P677D().run();
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
