package com.codeforces.smalex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class P644B {
  InputStream is;
  PrintWriter out;
  String INPUT = "5 1 2 9 4 8 10 9 15 2 19 1";
  
  enum Type { START, STOP };

  private static class Event implements Comparable<Event> {
    private int id;
    private long time;
    private long d;
    private Type type;

    /**
     * @param time
     * @param d
     * @param type
     */
    public Event(int id, long time, long d, Type type) {
      this.id = id;
      this.time = time;
      this.d = d;
      this.type = type;
    }

    @Override
    public int compareTo(Event o) {
      int res = Long.compare(time, o.time);
      if (res != 0)
        return res;
      return Integer.compare(id, o.id);
    }

    @Override
    public String toString() {
      return "Event{time=" + time + ",type=" + type + "}";
    }
  }

  void solve() {
    int n = ni();
    int b = ni();

    PriorityQueue<Event> incomingQueue = new PriorityQueue<>();
    LinkedList<Event> waitingQueue = new LinkedList<Event>();
    for (int i = 0; i < n; i++) {
      incomingQueue.add(new Event(i, ni(), ni(), Type.START));
    }
    long[] result = new long[n];
    Arrays.fill(result, -1L);
    boolean busy = false;
    while (!incomingQueue.isEmpty()) {
      Event event = incomingQueue.poll();
      if (event.type.equals(Type.START)) {
        if (!busy) {
          event.type = Type.STOP;
          event.time += event.d;
          incomingQueue.offer(event);
          busy = true;
        } else if (waitingQueue.size() < b) {
          waitingQueue.add(event);
        }
      } else {
        result[event.id] = event.time;
        busy = false;
        if (!waitingQueue.isEmpty()) {
          Event newEvent = waitingQueue.removeFirst();
          newEvent.time = event.time;
          incomingQueue.add(newEvent);
        }
      }
    }
    for (long res : result) {
      out.print(res + " ");
    }
  }
  
  public static void main(String[] args) throws Exception {
    new P644B().run();
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
