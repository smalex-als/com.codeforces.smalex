package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P659B {
  InputStream is;
  PrintWriter out;
  // String INPUT = "5 2 Ivanov 1 763 Andreev 2 800 Petrov 1 595 Sidorov 1 790 Semenov 2 503";
  String INPUT = "5 2 Ivanov 1 800 Andreev 2 763 Petrov 1 800 Sidorov 1 800 Semenov 2 503";
  
  private static class Person implements Comparable<Person> {
    String name;
    int region;
    int score;

    /**
     * @param name
     * @param region
     * @param score
     */
    public Person(String name, int region, int score) {
      this.name = name;
      this.region = region;
      this.score = score;
    }

    @Override
    public int compareTo(Person o) {
      return Integer.compare(o.score, score);
    }
  }

  void solve() {
    int n = ni();
    int m = ni();

    Map<Integer, List<Person>> map = new HashMap<>();
    for (int i = 0; i < m; i++) {
      map.put(i, new ArrayList<Person>());
    }
    for (int i = 0; i < n; i++) {
      String name = ns();
      int region = ni() - 1;
      int score = ni();
      map.get(region).add(new Person(name, region, score));
    }
    for (int i = 0; i < m; i++) {
      List<Person> people = map.get(i);
      Collections.sort(people);
      Set<Integer> prevValues = new HashSet<Integer>();
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < Math.min(people.size(), 3); j++) {
        Person p = people.get(j);
        if (j > 1 && prevValues.contains(p.score)) {
          sb.delete(0, sb.length());
          sb.append("?");
        } else if (j <= 1) {
          prevValues.add(p.score);
          if (sb.length() > 0) {
            sb.append(" ");
          }
          sb.append(p.name);
        }
        
      }
      System.out.println(sb.toString());
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P659B().run();
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
