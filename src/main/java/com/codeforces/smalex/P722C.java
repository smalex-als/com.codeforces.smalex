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

public class P722C {
  // problem http://codeforces.com/contest/722/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT =  "10\n" +
                  "3 3 3 5 6 9 3 1 7 3\n" +
                  "3 4 6 7 5 1 10 9 2 8\n" ;

  void solve() {
    int n = ni();
    long[] a = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = ni();
    }
    int[] b = new int[n];
    for (int i = 0; i < n; i++) {
      b[i] = ni() - 1;
    }
    int color = 0;
    int[] field = new int[n];
    HashMap<Integer, Long> sumMap = new HashMap<>();
    int[] len = new int[n + 1];
    long[] res = new long[n + 1];
    for (int i = n - 1; i >= 0; i--) {
      int index = b[i];
      int prevColor = index > 0 ? field[index - 1] : 0;
      int nextColor = index + 1 < n ? field[index + 1] : 0;
      int newcolor = 0;
      long sum = a[index];
      int newlen = 1;
      if (prevColor != 0 && nextColor != 0) {
        if (len[prevColor] > len[nextColor]) {
          newcolor = prevColor;
        } else {
          newcolor = nextColor;
        }
      } else if (prevColor != 0) {
        newcolor = prevColor;
      } else if (nextColor != 0) {
        newcolor = nextColor;
      } else {
        newcolor = ++color;
      }
      if (prevColor != 0) {
        sum += sumMap.get(prevColor);
        newlen += len[prevColor];
      } 
      if (nextColor != 0) {
        sum += sumMap.get(nextColor);
        newlen += len[nextColor];
      } 
      len[newcolor] = newlen;
      sumMap.put(newcolor, sum);
      res[i] = sum;
      field[index] = newcolor;
      if (prevColor != 0) {
        join(field, index, -1, prevColor, newcolor);
      }
      if (nextColor != 0) {
        join(field, index, 1, nextColor, newcolor);
      }
    }
    long max = 0;
    List<Long> output = new ArrayList<>();
    for (int i = res.length - 1; i > 0; i--) {
      if (res[i] < max) {
      } else {
        max = res[i];
      }
      output.add(max);
    }
    Collections.reverse(output);
    for (long num : output) {
      out.println(num);
    }
  }

  private void join(int[] field, int pos, int delta, int oldcolor, int newcolor) {
    pos += delta;
    while (pos >= 0 && pos < field.length && field[pos] == oldcolor) {
      field[pos] = newcolor;
      pos += delta;
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P722C().run();
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
