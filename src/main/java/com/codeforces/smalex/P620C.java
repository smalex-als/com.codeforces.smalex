package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class P620C {
  InputStream is;
  PrintWriter out;
  // String INPUT = "7 1 2 1 3 1 2 1";
  String INPUT = "5 1 2 3 4 1";
  
  void solve() {
    int n = ni();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = ni();
    }
    MapCounter counter = new MapCounter();
    int start = 0;
    int end = 0;
    boolean found = false;
    List<Integer> starts = new ArrayList<Integer>();
    List<Integer> ends = new ArrayList<Integer>();
    while (start < n && end < n) {
      while (end < n) {
        counter.add(a[end]);
        if (counter.good) {
          starts.add(start + 1);
          ends.add(end + 1);
          counter.clear();
          start = end + 1;
          end = start;
          break;
        }
        end++;
      }
    }
    if (!starts.isEmpty()) {
      System.out.println(starts.size());
      for (int i = 0; i < starts.size(); i++) {
        if (i == starts.size() - 1) {
          System.out.println(starts.get(i) + " " + n);
        } else {
          System.out.println(starts.get(i) + " " + ends.get(i));
        }
      }
    } else {
      System.out.println(-1);
    }
  }

  private static class MapCounter {
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private boolean good = false;

    public void add(int num) {
      Integer cnt = map.get(num);
      if (cnt == null) {
        cnt = 1;
        map.put(num, cnt);
      } else {
        good = true;
        cnt++;
        map.put(num, cnt);
      }
    }

    public void clear() {
      good = false;
      map.clear();
    }

    public void remove(int num) {
      Integer cnt = map.get(num);
      if (cnt == 1) {
        map.remove(num);
      } else {
        map.put(num, cnt - 1);
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P620C().run();
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
