package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class P659E {
  InputStream is;
  PrintWriter out;
  String INPUT = "4 3 2 1 1 3 4 3";
  // String INPUT = "5 5 2 1 1 3 2 3 2 5 4 3";
  // String INPUT = "6 5 1 2 2 3 4 5 4 6 5 6";
  
  private static class Graph {
    private List<Integer>[] adj;
    private int edges;
  
    public Graph(int v) {
      adj = new List[v];
      for (int i = 0; i < v; i++) {
        adj[i] = new ArrayList<Integer>();
      }
    }
  
    public void addEdge(int v, int w) {
      adj[v].add(w);
      adj[w].add(v);
      edges++;
    }
  
    public List<Integer> adj(int v) {
      return adj[v];
    }
  
    public boolean dfs(boolean[] w, int q, int p) {
      boolean res = true;
      w[q] = true;
      for (int v : adj(q)) {
        if (v == p) continue;
        if (w[v]) {
          res = false;
        } else {
          res &= dfs(w, v, q);
        }
      }
      return res;
    }
  }

  void solve() {
    int n = ni();
    int m = ni();
    Graph g = new Graph(n);
    for (int i = 0; i < m; i++) {
      g.addEdge(ni() - 1, ni() - 1);
    }
    boolean[] w = new boolean[n];
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (!w[i] && g.dfs(w, i, -1)) {
        res++;
      }
    }
    System.out.println(res);
  }

  public static void main(String[] args) throws Exception {
    new P659E().run();
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
