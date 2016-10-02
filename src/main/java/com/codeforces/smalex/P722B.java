package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P722B {
  // problem http://codeforces.com/contest/722/problem/B
  InputStream is;
  PrintWriter out;
  String INPUT =  "3\n" + 
                  "2 2 3\n" + 
                  "intel\n" + 
                  "code\n" + 
                  "ch allenge\n";

  String INPUT2 = 
"4\n" +
"13 11 15 15\n" +
"to be or not to be that is the question\n" +
"whether tis nobler in the mind to suffer\n" +
"the slings and arrows of outrageous fortune\n" +
"or to take arms against a sea of troubles\n";
  
  void solve() {
    int n = ni();
    int[] p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = ni();
    }
    
    boolean ok = true;
    for (int i = 0; i < n; i++) {
      String line = nextLine();
      String[] words = line.split(" ");
      int cnt = 0;
      for (String word : line.split(" ")) {
        cnt += isValid(word, p[i]);
      }
      if (cnt != p[i]) {
        // System.out.println(String.format("fail line = %s cnt = %s ", line, cnt));
        ok = false;
      }
    }
    System.out.println(ok ? "YES" : "NO");
  }

  private int isValid(String word, int p) {
    char[] a = word.toCharArray();
    int cnt = 0;
    boolean wasVowel = false;
    for (int i = 0; i < a.length; i++) {
      if (isVowel(a[i])) {
        // if (!wasVowel) {
        //  wasVowel = true;
          cnt++;
        // }
      } else {
          wasVowel = false;
      }
    }
    return cnt;
  }
  boolean isVowel(char ch) {
    return ch == 'e' || ch == 'a' || ch == 'i' || ch == 'o'
        || ch == 'u' || ch == 'y';
  }

  public static void main(String[] args) throws Exception {
    new P722B().run();
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

  private String nextLine() {
    int b = skip();
    StringBuilder sb = new StringBuilder();
    while ((b >= 33 && b <= 126 || b == ' ')) {
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
