package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P001B {
  // problem http://codeforces.com/contest/1/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT = "2 R23C55 BC23";

  void solve() {
		int n = ni();
    for (int i = 0; i < n; i++) {
      String str = ns();
      getFormat(str);
    }
  }

  private void getFormat(String str) {
    {
      Pattern p = Pattern.compile("^R([0-9]+)C([0-9]+)$");
      Matcher m = p.matcher(str);
      if (m.matches()) {
        int row = Integer.parseInt(m.group(1));
        int col = Integer.parseInt(m.group(2));
        StringBuilder sb = new StringBuilder();
        while (col > 0) {
          col--;
          sb.append((char) ((col % 26) + 'A'));
          col /= 26;
        }
        sb.reverse();
        sb.append(row);
        out.println(sb.toString());
      } 
    }
    Pattern p = Pattern.compile("^([A-Z]+)([0-9]+)$");
    Matcher m = p.matcher(str);
    if (m.matches()) {
      String row = m.group(1);
      int col = Integer.parseInt(m.group(2));
      int res = 0;
      for (int i = 0; i < row.length(); i++) {
        res *= 26;
        res += row.charAt(i) - 'A' + 1;
      }
      out.println(String.format("R%dC%d", col, res));
    }
  }

  public static void main(String[] args) throws Exception {
    new P001B().run();
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
