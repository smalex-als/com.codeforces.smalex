package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class P682C {
  InputStream is;
  PrintWriter out;

  String INPUT = "9\n"
    + "88 22 83 14 95 91 98 53 11\n"
    + "3 24\n"
    + "7 -8\n"
    + "1 67\n"
    + "1 64\n"
    + "9 65\n"
    + "5 12\n"
    + "6 -80\n"
    + "3 8";
  
  private static class Graph {
    private List<Integer>[] adj;
    private List<Long>[] cost;
    private int edges;
    private int[] a;
    private int deleted;
    private int[] cntChildren;
    private long[] path;
  
    public Graph(int v, int[] a) {
      this.a = a;
      cntChildren = new int[v];
      path = new long[v];
      adj = new List[v];
      cost = new List[v];
      for (int i = 0; i < v; i++) {
        adj[i] = new ArrayList<Integer>();
        cost[i] = new ArrayList<Long>();
      }
    }
  
    public void addEdge(int v, int w, long c) {
      // adj[v].add(w);
      adj[w].add(v);
      cost[w].add(c);
      edges++;
    }
  
    public List<Integer> adj(int v) {
      return adj[v];
    }

    public List<Long> cost(int v) {
      return cost[v];
    }
  
    public int children(int q) {
      int children = 0;
      List<Integer> next = adj[q];
      for (int i = 0; i < next.size(); i++) {
        int v = next.get(i);
        children += children(v);
      }
      cntChildren[q] = children;
      return children + 1;
    }

    public void dfs(int q, long prevcost) {
      if (prevcost > a[q]) {
        deleted += cntChildren[q] + 1;
        return;
      }

      List<Integer> next = adj[q];
      List<Long> costs = cost[q];
      for (int i = 0; i < next.size(); i++) {
        int v = next.get(i);
        long c = costs.get(i);
        dfs(v, Math.max(0, prevcost + c));
      }
    }
  }

  void solve() {
    int n = ni();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = ni();
    }
    Graph g = new Graph(n, a);
    for (int i = 0; i < n - 1; i++) {
      int u = ni() - 1;
      int value = ni();
      g.addEdge(i + 1, u, value);
    }
    g.children(0);
    g.dfs(0, 0);
    System.out.println(g.deleted);
  }
  
  public static void main(String[] args) throws Exception {
    new P682C().run();
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
