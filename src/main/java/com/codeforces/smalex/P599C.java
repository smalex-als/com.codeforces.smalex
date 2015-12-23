package com.codeforces.smalex;

import java.util.Arrays;
import java.util.Scanner;

public class P599C {
  public static void main(String[] args) {
    new P599C().run();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    int[] prefmax = new int[n];
    int[] suffmin = new int[n];
    prefmax[0] = a[0];
    suffmin[n - 1] = a[n - 1];
    for (int i = 0; i < n - 1 ;i ++) {
      prefmax[i + 1] = Math.max(a[i], prefmax[i]);
      suffmin[n - 2 - i] = Math.min(a[n - 2 - i], suffmin[n - 1 - i]);
    }
    System.out.println(Arrays.toString(prefmax));
    System.out.println(Arrays.toString(suffmin));
    int res = 1;
    for (int i = 0; i < n - 1; i++) {
      if (prefmax[i] <= suffmin[i + 1]) {
        res++;
      }
    }
    System.out.println(res);
  }
}
