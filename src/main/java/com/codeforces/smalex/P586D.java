package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P586D {
  // problem http://codeforces.com/contest/586/problem/D
  InputStream is;
  PrintWriter out;
  String INPUT = 
    "2\n" + 
    "  16 4\n" + 
    "  ...AAAAA........\n" + 
    "  s.BBB......CCCCC\n" + 
    "  ........DDDDD...\n" + 
    "  16 4\n" + 
    "  ...AAAAA........\n" + 
    "  s.BBB....CCCCC..\n" + 
    "  .......DDDDD....\n";

  void solve() {
    int t = ni();
    for (int i = 0; i < t; i++) {
      int n = ni();
      int k = ni();
      String[] rows = new String[]{ns(), ns(), ns()};
      boolean[][] visit = new boolean[3][n];
      int start = 0;
      for (int j = 0; j < 3; j++) {
        if (rows[j].charAt(0) == 's') {
          start = j;
          break;
        }
      }

      if (dfs(rows, visit, start, 0, n)) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }

  private boolean dfs(String[] rows, boolean[][] visit, int y, int x, int n) {
    visit[y][x] = true;
    if (x == n) return true;
    x++;
    if (x >= rows[y].length()) {
      return true;
    }
    if (rows[y].charAt(x) != '.') {
      return false;
    }
    for (int i = -1; i < 2; i++) {
      int ny = y + i;
      if (ny < 0 || ny >= 3) continue;
      boolean ok = true;
      for (int j = 0; j <= 2; j++) {
        int nx = x + j;
        if (nx >= rows[ny].length()) {
          return true;
        }
        char ch = rows[ny].charAt(nx);
        if (ch != '.' && ch != 's') {
          ok = false;
          break;
        }
      }
      if (ok && !visit[ny][x + 2]) {
        visit[y][x] = true;
        visit[ny][x + 1] = true;
        visit[ny][x + 2] = true;
        boolean res = dfs(rows, visit, ny, x + 2, n);
        if (res) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    new P586D().run();
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
