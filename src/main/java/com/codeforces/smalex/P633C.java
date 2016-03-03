package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class P633C {
  InputStream is;
  PrintWriter out;
  String INPUT = "12\n" +
      "iherehtolleh\n" +
      "5\n" +
      "HI\n" +
      "he\n" +
      "Ho\n" +
      "there\n" +
      "HeLLo\n" +
      "hello";

  private static class Trie {
    private Node root;

    private static class Node {
      private String word;
      private Node[] next = new Node[26];
    }

    public String get(String key, int start, int end) {
      Node x = get(root, key, start, end, 0);
      if (x == null) return null;
      return x.word;
    }

    private Node get(Node x, String key, int start, int end, int d) {
      if (x == null) return null;
      if (start + d == end) return x;
      int c = key.charAt(start + d) - 'a';
      return get(x.next[c], key, start, end, d + 1);
    }

    public void put(String key, String val) {
      root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, String val, int d) {
      if (x == null) x = new Node();
      if (d == key.length()) { x.word = val; return x; }
      int c = key.charAt(d) - 'a';
      x.next[c] = put(x.next[c], key, val, d + 1);
      return x;
    }
  }
  
  void solve() {
    Trie trie = new Trie();
    ni();
    String str = ns();
    int n = ni();
    for (int i = 0; i < n; i++) {
      String word = ns();
      String reverse = new StringBuilder(word.toLowerCase()).reverse().toString();
      trie.put(reverse, word);
    }
    List<String> result = new ArrayList<>();
    dfs(trie, str, result, 0);
    printResult(result);
  }

  private void printResult(List<String> result) {
    StringBuilder sb = new StringBuilder();
    for (String word : result) {
      if (sb.length() > 0) {
        sb.append(' ');
      }
      sb.append(word);
    }
    out.println(sb.toString());
  }

  private boolean dfs(Trie trie, String str, List<String> result, final int start) {
    if (start == str.length()) {
      return true;
    }
    for (int end = start + 1; end <= str.length(); end++) {
      String word = trie.get(str, start, end);
      if (word != null) {
        result.add(word);
        if (dfs(trie, str, result, end)) {
          return true;
        }
        result.remove(result.size() - 1);
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    new P633C().run();
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
