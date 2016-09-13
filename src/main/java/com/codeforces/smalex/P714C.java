package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P714C {
  // problem http://codeforces.com/contest/714/problem/C
  InputStream is;
  PrintWriter out;
  String INPUT =  "12\n" + 
                  "+ 1\n" + 
                  "+ 241\n" + 
                  "? 1\n" + 
                  "+ 361\n" + 
                  "- 241\n" + 
                  "? 0101\n" + 
                  "+ 101\n" + 
                  "? 101\n" + 
                  "- 101\n" + 
                  "? 101\n" + 
                  "+ 4000\n" + 
                  "? 0\n";

  private static class Node {
    Node[] nodes = new Node[2];
    int cnt = 0;
    
    public String toString() {
      return "node{cnt=" + cnt + "}";
    }
  }
  
  void solve() {
    int n = ni();
    Node root = new Node();
    for (int i = 0; i < n; i++) {
      char cmd = ns().charAt(0);
      String arg = ns();
      if (cmd == '+') {
        long x = Long.parseLong(arg);
        add(root, x, 0);
      } else if (cmd == '-') {
        long x = Long.parseLong(arg);
        remove(root, x, 0);
      } else if (cmd == '?') {
        int x = Integer.parseInt(arg, 2);
        int res = query(root, x, 0);
        System.out.println(res);
      }
    }
  }

  private int query(Node node, long value, int bit) {
    if (bit == 18) return node.cnt;
    int index = (int)(value % 2 == 0 ? 0 : 1);
    Node next = node.nodes[index];
    if (next == null) {
      return 0;
    }
    return query(next, value / 2, bit + 1);
  }

  private void remove(Node node, long value, int bit) {
    node.cnt--;
    if (bit == 18) return;
    int index = (int) (value % 2);
    Node next = node.nodes[index];
    remove(next, value / 10, bit + 1);
  }

  private void add(Node node, long value, int bit) {
    node.cnt++;
    if (bit == 18) {
      return;
    }
    int index = (int) (value % 2);
    Node next = node.nodes[index];
    if (next == null) {
      next = new Node();
      node.nodes[index] = next;
    }
    add(next, value / 10, bit + 1);
  }

  private long codec(long x) {
    long k = 1;
    long res = 0;
    while (x > 0) {
      long kol = x % 10;
      if (kol % 2 == 1) res += k;
      x /= 10;
      k *= 2;
    }
    return res;
  }

  public static void main(String[] args) throws Exception {
    new P714C().run();
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
