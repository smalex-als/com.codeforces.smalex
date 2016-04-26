package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P669C {
  InputStream is;
  PrintWriter out;
  // String INPUT = 
  //     " 3 3 2" +
  //     " 1 2" +
  //     " 3 2 2 5";
  String INPUT = "2 2 6" +
    " 2 1" + 
    " 2 2" + 
    " 3 1 1 1" + 
    " 3 2 2 2" + 
    " 3 1 2 8" + 
    " 3 2 1 8";
  
  void solve() {
    int n = ni();
    int m = ni();
    int q = ni();
    int[][] queries = new int[q][4];
    for (int i = 0; i < q; i++) {
      int t = ni();
      queries[i][0] = t;
      if (t == 1 || t == 2) {
        queries[i][1] = ni() - 1;
      } else {
        queries[i][1] = ni() - 1;
        queries[i][2] = ni() - 1;
        queries[i][3] = ni();
      }
    }
    int[][] matrix = new int[n][m];
    for (int i = q - 1; i >= 0; i--) {
      int[] query = queries[i];
      if (query[0] == 3) {
        int r = query[1];
        int c = query[2];
        int v = query[3];
        matrix[r][c] = v;
      } else if (query[0] == 1) {
        int v = query[1];
        int first = matrix[v][m - 1];
        for (int k = m - 1; k > 0; k--) {
          matrix[v][k] = matrix[v][k - 1];
        }
        matrix[v][0] = first;
      } else if (query[0] == 2) {
        int v = query[1];
        int first = matrix[n - 1][v];
        for (int k = n - 1; k > 0; k--) {
          matrix[k][v] = matrix[k - 1][v];
        }
        matrix[0][v] = first;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (j > 0) {
          sb.append(" ");
        }
        sb.append(matrix[i][j]);
      }
      sb.append("\n");
    }
    System.out.println(sb.toString());
  }
  
  public static void main(String[] args) throws Exception {
    new P669C().run();
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
