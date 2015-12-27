package com.codeforces.smalex;

import java.util.Scanner;

public class P612B {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[scanner.nextInt() - 1] = i;
    }

    long res = 0L;
    for (int i = 1; i < n; i++) {
      int step = 0;
      step = Math.abs(a[i] - a[i-1]);
      res += step;
    }
    System.out.println(res);
  }
}
