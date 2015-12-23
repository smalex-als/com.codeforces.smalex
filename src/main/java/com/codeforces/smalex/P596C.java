package com.codeforces.smalex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class P596C {
  public static void main(String[] args) {
    new P596C().run();
  }

  public  static class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Point b){
      int cmp = Integer.compare(b.y, y);
      if (cmp == 0) {
        cmp = Integer.compare(b.x, x);
      }
      return cmp;
    }

    @Override
    public String toString() {
      return "x = " + x + " y = " + y;
    }
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      points[i] = new Point(scanner.nextInt(), scanner.nextInt());
    }
    int[] w = new int[n];
    for (int i = 0; i < n; i++) {
      w[i] = scanner.nextInt();
    }
    System.out.print(solve(points, w));
  }

  public String solve(Point[] points, int[] wa) {
    Map<Integer, List<Point>> map = new HashMap<Integer, List<Point>>();
    Arrays.sort(points);
    for (int i = 0; i < points.length; i++) {
      int key = points[i].y - points[i].x;
      List<Point> sub = map.get(key);
      if (sub == null) {
        sub = new ArrayList<Point>();
        map.put(key, sub);
      }
      sub.add(points[i]);
    }
    List<Point> res = new ArrayList<Point>();
    for (int i = 0; i < wa.length; i++) {
      int w = wa[i];
      List<Point> sub = map.get(w);
      if (sub == null || sub.isEmpty()) {
        return "NO";
      }
      res.add(sub.remove(sub.size() - 1));
    }
    StringBuilder sb = new StringBuilder();
    sb.append("YES\n");
    for (Point p : res) {
      sb.append(p.x).append(" ").append(p.y).append("\n");
    }
    return sb.toString(); 
  }
}
