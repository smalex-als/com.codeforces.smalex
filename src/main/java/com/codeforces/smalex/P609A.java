package com.codeforces.smalex;

import java.util.Arrays;
import java.util.Scanner;

public class P609A {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    Arrays.sort(a);
    int res = 0;
    for (int i = n - 1; i >= 0 && m > 0; i--) {
      m -= a[i];
      res++;
    }
    System.out.println(res);
  }
}
