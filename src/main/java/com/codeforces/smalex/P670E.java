package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Stack;

public class P670E {
  InputStream is;
  PrintWriter out;
  String INPUT = "8 4 5\n"
    + "(())()()\n"
    + "RDLD";

  private static class Symbol {
    private boolean open;
    private Symbol prev;
    private Symbol next;
    private Symbol buddy;
  }
  
  void solve() {
    int n = ni();
    int m = ni();
    int p = ni() - 1;
    String str = ns();
    char[] cmds = ns().toCharArray();

    Stack<Symbol> stack = new Stack<>();
    Symbol first = null;
    Symbol last = null;
    for (int i = 0; i < str.length(); i++) {
      Symbol sym = new Symbol();
      sym.prev = last;
      if (first == null) {
        first = sym;
      } else {
        last.next = sym;
      }
      last = sym;
      if (str.charAt(i) == '(') {
        sym.open = true;
        stack.add(sym);
      } else {
        Symbol prev = stack.pop();
        prev.buddy = sym;
        sym.buddy = prev;
      }
    }

    int pos = 0;
    Symbol current = first;

    while (pos < p) {
      current = current.next;
      pos++;
    }
    for (char cmd : cmds) {
      if (cmd == 'R') {
        current = current.next;
      } else if (cmd == 'L') {
        current = current.prev;
      } else if (cmd == 'D') {
        Symbol open = current.open ? current : current.buddy;
        Symbol close = !current.open ? current : current.buddy;
        Symbol prev = open.prev;
        Symbol next = close.next;
        if (prev != null) {
          prev.next = next;
        } else {
          first = next;
        }
        if (next != null) {
          next.prev = prev;
        }
        current = next != null ? next : prev;
      }
    }
    printResult(first, null);
  }

  private void printResult(Symbol first, Symbol cursor) {
    Symbol current = first;
    while (current != null) {
      out.print(current.open ? '(' : ')');
      if (cursor == current) {
        out.print("<");
      }
      current = current.next;
    }
    out.println();
    out.flush();
  }

  public static void main(String[] args) throws Exception {
    new P670E().run();
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
