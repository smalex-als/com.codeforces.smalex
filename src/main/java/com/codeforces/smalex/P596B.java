package com.codeforces.smalex;

import java.util.Scanner;

public class P596B {
  public static void main(String[] args) {
    new P596B().run();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    System.out.print(solve(a));
  }

  public long solve(int[] a) {
    int cur = 0;
    long res = 0;
    for (int i = 0; i < a.length; i++) {
      if (cur != a[i]) {
        res += Math.abs(cur - a[i]);
        cur = a[i];
      }
    }
    return res;
  }
}
