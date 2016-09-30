package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class P721C2 {
  // problem http://codeforces.com/contest/7212/problem/2
  InputStream is;
  PrintWriter out;
  String INPUT2 = 
  "6 6 7\n" +
  "1 2 2\n" +
  "1 3 3\n" +
  "3 6 3\n" +
  "2 4 2\n" +
  "4 6 2\n" +
  "6 5 1";
  String INPUT = 
      "10 10 100\n" +
      "1 4 1\n" +
      "6 4 1\n" +
      "9 3 2\n" +
      "2 7 2\n" +
      "5 8 11\n" +
      "1 2 8\n" +
      "4 10 10\n" +
      "8 9 2\n" +
      "7 5 8\n" +
      "3 6 4\n";
  void solve() {
    int n = ni();
    int m = ni();
    int T = ni();
    Graph g = new Graph(n);
    Graph ig = new Graph(n);
    for (int i = 0; i < m; i++) {
      int u = ni();
      int v = ni();
      int t = ni();
      g.addEdge(u - 1, v - 1, t);
      ig.addEdge(v - 1, u - 1, t);
    }
    int[][] dp = new int[n][n + 1];
    int I = 1000000009;
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], I);
    }
    List<Integer> order = sortGraph(g);
    dp[n - 1][1] = 0;
    for (int u : order) {
      for (int len = 0; len <= n; len++) {
        if (dp[u][len] == I) continue;
        List<Integer> vertexes = ig.adj(u);
        List<Integer> weights = ig.weight(u);
        for (int i = 0; i < vertexes.size(); i++) {
          int v = vertexes.get(i);
          int w = weights.get(i);
          int newtime = dp[u][len] + w;
          if (newtime < dp[v][len + 1]) {
            dp[v][len + 1] = newtime;
          }
        }
      }
      if (u == 0) {
        for (int j = n; j >= 0; j--) {
          if (dp[u][j] <= T) {
            out.println(j);
            while (true) {
              out.print((u + 1) + " ");
              if (u == n - 1) break;
              List<Integer> vertexes = g.adj(u);
              List<Integer> weights = g.weight(u);
              for (int i = 0; i < vertexes.size(); i++) {
                int v = vertexes.get(i);
                int w = weights.get(i);
                if (dp[v][j-1] + w == dp[u][j]) {
                  u = v;
                  break;
                }
              }
              j--;
            }
            break;
          }
        }
        break;
      }
    }
  }

  List<Integer> sortGraph(Graph g) {
    boolean[] visited = new boolean[g.size()];
    List<Integer> order = new ArrayList<>();
    dfs(g, 0, visited, order);
    return order;
  }

  private void dfs(Graph g, int i, boolean[] visited, List<Integer> order) {
    if (visited[i]) {
      return;
    }
    visited[i] = true;
    for (int u : g.adj(i)) {
      dfs(g, u, visited, order);
    }
    order.add(i);
  }

  private static class Graph {
    private List<Integer>[] adj;
    private List<Integer>[] weight;
    private int edges;
  
    public Graph(int v) {
      adj = new List[v];
      weight = new List[v];
      for (int i = 0; i < v; i++) {
        adj[i] = new ArrayList<Integer>();
        weight[i] = new ArrayList<Integer>();
      }
    }

    public int size() {
      return adj.length;
    }
  
    public void addEdge(int v, int w, int t) {
      adj[v].add(w);
      weight[v].add(t);
      edges++;
    }
  
    public List<Integer> adj(int v) {
      return adj[v];
    }
    public List<Integer> weight(int v) {
      return weight[v];
    }
  }
  
  
  public static void main(String[] args) throws Exception {
    new P721C2().run();
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
