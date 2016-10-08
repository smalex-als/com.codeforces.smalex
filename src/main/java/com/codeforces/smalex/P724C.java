package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P724C {
  // problem http://codeforces.com/contest/724/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT =  "3 3 4\n" +
                  "1 1\n" +
                  "1 2\n" +
                  "2 1\n" +
                  "2 2\n";

  Map<Long, Set<Integer>> map = new HashMap<>();
  
  void solve() {
    int width = ni();
    int height = ni();
    int k = ni();
    int[][] xy = new int[k][2];
    for (int i = 0; i < k; i++) {
      int x = ni();
      int y = ni();
      xy[i][0] = x;
      xy[i][1] = y;

      int min = Math.min(y, x);
      addPoint(x - min, y - min, i);
      min = Math.min(height - y, width - x);
      addPoint(x + min, y + min, i);

      min = Math.min(height - y, x);
      addPoint(x - min, y + min, i);
      min = Math.min(y, width - x);
      addPoint(x + min, y - min, i);
    }
    long[] res = new long[k];
    Arrays.fill(res, -1);
    int x = 0, y = 0;
    int dx = 1, dy = 1;
    long current = 0;
    while (true) {
      long key = toKey(x, y);
      Set<Integer> indexes = map.get(key);
      if (indexes != null) {
        List<Integer> toDelete = null;
        for (int index : indexes) {
          if (res[index] != -1) {
            if (toDelete == null) {
              toDelete = new ArrayList<>();
            }
            toDelete.add(index);
            continue;
          }
          int dstX = xy[index][0];
          int dstY = xy[index][1];
          long t0 = (dstX - x) * dx;
          long t1 = (dstY - y) * dy;
          if (t0 == t1 && t0 > 0) {
            if (res[index] == -1) {
              res[index] = current + t0;
            }
            if (toDelete == null) {
              toDelete = new ArrayList<>();
            }
            toDelete.add(index);
          }
        }
        if (toDelete != null) {
          for (int index : toDelete) {
            indexes.remove(index);
          }
          if (indexes.isEmpty()) {
            map.remove(key);
          }
        }
      }
      if (dx == 1 && dy == 1) {
        if (width - x < height - y) {
          dx = -1;
          dy = 1;
          current += width - x;
          y = y + width - x;
          x = width;
        } else {
          dx = 1;
          dy = -1;
          current += height - y;
          x = x + height - y;
          y = height;
        }
      } else if (dx == -1 && dy == 1) {
        if (x < height - y) {
          dx = 1;
          dy = 1;
          current += x;
          y = y + x;
          x = 0;
        } else {
          dx = -1;
          dy = -1;
          current += height - y;
          x = x - (height - y);
          y = height;
        }
      } else if (dx == -1 && dy == -1) {
        if (x < y) {
          dx = 1;
          dy = -1;
          current += x;
          y = y - x;
          x = 0;
        } else {
          dx = -1;
          dy = 1;
          current += y;
          x = x - y;
          y = 0;
        }
      } else if (dx == 1 && dy == -1) {
        if (y < width - x) {
          dx = 1;
          dy = 1;
          current += y;
          x = x + y;
          y = 0;
        } else {
          dx = -1;
          dy = -1;
          current += width - x;
          y = y - (width - x);
          x = width;
        }
      }
      if ((x == 0 || x == width) && (y == 0 || y == height)) {
        break;
      }
    }
    for (int i = 0; i < k; i++) {
      out.println(res[i]);
    }
  }

  long toKey(int x, int y) {
    long key = 200_000L * x + y;
    return key;
  }

  private void addPoint(int x, int y, int i) {
    long key = toKey(x, y);
    Set<Integer> points = map.get(key);
    if (points == null) {
      points = new HashSet<>();
      map.put(key, points);
    }
    points.add(i);
  }

  public static void main(String[] args) throws Exception {
    new P724C().run();
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
