package com.codeforces.smalex;

import java.util.Arrays;
import java.util.Scanner;

public class P604B {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int k = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    Arrays.sort(a);
    int cnt = k * 2 - n;
    int lo = 0;
    int hi = n - cnt - 1;
    int res = 0;
    if (n <= cnt) {
      System.out.println(a[a.length - 1]);
      return;
    }
    while (lo <= hi) {
      if (lo == hi) {
        res = Math.max(res, a[lo]);
      } else {
        res = Math.max(res, a[lo] + a[hi]);
      }
      lo++;
      hi--;
    }
    if (n - cnt >= 0) {
      for (int i = n - cnt; i < n; i++) {
        res = Math.max(res, a[i]);
      }
    }
    System.out.println(res);
  }
}
