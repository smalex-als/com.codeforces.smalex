package com.codeforces.smalex;

import java.util.Arrays;
import java.util.Scanner;

public class P608A {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int s = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      int f = scanner.nextInt();
      int t = scanner.nextInt();
      a[i] = f * 10000 + t;
    }
    Arrays.sort(a);
    int curt = 0;
    for (int i = n - 1; i >= 0; i--) {
      int f = a[i] / 10000;
      int t = a[i] % 10000;
      if (f != s) {
        curt += (s - f);
        s = f;
      }
      if (t > curt) {
        curt = t;
      }
    }
    if (s > 0) {
      curt += s;
    }
    System.out.println(curt);
  }
}
