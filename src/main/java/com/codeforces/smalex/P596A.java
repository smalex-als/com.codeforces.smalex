package com.codeforces.smalex;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P596A {
  public static void main(String[] args) {
    new P596A().run();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[][] a = new int[n][2];
    for (int i = 0; i < n; i++) {
      a[i][0] = scanner.nextInt();
      a[i][1] = scanner.nextInt();
    }
    int res = solve(a);
    System.out.print(res);
  }

  public int solve(int[][] a) {
    Set<Integer> w = new HashSet<Integer>();
    Set<Integer> h = new HashSet<Integer>();
    for (int j = 0; j < a.length; j++) {
      for (int i = j + 1; i < a.length; i++) {
        w.add(Math.abs(a[j][0] - a[i][0]));
        h.add(Math.abs(a[j][1] - a[i][1]));
      }
    }
    w.remove(0);
    h.remove(0);

    return w.isEmpty() || h.isEmpty() ? -1 : w.iterator().next() * h.iterator().next();
  }
}
