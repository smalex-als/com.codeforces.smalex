package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P727B {
  // problem http://codeforces.com/contest/727/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT3 = "a1b2c3.38";
  String INPUT2 = "chipsy48.32televizor12.390";
  String INPUT = "aa1t3";
  int pos = 0;
  String body;

  void solve() {
    body = ns();
    long total = 0;
    while (true) {
      String name = readName();
      if (name == null) {
        break;
      }
      String price = readPrice();
      long num = Long.parseLong(price.replaceAll("\\.", "")); 
      total += num;
      
    }
    System.out.println(priceToString(total));
  }

  private String priceToString(long total) {
    String str = String.valueOf(total);
    while (str.length() < 3) {
      str = "0" + str;
    }
    str = str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
    int pos = str.length() - 6;
    while (pos > 0) {
      str = str.substring(0, pos) + "." + str.substring(pos);
      pos -= 3;
    }
    if (str.endsWith(".00")) {
      str = str.substring(0, str.length() - 3);
    }
    return str;
  }

  private String readPrice() {
    int start = pos;
    while (pos < body.length()) {
      int ch = body.charAt(pos);
      if ((ch >= '0' && ch <= '9') || ch == '.') {
        pos++;
      } else {
        break;
      }
    }
    String price = body.substring(start, pos);
    if (price.length() < 3 || price.charAt(price.length() - 3) != '.') {
      price += ".00";
    }
    return price;
  }

  private String readName() {
    int start = pos;
    while (pos < body.length()) {
      int ch = body.charAt(pos);
      if (ch >= 'a' && ch <= 'z') {
        pos++;
      } else {
        break;
      }
    }
    if (start == pos) {
      return null;
    }
    return body.substring(start, pos);
  }

  public static void main(String[] args) throws Exception {
    new P727B().run();
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
